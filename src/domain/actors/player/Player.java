package domain.actors.player;

import java.util.ArrayList;

import domain.objects.obstacle.types.Obstacle;

public class Player {
	
	private String username;						//player's account information
	private String password;
	
	private int score;								//player's saved game variables
	private int lives;
	private ArrayList<Obstacle> listOfSavedGameObstacles;
	
	private ArrayList<String> currentAbilities;		//player's abilities (gained, but not used)
	private boolean unstoppableActive;	 			
	private boolean expansionActive;
	private boolean hexActive;
	
	public Player() {
		currentAbilities = new ArrayList<String>();
		unstoppableActive = false;
		expansionActive = false;
		hexActive = false;
	}
	
	// getters and setters for private fields
	public ArrayList<String> getCurrentAbilities() {
		return currentAbilities;
	}
	public void setCurrentAbilities(ArrayList<String> currentAbilities) {
		this.currentAbilities = currentAbilities;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public ArrayList<Obstacle> getListOfSavedGameObstacles() {
		return listOfSavedGameObstacles;
	}
	public void setListOfSavedGameObstacles(ArrayList<Obstacle> listOfSavedGameObstacles) {
		this.listOfSavedGameObstacles = listOfSavedGameObstacles;
	}
	public boolean isUnstoppableActive() {
		return unstoppableActive;
	}
	public void setUnstoppableActive(boolean unstoppableActive) {
		this.unstoppableActive = unstoppableActive;
	}
	public boolean isExpansionActive() {
		return expansionActive;
	}
	public void setExpansionActive(boolean expansionActive) {
		this.expansionActive = expansionActive;
	}
	public boolean isHexActive() {
		return hexActive;
	}
	public void setHexActive(boolean hexActive) {
		this.hexActive = hexActive;
	}
}
