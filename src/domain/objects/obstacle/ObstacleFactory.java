package domain.objects.obstacle;

import java.util.ArrayList;
import java.util.Random;

import domain.objects.obstacle.movement.Circular;
import domain.objects.obstacle.movement.Horizontal;
import domain.objects.obstacle.movement.Stationary;
import domain.objects.obstacle.types.ExplosiveObstacle;
import domain.objects.obstacle.types.FirmObstacle;
import domain.objects.obstacle.types.GiftObstacle;
import domain.objects.obstacle.types.HollowObstacle;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.obstacle.types.SimpleObstacle;

public class ObstacleFactory {
	//OVERVIEW: ObstacleFactory is the class responsible for the creation of different Obstacle subclasses
	//			It is designed by Factory pattern

	private static ObstacleFactory instance;

	private ObstacleFactory() {
	}

	public static ObstacleFactory getInstance() {
		if (instance == null) {
			instance = new ObstacleFactory();
		}
		return instance;
	}

	public Obstacle getSimpleObstacle(int x_position, int y_position) {
		SimpleObstacle simpleObstacle = new SimpleObstacle();
		simpleObstacle.setX_position(x_position);
		simpleObstacle.setY_position(y_position);

		Random rand3 = new Random();
		if (rand3.nextInt(2) + 1 == 2) {
			simpleObstacle.setMoving(new Horizontal());
			simpleObstacle.setIs_moving(true);
		} else {
			simpleObstacle.setMoving(new Stationary());
			simpleObstacle.setIs_moving(false);

		}
		return simpleObstacle;
	}

	public Obstacle getFirmObstacle(int x_position, int y_position) {

		FirmObstacle firmObstacle = new FirmObstacle();
		firmObstacle.setX_position(x_position);
		firmObstacle.setY_position(y_position);
		Random rand4 = new Random();
		if (rand4.nextInt(2) + 1 == 2) {
			firmObstacle.setMoving(new Horizontal());
			firmObstacle.setIs_moving(true);

		} else {
			firmObstacle.setMoving(new Stationary());
			firmObstacle.setIs_moving(false);

		}
		return firmObstacle;
	}

	public Obstacle getExplosiveObstacle(ArrayList<Obstacle> list,int x_position, int y_position) {

		ExplosiveObstacle explosiveObstacle = new ExplosiveObstacle();
		explosiveObstacle.setX_position(x_position);
		explosiveObstacle.setY_position(y_position);

		Random rand5 = new Random();
		if (rand5.nextInt(2) + 1 == 2) {
			explosiveObstacle.setMoving(new Circular(list));
			explosiveObstacle.setIs_moving(true);

		} else {
			explosiveObstacle.setIs_moving(false);

			explosiveObstacle.setMoving(new Circular(list));
		}

		return explosiveObstacle;
	}

	public Obstacle getGiftObstacle(int x_position, int y_position) {

		GiftObstacle giftObstacle = new GiftObstacle();
		giftObstacle.setX_position(x_position);
		giftObstacle.setY_position(y_position);
		giftObstacle.setMoving(new Stationary());
		giftObstacle.setIs_moving(false);

		return giftObstacle;
	}
	public Obstacle getHollowObstacle(int x_position, int y_position) {

		HollowObstacle hollowObstacle = new HollowObstacle();
		hollowObstacle.setX_position(x_position);
		hollowObstacle.setY_position(y_position);

		Random rand3 = new Random();
		if (rand3.nextInt(2) + 1 == 2) {
			hollowObstacle.setMoving(new Horizontal());
			hollowObstacle.setIs_moving(true);
		} else {
			hollowObstacle.setMoving(new Stationary());
			hollowObstacle.setIs_moving(false);

		}
		return hollowObstacle;
	}
}
