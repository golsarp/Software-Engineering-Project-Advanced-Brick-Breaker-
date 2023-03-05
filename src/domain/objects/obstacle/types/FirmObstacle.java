package domain.objects.obstacle.types;

import java.util.Random;

public class FirmObstacle extends Obstacle {
	//OVERVIEW: FirmObstacle is a type of Obstacle that has a number on it, specifying the number of hits
	//			required to destroy the obstacle

	public FirmObstacle() {
		Random rand2 = new Random();
		this.setLife(rand2.nextInt(5) + 1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		this.color = "red";
		this.hasText = true;
		this.hasParticle = false;
		this.is_rectangle = true;
		this.hasGiftBox = false;
	}

}
