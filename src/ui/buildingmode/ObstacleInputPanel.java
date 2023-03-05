package ui.buildingmode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.GameConstants;
import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.ObstacleFactory;
import domain.objects.obstacle.types.Obstacle;
import domain.validity.InputValidity;

public class ObstacleInputPanel extends JPanel implements ActionListener {
	//OVERVIEW: ObstacleInputPanel is a panel for taking user input on the number of obstacles
	//			It also allows the addition of a single obstacle of choice to the game
	
	private JButton simpleButton;
	private JButton firmButton;
	private JButton explosiveButton;
	private JButton giftButton;
	private JLabel addObstacle1;
	private JLabel addObstacle2;
	private JLabel label1 = new JLabel("Simple:");
	private JButton button = new JButton("Apply");
	private JTextField field1 = new JTextField(16);
	private JLabel label2 = new JLabel("Gift:");
	private JTextField field2 = new JTextField(16);
	private JLabel label3 = new JLabel("Explosive:");
	private JTextField field3 = new JTextField(16);
	private JLabel label4 = new JLabel("Firm:");
	private JTextField field4 = new JTextField(16);
	public static int simple_num = 0;
	public static int firm_num = 0;
	public static int gift_num = 0;
	public static int explosive_num = 0;
	private InputValidity inputValidity;
	private ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();

	public ObstacleInputPanel() {
		inputValidity = new InputValidity();
	
		setButtons();
		setLabels();
		organizeLayout();
		this.setBorder(BorderFactory.createTitledBorder("Number of Obstacles"));
		this.setBackground(Color.LIGHT_GRAY);

	}

	private void setLabels() {
		addObstacle1 = new JLabel("ADD SINGLE");
		addObstacle2 = new JLabel("OBSTACLE");
		addObstacle1.setFont(new Font("Arial", Font.BOLD, 12));
		addObstacle2.setFont(new Font("Arial", Font.BOLD, 12));
	}

	private void setButtons() {
		simpleButton = new JButton("Simple");
		simpleButton.addActionListener(this);
		firmButton = new JButton("Firm");
		firmButton.addActionListener(this);
		explosiveButton = new JButton("Explosive");
		explosiveButton.addActionListener(this);
		explosiveButton.setPreferredSize(new Dimension(90, 25));
		giftButton = new JButton("Gift");
		giftButton.addActionListener(this);
		button.addActionListener(this);
	}

	private void organizeLayout() {
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(label1)
						.addComponent(label2).addComponent(label3)

						.addComponent(label4).addComponent(button).addComponent(addObstacle1).addComponent(simpleButton)
						.addComponent(explosiveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(field1)
						.addComponent(field2).addComponent(field3).addComponent(field4).addComponent(addObstacle2)
						.addComponent(firmButton).addComponent(giftButton)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1)
						.addComponent(field1))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2)
						.addComponent(field2))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label3)
						.addComponent(field3))
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label4).addComponent(field4))
				.addComponent(button).addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addObstacle1)
						.addComponent(addObstacle2))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(simpleButton)
						.addComponent(firmButton))
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(explosiveButton,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(giftButton))

		);
		this.setLayout(layout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			String s1 = field1.getText();
			String s2 = field2.getText();
			String s3 = field3.getText();
			String s4 = field4.getText();

			if (!inputValidity.isValidObstacleNumber(s1)) {
				this.simple_num = 0;
			} else {
				this.simple_num = Integer.parseInt(s1);
			}

			if (!inputValidity.isValidObstacleNumber(s2)) {
				this.gift_num = 0;
			} else {
				this.gift_num = Integer.parseInt(s2);
			}

			if (!inputValidity.isValidObstacleNumber(s3)) {
				this.explosive_num = 0;
			} else {
				this.explosive_num = Integer.parseInt(s3);
			}

			if (!inputValidity.isValidObstacleNumber(s4)) {
				this.firm_num = 0;
			} else {
				this.firm_num = Integer.parseInt(s4);
			}
			int sum = this.simple_num + this.gift_num +this.explosive_num +this.firm_num;
			
			if (sum > GameConstants.MAX_NUMBER_OF_OBSTACLES) {
				this.simple_num = 0; this.gift_num = 0; this.explosive_num = 0; this.firm_num = 0;
			}

			obstacleConfiguration.initializeObstacleConfiguration(simple_num, firm_num, explosive_num, gift_num);

		}

		if (e.getSource() == simpleButton) {
			int positionIndex = obstacleConfiguration.getRandomPosition();
			// while it's occupied, keep looking for an empty position
			while (positionIndex == -1) {
				positionIndex = obstacleConfiguration.getRandomPosition();
			}

			Obstacle o = ObstacleFactory.getInstance().getSimpleObstacle(
					obstacleConfiguration.getPositions()[positionIndex][0],
					obstacleConfiguration.getPositions()[positionIndex][1]);

			obstacleConfiguration.getListOfObstacles().add(o);
			obstacleConfiguration.getPositions()[positionIndex][0] = -1;
			obstacleConfiguration.getPositions()[positionIndex][1] = -1;
		} 
		if (e.getSource() == firmButton) {
			int positionIndex = obstacleConfiguration.getRandomPosition();
			// while it's occupied, keep looking for an empty position
			while (positionIndex == -1) {
				positionIndex = obstacleConfiguration.getRandomPosition();
			}

			Obstacle o = ObstacleFactory.getInstance().getFirmObstacle(
					obstacleConfiguration.getPositions()[positionIndex][0],
					obstacleConfiguration.getPositions()[positionIndex][1]);

			obstacleConfiguration.getListOfObstacles().add(o);
			obstacleConfiguration.getPositions()[positionIndex][0] = -1;
			obstacleConfiguration.getPositions()[positionIndex][1] = -1;
		} 
		if (e.getSource() == explosiveButton) {
			int positionIndex = obstacleConfiguration.getRandomPosition();
			// while it's occupied, keep looking for an empty position
			while (positionIndex == -1) {
				positionIndex = obstacleConfiguration.getRandomPosition();
			}
			
			Obstacle o = ObstacleFactory.getInstance().getExplosiveObstacle(
					obstacleConfiguration.getListOfObstacles(),
					obstacleConfiguration.getPositions()[positionIndex][0],
					obstacleConfiguration.getPositions()[positionIndex][1]);

			obstacleConfiguration.getListOfObstacles().add(o);
			obstacleConfiguration.getPositions()[positionIndex][0] = -1;
			obstacleConfiguration.getPositions()[positionIndex][1] = -1;
			
		} 
		if (e.getSource() == giftButton) {
			int positionIndex = obstacleConfiguration.getRandomPosition();
			// while it's occupied, keep looking for an empty position
			while (positionIndex == -1) {
				positionIndex = obstacleConfiguration.getRandomPosition();
			}

			Obstacle o = ObstacleFactory.getInstance().getGiftObstacle(
					obstacleConfiguration.getPositions()[positionIndex][0],
					obstacleConfiguration.getPositions()[positionIndex][1]);

			obstacleConfiguration.getListOfObstacles().add(o);
			obstacleConfiguration.getPositions()[positionIndex][0] = -1;
			obstacleConfiguration.getPositions()[positionIndex][1] = -1;
		}
	

	}

	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}