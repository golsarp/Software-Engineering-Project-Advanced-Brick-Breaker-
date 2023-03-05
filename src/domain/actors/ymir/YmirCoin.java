package domain.actors.ymir;

import java.util.Random;

public class YmirCoin {
	// OVERVIEW: This class is responsible for flipping a coin to decide the abilities of Ymir 
	//			It is designed by Pure Fabrication
	
	public YmirCoin() {}
	
	public int flip() {
		// EFFECTS: returns a random number between 0-1, 
		Random random = new Random();
		return random.nextInt(2);
	}
}
