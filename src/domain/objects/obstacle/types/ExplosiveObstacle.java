package domain.objects.obstacle.types;

public class ExplosiveObstacle extends Obstacle {
	//OVERVIEW: ExplosiveObstacle is a type of Obstacle that can be destroyed at one hit
	//			Once destroyed, it drops an ExplosiveParticle

	public ExplosiveObstacle() {
		this.setLife(2);
		this.setIs_rectangle(false);
		this.setLength(30);
		this.setWidth(25);
		this.setLife(1);
		this.hasParticle = true;
		this.is_rectangle = false;
		this.hasGiftBox = false;
		this.hasText = false;
		this.color = "blue";

	}

}
