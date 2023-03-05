package domain.controller;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import domain.actors.player.ability.GameObject;
import domain.actors.ymir.YmirMaster;
import domain.game.Game;
import domain.helpers.GameTime;
import domain.listeners.YmirListener;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.types.Obstacle;
import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.CannonBulletList;
import domain.objects.phantasm.NoblePhantasm;
import domain.objects.sphere.EnchantedSphere;

import domain.validity.BuildingModeRequirement;

public class GameController {
	// OVERVIEW: GameController is the class responsible for handling game related events
	//			Receives messages from ui, delivers to Game

	private static GameController instance;
	private Game game;
	private YmirMaster ymirMaster;

	private GameController() {
		ymirMaster = new YmirMaster();
		game = new Game(ymirMaster);

	}

	public static GameController getInstance() {

		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	public void initializeGameTime() {
		game.initializeGameTime();
	}

	public void initializeAllTasks() {
		game.initializeAllTasks();
	}

	public void initializeCanon() {
		game.initializeCanon();
	}

	public void AddBulletTask(GameObject object) {
		game.AddBulletTask(object);
	}

	public void initializeCannonBullets(GameObject object, CannonBulletList list) {
		game.initializeCannonBullets(object, list);
	}

	public void initializeGameObjects() {
		game.initializeGameObjects();
	}

	public void initializeTimerTaskSphere() {
		game.initializeTimerTaskSphere();
	}

	public void initializeTimerTaskExplosiveParticle() {
		game.initializeTimerTaskExplosiveParticle();
	}

	public void initializeTimerTaskObstacleMovement() {
		game.initializeTimerTaskObstacleMovement();
	}

	public void initializeTimerTaskGiftBox() {
		game.initializeTimerTaskGiftBox();
	}

	public void initializeRequirements() {
		game.initializeRequirements();

	}

	public NoblePhantasm getPhantasm() {
		return game.getPhantasm();
	}

	public EnchantedSphere getBall() {
		return game.getBall();
	}

	public boolean checkRequirements(int s, int f, int e, int g) {
		return game.checkRequirements(s, f, e, g);
	}

	public void cancelAllTasks() {
		game.cancelAllTasks();
	}

	public void changeSphereSpeed(long newSpherePeriod) {
		game.changeSphereSpeed(newSpherePeriod);
	}

	public void sphereObstacleCollision(Obstacle ob, boolean ball_inter_obj) {
		game.sphereObstacleCollision(ob, ball_inter_obj);
	}

	public void terminateInfiniteVoid() {
		ymirMaster.setInfiniteVoidWasActive(false);
	}

	public void addYmirListener(YmirListener lis) {
		ymirMaster.addYmirListener(lis);
	}

	// getters and setters
	public Timer getTimer() {
		return game.getTimer();
	}

	public void setTimer(Timer timer) {
		game.setTimer(timer);
	}

	public TimerTask getTimerTask() {
		return game.getTimerTask();
	}

	public void setTimerTask(TimerTask timerTask) {
		game.setTimerTask(timerTask);
	}

	public GameTime getGameTime() {
		return game.getGameTime();
	}

	public Cannon getCannon() {
		return game.getCannon();
	}

	public ArrayList<BuildingModeRequirement> getRequirements() {
		return game.getRequirements();
	}

	public void setRequirements(ArrayList<BuildingModeRequirement> requirements) {
		game.setRequirements(requirements);
	}

	public long getSpherePeriod() {
		return game.getSpherePeriod();
	}

	public void setSpherePeriod(long spherePeriod) {
		game.setSpherePeriod(spherePeriod);
	}

	public void setPhantasm(NoblePhantasm phantasm) {
		game.setPhantasm(phantasm);
	}

	public ArrayList<Obstacle> getListOfObstacles() {
		return game.getListOfObstacles();
	}

	public void setListOfObstacles(ArrayList<Obstacle> list) {
		game.setListOfObstacles(list);
	}

	public ObstacleConfiguration getObstacleConfiguration() {
		return game.getObstacleConfiguration();
	}

	public void scoreListUpdated(int new_score) {
		game.scoreListUpdated(new_score);
	}	

}