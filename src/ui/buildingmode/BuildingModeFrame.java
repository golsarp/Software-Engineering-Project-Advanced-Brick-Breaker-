package ui.buildingmode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import constants.GameConstants;
import domain.controller.NewGameHandler;
import domain.listeners.ValidityListener;
import ui.messages.ExcessObstacleError;
import ui.messages.MinRequirementError;
import ui.runningmode.RunningModeFrame;

public class BuildingModeFrame extends JFrame implements ValidityListener {
	//OVERVIEW: BuildingModeFrame serves as a frame for the game's Building Mode. It includes two panels.
	//			editingPanel: panel for displaying and changing the positions of the obstacles
	//			inputPanel: panel for taking user inputs regarding the number of obstacles
	
	private BuildingModeMainPanel editingPanel;
	private EditorPanel inputPanel;

	public BuildingModeFrame() {

		editingPanel = new BuildingModeMainPanel();
		inputPanel = new EditorPanel();
		inputPanel.setPreferredSize(
				new Dimension(GameConstants.GROUP_PANEL_WIDTH, GameConstants.BUILDING_MODE_FRAME_HEIGHT));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(inputPanel, BorderLayout.EAST);
		this.add(editingPanel, BorderLayout.CENTER);

		this.setVisible(true);
		this.setSize(GameConstants.BUILDING_MODE_FRAME_WIDTH, GameConstants.BUILDING_MODE_FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.BLACK);

		setButtons();
	}

	private void setButtons() {
		
		JButton startButton = new JButton("Start New Game");
		this.add(startButton, BorderLayout.SOUTH);
		startButton.addActionListener(e -> {

			// check the user input of obstacle numbers for building mode requirements
			boolean passAll = NewGameHandler.getInstance().checkRequirements(ObstacleInputPanel.simple_num,
					ObstacleInputPanel.firm_num, ObstacleInputPanel.explosive_num, ObstacleInputPanel.gift_num);

			// if it passes all the requirements, go to running mode
			if (passAll) {
				this.remove(inputPanel);
				this.dispose();
				RunningModeFrame rmf = new RunningModeFrame();

			}
		});
	}

	@Override
	public void onValidityEvent(String message) {
		// TODO Auto-generated method stub

		if (message.equals("Minimum requirements not satisfied.")) {
			MinRequirementError error = new MinRequirementError();
			error.showMessage();
		} else if (message.equals("Maximum number of obstacles is 120.")) {
			ExcessObstacleError error = new ExcessObstacleError();
			error.showMessage();
		}
	}

}
