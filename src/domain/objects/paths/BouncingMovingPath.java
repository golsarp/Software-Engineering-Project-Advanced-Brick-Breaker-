package domain.objects.paths;

import domain.controller.GameController;
import domain.helpers.Point;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.types.Obstacle;

public class BouncingMovingPath implements EnchantedPath {
	private int radius;
	private static final int LEFT_DIRECTION = 1;
	private static final int RIGHT_DIRECTION = 2;
	private static final int ABOVE_DIRECTION = 3;
	private static final int BOTTOM_DIRECTION = 7;
	private static final int FROZEN_OBSTACLE = 100;
	private ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();
	private Obstacle ob;
	private boolean intersected = false;
	private double currentX, currentY;
	private double deltaX, deltaY;

	public BouncingMovingPath(double X1, double Y1, double deltaX, double deltaY, int radius) {
		this.currentX = X1;
		this.currentY = Y1;
		this.radius = radius;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public int getHitDirection() {
		// TODO Auto-generated method stub

		// effects: Iterates through Obstacles at obstacleList
		// and returns the hit direction of enchanted sphere to the obstacle if the
		// enchanted sphere collides with the obstacle
		// else
		// returns 0 (0 means there is no collision between sphere and obstacles
		// occurred)

		int sum = 0;

		if (intersected) {
			if (ob.isFrozen()) {
				sum += FROZEN_OBSTACLE;
			}

			if ((currentX + radius >= ob.getX_position()) && (currentX + radius <= ob.getX_position() + 10.0)) {

				sum += LEFT_DIRECTION;

			}
			if ((currentX >= ob.getX_position() + ob.getLength() - 10.0)
					&& (currentX <= ob.getX_position() + ob.getLength())) {

				sum += RIGHT_DIRECTION;

			}

			if ((currentY >= ob.getY_position() + ob.getWidth() - 15.0)
					&& (currentY <= ob.getY_position() + ob.getWidth())) {

				sum += BOTTOM_DIRECTION;

			}

			if ((currentY + radius <= ob.getY_position() + 15.0) && (currentY + radius >= ob.getY_position())) {

				sum += ABOVE_DIRECTION;

			}
		}

		return sum;
	}

	public Point nextPosition() {

		currentX = currentX + deltaX;
		currentY = currentY + deltaY;
		return new Point((currentX + deltaX), (currentY + deltaY));
	}

	public Point currentPosition() {
		return new Point(currentX, currentY);
	}

	public boolean isUp(int up) {
		if (ABOVE_DIRECTION == up)
			return true;
		return false;
	}

	public boolean isBottom(int up) {
		if (BOTTOM_DIRECTION == up)
			return true;
		return false;
	}

	public boolean isRight(int up) {
		if (RIGHT_DIRECTION == up)
			return true;
		return false;
	}

	public boolean isLeft(int up) {
		if (LEFT_DIRECTION == up)
			return true;
		return false;
	}

	public boolean isNoHit(int up) {
		if (0 == up)
			return true;
		return false;
	}

	//getters and setters
	public ObstacleConfiguration getObstacleConfiguration() {
		return obstacleConfiguration;
	}

	public void setObstacleConfiguration(ObstacleConfiguration obstacleConfiguration) {
		this.obstacleConfiguration = obstacleConfiguration;
	}
	public boolean isIntersected() {
		return intersected;
	}

	public void setIntersected(boolean intersected) {

		this.intersected = intersected;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public Obstacle getOb() {
		return ob;
	}

	public void setOb(Obstacle ob) {
		this.ob = ob;
	}
}
