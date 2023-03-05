package domain.game;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;
import domain.actors.player.ability.GameObject;
import domain.actors.ymir.YmirMaster;
import domain.controller.GameController;
import domain.controller.LoadSaveHandler;
import domain.helpers.GameTime;
import domain.listeners.YmirListener;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.CannonBulletList;
import domain.objects.phantasm.NoblePhantasm;
import domain.objects.sphere.EnchantedSphere;
import domain.validity.MinRequirements;
import domain.validity.ExcessObstacles;
import domain.timertask.UpdateBoxTask;
import domain.timertask.UpdateCannonBulletsTask;
import domain.timertask.UpdateExplosiveParticleTask;
import domain.timertask.UpdateObstaclePositions;
import domain.timertask.UpdateSphereTask;

import domain.validity.BuildingModeRequirement;

import domain.timertask.AddBulletTask;
import domain.timertask.CancelBulletTimerTask;

public class Game{
	// OVERVIEW: Game is the class responsible for handling game related events
	//			It initializes the game objects and timers, it also keeps track of the obstacles in the game

	private static GameController instance;
	private GameTime gameTime = new GameTime();
	private NoblePhantasm phantasm;
	private Cannon cannon;
	private EnchantedSphere ball;
	private TimerTask timerTask;
	private TimerTask timerTask2;
	private Timer timer;
	private Timer timer2;
	private ArrayList<BuildingModeRequirement> requirements = new ArrayList<BuildingModeRequirement>();
	private ArrayList<TimerTask> tasks = new ArrayList<TimerTask>();
	private ObstacleConfiguration obstacleConfiguration;
	private YmirMaster ymirMaster;
	private long spherePeriod;
	private Timer sphereTimer;
	private TimerTask sphereTimerTask;
	private int obstacleMovementDelay = (int) Math.round(1000 / (GameConstants.PHANTASM_LENGTH / 4.0));

	public Game(YmirMaster ym) {
		obstacleConfiguration = new ObstacleConfiguration();
		ymirMaster = ym;
		spherePeriod = 5;
	}

	public void initializeGameTime() {

		if (gameTime.getCurrentTime() == 0) {
			gameTime = new GameTime();
		}
	}

	public void initializeAllTasks() {
		initializeTimerTaskSphere();
		initializeTimerTaskExplosiveParticle();
		initializeTimerTaskGiftBox();
		initializeTimerTaskObstacleMovement();
		ymirMaster.initializeTimer();

	}

	public void initializeCanon() {
		cannon = new Cannon(getPhantasm().getX_coor(), getPhantasm().getY_coor(), getPhantasm().getLength(),
				getPhantasm().getWidth());
		cannon.setActive(true);
	}

	public void AddBulletTask(GameObject ob) {

		timer2 = new Timer(true);
		timerTask2 = new AddBulletTask(ob);
		tasks.add(timerTask2);

		// running timer task as daemon thread
		timer2.scheduleAtFixedRate(timerTask2, 0, 90);

	}

	public void initializeCannonBullets(GameObject object, CannonBulletList list) {

		timer2 = new Timer(true);
		Timer timer1 = new Timer(true);
		TimerTask timerTaskCanonCancel = new CancelBulletTimerTask(timer1, timer2, cannon);
		timerTask2 = new UpdateCannonBulletsTask(list, object);

		tasks.add(timerTask2);
		tasks.add(timerTaskCanonCancel);

		// running timer task as daemon thread
		timer1.schedule(timerTaskCanonCancel, 30 * 1000);
		timer2.scheduleAtFixedRate(timerTask2, 0, 20);

	}

	public void initializeGameObjects() {
		phantasm = new NoblePhantasm((GameConstants.RUNNING_MODE_FRAME_WIDTH - NoblePhantasm.length) / 2,
				GameConstants.RUNNING_MODE_FRAME_HEIGHT - 90, 0);
		ball = new EnchantedSphere(0, 0, (GameConstants.RUNNING_MODE_FRAME_WIDTH - 16) / 2,
				GameConstants.RUNNING_MODE_FRAME_HEIGHT - (90 + GameConstants.ENCHANTED_SPHERE_RADIUS + 1));
		initializeGameTime();
	}

	public void initializeTimerTaskSphere() {
		sphereTimer = new Timer(true);
		sphereTimerTask = new UpdateSphereTask(ball);
		tasks.add(sphereTimerTask);
		// running timer task as daemon thread
		sphereTimer.scheduleAtFixedRate(sphereTimerTask, 0, spherePeriod);
	}

	public void initializeTimerTaskExplosiveParticle() {
		timer2 = new Timer(true);
		timerTask2 = new UpdateExplosiveParticleTask();
		tasks.add(timerTask2);
		// running timer task as daemon thread
		timer2.scheduleAtFixedRate(timerTask2, 0, obstacleMovementDelay);
	}

	public void initializeTimerTaskObstacleMovement() {
		for (Obstacle o : obstacleConfiguration.getListOfObstacles()) {
			timer2 = new Timer(true);
			timerTask2 = new UpdateObstaclePositions(o);
			tasks.add(timerTask2);
			// running timer task as daemon thread
			timer2.scheduleAtFixedRate(timerTask2, 0, obstacleMovementDelay);
		}
	}

	public void initializeTimerTaskGiftBox() {
		timer2 = new Timer(true);
		timerTask2 = new UpdateBoxTask();
		tasks.add(timerTask2);
		// running timer task as daemon thread
		timer2.scheduleAtFixedRate(timerTask2, 0, obstacleMovementDelay);
	}

	public void initializeRequirements() {
		// EFFECTS: initializes building mode requirements for obstacles
		getRequirements().add(new BuildingModeRequirement(new MinRequirements()));
		getRequirements().add(new BuildingModeRequirement(new ExcessObstacles()));

	}

	public boolean checkRequirements(int s, int f, int e, int g) {
		// EFFECTS: returns true if all the requirements are satisfied, else returns
		// 			false
		int numberOfPasses = 0;
		int counter = 0;

		// iterate through all requirements and execute the strategies
		for (BuildingModeRequirement req : getRequirements()) {
			boolean pass = req.executeStrategy(s, f, e, g);

			if (pass) {
				numberOfPasses += 1;
			}
			counter += 1;
		}
		// if all requirements are satisfied, return true
		if (numberOfPasses == counter) {
			return true;
		} else
			return false;
	}

	public void cancelAllTasks() {
		for (TimerTask t : this.tasks) {
			t.cancel();
		}
		ymirMaster.cancelTask();
	}
	public void scoreListUpdated(int new_score) {
		boolean isTopFive = false;
		int index=0;
		
		for (int score : LoadSaveHandler.getInstance().getFileAccessor().getTop_five_scores()) {

			if (new_score > score) {
				isTopFive = true;
				break;
			}
			index++;
		}
		if (isTopFive) {
			int index_2=0;
			int [] new_top_five_scores = new int[5];
			String [] new_top_five_users = new String[5];
			int cast =0;
			while(index_2<5) {
			
			if(index_2==index) {
				
				new_top_five_scores[index_2]=new_score;
				new_top_five_users[index_2]=PlayerMaster.getInstance().getPlayer().getUsername();
				index=7;
				cast=1;
				index_2++;		
			}
			
			else {
				new_top_five_scores[index_2]=LoadSaveHandler.getInstance().getFileAccessor().getTop_five_scores()[index_2-cast];
				new_top_five_users[index_2]=LoadSaveHandler.getInstance().getFileAccessor().getTop_five_users()[index_2-cast];
				index_2++;
				
			}
			}
			LoadSaveHandler.getInstance().getFileAccessor().setTop_five_users(new_top_five_users);
			LoadSaveHandler.getInstance().getFileAccessor().setTop_five_scores(new_top_five_scores);
		}
	}


	public void changeSphereSpeed(long newSpherePeriod) {
		// TODO Auto-generated method stub
		setSpherePeriod(newSpherePeriod);
		sphereTimerTask.cancel();
		initializeTimerTaskSphere();
	}

	public void sphereObstacleCollision(Obstacle ob, boolean ball_inter_obj) {

		getBall().getMyPath().setIntersected(ball_inter_obj);
		getBall().getMyPath().setOb(ob);
		if (obstacleConfiguration.getPlayerHandler() != null) {
			obstacleConfiguration.hitHappened(ob);

		}
	}

	// getters and setters
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public TimerTask getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	public GameTime getGameTime() {
		return gameTime;
	}

	public Cannon getCannon() {
		return cannon;
	}

	public ArrayList<BuildingModeRequirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<BuildingModeRequirement> requirements) {
		this.requirements = requirements;
	}

	public long getSpherePeriod() {
		return spherePeriod;
	}

	public void setSpherePeriod(long spherePeriod) {
		this.spherePeriod = spherePeriod;
	}

	public void setPhantasm(NoblePhantasm phantasm) {
		this.phantasm = phantasm;
	}

	public ArrayList<Obstacle> getListOfObstacles() {
		return obstacleConfiguration.getListOfObstacles();
	}

	public void setListOfObstacles(ArrayList<Obstacle> list) {
		obstacleConfiguration.setListOfObstacles(list);
	}

	public ObstacleConfiguration getObstacleConfiguration() {
		return obstacleConfiguration;
	}
	public NoblePhantasm getPhantasm() {
		return phantasm;
	}

	public EnchantedSphere getBall() {
		return ball;
	}
}