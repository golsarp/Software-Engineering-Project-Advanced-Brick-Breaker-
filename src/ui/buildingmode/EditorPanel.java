package ui.buildingmode;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import constants.GameConstants;

public class EditorPanel extends JPanel{
	//OVERVIEW: EditorPanel is a panel for taking user inputs regarding the number of obstacles. It includes two panels
	//			obstacleInputPanel: panel for taking user input on the number of obstacles
	//			obstacleTrashPanel: panel for removing the obstacles by mouse drags within its borders
	
	private ObstacleInputPanel obstacleInputPanel;
	private ObstacleTrashPanel obstacleTrashPanel;
	
	public EditorPanel() {
		obstacleInputPanel = new ObstacleInputPanel();
		obstacleTrashPanel = new ObstacleTrashPanel();
		
		obstacleInputPanel.setPreferredSize(new Dimension(GameConstants.GROUP_PANEL_WIDTH, GameConstants.BUILDING_MODE_FRAME_HEIGHT/2));	
		obstacleTrashPanel.setPreferredSize(new Dimension(GameConstants.GROUP_PANEL_WIDTH, GameConstants.BUILDING_MODE_FRAME_HEIGHT/2));	
		
		this.setPreferredSize(new Dimension(GameConstants.GROUP_PANEL_WIDTH, 1));
		this.setLayout(new BorderLayout());
		this.add(obstacleInputPanel, BorderLayout.NORTH);
		this.add(obstacleTrashPanel, BorderLayout.EAST);
		
	}
}
