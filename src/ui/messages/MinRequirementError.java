package ui.messages;

import javax.swing.JOptionPane;
import constants.GameConstants;
import domain.validity.ExcessObstacles;

public class MinRequirementError {
	
	String message  = "Minimum Requirements : \n"
			+ "Simple Obstacle : "+GameConstants.MIN_SIMPLE_OBS_NUM+" \n"
			+ "Firm Obstacle : "+GameConstants.MIN_FIRM_OBS_NUM+"\n"
			+ "Explosive Obstacle : " +GameConstants.MIN_EXP_OBS_NUM+" \n"
			+ "Gift Obstacle : "+GameConstants.MIN_GIFT_OBS_NUM+"\n"
			+ "Max Obstacle Number : "+GameConstants.MAX_NUMBER_OF_OBSTACLES +"\n";
	
	public void showMessage() {
		JOptionPane.showMessageDialog(null, message, "Minimum requirements not satisfied", JOptionPane.WARNING_MESSAGE);
	}
}
