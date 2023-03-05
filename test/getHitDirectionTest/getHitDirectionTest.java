package getHitDirectionTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.paths.BouncingMovingPath;





public class getHitDirectionTest {
	//DORUK OZER 70192
	
	BouncingMovingPath myPathWithCollisionRight;
	 BouncingMovingPath myPathWithCollisionLeft;
	 BouncingMovingPath myPathWithCollisionBottom;
	 BouncingMovingPath myPathWithCollisionUp;
	 ObstacleConfiguration obConfigRight ;
	 ObstacleConfiguration obConfigLeft;
	 ObstacleConfiguration obConfigUp ;
	 ObstacleConfiguration obConfigBottom;
	 
	 
		@BeforeEach
		void setUp() {
			myPathWithCollisionRight = new BouncingMovingPath(30, 20, 1, 1, 10);
			myPathWithCollisionLeft = new BouncingMovingPath(30, 20, 1, 1, 10);
			myPathWithCollisionBottom = new BouncingMovingPath(30, 20, 1, 1, 10);
			myPathWithCollisionUp = new BouncingMovingPath(30, 20, 1, 1, 10);
			 obConfigRight = new ObstacleConfiguration();
			obConfigRight.initializeObstacleConfiguration(1,0 , 0, 0);
			obConfigRight.getListOfObstacles().get(0).setX_position(0);
			obConfigRight.getListOfObstacles().get(0).setY_position(22);
			
			obConfigLeft = new ObstacleConfiguration();
			obConfigLeft.initializeObstacleConfiguration(1,0 , 0, 0);
			obConfigLeft.getListOfObstacles().get(0).setX_position(40);
			obConfigLeft.getListOfObstacles().get(0).setY_position(25);
			
			
			obConfigUp = new ObstacleConfiguration();
			obConfigUp.initializeObstacleConfiguration(1,0 , 0, 0);
			obConfigUp.getListOfObstacles().get(0).setX_position(25);
			obConfigUp.getListOfObstacles().get(0).setY_position(0);
			
			
			obConfigBottom = new ObstacleConfiguration();
			obConfigBottom.initializeObstacleConfiguration(1,0 , 0, 0);
			obConfigBottom.getListOfObstacles().get(0).setX_position(25);
			obConfigBottom.getListOfObstacles().get(0).setY_position(31);
			
			
		}
	
	
		@Test
		void TestHitRight() {
			obConfigRight.setPlayerHandler(null);
			myPathWithCollisionRight.setObstacleConfiguration(obConfigRight);
			System.out.println("--->"+myPathWithCollisionRight.getHitDirection());
			assertTrue(myPathWithCollisionRight.isRight(myPathWithCollisionRight.getHitDirection()));
			}
		
		@Test
		void TestHitLeft() {
			obConfigLeft.setPlayerHandler(null);
			myPathWithCollisionLeft.setObstacleConfiguration(obConfigLeft);
			System.out.println("--->"+myPathWithCollisionLeft.getHitDirection());
			assertTrue(myPathWithCollisionLeft.isLeft(myPathWithCollisionLeft.getHitDirection()));
			}
		
		@Test
		void TestNoHit() {
			obConfigUp.setPlayerHandler(null);
			myPathWithCollisionLeft.setObstacleConfiguration(obConfigUp);
			System.out.println("--->"+myPathWithCollisionUp.getHitDirection());
			assertTrue(myPathWithCollisionUp.isNoHit((myPathWithCollisionUp.getHitDirection())));
			}
		@Test
		void TestHitUp() {
			obConfigUp.setPlayerHandler(null);
			myPathWithCollisionUp.setObstacleConfiguration(obConfigUp);
			System.out.println("--->"+myPathWithCollisionUp.getHitDirection());
			assertTrue(myPathWithCollisionUp.isUp(((myPathWithCollisionUp.getHitDirection()))));
			}
		@Test
		void TestHitBottom() {
			obConfigBottom.setPlayerHandler(null);
			myPathWithCollisionBottom.setObstacleConfiguration(obConfigBottom);
			System.out.println("--->"+myPathWithCollisionBottom.getHitDirection());
			assertTrue(myPathWithCollisionBottom.isBottom(((myPathWithCollisionBottom.getHitDirection()))));
			}
		
}
