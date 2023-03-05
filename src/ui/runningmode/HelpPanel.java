package ui.runningmode;

import javax.swing.JOptionPane;

public class HelpPanel {
	//OVERVIEW: HelpPanel is a panel shown when the Player presses the Help button
	//			It displays main rules of the game, as well as instructions on how to play

	private static String message = "Welcome to the Need For Spear game. In this game you are controlling pad called noble phantasm. Noble phantasm can reflect the ball shaped object called enchanted sphere.\n"
			+ "This is way of keeping the enchanted sphere in area. If the enchanted sphere gets drop down you will lose one of your lives. You have 3 lives,\n"
			+ "if you lose all your lives game will end and your score will show up. There are many obstacles in the game area. If an obstacle gets destroyed your score will increase.\n"
			+ "\n" + "Types of obstacles\n"
			// + "\n"
			+ "<html><font color=#FFFF00>Simple obstacle:</font> Yellow colored. Can be broken in one hit.\n"
			// + "\n"
			+ "<html><font color=#FF0000>Firm obstacle:</font> Red colored. Each one contains a number written on it, which corresponds to the number of hits it requires to be destroyed.\n"
			// + "\n"
			+ "<html><font color=#0000FF>Explosive obstacle:</font> Blue colored and circular. Explodes once it is hit. Once exploded, its remains fall downwards towards the noble phantasm.\n"
			+ "If the remains touch the noble phantasm, the player loses a chance.\n"
			// + "\n"
			+ "<html><font color=#00FF00>Gift Obstacle:</font> Green colored. Once destroyed, it drops a box downwards towards the noble phantasm. If the noble phantasm touches the box,\n"
			+ "then the box opens and rewards you with a magical ability that can be used to support you.\n" + "\n"
			+ "Magical Abilities\n"
			// + "\n"
			+ "<html><font color=#00FFFF>Chance Giving Ability:</font> Increases the player’s chances by 1.\n"
			+ "<html><font color=#00FFFF>Noble Phantasm Expansion:</font> Doubles the length of the noble phantasm. Pressing the button T, or pressing its icon on the screen activates this ability.\n"
			+ "Once activated, it lasts for only 30 seconds\n"
			+ "<html><font color=#00FFFF>Magical Hex:</font> Equips the noble phantasm with two magical canons on both of its ends. They can fire magical hexes that can hit the obstacles.\n"
			+ "Pressing H or pressing its icon on the screen activates this ability. Once activated it remains active for only 30 seconds.\n"
			+ "<html><font color=#00FFFF>Unstoppable Enchanted Sphere:</font> Upgrades the enchanted sphere and makes it much more powerful, such that if it hits any obstacles,\n"
			+ "it destroys it and passes through it regardless of its type. Pressing U or pressing its icon on the screen activates this ability. This upgrade only lasts 30 seconds after it is activated.\n"
			+ "\n"
			+ "You are also playing againts Ymir, the great sorcerer. Ymir will decide to use his one of magical abilites or not every 30 seconds.\n"
			+ "\n" + "Ymir's Magical Abilities\n"
			+ "<html><font color=#800080>Infinite Void:</font> Makes randomly chosen obstacles frozen for 15 seconds. While they are frozen they cannot be destroyed. Only way to destroy them is using unstoppale enchanted sphere ability.\n"
			+ "<html><font color=#800080>Double Accel:</font> Reduces the speed of the enchanted sphere by half. This ability lasts for 15 seconds\n"
			+ "<html><font color=#800080>Hollow Purple:</font> Adds 8 hollow purple obstacles in random places. Hollow obstacle colored purple. Destroying hollow obstacle does not increase your score.\n"
			+ "\n" + "Noble Phantasm Controls\n"
			+ "<html><font color=#FFA500>Left arrow key:</font> moves noble phantasm to the left.\n"
			+ "<html><font color=#FFA500>Right arrow key:</font> moves noble phantasm to the right.\n"
			+ "<html><font color=#FFA500>A key:</font> rotates the noble phantasm to the left.\n"
			+ "<html><font color=#FFA500>D key:</font> rotates the noble phantasm to the right.\n"
			+ "Press W to start the game.\n";

	public static void showHelpMessage() {
		JOptionPane.showMessageDialog(null, message, "How to Play", JOptionPane.INFORMATION_MESSAGE);
	}
}
