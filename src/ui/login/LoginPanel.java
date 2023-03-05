package ui.login;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;
import domain.controller.LoadSaveHandler;
import domain.helpers.FileAccessor;
import domain.controller.LoadSaveHandler;
import domain.objects.obstacle.ObstacleConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

public class LoginPanel extends JPanel implements ActionListener {
	// OVERVIEW: LoginPanel is a panel shown at the beginning of the game, serves as
	// 			  a login interface

	private JTextField f1, f2;
	private JButton loginButton, registerButton;
	private JLabel usernameLabel, passwordLabel;
	private JFrame frame;

	public LoginPanel() {

		setButtons();
		setTextAndLabels();
		organizeLayout();

		// create a frame, set its content pane
		frame = new JFrame("Login Screen");
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
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(usernameLabel)
						.addComponent(passwordLabel).addComponent(loginButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(f1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(f2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(registerButton))
				.addGap(100)

		);
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(100)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(usernameLabel)
						.addGap(25).addComponent(f1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordLabel)
						.addGap(25).addComponent(f2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(loginButton).addGap(25)
						.addComponent(registerButton)));
		this.setLayout(layout);
	}

	private void setTextAndLabels() {
		f1 = new JTextField();
		f1.setPreferredSize(new Dimension(120, 30));
		f2 = new JPasswordField();
		f2.setPreferredSize(new Dimension(120, 30));

		// initialize the labels
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
	}

	private void setButtons() {
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);

		registerButton = new JButton("Register");
		registerButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String username = f1.getText();
		String password = f2.getText();
		String userFound;
		String isRegistered;
		if (e.getSource() == loginButton) {
			userFound = LoadSaveHandler.getInstance().isAuthenticated(username, password);
			if (userFound.equals("invalid")) {

				String message = "Invalid Character in Username or Password\n" + "Invalid Characters: "
						+ ". # % & { } < > * ? / $ ! : @ | = ` ' \\ \" ";

				JOptionPane.showMessageDialog(null, message, "", JOptionPane.DEFAULT_OPTION);

			}

			else if (userFound.equals("Login Successfull")) {
				frame.dispose();
				GameOptionPanel gop = new GameOptionPanel();
			}

			else {
				JOptionPane.showMessageDialog(null, userFound, "", JOptionPane.DEFAULT_OPTION);
			}

		}

		if (e.getSource() == registerButton) {

			isRegistered = LoadSaveHandler.getInstance().isRegistered(username, password);
			if (isRegistered.equals("invalid")) {

				JOptionPane.showMessageDialog(null, "Invalid Character in Username or Password"  
						+ "\nPassword should contain at least one capital letter"
						+ "\nInvalid Characters: "
						+ ". # % & { } < > * ? / $ ! : @ | = ` ' \\ \" ", "",
						JOptionPane.DEFAULT_OPTION);

			}

			else if (isRegistered.equals("RegisterSuccessfull")) {
				frame.dispose();
				GameOptionPanel gop = new GameOptionPanel();

			} else {

				JOptionPane.showMessageDialog(null, "User Already Exists", "", JOptionPane.DEFAULT_OPTION);

			}
		}
	}
}
