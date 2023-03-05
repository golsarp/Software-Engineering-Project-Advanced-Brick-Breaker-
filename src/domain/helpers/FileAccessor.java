package domain.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.controller.LoadSaveHandler;
import domain.objects.obstacle.ObstacleFactory;
import domain.objects.obstacle.movement.Horizontal;
import domain.objects.obstacle.movement.Stationary;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.particles.ExplosiveParticle;
import domain.objects.particles.ExplosiveParticleList;
import domain.objects.particles.GiftBox;
import domain.objects.particles.GiftBoxList;
import domain.validity.InputValidity;

public class FileAccessor {
	// OVERVIEW: FileAccessor is the class responsible for accessing and writing to files
	//			It is designed by Pure Fabrication and Indirection

	private ArrayList<Obstacle> listOfSavedGameObstacles;
	private InputValidity validity;
	private String [] top_five_users;
	private int [] top_five_scores;

	public FileAccessor() {
		validity = new InputValidity();
	}

	public String[] stringList(File[] flist) {

		String[] l = new String[flist.length];
		int count = 0;

		for (File f : flist) {
			l[count] = f.getName();
			count++;
		}
		return l;
	}

	public File[] gameList() {

		String pathSymbol = getPathSymbol();

		File folder = new File(System.getProperty("user.dir") + pathSymbol + "SaveFolder" + pathSymbol
				+ PlayerMaster.getInstance().getPlayer().getUsername());
		File[] SaveList = folder.listFiles();

		File[] Saves = new File[SaveList.length - 1];

		int c = 0;
		for (File f : SaveList) {

			if (f.getName().equals("Empty.txt")) {
				continue;
			}

			Saves[c] = f;

			c++;
		}

		return Saves;

	}

	public void loadGame(String path) {

		listOfSavedGameObstacles = new ArrayList<Obstacle>();

		try {
			boolean obstacle_check = true;
			boolean ability_check = false;
			boolean explosive_check = false;
			boolean giftbox_check = false;

			File savedGame = new File(path);
			Scanner loadScanner = new Scanner(savedGame);

			while (loadScanner.hasNextLine()) {

				String scannedLine = loadScanner.nextLine();

				// load the obstacles
				while (!scannedLine.equals("OBJECTS END") && obstacle_check) {

					int x = Integer.parseInt(scannedLine.split(" ")[0]);
					int y = Integer.parseInt(scannedLine.split(" ")[1]);
					String type = scannedLine.split(" ")[3];
					String moving = scannedLine.split(" ")[4];
					int live = Integer.parseInt(scannedLine.split(" ")[5]);
					Obstacle obstacle = new Obstacle();
					switch (type) {
					case "domain.objects.obstacle.types.SimpleObstacle":
						obstacle = ObstacleFactory.getInstance().getSimpleObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {
							obstacle.setIs_moving(true);
							obstacle.setMoving(new Horizontal());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new Stationary());

						}

						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.objects.obstacle.types.FirmObstacle":
						obstacle = ObstacleFactory.getInstance().getFirmObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new Horizontal());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new Stationary());

						}
						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.objects.obstacle.types.ExplosiveObstacle":

						obstacle = ObstacleFactory.getInstance().getExplosiveObstacle(listOfSavedGameObstacles, x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new Horizontal());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new Stationary());

						}

						listOfSavedGameObstacles.add(obstacle);
						break;
					case "domain.objects.obstacle.types.GiftObstacle":

						obstacle = ObstacleFactory.getInstance().getGiftObstacle(x, y);
						obstacle.setLife(live);

						if (moving.equalsIgnoreCase("true")) {

							obstacle.setIs_moving(true);
							obstacle.setMoving(new Horizontal());

						} else {

							obstacle.setIs_moving(false);
							obstacle.setMoving(new Stationary());

						}
						listOfSavedGameObstacles.add(obstacle);
						break;
					}

					scannedLine = loadScanner.nextLine();
					if (scannedLine.equals("OBJECTS END")) {
						scannedLine = loadScanner.nextLine();
						obstacle_check = false;
						break;
					}

				}
				// load the player's score
				if (scannedLine.equals("SCORE START")) {
					PlayerMaster.getInstance().setScore(Integer.parseInt(loadScanner.nextLine()));
				}
				// load the player's lives
				if (scannedLine.equals("LIVES START")) {
					PlayerMaster.getInstance().setLives(Integer.parseInt(loadScanner.nextLine()));
				}
				// load the player's abilities
				if (scannedLine.equals("ABILITIES END")) {

					ability_check = false;
				}

				if (scannedLine.equals("ABILITIES START")) {
					scannedLine = loadScanner.nextLine();
					ability_check = true;
					if (scannedLine.equals("ABILITIES END")) {

						ability_check = false;

					}

				}

				if (ability_check == true) {

					PlayerMaster.getInstance().loadAbility(scannedLine);

				}
				// load the gift boxes that were falling
				if (scannedLine.equals("GIFTBOXES END")) {

					giftbox_check = false;
				}

				if (scannedLine.equals("GIFTBOXES START")) {
					scannedLine = loadScanner.nextLine();
					giftbox_check = true;
					if (scannedLine.equals("GIFTBOXES END")) {

						giftbox_check = false;

					}

				}

				if (giftbox_check == true) {
					boolean active = Boolean.parseBoolean(scannedLine.split(" ")[0]);
					int currentAbility = Integer.parseInt(scannedLine.split(" ")[1]);
					int box_X = Integer.parseInt(scannedLine.split(" ")[2]);
					int box_Y = Integer.parseInt(scannedLine.split(" ")[3]);

					GiftBox g = new GiftBox();

					g.setActive(active);
					g.setCurrentAbility(currentAbility);
					g.setX(box_X);
					g.setY(box_Y);
					g.setUpwardPathX(box_X);
					g.setUpwardPathY(box_Y);
					GiftBoxList.getInstance().addGiftBox2(g);

				}
				// load the explosive particles that were falling
				if (scannedLine.equals("EXPLOSIVE PARTICLES END")) {

					explosive_check = false;
				}

				if (scannedLine.equals("EXPLOSIVE PARTICLES START")) {
					scannedLine = loadScanner.nextLine();
					explosive_check = true;
					if (scannedLine.equals("EXPLOSIVE PARTICLES END")) {

						explosive_check = false;

					}

				}

				if (explosive_check == true) {

					double e_X = Double.parseDouble((scannedLine.split(" ")[0]));
					double e_Y = Double.parseDouble(scannedLine.split(" ")[1]);

					System.out.println("------------------");
					ExplosiveParticle e = new ExplosiveParticle();

					e.setX(e_X);
					e.setY(e_Y);
					e.setDownwardPathX(e_X);
					e.setDownwardPathY(e_Y);
					ExplosiveParticleList.getInstance().addParticles(e);

				}
				// load the game time
				if (scannedLine.equals("GAMETIME START")) {
					scannedLine = loadScanner.nextLine();
					GameController.getInstance().getGameTime().setCurrentTime(Integer.parseInt(scannedLine));

				}

			}

			loadScanner.close();
		} catch (

		FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		GameController.getInstance().setListOfObstacles(listOfSavedGameObstacles);

	}

	public void saveGame() {

		String username = PlayerMaster.getInstance().getPlayer().getUsername();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("-dd-MM-yyyy-HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();

		String date = dtf.format(now);
		String savename = username + date + ".txt";
		String pathSymbol = getPathSymbol();

		String s = System.getProperty("user.dir") + pathSymbol + "SaveFolder" + pathSymbol + username + pathSymbol
				+ savename;
		File file = new File(s);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			PrintWriter pwriter = new PrintWriter(writer);
			System.out.println(file.getName());

			// save the obstacles
			for (Obstacle o : GameController.getInstance().getListOfObstacles()) {
				pwriter.print(o.getX_position() + " ");
				pwriter.print(o.getY_position() + " ");
				pwriter.print(o.getClass() + " ");
				pwriter.print(o.getIs_moving() + " ");
				pwriter.print(o.getLife());
				pwriter.println();

			}

			pwriter.println("OBJECTS END");

			// save the player's score
			pwriter.println("SCORE START");
			pwriter.println(PlayerMaster.getInstance().getPlayer().getScore());
			pwriter.println("SCORE END");

			// save the player's lives
			pwriter.println("LIVES START");
			pwriter.println(PlayerMaster.getInstance().getPlayer().getLives());
			pwriter.println("LIVES END");

			// save the player's abilities (gained, but not yet used)
			pwriter.println("ABILITIES START");

			for (String ability : PlayerMaster.getInstance().getCurrentAbilities()) {
				pwriter.print(ability);
				pwriter.println();

			}
			pwriter.println("ABILITIES END");

			// save the gift boxes that are falling
			pwriter.println("GIFTBOXES START");
			for (GiftBox g : GiftBoxList.getInstance().getList()) {
				pwriter.print(g.isActive() + " ");
				pwriter.print(g.getCurrentAbility() + " ");
				pwriter.print(g.getX() + " ");
				pwriter.print(g.getY());

				pwriter.println();
			}
			pwriter.println("GIFTBOXES END");

			// save the explosive particles that are falling
			pwriter.println("EXPLOSIVE PARTICLES START");

			for (ExplosiveParticle e : ExplosiveParticleList.getInstance().getList()) {

				pwriter.print(e.getX() + " ");
				pwriter.print(e.getY());

				pwriter.println();

			}
			pwriter.println("EXPLOSIVE PARTICLES END");

			// save the game time
			pwriter.println("GAMETIME START");
			pwriter.println(GameController.getInstance().getGameTime().getCurrentTime());
			pwriter.println("GAMETIME END");

			pwriter.close();
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String getPathSymbol() {
		// EFFECTS: Returns the path symbol that the player's local device is using
		// to be used in configuring directories for saved games
		String symbol;
		if (System.getProperty("user.dir").startsWith("/")) {
			symbol = "/";
		} else {
			symbol = "\\";

		}
		return symbol;

	}

	public String isRegistered(String username, String password) {
		if (!validity.isValidCredential(username, password)) {

			return "invalid";

		} else {
			PlayerMaster.getInstance().initializePlayer(username, password);
			PlayerMaster.getInstance().setNewPlayer(true);

			String symbol = LoadSaveHandler.getInstance().getPathSymbol();

			String path = System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + username;

			File f1 = new File(path);

			// if username does not exist
			if (!f1.exists()) {

				f1.mkdir();

				try {
					File empty = new File(f1 + symbol + "Empty.txt");
					FileWriter w2 = new FileWriter(empty);
					PrintWriter p2 = new PrintWriter(w2);
					p2.print("This is an automatically generated empty file to avoid vanishing folders by git");
					p2.close();
					w2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				File f2 = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "Userlist.txt");

				FileWriter writer;
				try {
					writer = new FileWriter(f2, true);
					PrintWriter pwriter = new PrintWriter(writer);

					pwriter.println(username + " " + password);

					writer.close();
					pwriter.close();
					return "RegisterSuccessfull";

				} catch (IOException e1) {
					return "FileNotFound";
					// TODO Auto-generated catch block

				}

			} else {

				return "User Already Exists";
			}
		}
	}

	public String isAuthenticated(String username, String password) {
		if (!validity.isValidCredential(username, password)) {

			return "invalid";

		} else {
			String symbol = getPathSymbol();
			File userList = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "Userlist.txt");

			try {
				Scanner loginScanner = new Scanner(userList);
				while (loginScanner.hasNextLine()) {
					String line = loginScanner.nextLine();
					String name = line.split(" ")[0];
					String pass = line.split(" ")[1];

					if (name.equals(username)) {

						if (pass.equals(password)) {

							PlayerMaster.getInstance().initializePlayer(username, password);

							String path = System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + username;
							File folder = new File(path);
							File[] SaveList = folder.listFiles();

							if (SaveList.length == 1) {

								PlayerMaster.getInstance().setNewPlayer(true);
							} else {
								PlayerMaster.getInstance().setNewPlayer(false);

							}

							return "Login Successfull";

						} else {
							return "Invalid Password";

						}

					}
				}
				loginScanner.close();
				return "User Not Found";

			} catch (FileNotFoundException e1) {
				return "FILE NOT FOUND";
				// TODO Auto-generated catch block
			}
		}
	}

	public ArrayList<Obstacle> getListOfSavedGameObstacles() {
		return listOfSavedGameObstacles;
	}

	public void setListOfSavedGameObstacles(ArrayList<Obstacle> listOfSavedGameObstacles) {
		this.listOfSavedGameObstacles = listOfSavedGameObstacles;
	}

	public void saveScoreBoard() {
		String symbol = getPathSymbol();
		File scorelist = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "topscores.txt");

		
		FileWriter writer;
		try {
			writer = new FileWriter(scorelist);
			PrintWriter pwriter = new PrintWriter(writer);
			int count=0;
			while(count<5) {
			pwriter.print(this.top_five_users[count] + " " +this.top_five_scores[count]);
			
			pwriter.println();

				
				
				count++;
			}
			


			writer.close();
			pwriter.close();

		} catch (IOException e1) {
			

		}

		
		
		
		
		
		
		

	}

	public void loadScoreBoard() {

		String symbol = getPathSymbol();
		File scorelist = new File(System.getProperty("user.dir") + symbol + "SaveFolder" + symbol + "topscores.txt");

		int[] scores = new int[5];
		String[] users = new String[5];
		int index = 0;
		try {
			Scanner scoreScanner = new Scanner(scorelist);
			while (scoreScanner.hasNextLine()) {
				String line = scoreScanner.nextLine();
				int score = Integer.parseInt(line.split(" ")[1]);
				String username = line.split(" ")[0];

				users[index] = username;
				scores[index] = score;
				index++;

			}
			this.setTop_five_scores(scores);
			this.setTop_five_users(users);

			scoreScanner.close();
			
			
			
			


		}
		
		
		
		
		
		
		

		catch (FileNotFoundException e1) {
		}

	}

	public String [] getTop_five_users() {
		return top_five_users;
	}

	public void setTop_five_users(String [] top_five_users) {
		this.top_five_users = top_five_users;
	}

	public int [] getTop_five_scores() {
		return top_five_scores;
	}

	public void setTop_five_scores(int [] top_five_scores) {
		this.top_five_scores = top_five_scores;
	}

}
