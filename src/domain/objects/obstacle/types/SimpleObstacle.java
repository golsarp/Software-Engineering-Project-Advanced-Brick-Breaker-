package domain.objects.obstacle.types;

public class SimpleObstacle extends Obstacle {
	//OVERVIEW: SimpleObstacle is a type of Obstacle that can be destroyed at one hit

	public SimpleObstacle() {

		this.setLife(1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		this.hasParticle = false;
		this.is_rectangle = true;
		this.hasGiftBox = false;
		this.hasText = false;
		this.color = "yellow";

	}

}
