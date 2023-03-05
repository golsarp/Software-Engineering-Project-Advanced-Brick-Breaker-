package ui.messages;

import javax.swing.JOptionPane;
import constants.GameConstants;

public class ExcessObstacleError {

	String message  = "Max Number of Obstacles is " + GameConstants.MAX_NUMBER_OF_OBSTACLES;
	
	public void showMessage() {
		JOptionPane.showMessageDialog(null, message, "Obstacle number exceed error", JOptionPane.WARNING_MESSAGE);
	}
	
}
