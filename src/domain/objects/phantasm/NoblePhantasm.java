package domain.objects.phantasm;

import constants.GameConstants;
import domain.actors.player.ability.GameObject;

public class NoblePhantasm extends GameObject {
	public static int length = GameConstants.RUNNING_MODE_FRAME_WIDTH / 10;
	private int width = 20;
	private int x_coordinate;
	private int y_coordinate;
	private int direction;
	private int left_rot_count = 0;
	private int right_rot_count = 0;
	private int rot_measure = 0;
	private String color = "Blue";
	private int move_speed = (int)Math.round( length *2 *GameConstants.DELAY/1000.0);


	public NoblePhantasm(int x, int y, int direction) {
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.direction = direction;

	}
	
	public void moveLeft() {
		
		if (x_coordinate < 0) {
			
			x_coordinate = 0;


			left_rot_count = 0;
			right_rot_count = 0;

			direction = -1;
			
		} else {

			x_coordinate = x_coordinate - move_speed;

			left_rot_count = 0;
			right_rot_count = 0;

			direction = -1;

		}
	}

	public void moveRight() {
		if (x_coordinate > GameConstants.RUNNING_MODE_FRAME_WIDTH - length) {
			x_coordinate = GameConstants.RUNNING_MODE_FRAME_WIDTH - length;

			left_rot_count = 0;
			right_rot_count = 0;

			direction = 1;

		} else {
			x_coordinate = x_coordinate + move_speed;

			left_rot_count = 0;
			right_rot_count = 0;

			direction = 1;

		}

	}

	public void rotateRight() {

		right_rot_count++;
		rot_measure++;
	}

	public void rotateLeft() {

		left_rot_count++;
		rot_measure--;

	}

	@Override
	public void enhance() {
		// TODO Auto-generated method stub

	}

	// getters and setters
	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int lengthNew) {
		this.length = lengthNew;

	}

	public int getLeft_rot_count() {
		return left_rot_count;
	}

	public void setLeft_rot_count(int left_rot_count) {
		this.left_rot_count = left_rot_count;
	}

	public int getRight_rot_count() {
		return right_rot_count;
	}

	public void setRight_rot_count(int right_rot_count) {
		this.right_rot_count = right_rot_count;
	}

	public int getRot_measure() {
		return rot_measure;
	}

	public void setRot_measure(int rot_measure) {
		this.rot_measure = rot_measure;
	}

	public int getX_coor() {
		return x_coordinate;
	}

	public void setX_coor(int x_coor) {
		this.x_coordinate = x_coor;
	}

	public int getY_coor() {
		return y_coordinate;
	}

	public void setY_coor(int y_coor) {
		this.y_coordinate = y_coor;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
