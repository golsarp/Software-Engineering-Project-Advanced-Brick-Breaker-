package domain.objects.phantasm;

import java.util.ConcurrentModificationException;

import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.paths.UpwardLinearPath;

public class CannonBullet {
	private double deltaX, deltaY, x, y;
	private static int radius;
	private boolean drawable;
	private String color;
	private boolean collided = false;
	private ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();
	private UpwardLinearPath myPath;

	public CannonBullet(double deltaX, double deltaY) {
		this.color = "RED";
		this.myPath = new UpwardLinearPath(deltaX, deltaY);
		this.drawable = true;
		radius = 5;
	}

	public void move() {
		double[] position = myPath.nextPosition();
		this.x = position[0];
		this.y = position[1];

		try {

			for (Obstacle ob : obstacleConfiguration.getListOfObstacles()) {
				// bounce from right
				if (x <= ob.getX_position() + ob.getLength() + 1 && x >= ob.getX_position() + ob.getLength()
						&& y + radius > ob.getY_position() && y < ob.getY_position() + ob.getWidth()) {
					obstacleConfiguration.hitHappened(ob);
					collided = true;
				}
				// bounce from left
				else if (x + radius >= ob.getX_position() - 1 && x + radius <= ob.getX_position()
						&& y + radius >= ob.getY_position() && y <= ob.getY_position() + ob.getWidth()) {
					obstacleConfiguration.hitHappened(ob);
					collided = true;
				}
				// bounce from top
				else if (y + radius >= ob.getY_position() - 1 && y + radius <= ob.getY_position()
						&& x + radius >= ob.getX_position() && x <= ob.getX_position() + ob.getLength()) {
					obstacleConfiguration.hitHappened(ob);
					collided = true;
				}
				// bounce from bottom
				else if (y <= ob.getY_position() + ob.getWidth() + 1 && y >= ob.getY_position() + ob.getWidth()
						&& x + radius >= ob.getX_position() && x <= ob.getX_position() + ob.getLength()) {
					obstacleConfiguration.hitHappened(ob);
					collided = true;

				}
			}
		}

		catch (ConcurrentModificationException e) {

		} catch (NullPointerException e) {

		}

	}

	public void setUpwardPathY(double y) {
		myPath.setY(y);
	}

	public void setUpwardPathX(double x) {
		myPath.setX(x);
	}

	// getters and setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;

	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	public boolean getCollided() {
		return collided;
	}
	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public static int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public UpwardLinearPath getMyPath() {
		return myPath;
	}

	public void setMyPath(UpwardLinearPath myPath) {
		this.myPath = myPath;
	}
	
	public boolean getDrawable() {
		return drawable;
	}

	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}

}