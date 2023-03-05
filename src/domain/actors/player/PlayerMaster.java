package domain.actors.player;

import java.util.ArrayList;

import domain.actors.player.ability.GameObject;
import domain.actors.player.ability.MagicalHex;
import domain.actors.player.ability.PhantasmExpansion;
import domain.actors.player.ability.UnstoppableSphere;
import domain.controller.GameController;
import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.NoblePhantasm;

public class PlayerMaster {
	//OVERVIEW: PlayerHandler is the class responsible for operations regarding the player
	
	private static PlayerMaster instance;
	private Player player;
	private boolean newPlayer;
	private static final Integer CHANCE_GIVING = 0;
	private static final Integer MAGICAL_HEX = 1;
	private static final Integer PHANTASM_EXPENSION = 2;
	private static final Integer UNSTOPPABLE_SPHERE = 3;
	private GameObject obj;

	private PlayerMaster() {}
	
	public static PlayerMaster getInstance() {
        if (instance == null) {
        	instance = new PlayerMaster();
        }
        return instance;
    }

	public void initializePlayer(String username, String password) {
		Player p = new Player();
		p.setUsername(username);
		p.setPassword(password);
		this.player = p;
	}
	
	public void initializeScoreAndLives() {
		player.setLives(3);
		player.setScore(0);
	}
	
	public void decreaseLife() {
		setLives(getLives() - 1);
	}
	
	public void increaseLife() {
		setLives(getLives() + 1);
	}
	
	public void increaseScore() {
		GameController.getInstance().getGameTime().stopTime();
		int additionalTime = GameController.getInstance().getGameTime().measureBetween();
		int totalAdditionalTime = GameController.getInstance().getGameTime().getCurrentTime() + additionalTime;
		int additionalScore = calculateScore(totalAdditionalTime);
		setScore(getScore() + additionalScore);
	}
	
	public int calculateScore(int time) {
		// REQUIRES: time >= 0
		// EFFECTS: If time is 0 returns 300 else returns score such that score = 300 / time.
		int score;
		if(time == 0) {
			score = 300;
		} else {
			score = 300 / time;
		}
		return score;
	}

	public void addAbility(int abilityNo) {
		//EFFECTS: Adds a gained ability to the player's current abilities list
		//MODIFIES: currentAbilities
		String abilityName = "Chance Giving";
		if (abilityNo == MAGICAL_HEX) {
			abilityName = "Magical Hex";
		} else if (abilityNo == PHANTASM_EXPENSION) {
			abilityName = "Phantasm Expension";
		} else if (abilityNo == UNSTOPPABLE_SPHERE) {
			abilityName = "Unstoppable Sphere";
		}
		getCurrentAbilities().add(abilityName);
	}
	
	public void loadAbility(String ability) {
		getCurrentAbilities().add(ability);
	}
	
	public boolean activateAbility(int abilityNo) {
		//EFFECTS: If the player has the specified ability in his/ her current abilities,
		//			activates the specified ability and removes the used ability from player's current abilities, returns true
		//		   Else returns false
		//MODIFIES: currentAbilities, game object that is enhanced depending on the type of ability
		if (abilityNo == MAGICAL_HEX) {
			if (getCurrentAbilities().contains("Magical Hex")) {
				 obj = GameController.getInstance().getPhantasm();
				NoblePhantasm nb = GameController.getInstance().getPhantasm();
				GameController.getInstance().initializeCanon();
				Cannon cannon = GameController.getInstance().getCannon();
				obj = new MagicalHex(obj, cannon, nb);
				GameController.getInstance().initializeCannonBullets(obj, cannon.getCanonBulletList());
				player.setHexActive(true);
				getCurrentAbilities().remove("Magical Hex");
				return true;
			}

		} else if (abilityNo == PHANTASM_EXPENSION) {
			if(getCurrentAbilities().contains("Phantasm Expension")){
				GameObject obj = GameController.getInstance().getPhantasm();
				obj = new PhantasmExpansion(obj);
				obj.enhance();
				player.setExpansionActive(true);
				getCurrentAbilities().remove("Phantasm Expension");
				return true;
			}

		} else if (abilityNo == UNSTOPPABLE_SPHERE) {
			if (getCurrentAbilities().contains("Unstoppable Sphere")) {
				GameObject o = GameController.getInstance().getBall();
				o = new UnstoppableSphere(o);
				o.enhance();
				player.setUnstoppableActive(true);
				getCurrentAbilities().remove("Unstoppable Sphere");
				return true;
			}
		}
		return false;

	}
	
	//getters and setters
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<String> getCurrentAbilities() {
		return player.getCurrentAbilities();
	}
	
	public void removeAbility(String ability) {
		player.getCurrentAbilities().remove(ability);
	}
	
	public boolean isNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(boolean newPlayer) {
		this.newPlayer = newPlayer;
	}
	
	public int getLives() {
		return player.getLives();
	}
	
	public void setLives(int lives) {
		player.setLives(lives);
	}
	
	public int getScore() {
		return player.getScore();
	}
	
	public void setScore(int score) {
		player.setScore(score);
	}
	
	public GameObject getObj() {
		return obj;
	}

	public void setObj(GameObject obj) {
		this.obj = obj;
	}
	
	
	
    	
}

