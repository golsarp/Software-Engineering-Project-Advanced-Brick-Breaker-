package BuildingModePass_ExcessObstacle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.validity.ExcessObstacles;;

class BuildingModePassTest {
	//SARP GOL 72368
	
	/* true/false case, corner case 
	 * Overflow 
	 * Underflow 
	 */
	ExcessObstacles e;
	@BeforeEach
	void setUp() {
		 e = new ExcessObstacles();
		 
		
	}
	
	@Test
	void mustBeTrue() {
		// passes excess obstacle test
		 assertTrue(e.BuildingModePass(20,20,20,20));// GB returns true
		 assertTrue(e.BuildingModePass(40,30,20,20));// GB returns true
		 assertTrue(e.BuildingModePass(10,30,50,40));// GB returns true
		
		
	}
	
	@Test
	void mustBeFalse() {
		 //checks negative numbers 
		 assertFalse(e.BuildingModePass(-1,0,30,20)); // BB false
		 //total number of obstacles exceed max number of obstacles
		 assertFalse(e.BuildingModePass(120,50,40,30)); // GB first returns false
		 //checks overflows
		 assertFalse(e.BuildingModePass(Integer.MAX_VALUE,+3,0,0)); // GB second overflow
		 //checks underflows
		 assertFalse(e.BuildingModePass(Integer.MIN_VALUE,0,-1,0)); // GB third overflow
		 //Checking max number of obstacles
		 assertFalse(e.BuildingModePass(201,0,0,0)); // BB corner case
		
	}
	
}