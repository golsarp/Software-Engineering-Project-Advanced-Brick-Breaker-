package domain.objects.obstacle.movement;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import constants.GameConstants;
import domain.controller.GameController;
import domain.objects.obstacle.types.Obstacle;

public class Circular implements Movement {
	private double angle = 0;
	private final int RIGHT_DIRECTION = 1;
	private final int LEFT_DIRECTION = -1;
	private int moveDirection = RIGHT_DIRECTION;
	private int collusionBoundriesBias = 4;
	private ArrayList<Obstacle> listOfObstacles;

	public Circular(ArrayList<Obstacle> listOfObstacles) {
		this.listOfObstacles = listOfObstacles;
	}

	public void move(Obstacle ob) {
		try {
			if (moveDirection > 0) {
				if (ob.getWidth() + ob.getX_position()
						+ collusionBoundriesBias == GameConstants.RUNNING_MODE_FRAME_WIDTH)
					moveDirection = LEFT_DIRECTION;
				for (Obstacle obstacle : GameController.getInstance().getListOfObstacles()) {

					if (obstacle.getX_position() == collusionBoundriesBias + ob.getX_position() + ob.getWidth()
							&& obstacle.getY_position() >= ob.getY_position()
							&& obstacle.getY_position() < ob.getY_position() + ob.getWidth()) {
						moveDirection = LEFT_DIRECTION;
					}
				}
			} else if (ob.getX_position() - collusionBoundriesBias == 0) {
				moveDirection = RIGHT_DIRECTION;
			}
			for (Obstacle obstacle : GameController.getInstance().getListOfObstacles()) {
				if (obstacle.getX_position() + obstacle.getWidth() == ob.getX_position() - collusionBoundriesBias
						&& obstacle.getY_position() >= ob.getY_position()
						&& obstacle.getY_position() < ob.getY_position() + ob.getWidth()) {
					moveDirection = RIGHT_DIRECTION;
				}
			}

		} catch (NoSuchElementException e) {

		} catch (NullPointerException u) {

		} catch (ConcurrentModificationException r) {

		}

		if (ob.hasEnoughSpaceToMove()) {

			angle += 0.1;
			int x = (int) (Math.cos(angle) * 2);

			int y = (int) (Math.sin(angle) * 2);

			ob.setX_position(ob.getX_position() + x);
			ob.setY_position(ob.getY_position() + y);
		} else {

		}

	}
}