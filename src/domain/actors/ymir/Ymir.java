package domain.actors.ymir;

import java.util.Random;

import domain.actors.ymir.ability.DoubleAccel;
import domain.actors.ymir.ability.HollowPurple;
import domain.actors.ymir.ability.InfiniteVoid;
import domain.actors.ymir.ability.YmirAbility;

public class Ymir {
	YmirAbility ability;

	public Ymir() {}
	
	public void chooseAbility() {
		
		//randomly chooses an ability
		Random random = new Random();
		int abilityNo = random.nextInt(3);
		switch(abilityNo) {
		case 0:	
			setAbility(new InfiniteVoid());
			break;
		case 1:	
			setAbility(new HollowPurple());
			break;
		case 2:	
			setAbility(new DoubleAccel());
			break;
		}
		
		//activates ability
		ability.activate();
	}

	
	public YmirAbility getAbility() {
		return ability;
	}

	public void setAbility(YmirAbility ability) {
		this.ability = ability;
	}
}
