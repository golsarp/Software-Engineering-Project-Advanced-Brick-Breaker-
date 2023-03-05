package ui.runningmode;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import constants.GameConstants;
import domain.*;
import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.controller.LoadSaveHandler;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.ObstacleMover;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.particles.ExplosiveParticle;
import domain.objects.particles.ExplosiveParticleList;
import domain.objects.particles.GiftBox;
import domain.objects.particles.GiftBoxList;
import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.CannonBullet;
import domain.objects.phantasm.NoblePhantasm;
import domain.objects.sphere.EnchantedSphere;
import ui.helpers.ImageLoader;
import ui.helpers.Intersect;
import ui.helpers.ShapeRotator;
import ui.inputHandler.keyHandler;
import ui.inputHandler.mouseHandler;

public class RunningModePanel extends JPanel implements ActionListener {
	//OVERVIEW: RunningModePanel is the main panel for Running Mode. It handles events during game time. 
	//			It includes another panel, buttonPanel for the menu

	public NoblePhantasm noblePhantasm;
	public EnchantedSphere ball;
	public Cannon cannon;
	public boolean firstt = true;
	protected boolean padRight = false;
	protected boolean padLeft = false;
	protected boolean rotateRight = false;
	protected boolean rotateLeft = false;
	protected boolean rotateFromLeftToCenter = false;
	protected boolean rotateFromRightToCenter = false;
	public static boolean gameStarted = false;
	public static boolean new_game_start = true;
	public static boolean first = true;
	public static boolean intersected = false;
	public boolean ball_inter_obj = false;
	private boolean ended = false;
	private int delay = GameConstants.DELAY;
	private double addXleft = 0;
	private double addYleft = 0;
	private double addXright = 0;
	private double addYright = 0;
	private Shape np_shape;
	private Shape canon_shape_left;
	private Shape canon_shape_right;
	private static Shape ball_shape;
	private static final Integer CHANCE_GIVING = 0;
	private static final Integer MAGICAL_HEX = 1;
	private static final Integer PHANTASM_EXPENSION = 2;
	private static final Integer UNSTOPPABLE_SPHERE = 3;
	private Timer timer;
	private Intersect intersect;
	private ShapeRotator rotator;
	private Rectangle object;
	private Rectangle2D.Double ballRect;
	private ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();
	private ArrayList<Obstacle> listOfObstacle = obstacleConfiguration.getListOfObstacles();
	private ObstacleMover runningModeHandler = new ObstacleMover(listOfObstacle);
	private ButtonPanel buttonPanel;
	private GameEndPanel gameEndPanel;
	private JFrame parent;
	protected keyHandler key = new keyHandler(this);
	protected mouseHandler mouse = new mouseHandler(this);
	private ImageLoader  loader = new ImageLoader();
	private String pathSymbol = LoadSaveHandler.getInstance().getPathSymbol();
	private BufferedImage  image = loader.LoadImage(
			System.getProperty("user.dir") + pathSymbol + "images" + pathSymbol + "background.jpeg");

	public RunningModePanel() {
		initiliaze();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawCannonBullet(g);				// draw cannon bullets
		drawExplosiveParticle(g);			// draw all the explosive particles
		drawGiftBox(g);						// draw all the gift boxes
		drawObstacle(g, listOfObstacle);	// draw all the obstacles
		shapeDrawer(g, np_shape, "Rect");	// draw the noble phantasm
		shapeDrawer(g, ball_shape, "Oval");	// draw the enchanted sphere

	}

	public void actionPerformed(ActionEvent e) {
		if (!new_game_start) {
			buttonPanel.pauseButton.setEnabled(true);
			buttonPanel.helpButton.setEnabled(false);
			buttonPanel.saveButton.setEnabled(false);

		}
		buttonPanel.activateAbilityButtons();
		setFocusable(true);
		if (!ended) {
			configureKeys();
			checkRotation();
			checkCollision();
			moveExp();
			setLives();
			ended = checkRestart();
			ended = checkEnding();
		}

		repaint();
	}

	public void initiliaze() {
		this.addMouseListener(mouse);
		this.setBackground(Color.BLACK);
		
		// initialize game objects
		noblePhantasm = GameController.getInstance().getPhantasm();
		ball = GameController.getInstance().getBall();
       
		np_shape = new Rectangle2D.Double(noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
				noblePhantasm.getLength(), noblePhantasm.getWidth());
		canon_shape_left = new Rectangle2D.Double(noblePhantasm.getX_coor() + addXleft,
				addYleft + noblePhantasm.getY_coor() - 10, 10, 20);
		canon_shape_right = new Rectangle2D.Double(
				addXright + noblePhantasm.getX_coor() + noblePhantasm.getLength() - 10,
				addYright + noblePhantasm.getY_coor() - 10, 10, 20);

		ball_shape = new Rectangle2D.Double(ball.getX(), ball.getY(), GameConstants.ENCHANTED_SPHERE_RADIUS,
				GameConstants.ENCHANTED_SPHERE_RADIUS);
		rotator = new ShapeRotator();
		intersect = new Intersect();

		// initialize timer
		timer = new Timer(delay, (ActionListener) this);

		// initialize button panel
		buttonPanel = new ButtonPanel(this);
		buttonPanel
				.setPreferredSize(new Dimension(GameConstants.BUTTON_PANEL_WIDTH, GameConstants.BUTTON_PANEL_HEIGHT));
		this.add(buttonPanel, BorderLayout.NORTH);

		addKeyListener(key);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void configureKeys() {
		// handle right arrow
		if (keyHandler.RIGHT) {
			padRight = true;
		} else if (!keyHandler.RIGHT) {
			padRight = false;
			GameController.getInstance().getPhantasm().setDirection(0);
		}

		// handle left arrow
		if (keyHandler.LEFT) {
			padLeft = true;
		} else if (!keyHandler.LEFT) {
			padLeft = false;
			GameController.getInstance().getPhantasm().setDirection(0);
		}

		// handle key A
		if (keyHandler.A && gameStarted) {
			rotateLeft = true;

		} else if (keyHandler.A_rel && gameStarted) {

			rotateLeft = false;
			rotateFromLeftToCenter = true;
			GameController.getInstance().getPhantasm().setRight_rot_count(0);
			GameController.getInstance().getPhantasm().setLeft_rot_count(0);
		}

		// handle key D
		if (keyHandler.D && gameStarted) {
			rotateRight = true;
		} else if (keyHandler.D_rel && gameStarted) {
			rotateRight = false;
			rotateFromRightToCenter = true;
			GameController.getInstance().getPhantasm().setRight_rot_count(0);
			GameController.getInstance().getPhantasm().setLeft_rot_count(0);
		}

		if (keyHandler.T) {
			if (!PlayerMaster.getInstance().getPlayer().isExpansionActive()) {
				PlayerMaster.getInstance().activateAbility(PHANTASM_EXPENSION);
			}
		}

		if (keyHandler.U) {
			if (!PlayerMaster.getInstance().getPlayer().isUnstoppableActive()) {
				PlayerMaster.getInstance().activateAbility(UNSTOPPABLE_SPHERE);
			}

		}

		if (keyHandler.X) {
			if (!PlayerMaster.getInstance().getPlayer().isHexActive()) {
				PlayerMaster.getInstance().activateAbility(MAGICAL_HEX);
			}

		}

	}

	public static void drawObstacle(Graphics g, ArrayList<Obstacle> listOfObstacle) {

		try {
			for (Obstacle ob : listOfObstacle) {

				int x_obst = ob.getX_position();
				int y_obst = ob.getY_position();
				int width_obst = ob.getWidth();
				int length_obst = ob.getLength();

				if (ob.getColor() == "blue") {
					g.setColor(Color.blue);
				} else if (ob.getColor() == "red") {
					g.setColor(Color.red);
				} else if (ob.getColor() == "yellow") {
					g.setColor(Color.yellow);
				} else if (ob.getColor() == "green") {
					g.setColor(Color.green);
				} else if (ob.getColor() == "purple") {
					Color purple = new Color(203, 91, 252);
					g.setColor(purple);
				}
				if (ob.isFrozen()) {
					Color ice = new Color(155, 214, 236);
					g.setColor(ice);

				}
				if (ob.isIs_rectangle()) {
					g.fillRect(x_obst, y_obst, length_obst, width_obst);
					if (ob.getHasText()) {
						g.setColor(Color.DARK_GRAY);
						g.drawString(String.valueOf(ob.getLife()), x_obst + 10, y_obst + 15);

					}
				} else {
					g.fillOval(x_obst, y_obst, width_obst, width_obst);
				}

			}
		} catch (ConcurrentModificationException e) {

		}

	}

	public void drawExplosiveParticle(Graphics g) {
		try {
			for (ExplosiveParticle exp : ExplosiveParticleList.getInstance().getList()) {

				if (!exp.isActive()) {
					g.setColor(Color.RED);
					double xPositionParticle = exp.getX();
					double yPositionParticle = exp.getY();
					int radiusParticle = exp.getRadius();
					g.fillOval((int) xPositionParticle, (int) yPositionParticle, radiusParticle, radiusParticle);
				}
			}
		} catch (ConcurrentModificationException e) {

		}

	}

	public void drawCannonBullet(Graphics g) {

		try {
			cannon = GameController.getInstance().getCannon();
			if (cannon != null) {
				if (cannon.getActive() == true) {

					for (CannonBullet bullet : cannon.getCanonBulletList().getCanonBulletList()) {
						g.setColor(Color.RED);
						double xPositionParticle = bullet.getX();
						double yPositionParticle = bullet.getY();
						int radiusParticle = bullet.getRadius();
						if (!bullet.isCollided()) {
							drawCanons(g, canon_shape_left);
							drawCanons(g, canon_shape_right);
						}
						g.fillOval((int) xPositionParticle, (int) yPositionParticle, radiusParticle, radiusParticle);
						cannon.setX_positionLeft(noblePhantasm.getX_coor());
						cannon.setY_positionLeft(noblePhantasm.getY_coor());
						cannon.setX_positionRight(noblePhantasm.getX_coor(), noblePhantasm.getLength());
						cannon.setY_positionRight(noblePhantasm.getY_coor());

					}
				}
			}
		} catch (ConcurrentModificationException e) {

		}
	}

	public void drawGiftBox(Graphics g) {

		try {
			for (GiftBox gift : GiftBoxList.getInstance().getList()) {
				if (!gift.isActive()) {
					g.setColor(Color.GREEN);
					int xPositionParticle = gift.getX();
					int yPositionParticle = gift.getY();
					int radiusParticle = gift.getRadius();
					g.fillOval(xPositionParticle, yPositionParticle, radiusParticle, radiusParticle);
				}
			}
		} catch (ConcurrentModificationException e) {

		}

	}

	public void drawCanons(Graphics g, Shape shape) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(shape);

	}

	public static void shapeDrawer(Graphics g, Shape shape, String s) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		if (s.equalsIgnoreCase("Rect")) {
			g2d.fill(shape);
		} else {
			g2d.fillOval((int) ball_shape.getBounds2D().getX(), (int) ball_shape.getBounds2D().getY(),
					(int) ball_shape.getBounds2D().getWidth(), (int) ball_shape.getBounds2D().getWidth());
		}

	}

	public void stopTimer() {

		this.timer.stop();

	}

	public void restartTimer() {

		this.timer.restart();
	}

	public void checkCollision() {

		ballRect = new Rectangle2D.Double(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
		// understand if ball and any object intersects
		try {
			for (Obstacle ob : listOfObstacle) {

				object = new Rectangle(ob.getX_position(), ob.getY_position(), ob.getLength(), ob.getWidth());
				ball_inter_obj = object.intersects(ballRect);
				if (ball_inter_obj) {
					GameController.getInstance().sphereObstacleCollision(ob, ball_inter_obj);
				}

			}
		} catch (ConcurrentModificationException e) {

		} catch (NullPointerException e) {

		}

	}

	public void canonSetter() {
		NoblePhantasm nb = noblePhantasm;
		double converted_in_degrees = -Math.PI / 180 * nb.getRot_measure();
		int mul=1;	
		if(nb.getLength()!=120) {
			mul=2;
		}
		if (nb.getRot_measure() == 0) {
			addXleft = 0;
			addYleft = 0;
			addXright = 0;
			addYright = 0;
		} else if (nb.getRot_measure() > 0) {
			addYleft = (mul*55-(nb.getLength() / 2 + (nb.getLength() / 2) * Math.sin(converted_in_degrees)));
			addXleft = (mul*55+(nb.getLength() / 2) - (nb.getLength() / 2) * Math.cos(converted_in_degrees));

			addYright = (mul*(-30)+(nb.getLength() / 2 + (nb.getLength() / 2) * Math.sin(converted_in_degrees)));
			addXright =  ( -(mul*34+(nb.getLength() / 2) - (nb.getLength() / 2) * Math.cos(converted_in_degrees)));
		} else if (nb.getRot_measure() < 0) {
			addYleft = (mul*(-26)+(nb.getLength() / 2 - (nb.getLength() / 2) * Math.sin(converted_in_degrees)));
			addXleft = (mul*(+50)-(+(nb.getLength() / 2) - (nb.getLength() / 2) * Math.cos(converted_in_degrees)));

			addYright = (mul*55-(nb.getLength() / 2 - (nb.getLength() / 2) * Math.sin(converted_in_degrees)));
			addXright = (mul*(-70)+((nb.getLength() / 2) - (nb.getLength() / 2) * Math.cos(converted_in_degrees)));

		}
	}

	public void checkRotation() {

		ball_shape = new Rectangle2D.Double(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
		intersected = intersect.collision(np_shape, ball_shape);

		// set the intersection using game controller
		GameController.getInstance().getBall().setIntersected(intersected);

		if (padRight) {
			noblePhantasm.moveRight();
			np_shape = new Rectangle2D.Double(noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
					noblePhantasm.getLength(), noblePhantasm.getWidth());

			canon_shape_left = new Rectangle2D.Double(noblePhantasm.getX_coor() + addXleft,
					addYleft + noblePhantasm.getY_coor() - 10, 10, 20);
			canon_shape_right = new Rectangle2D.Double(
					addXright + noblePhantasm.getX_coor() + noblePhantasm.getLength() - 10,
					addYright + noblePhantasm.getY_coor() - 10, 10, 20);

			if (cannon != null) {
				cannon.setRot_measure(0);
			}
			GameController.getInstance().getPhantasm().setRot_measure(0);
			rotateFromLeftToCenter = false;
			rotateFromRightToCenter = false;

		}

		if (padLeft) {
			noblePhantasm.moveLeft();
			np_shape = new Rectangle2D.Double(noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
					noblePhantasm.getLength(), noblePhantasm.getWidth());

			canon_shape_left = new Rectangle2D.Double(noblePhantasm.getX_coor() + addXleft,
					addYleft + noblePhantasm.getY_coor() - 10, 10, 20);
			canon_shape_right = new Rectangle2D.Double(
					addXright + noblePhantasm.getX_coor() + noblePhantasm.getLength() - 10,
					addYright + noblePhantasm.getY_coor() - 10, 10, 20);

			if (cannon != null) {
				cannon.setRot_measure(0);
			}
			GameController.getInstance().getPhantasm().setRot_measure(0);
			rotateFromLeftToCenter = false;
			rotateFromRightToCenter = false;

		}

		if (rotateRight) {
			// assign shape here
			if (noblePhantasm.getRight_rot_count() < 45) {

				np_shape = rotator.rotate(Math.PI / 180, noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
						noblePhantasm.getLength(), noblePhantasm.getWidth(), np_shape);

				canon_shape_left = rotator.rotate(Math.PI / 180, noblePhantasm.getX_coor() + (int) (addXleft),
						(int) (addYleft) + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_left);
				canon_shape_right = rotator.rotate(Math.PI / 180,
						noblePhantasm.getX_coor() + (int) addXright + noblePhantasm.getLength() - 10,
						(int) addYright + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_right);

				noblePhantasm.rotateRight();
				if (cannon != null) {
					cannon.rotateRight();
				}
			}

		}
		// revert rotation
		if (rotateFromLeftToCenter) {

			np_shape = rotator.rotate(Math.PI / 180, noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
					noblePhantasm.getLength(), noblePhantasm.getWidth(), np_shape);
			canon_shape_left = rotator.rotate(Math.PI / 180, noblePhantasm.getX_coor() + (int) (addXleft),
					(int) (addYleft) + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_left);
			canon_shape_right = rotator.rotate(Math.PI / 180,
					noblePhantasm.getX_coor() + (int) addXright + noblePhantasm.getLength() - 10,
					(int) addYright + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_right);

			if (cannon != null) {
				cannon.rotateRight();
			}
			noblePhantasm.rotateRight();
		}

		if (rotateLeft) {
			// assign shape here
			if (noblePhantasm.getLeft_rot_count() < 45) {

				np_shape = rotator.rotate(-Math.PI / 180, noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
						noblePhantasm.getLength(), noblePhantasm.getWidth(), np_shape);
				canon_shape_left = rotator.rotate(-Math.PI / 180, noblePhantasm.getX_coor() + (int) (addXleft),
						(int) (addYleft) + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_left);
				canon_shape_right = rotator.rotate(-Math.PI / 180,
						noblePhantasm.getX_coor() + (int) addXright + noblePhantasm.getLength() - 10,
						(int) addYright + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_right);
				if (cannon != null) {
					cannon.rotateLeft();
				}
				noblePhantasm.rotateLeft();
			}
		}
		// revert rotation
		if (rotateFromRightToCenter) {

			np_shape = rotator.rotate(-Math.PI / 180, noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
					noblePhantasm.getLength(), noblePhantasm.getWidth(), np_shape);
			noblePhantasm.rotateLeft();
			canon_shape_left = rotator.rotate(-Math.PI / 180, noblePhantasm.getX_coor() + (int) (addXleft),
					(int) (addYleft) + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_left);
			canon_shape_right = rotator.rotate(-Math.PI / 180,
					noblePhantasm.getX_coor() + (int) addXright + noblePhantasm.getLength() - 10,
					(int) addYright + noblePhantasm.getY_coor() - 10, 10, 20, canon_shape_right);

			if (cannon != null) {
				cannon.rotateLeft();
			}
		}

		if (noblePhantasm.getRot_measure() == 0) {
			keyHandler.setD_rel(false);
			keyHandler.setA_rel(false);

			rotateFromLeftToCenter = false;
			rotateFromRightToCenter = false;
			noblePhantasm.setRight_rot_count(0);
			noblePhantasm.setLeft_rot_count(0);
			if (cannon != null) {
				cannon.setRight_rot_count(0);
				cannon.setLeft_rot_count(0);
			}
		}
		canonSetter();
	}


	public void moveExp() {

		runningModeHandler.letExplosiveMove();

	}

	public boolean checkRestart() {
		firstt = false;

		if (ball.getDeltaX() == 0 && ball.getDeltaY() == 0 && !first && gameStarted) {
			noblePhantasm.setX_coor((GameConstants.RUNNING_MODE_FRAME_WIDTH - NoblePhantasm.length) / 2);
			noblePhantasm.setY_coor(GameConstants.RUNNING_MODE_FRAME_HEIGHT - 90);
			noblePhantasm.setDirection(0);

			GameController.getInstance().getPhantasm()
					.setX_coor((GameConstants.RUNNING_MODE_FRAME_WIDTH - NoblePhantasm.length) / 2);
			GameController.getInstance().getPhantasm().setY_coor(GameConstants.RUNNING_MODE_FRAME_HEIGHT - 90);

			buttonPanel.helpButton.setEnabled(true);
			buttonPanel.saveButton.setEnabled(true);
			buttonPanel.pauseButton.setEnabled(false);

			np_shape = new Rectangle2D.Double(noblePhantasm.getX_coor(), noblePhantasm.getY_coor(),
					noblePhantasm.getLength(), noblePhantasm.getWidth());

			GameController.getInstance().getGameTime().stopTime();
			GameController.getInstance().getGameTime().addCurrentTime();
			gameStarted = false;
			ball.setDeltaX(0);
			ball.setDeltaY(0);
			ball.setX((GameConstants.RUNNING_MODE_FRAME_WIDTH - GameConstants.ENCHANTED_SPHERE_RADIUS) / 2);
			ball.setY(GameConstants.RUNNING_MODE_FRAME_HEIGHT - (90 + GameConstants.ENCHANTED_SPHERE_RADIUS + 1));
			
			ball_shape = new Rectangle2D.Double(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());

			timer.stop();
			PlayerMaster.getInstance().decreaseLife();

			if (PlayerMaster.getInstance().getLives() == 0) {
				GameController.getInstance().cancelAllTasks();

				parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
				return true;
				
			} else {
				second = true;
			}
		}
		return false;

	}

	public boolean checkEnding() {

		if (PlayerMaster.getInstance().getLives() == 0) {
			LoadSaveHandler.getInstance().loadScoreBoard();
			GameController.getInstance().scoreListUpdated(PlayerMaster.getInstance().getScore());
			
			LoadSaveHandler.getInstance().getFileAccessor().saveScoreBoard();
			GameController.getInstance().cancelAllTasks();
			
			parent = (JFrame) this.getTopLevelAncestor();
			parent.dispose();
			gameEndPanel = new GameEndPanel("f");
			return true;
		}
		if (listOfObstacle.size() == 0) {
			LoadSaveHandler.getInstance().loadScoreBoard();

			GameController.getInstance().scoreListUpdated(PlayerMaster.getInstance().getScore());
			LoadSaveHandler.getInstance().getFileAccessor().saveScoreBoard();

			GameController.getInstance().cancelAllTasks();
			parent = (JFrame) this.getTopLevelAncestor();
			parent.dispose();
			gameEndPanel = new GameEndPanel("s");
			return true;
		}
		return false;

	}

	public void setLives() {
		buttonPanel.getLivesLabel().setText("Lives = " + PlayerMaster.getInstance().getLives());
	}

	public static boolean isGameStarted() {
		return gameStarted;
	}

	public static void setGameStarted(boolean gameStarted) {
		RunningModePanel.gameStarted = gameStarted;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	boolean second = false;

	public boolean isSecond() {
		return second;
	}

	public void setSecond(boolean second) {
		this.second = second;
	}

	public boolean isNew_game_start() {
		return new_game_start;
	}

	public void setNew_game_start(boolean new_game_start) {
		this.new_game_start = new_game_start;
	}

}
