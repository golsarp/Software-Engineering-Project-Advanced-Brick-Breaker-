package ui.runningmode;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;

public class GameEndPanel extends JPanel {
	//OVERVIEW: GameEndPanel is a panel shown when the game ends
	//			It displays the ending reason and the score of the player
	
	private JFrame frame;
	private JLabel scoreLabel;
	private JLabel endLabel;
	private ScoreBoard scoreBoard;
	
	public GameEndPanel(String success) {
		scoreBoard = new ScoreBoard();
		setLabels(success);
		organizeLayout();
		frame = new JFrame("Game Over");
		frame.setContentPane(this);
		frame.setSize(GameConstants.GAME_OVER_LENGTH, GameConstants.GAME_OVER_WIDTH);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);

	}
	private void setLabels(String s) {
		if(s.equalsIgnoreCase("f")) {
			endLabel = new JLabel("NO LIVES LEFT");
			endLabel.setFont(new Font("Verdana",1,20));
			
		}else {	
			endLabel = new JLabel("YOU FINISHED THE GAME");
			endLabel.setFont(new Font("Verdana",Font.BOLD,18));
		}
		scoreLabel = new JLabel("Score: " + PlayerMaster.getInstance().getScore());
		scoreLabel.setFont(new Font("Verdana",Font.BOLD,15));
	}
	public void organizeLayout() {
		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(110)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGap(100)
						.addComponent(endLabel)
						.addComponent(scoreLabel)
						.addComponent(scoreBoard)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(80)
				.addComponent(endLabel)
				.addComponent(scoreLabel)
				.addGap(20)
				.addComponent(scoreBoard)
		);
		
		this.setLayout(layout);
	}
	
	
	
}
