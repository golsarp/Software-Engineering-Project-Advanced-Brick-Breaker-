package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;
import domain.controller.NewGameHandler;
import ui.buildingmode.BuildingModeFrame;

public class GameOptionPanel extends JPanel implements ActionListener {
	//OVERVIEW: GameOptionPanel is a panel shown after the player logs in
	//			It displays two options: starting a new game or loading a previous save
	
	private JButton newGameButton;
	private JButton loadGameButton;
	private JLabel header;
	private JFrame frame;

	public GameOptionPanel() {

		header = new JLabel("Choose a game option to continue");
		setButtons();
		organizeLayout();

		// create a frame, set its content pane
		frame = new JFrame();
		frame.setContentPane(this);
		frame.setSize(GameConstants.LOGIN_FRAME_WIDTH, GameConstants.LOGIN_FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private void organizeLayout() {
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(100)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(header)
						.addComponent(newGameButton).addComponent(loadGameButton))
				.addGap(100)

		);
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(100).addComponent(header)
				.addComponent(newGameButton).addComponent(loadGameButton));
		this.setLayout(layout);
	}

	private void setButtons() {
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(this);

		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(this);
		
		if (PlayerMaster.getInstance().isNewPlayer()) {
			loadGameButton.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == newGameButton) {
			frame.dispose();
			BuildingModeFrame gframe = new BuildingModeFrame();
			NewGameHandler.getInstance().initializeRequirements();
			NewGameHandler.getInstance().subscribeToStrategies(gframe);

		}
		if (e.getSource() == loadGameButton) {
			frame.dispose();
			SavedGamesPanel panel = new SavedGamesPanel();

		}

	}

}
