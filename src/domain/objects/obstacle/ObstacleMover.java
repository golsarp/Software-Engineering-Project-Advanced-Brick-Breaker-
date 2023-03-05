package domain.objects.obstacle;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import constants.GameConstants;
import domain.objects.obstacle.types.Obstacle;

public class ObstacleMover {
	//OVERVIEW: RunningModePanelHandler is the class responsible for obstacle movement in Running Mode

	ArrayList<Obstacle> listOfObstacle;

	public ObstacleMover(ArrayList<Obstacle> listOfObstacle) {
		this.listOfObstacle = listOfObstacle;
	}

	public void letExplosiveMove() {
		try {
			for (Obstacle ob : listOfObstacle) {
				int flag = 0;
				if (ob.getHasParticle() == true) {
					for (Obstacle neigbours : listOfObstacle) {
						if (ob.getX_position() != neigbours.getX_position()) {

							if (Math.abs(ob.getX_position() - neigbours.getX_position()) < 50
									&& Math.abs(ob.getY_position() - neigbours.getY_position()) < 50) {
								flag = 1;
							}
						}
					}

					if (ob.getX_position() - 12 < 0)
						flag = 1;
					if (ob.getX_position() + ob.getWidth() + 12 > GameConstants.BUILDING_MODE_FRAME_WIDTH)
						flag = 1;
					if (ob.getY_position() - 12 < 0)
						flag = 1;
					if (flag == 1) {
						ob.setEnoughSpaceToMove(false);
					} else {
						ob.setEnoughSpaceToMove(true);
					}
				}
			}
		} catch (ConcurrentModificationException e) {

		} catch (NullPointerException e) {

		}
	}

}
