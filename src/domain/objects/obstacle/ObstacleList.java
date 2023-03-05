package domain.objects.obstacle;

import java.util.ArrayList;

import constants.GameConstants;
import domain.objects.obstacle.types.Obstacle;

public class ObstacleList {
	//OVERVIEW: 
    //ObstacleList is an  unbounded list of obstacles and is mutable. 
	//A typical listOfObstacles is [SimpleObstacle, FirmObstacle ,SimpleObstacle ,ExplosiveObstacle , ExplosiveObstacle]
	
	private int max_num = GameConstants.MAX_NUMBER_OF_OBSTACLES;
	private int simple;
	private int firm;
	private int explosive;
	private int gift;
	private ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>(); // the rep
	
	// The Abstraction Function is
	// AF(x) = { x.listOfObstacles[i] | 0 <= i < x.max_num }
	
	// The rep invariant is 
	// x.listOfObstacles not null && 
	// all elements of x.listOfObstacles are in frame &&
	// all elements of x.listOfObstacless' lives are bigger than 1 && 
	// x.listOfObstacles.size < max_num
	

	//Constructor
	public ObstacleList(int simple, int firm, int explosive, int gift) {
		this.simple = simple;
		this.firm = firm;
		this.explosive = explosive;
		this.gift = gift;
		
	}
	
	//Methods
	public void addObstacle(Obstacle o) throws NullPointerException {
		//MODIFIES: this
		//EFFECTS:  adds o to this ,throws NullPointerException if the obstacle is null
		if(o == null) {
			throw new NullPointerException();
		}
		this.listOfObstacles.add(o);
		
	}
	
	public void removeObstacle(Obstacle o) throws NullPointerException {
		//MODIFIES: this
		//EFFECTS:  removes o from this ,throws NullPointerException if the obstacle is null
		
		if(o == null) {
			throw new NullPointerException();
		}
		this.listOfObstacles.remove(o);
	}
	
	public boolean repOk() {
		//MODIFIES: 
		//EFFECTS: Checks the rep according to the requirements below. If the requirements are not satisfied returns false, else true. 
		
		if (listOfObstacles == null) {
			return false;
		}
		for(Obstacle o: listOfObstacles ) {
			if((o.getLife() < 1) || !inFrame(o) ) {
				return false;
			}
			
		}
		
		if(listOfObstacles.size() < max_num) {
			return true;
		}else return false;
		
		
	}
	
	public boolean isInList(Obstacle ob) {
		//MODIFIES: this
		//EFFECTS: Returns true if the given object is in the list, else returns false.
		return listOfObstacles.contains(ob);
	}
	
	public boolean inFrame(Obstacle o) {
		//MODIFIES: 
		//EFFECTS: Returns true if the given object is in the frame, else returns false.
		
		if(o.getX_position() < 0 || o.getX_position() > GameConstants.RUNNING_MODE_FRAME_WIDTH || o.getY_position() < 0|| o.getY_position()> GameConstants.RUNNING_MODE_FRAME_HEIGHT) {
			return false;
		}else return true;
		
	}
	
	
	public int size() {
		//MODIFIES: 
		//EFFECTS: returns the cardinality of this.
		return listOfObstacles.size();
	}
	
	//GETTERS AND SETTERS 
	
	public void setListOfObstacles(ArrayList<Obstacle> listOfObstacles) {
		this.listOfObstacles = listOfObstacles;
	}
	
	
	public ArrayList<Obstacle> getListOfObstacles() {
		return listOfObstacles;
	}


	
	public  int getSimple() {
		return this.simple;
	}



	public  void setSimple(int simple) {
		this.simple = simple;
	}



	public  int getFirm() {
		return this.firm;
	}



	public void setFirm(int firm) {
		this.firm = firm;
	}



	public int getExplosive() {
		return this.explosive;
	}



	public void setExplosive(int explosive) {
		this.explosive = explosive;
	}



	public int getGift() {
		return this.gift;
	}



	public void setGift(int gift) {
		this.gift = gift;
	}


	
	
}