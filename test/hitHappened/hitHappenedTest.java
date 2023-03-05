package hitHappened;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.actors.player.Player;
import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.ObstacleList;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.particles.ExplosiveParticleList;
import domain.objects.particles.GiftBoxList;



class hitHappenedTest {
	// BUMIN AYBARS INCI 69032
	
	ObstacleConfiguration configuration;
	Obstacle obstacle;
	
	ArrayList<Obstacle> list;
	
	
	
	@BeforeEach
	void setUp() {
		
		configuration = new ObstacleConfiguration();
		
		obstacle = new Obstacle();
		obstacle.setLife(1);
		obstacle.setFrozen(false);
		obstacle.setHasParticle(false);
		obstacle.setHasGiftBox(false);
		obstacle.setX_position(200);
		obstacle.setY_position(100);
		
		list = new ArrayList<Obstacle>();
		list.add(obstacle);
		configuration.setListOfObstacles(list);
		
				
		PlayerMaster.getInstance().initializePlayer("Aybars", "Inci");
		PlayerMaster.getInstance().initializeScoreAndLives();
		
		GameController.getInstance().initializeGameObjects();
		GameController.getInstance().initializeGameTime();
		GameController.getInstance().getGameTime().startTime();
		GameController.getInstance().getGameTime().stopTime();
	}
	

	@Test
	void obstacleFrozenTest() {  // checks if obstacle is frozen while hitHappened() occurs
		obstacle.setFrozen(true);
		obstacle.setLife(3);
		
		configuration.hitHappened(obstacle);
		assertEquals(obstacle.getLife(), 3);  // nothing happens GB test
	}
	
	@Test
	void obstacleFrozenAndUnstoppableTest() {  // checks if obstacle is frozen and enchanted sphere is unstoppable while hitHappened() occurs
		
		obstacle.setFrozen(true); 
		obstacle.setLife(3);
		GameController.getInstance().getBall().setUnstoppable(true);
		
		configuration.hitHappened(obstacle);
		assertEquals(obstacle.getLife(), 2); // obstacle's life decremented by 1  GB test
	}
	
	@Test
	void obstacleRemoveTest() {  // checks if obstacle is removed while hitHappened() occurs
		assertTrue(configuration.getListOfObstacles().contains(obstacle));// tests initial condition
		obstacle.setLife(1);
		
		
		configuration.hitHappened(obstacle);
		assertEquals(obstacle.getLife(), 0);
		assertEquals(PlayerMaster.getInstance().getScore(), 300);
		assertFalse(configuration.getListOfObstacles().contains(obstacle)); // obstacle is removed BB test
	}
	
	@Test
	void obstacleHasParticleTest() {  // checks if obstacle creates particle while hitHappened() occurs
		assertEquals(ExplosiveParticleList.getInstance().getList().size(), 0); // tests initial condition
		obstacle.setHasParticle(true);
		
		configuration.hitHappened(obstacle);
		assertEquals(obstacle.getLife(), 0);
		assertEquals(ExplosiveParticleList.getInstance().getList().size(), 1); // explosive particle is added to explosive particle list BB test
	}
	
	@Test
	void obstacleHasGiftBoxTest() { // checks if obstacle creates gift box while hitHappened() occurs
		assertEquals(GiftBoxList.getInstance().getList().size(), 0);// tests initial condition
		obstacle.setHasGiftBox(true);
		
		configuration.hitHappened(obstacle);
		assertEquals(obstacle.getLife(), 0);
		assertEquals(GiftBoxList.getInstance().getList().size(), 1); // gift box is added to gift box list BB test
	}
	
	
	
	
	
	
	
	
	

}
