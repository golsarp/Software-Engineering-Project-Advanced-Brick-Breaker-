package domain.objects.obstacle;

import domain.objects.obstacle.types.Obstacle;

public class ObstaclePositionSetter {
	//OVERVIEW: ObstaclePositionSetter is the class responsible for changing the position of already placed obstacles
	//			Used when the game is in Building Mode and the player changes the places of obstacles
	
	private ObstacleConfiguration configuration;
	private int[][]positions;
	private Obstacle obstacle;
	private int x_position;
	private int y_position;
	
	public ObstaclePositionSetter(Obstacle obs , int x_pos , int y_pos, ObstacleConfiguration obstacleConfiguration){
		this.configuration = obstacleConfiguration;
		this.positions = configuration.getPositions();
		this.obstacle = obs;
		this.x_position = x_pos;
		this.y_position = y_pos;
	}
	
	public void setPosition(int prex, int prey, int x , int y) {
		//EFFECTS: sets the obstacle's position from prex and prey to x and y
		//MODIFIES: obs, positions
		
		this.obstacle.setX_position(x);
		this.obstacle.setY_position(y);
		
		//update positions array with the information that new position is occupied and the previous one is available
		int newPositionIndex = 0;
		int previousPositionIndex = 0;
		for (int index = 0; x < 250; x++) {
			if (positions[index][0] == x && positions[index][1] == y) {
				newPositionIndex = index;
				break;
			}
		}	
		//mark the previous position as available by getting an available positions entry
		for (int index = 0; x < 250; x++) {
			if (positions[index][0] == -1 && positions[index][1] == -1) {
				previousPositionIndex = index;
				break;
			}
		}
		positions[newPositionIndex][0] = -1;
		positions[newPositionIndex][1] = -1;
		positions[previousPositionIndex][0] = prex;
		positions[previousPositionIndex][1] = prey;
	}
	
	public void fitToExistingPosition(int prex, int prey, int x , int y) {

		//get a positions's width and height
		int deltax = configuration.getDeltax();
		int deltay = configuration.getDeltay();
		
		//adjust the x and y coordinates so that they correspond to a previously initialized obstacle position
		int adjusted_x = x - (x % deltax);
		int adjusted_y = y - (y % deltay);
		
		//set the obstacles position to adjusted ones
		setPosition(prex, prey, adjusted_x , adjusted_y);
	}
}
