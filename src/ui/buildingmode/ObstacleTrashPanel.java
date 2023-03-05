package ui.buildingmode;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObstacleTrashPanel extends JPanel{
	//OVERVIEW: ObstacleTrashPanel is a panel for specifying the trash zone
	//			When an obstacle is dragged within this region, it will be removed from the game
	
	private JLabel trashLabel;
	
	public ObstacleTrashPanel() {
		
		trashLabel = new JLabel("DRAG HERE TO REMOVE", JLabel.CENTER);
		trashLabel.setFont(new Font("Arial",Font.BOLD, 12));
		trashLabel.setForeground(Color.WHITE);
	    this.setBorder(BorderFactory.createLineBorder(Color.white));
	    this.add(trashLabel);
	    this.setBackground(Color.DARK_GRAY);
	    
	}
}