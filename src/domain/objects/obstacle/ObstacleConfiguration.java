package domain.objects.obstacle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.listeners.LifeListener;
import domain.listeners.ScoreListener;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.particles.ExplosiveParticleList;
import domain.objects.particles.GiftBoxList;

public class ObstacleConfiguration {
	//OVERVIEW: ObstacleConfiguration is the class responsible for the initializing the positions of the obstacles in the game
	//			it also keeps track of the state of obstacles in the game

	private ObstacleList list;
	private List<LifeListener> listeners = new ArrayList<>();
	private List<ScoreListener> scoreListeners = new ArrayList<>();
	private ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();
	private int[][] positions = new int[250][2];;
	private int[] numberOfObstacles;
	private int[] orderOfObjects;
	private int deltax = 40;
	private int deltay = 35;
	private PlayerMaster playerHandler = PlayerMaster.getInstance();

	public ObstacleConfiguration() {}

	public void hitHappened(Obstacle ob) {
		// MODIFIES: ob, listOfObstacles, Player
		// EFFECTS: if obstacle's life is 0 or below then obstacle is removed from
		// listOfObstacles and player's score increases
		// if obstacle has particle then adds a new explosive particle to explosive
		// particle list
		// if obstacle has gift box then adds a new gift box to gift box list

		if (!ob.isFrozen() || (ob.isFrozen() && GameController.getInstance().getBall().isUnstoppable())) {
			ob.setLife(ob.getLife() - 1);
			if (ob.getLife() <= 0) {
				listOfObstacles.remove(ob);
				if (ob.getColor() != "purple") {
					playerHandler.increaseScore();
					publishScoreEvent();
				}

			}
			if (ob.getHasParticle() == true) {
				ExplosiveParticleList.getInstance().addParticles(ob);
			}
			if (ob.getHasGiftBox() == true) {
				GiftBoxList.getInstance().addGiftBox(ob);
			}
		}
	}

	public void initializeObstacleConfiguration(int simple, int firm, int explosive, int gift) {

		list = new ObstacleList(simple, firm, explosive, gift);
		listOfObstacles = list.getListOfObstacles();
		positions = setPositionList();
		numberOfObstacles = new int[4];
		numberOfObstacles[0] = simple;
		numberOfObstacles[1] = firm;
		numberOfObstacles[2] = explosive;
		numberOfObstacles[3] = gift;

		orderOfObjects = setObstacleOrder(simple, firm, explosive, gift);

		initializeObstacles();
	}

	public int[][] setPositionList() {

		int count = 1;
		int cor1 = 0;
		int cor2 = 2 * deltay;
		int delta1 = 40;
		int horizontal_max = GameConstants.RUNNING_MODE_FRAME_WIDTH / delta1;

		for (int x = 0; x < 250; x++) {

			positions[x][0] = cor1;
			positions[x][1] = cor2;

			if (count % horizontal_max == 0 && count != 0) {
				cor2 += deltay;
				cor1 = 0;
			} else {

				cor1 += deltax;
			}
			count++;

		}
		return positions;

	}

	public int[] setObstacleOrder(int simple, int firm, int explosive, int gift) {

		int total = simple + firm + explosive + gift;

		int count = 0;

		int[] list = new int[total];

		Random rand = new Random();

		while (count != total) {
			int index = rand.nextInt(4);

			if (numberOfObstacles[index] != 0) {
				list[count] = index;
				numberOfObstacles[index] = numberOfObstacles[index] - 1;
				count++;
			}

		}

		return list;
	}

	public void initializeObstacles() {
		Random rand = new Random();
		int length = orderOfObjects.length;
		Obstacle obstacle;
		int count = 0;

		while (count < length) {

			int pos_index = rand.nextInt(positions.length);

			if (positions[pos_index][0] != -1) {

				switch (orderOfObjects[count]) {
				case 0:
					obstacle = ObstacleFactory.getInstance().getSimpleObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 1:
					obstacle = ObstacleFactory.getInstance().getFirmObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 2:
					obstacle = ObstacleFactory.getInstance().getExplosiveObstacle(listOfObstacles,
							positions[pos_index][0], positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				case 3:
					obstacle = ObstacleFactory.getInstance().getGiftObstacle(positions[pos_index][0],
							positions[pos_index][1]);
					listOfObstacles.add(obstacle);
					break;
				}
				positions[pos_index][0] = -1;
				positions[pos_index][1] = -1;
				count++;

			}

		}
		// letExplosiveMove();
	}

	public int getRandomPosition() {
		Random random = new Random();
		int pos_index = random.nextInt(getPositions().length);
		boolean positionEmpty = false;
		// iterate over obstacles on screen
		for (Obstacle o : getListOfObstacles()) {
			// if there is an obstacle in the specified position, set positionEmpty false
			if (o.getX_position() == getPositions()[pos_index][0]) {
				positionEmpty = true;
				break;
			}
		}
		// if the position is empty, create an obstacle
		if (positionEmpty) {
			return pos_index;
		} else {
			return -1;
		}

	}

	public void publishHitEvent(Obstacle ob) {
		for (LifeListener l : listeners)
			l.onHitEvent();
	}

	public void publishScoreEvent() {
		for (ScoreListener l : scoreListeners) {
			l.onScoreEvent();
		}
	}
	public void addAlarmListener(LifeListener lis) {
		listeners.add(lis);
	}

	public void addScoreListener(ScoreListener lis) {
		scoreListeners.add(lis);
	}

	// getters and setters
	public int getDeltax() {
		return deltax;
	}

	public void setDeltax(int deltax) {
		this.deltax = deltax;
	}

	public int getDeltay() {
		return deltay;
	}

	public void setDeltay(int deltay) {
		this.deltay = deltay;
	}

	public PlayerMaster getPlayerHandler() {
		return playerHandler;
	}

	public void setPlayerHandler(PlayerMaster playerHandler) {
		this.playerHandler = playerHandler;
	}

	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] positions) {
		this.positions = positions;
	}

	public void setListOfObstacles(ArrayList<Obstacle> listOfObstacles) {
		this.listOfObstacles = listOfObstacles;
	}

	public ArrayList<Obstacle> getListOfObstacles() {
		return listOfObstacles;
	}

}