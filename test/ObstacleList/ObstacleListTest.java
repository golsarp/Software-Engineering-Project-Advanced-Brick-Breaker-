package ObstacleList;

import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.objects.obstacle.ObstacleList;
import domain.objects.obstacle.types.Obstacle;

class ObstacleListTest {
	//Test for ObstacleList class
	
	ObstacleList config ;
	Obstacle obs ;
	Obstacle obs2;

	
	@BeforeEach
	void setUp() {
		config = new ObstacleList(0, 0, 0, 0);
		obs = new Obstacle();
		obs.setLife(1);
		obs2 = new Obstacle();
		obs2.setLife(1);
	}
	@AfterEach
	void tearDown() {
		assertTrue(config.repOk());			//making sure that the representation is ok
	
	}
	
	@Test
	void testInsert() {
		assertTrue(config.repOk());
		config.addObstacle(obs);
		assertTrue(config.repOk());
		assertTrue(config.isInList(obs));
	
	}
	
	
	@Test
	void testSetters() {
		config.setFirm(10);
		assertEquals(config.getFirm(),10);
		
		
	}
	
	
	@Test
	void testinFrame() {
		assertTrue(config.inFrame(obs));
		obs.setX_position(-3);
		assertFalse(config.inFrame(obs));
		
		
	}
	
	
	
	@Test
	void testRemove() {
		assertTrue(config.repOk());
		config.getListOfObstacles().remove(obs);
		assertFalse(config.isInList(obs));
	
	}
	
	
	@Test
	void testNullPointerException() {
		
		assertTrue(config.repOk());
		assertThrows(NullPointerException.class,() -> {
			config.addObstacle(null);
		});
		assertTrue(config.repOk());
		
		assertThrows(NullPointerException.class,() -> {
			config.addObstacle(null);
		});
	
	}
	
	
	
	@Test
	void testWithCombinations() {
		assertEquals(config.size(),0);
		
		assertTrue(config.repOk());
		config.addObstacle(obs);
		assertTrue(config.repOk());
		assertEquals(config.size(),1);
		assertTrue(config.isInList(obs));
		//since it was not added to list
		assertFalse(config.isInList(obs2));
		config.getListOfObstacles().remove(obs);
		assertFalse(config.isInList(obs));
		
		config.setFirm(10);
		assertEquals(config.getFirm(),10);
		assertTrue(config.repOk());
		
		assertThrows(NullPointerException.class,() -> {
			config.addObstacle(null);
		});
		assertTrue(config.repOk());
		
		assertThrows(NullPointerException.class,() -> {
			config.addObstacle(null);
		});
	
	}

}
