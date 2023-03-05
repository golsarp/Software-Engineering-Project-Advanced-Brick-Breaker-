package isValidObstacleNumber;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.validity.InputValidity;

class isValidObstacleNumberTest {
	//ECE PINAR ÖZER 72047
	
	/*
	 * true/false cases, corner cases
	 * Overflow
	 * Underflow
	 * NumberFormatException
	 */
	
	InputValidity i;
	
	@BeforeEach
	void setUp() {
		i = new InputValidity();

	}

	@Test
	void mustBeTrue() {

		assertTrue(i.isValidObstacleNumber("0"));    // BB corner case
		assertTrue(i.isValidObstacleNumber("120"));  // BB true case
		assertTrue(i.isValidObstacleNumber("250"));  // BB corner case
	}
	@Test
	void mustBeFalse() {
		
		assertFalse(i.isValidObstacleNumber("-5"));					// BB false case
		assertFalse(i.isValidObstacleNumber("251")); 				// BB corner case
		assertFalse(i.isValidObstacleNumber("999999999999999999")); // GB Overflow
		assertFalse(i.isValidObstacleNumber("-999999999999999999"));// GB Underflow
		assertFalse(i.isValidObstacleNumber("not an int"));			// GB NumberFormatException
			
	}

}