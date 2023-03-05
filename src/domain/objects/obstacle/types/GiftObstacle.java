package domain.objects.obstacle.types;

public class GiftObstacle extends Obstacle {
	//OVERVIEW: GiftObstacle is a type of Obstacle that can be destroyed at one hit
	//			Once destroyed, it drops a GiftBox containing a random player ability.

	public GiftObstacle() {

		this.setLife(1);
		this.setIs_rectangle(true);
		this.setLength(30);
		this.setWidth(20);
		this.hasGiftBox = true;
		this.hasParticle = false;
		this.is_rectangle = true;
		this.hasText = false;
		this.color = "green";
		this.setLife(1);

	}

}
