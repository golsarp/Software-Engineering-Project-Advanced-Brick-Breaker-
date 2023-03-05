package isValidCredentials;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import domain.validity.InputValidity;

class isValidCredentialsTest {
	//YIGIT YAKAR 72269
	
	InputValidity i;

	@BeforeEach
	void setUp() {
		
		
		/*
		 * true/false cases
		 * NullPointerException
		 * 
		 * */
		i = new InputValidity();

	}

	@Test
	void mustThrowNullPointerException() {

		assertThrows(NullPointerException.class, () -> {   //BB throws exception
			i.isValidCredential("", "pasSword");
		});

	}

	@Test
	void mustBeTrue() {

		assertTrue(i.isValidCredential("username", "Password")); // BB true case

	}

	@Test
	void mustBeFalse() {

		assertFalse(i.isValidCredential("username", "password")); // BB False
		assertFalse(i.isValidCredential(".sername", "password")); // GB First iteration
		assertFalse(i.isValidCredential("username", "p#ssword")); // GB Second iteration
		assertFalse(i.isValidCredential("username", "pas:word")); // GB Third iteration

	}

}
