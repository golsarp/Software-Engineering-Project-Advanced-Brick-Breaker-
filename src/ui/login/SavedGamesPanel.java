package ui.login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.GameConstants;
import domain.controller.LoadSaveHandler;
import ui.runningmode.RunningModeFrame;

public class SavedGamesPanel extends JPanel implements ActionListener {
	// OVERVIEW: SavedGamesPanel is a panel displaying the saved games of the player
	// 			It allows the player to choose amongst these saves and redirects the player to Running Mode

	private JComboBox<String> combo;
	private JButton button;
	private JLabel header;
	private JFrame frame;
	private File[] fileList;
	private String[] nameList;

	public SavedGamesPanel() {

		setComponents();
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

	private void setComponents() {
		// initialize the lists
		fileList = LoadSaveHandler.getInstance().gameList();
		nameList = LoadSaveHandler.getInstance().stringList(fileList);

		// initialize the button
		button = new JButton("Load Saved Game");
		button.addActionListener(this);

		// initialize the label
		header = new JLabel("Choose a saved game to continue playing");

		// initialize the combo box
		combo = new JComboBox<String>(nameList);
		combo.setPreferredSize(new Dimension(200, 30));
	}

	private void organizeLayout() {
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(100)
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(header).addComponent(combo,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
				.addGap(100)

		);
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(100).addComponent(header)
				.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(button));
		this.setLayout(layout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == button) {

			try {
				LoadSaveHandler.getInstance().loadGame(fileList[combo.getSelectedIndex()].getCanonicalPath());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			frame.dispose();
			RunningModeFrame rmf = new RunningModeFrame();
		}
	}

}
