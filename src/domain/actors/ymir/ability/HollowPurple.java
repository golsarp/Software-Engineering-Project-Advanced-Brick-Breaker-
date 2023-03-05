package domain.actors.ymir.ability;

import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.ObstacleFactory;
import domain.objects.obstacle.types.Obstacle;

public class HollowPurple implements YmirAbility {
	private String name = "HOLLOW PURPLE";
	ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();

	@Override
	public void activate() {
		// TODO Auto-generated method stub

		// set count of hollow obstacles to 8
		int hollowCount = 8;
		// Random random = new Random();
		while (hollowCount != 0) {

			// get a random position index
			int positionIndex = obstacleConfiguration.getRandomPosition();
			// while it's occupied, keep looking for an empty position
			while (positionIndex == -1) {
				positionIndex = obstacleConfiguration.getRandomPosition();
			}

			Obstacle o = ObstacleFactory.getInstance().getHollowObstacle(
					obstacleConfiguration.getPositions()[positionIndex][0],
					obstacleConfiguration.getPositions()[positionIndex][1]);
			o.setX_position(obstacleConfiguration.getPositions()[positionIndex][0]);
			o.setY_position(obstacleConfiguration.getPositions()[positionIndex][1]);

			obstacleConfiguration.getListOfObstacles().add(o);
			obstacleConfiguration.getPositions()[positionIndex][0] = -1;
			obstacleConfiguration.getPositions()[positionIndex][1] = -1;
			hollowCount--;
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
