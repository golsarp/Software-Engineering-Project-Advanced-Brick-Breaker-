package ui.runningmode;

import java.awt.Color;

import javax.swing.JFrame;

import constants.GameConstants;
import domain.controller.NewGameHandler;

public class RunningModeFrame extends JFrame {
	//OVERVIEW: RunningModeFrame serves as a frame for the game's Running Mode. It includes RunningModePanel
	
	public RunningModeFrame() {
		
		NewGameHandler.getInstance().initializeNewGame();
		JFrame rmf = new JFrame();
		rmf.setSize(GameConstants.RUNNING_MODE_FRAME_WIDTH,GameConstants.RUNNING_MODE_FRAME_HEIGHT);
		rmf.setVisible(true);
		rmf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rmf.setLocationRelativeTo(null);
		rmf.setBackground(Color.BLACK);
		
		RunningModePanel runningModePanel = new RunningModePanel();
		rmf.add(runningModePanel);
		rmf.setResizable(false);
	}
}


