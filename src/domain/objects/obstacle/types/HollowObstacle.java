package domain.objects.obstacle.types;

public class HollowObstacle extends Obstacle {
	//OVERVIEW: HollowObstacle is a type of Obstacle that can be destroyed at one hit
	//			HollowObstacle appears when Ymir activates Hollow Purple
	//			Once destroyed, it doesn't contribute to Player's score
	
	public HollowObstacle() {

		this.setLife(1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		super.hasParticle = false;
		this.is_rectangle = true;
		this.hasGiftBox = false;
		this.hasText = false;
		this.color = "purple";
	}

}
