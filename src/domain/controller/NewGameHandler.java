package domain.controller;

import domain.actors.player.PlayerMaster;
import domain.objects.phantasm.NoblePhantasm;
import domain.objects.sphere.EnchantedSphere;
import domain.validity.BuildingModeRequirement;
import ui.buildingmode.BuildingModeFrame;

public class NewGameHandler {
	//OVERVIEW: NewGameHandler is the class responsible for operations regarding starting a new game functionality
	//			Receives messages from ui, delivers to GameController and PlayerHandler
	
	private static NewGameHandler instance;

	private NoblePhantasm phantasm;
	private EnchantedSphere ball;

	private NewGameHandler() {
	}

	public static NewGameHandler getInstance() {

		if (instance == null) {
			instance = new NewGameHandler();
		}
		return instance;
	}

	public void initializeNewGame() {
		GameController.getInstance().initializeGameObjects();

		if (PlayerMaster.getInstance().getLives() == 0) {
			PlayerMaster.getInstance().initializeScoreAndLives();
		}
	}

	public void initializeRequirements() {
		GameController.getInstance().initializeRequirements();
	}

	public boolean checkRequirements(int s, int f, int e, int g) {
		return GameController.getInstance().checkRequirements(s, f, e, g);
	}

	public void subscribeToStrategies(BuildingModeFrame gameFrame) {
		//EFFECTS:  subscribes the game frame object to all of the building mode requirements'
		// 			strategy objects
		for (BuildingModeRequirement r : GameController.getInstance().getRequirements()) {
			r.getStrategy().addValidityListener(gameFrame);

		}
	}

	public NoblePhantasm getPhantasm() {
		return phantasm;
	}

	public EnchantedSphere getBall() {
		return ball;

	}

}
