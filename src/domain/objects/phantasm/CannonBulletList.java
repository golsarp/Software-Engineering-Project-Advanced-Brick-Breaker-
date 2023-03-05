package domain.objects.phantasm;

import java.util.ArrayList;
import java.util.List;

public class CannonBulletList {
	private List<CannonBullet> cannonBulletList;
	
	public CannonBulletList() {	
		cannonBulletList= new ArrayList<>();
	}
	
	public void addParticles(double addXleft,double addYleft,double addXright,double addYright,Cannon canon,double deltaX, double deltaY) {

		CannonBullet cannonBulletLeft = new CannonBullet(deltaX,  deltaY);
		CannonBullet cannonBulletRight = new CannonBullet(deltaX, deltaY);
		cannonBulletLeft.setX(canon.getX_positionLeft()+addXleft);
		cannonBulletLeft.setY(canon.getY_positionLeft()-5+addYleft);
		
		cannonBulletRight.setX(canon.getX_positionRight()-5+addXright);
		cannonBulletRight.setY(canon.getY_positionRight()-5+addYright);
		
		cannonBulletLeft.setUpwardPathX(cannonBulletLeft.getX());
		cannonBulletLeft.setUpwardPathY(cannonBulletLeft.getY());
		cannonBulletRight.setUpwardPathX(cannonBulletRight.getX());
		cannonBulletRight.setUpwardPathY(cannonBulletRight.getY());
		
		cannonBulletList.add(cannonBulletLeft);
		cannonBulletList.add(cannonBulletRight);

	}

	//getters and setters
	public List<CannonBullet> getCanonBulletList() {
		return cannonBulletList;
	}

	public void setCanonBulletList(List<CannonBullet> canonBulletList) {
		cannonBulletList = canonBulletList;
	}
	
}


