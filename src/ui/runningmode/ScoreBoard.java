package ui.runningmode;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import domain.controller.LoadSaveHandler;

public class ScoreBoard extends JPanel{
	private JLabel user1;
	private JLabel user2;
	private JLabel user3;
	private JLabel user4;
	private JLabel user5;
	private JLabel score1;
	private JLabel score2;
	private JLabel score3;
	private JLabel score4;
	private JLabel score5;
	private String[] users = LoadSaveHandler.getInstance().getTop_five_users();
	private int[] scores = LoadSaveHandler.getInstance().getTop_five_scores();
	
	public ScoreBoard() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Score Board"));
		setLabels();
		organizeLayout();
	}

	private void organizeLayout() {
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(user1)
						.addComponent(user2)
						.addComponent(user3)
						.addComponent(user4)
						.addComponent(user5))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(score1)
						.addComponent(score2)
						.addComponent(score3)
						.addComponent(score4)
						.addComponent(score5))

		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(user1)
						.addComponent(score1))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(user2)
						.addComponent(score2))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(user3)
						.addComponent(score3))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(user4)
						.addComponent(score4))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(user5)
						.addComponent(score5)));
		this.setLayout(layout);
		
	}

	private void setLabels() {
		user1 = new JLabel();
		user2 = new JLabel();
		user3 = new JLabel();
		user4 = new JLabel();
		user5 = new JLabel();

		score1 = new JLabel();
		score2 = new JLabel();
		score3 = new JLabel();
		score4 = new JLabel();
		score5 = new JLabel();
		
		user1.setText(users[0]);
		user2.setText(users[1]);
		user3.setText(users[2]);
		user4.setText(users[3]);
		user5.setText(users[4]);
		
		score1.setText(Integer.toString(scores[0]));
		score2.setText(Integer.toString(scores[1]));
		score3.setText(Integer.toString(scores[2]));
		score4.setText(Integer.toString(scores[3]));
		score5.setText(Integer.toString(scores[4]));
		
	}
	

}
