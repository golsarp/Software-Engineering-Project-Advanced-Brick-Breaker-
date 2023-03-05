package ui.runningmode;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.actors.player.PlayerMaster;
import domain.actors.player.ability.GameObject;
import domain.actors.ymir.ability.YmirAbility;
import domain.controller.GameController;
import domain.controller.LoadSaveHandler;
import domain.listeners.PlayerLivesListener;
import domain.listeners.ScoreListener;
import domain.listeners.YmirListener;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.particles.ExplosiveParticle;
import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.NoblePhantasm;

public class ButtonPanel extends JPanel implements ActionListener, ScoreListener, PlayerLivesListener, YmirListener, KeyListener{
	//OVERVIEW: ButtonPanel serves as the Menu panel for Running Mode
	//			It includes menu buttons, ability icons and shows Ymir's activated ability
	
	protected JButton pauseButton;
	private JButton resumeButton;
	protected JButton saveButton;
	protected JButton helpButton;
	protected JButton magicalHexButton;
	protected JButton phantasmExpansionButton;
	protected JButton unstoppableSphereButton;
	private JLabel scoreLabel;
	private JLabel ymirLabel;
	private Label scoreText;
	private JLabel livesLabel;
	private RunningModePanel rmp;
	private ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();
	private static final Integer CHANCE_GIVING = 0;
	private static final Integer MAGICAL_HEX = 1;
 	private static final Integer PHANTASM_EXPENSION = 2;
	private static final Integer UNSTOPPABLE_SPHERE = 3;

	public ButtonPanel(RunningModePanel panel) {
		rmp = panel; 
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.addKeyListener(this);
		this.setFocusable(true);
		
		setLabels();
		setMenuButtons();
		setAbilityButtons(LoadSaveHandler.getInstance().getPathSymbol());
		organizeLayout(new GroupLayout(this));
		subscribeToPublishers();
	}

	private void subscribeToPublishers() {
		obstacleConfiguration.addScoreListener(this);
		ExplosiveParticle.addPlayerLivesListener(this);
		GameController.getInstance().addYmirListener(this);
	}

	private void setMenuButtons() {
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setEnabled(true);
		saveButton.setFocusable(false);
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		pauseButton.setEnabled(false);
		pauseButton.setFocusable(false);
	
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
		resumeButton.setFocusable(false);
		
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(this);
		helpButton.setEnabled(true);
		helpButton.setFocusable(false);
	}

	private void setLabels() {
		livesLabel = new JLabel("Lives = " + PlayerMaster.getInstance().getLives());
		livesLabel.setPreferredSize(new Dimension(70,15));
		livesLabel.setForeground(Color.WHITE);
		
		scoreText = new Label("Score =");
		scoreText.setPreferredSize(new Dimension(50,15));
		scoreText.setForeground(Color.WHITE);
		
		scoreLabel = new JLabel(String.valueOf(PlayerMaster.getInstance().getScore()));
		scoreLabel.setPreferredSize(new Dimension(50,15));
		scoreLabel.setForeground(Color.WHITE);
		
		ymirLabel = new JLabel();
		ymirLabel.setPreferredSize(new Dimension(400,15));
		ymirLabel.setFont(new Font("Arial",Font.BOLD, 20));
		ymirLabel.setForeground(Color.WHITE);
		ymirLabel.setVisible(true);
	}

	private void setAbilityButtons(String pathSymbol) {
		magicalHexButton = new JButton();
		magicalHexButton.addActionListener(this);
		magicalHexButton.setEnabled(false);
		magicalHexButton.setPreferredSize(new Dimension(75,30));
		ImageIcon mhIcon = new ImageIcon(System.getProperty("user.dir") + pathSymbol + "images" + pathSymbol + "MH.jpeg");
		Image mhImage = mhIcon.getImage();
		Image mhImage2 = mhImage.getScaledInstance(57,22, Image.SCALE_SMOOTH);
		magicalHexButton.setIcon(new ImageIcon(mhImage2));
		magicalHexButton.setFocusable(false);	
		
		phantasmExpansionButton = new JButton();
		phantasmExpansionButton.addActionListener(this);
		phantasmExpansionButton.setEnabled(false);
		phantasmExpansionButton.setPreferredSize(new Dimension(75,30));
		ImageIcon peIcon = new ImageIcon(System.getProperty("user.dir") + pathSymbol + "images" + pathSymbol + "PE.jpeg");
		Image peImage = peIcon.getImage();
		Image peImage2 = peImage.getScaledInstance(57,22, Image.SCALE_SMOOTH);
		phantasmExpansionButton.setIcon(new ImageIcon(peImage2));
		phantasmExpansionButton.setFocusable(false);

		unstoppableSphereButton = new JButton();
		unstoppableSphereButton.addActionListener(this);
		unstoppableSphereButton.setEnabled(false);
		unstoppableSphereButton.setPreferredSize(new Dimension(75,30));
		ImageIcon usIcon = new ImageIcon(System.getProperty("user.dir") + pathSymbol + "images" + pathSymbol + "US.jpeg");
		Image usImage = usIcon.getImage();
		Image usImage2 = usImage.getScaledInstance(57,22, Image.SCALE_SMOOTH);
		unstoppableSphereButton.setIcon(new ImageIcon(usImage2));
		unstoppableSphereButton.setFocusable(false);
	}
	
	private void organizeLayout(GroupLayout layout) {
		layout.setHorizontalGroup(layout.createSequentialGroup()
	    		.addGap(50)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	    		.addComponent(livesLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	    						GroupLayout.PREFERRED_SIZE)
	    		.addComponent(magicalHexButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	    						GroupLayout.PREFERRED_SIZE))
	    	.addComponent(phantasmExpansionButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					GroupLayout.PREFERRED_SIZE)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	    		.addComponent(scoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	    						GroupLayout.PREFERRED_SIZE)
	    		.addComponent(unstoppableSphereButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	    						GroupLayout.PREFERRED_SIZE))
	    	
	    		.addComponent(scoreLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
	    		.addGap(100)
	    		.addComponent(ymirLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
	    		.addGap(50)
	    		.addComponent(saveButton)
	            .addComponent(pauseButton)
	            .addComponent(resumeButton)
	            .addComponent(helpButton));

	   layout.setVerticalGroup(layout.createSequentialGroup()
			   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	               .addComponent(livesLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
	               .addComponent(scoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
	               .addComponent(scoreLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
				   .addComponent(saveButton)
			       .addComponent(pauseButton)
			       .addComponent(resumeButton)
			       .addComponent(helpButton))
			   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				   .addComponent(ymirLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
				   .addComponent(magicalHexButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
   						GroupLayout.PREFERRED_SIZE)
				   .addComponent(phantasmExpansionButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
				   .addComponent(unstoppableSphereButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
   						GroupLayout.PREFERRED_SIZE)));
	   

	   this.setLayout(layout);
	}
	@Override
	public void ymirActivatedEvent(YmirAbility ability) {
		// TODO Auto-generated method stub
		String abilityName = ability.getName();
		if (abilityName == null) {
			ymirLabel.setText("");
		} else {
			ymirLabel.setText("Ymir activated: " + ability.getName());
		}
		ymirLabel.setFont(new Font("Arial",Font.BOLD, 18));
		ymirLabel.setForeground(Color.WHITE);
		ymirLabel.setVisible(true);
	}
	@Override
	public void onScoreEvent() {
		// TODO Auto-generated method stub
		scoreLabel.setText(String.valueOf(PlayerMaster.getInstance().getScore()));
	}
	
	public void updatePlayerLives() {
		// TODO Auto-generated method stub
		if (PlayerMaster.getInstance().getLives() == 0) {
			GameController.getInstance().cancelAllTasks();
			JFrame parent = (JFrame) this.getTopLevelAncestor();
			parent.dispose();
			//GameEndPanel gep = new GameEndPanel("f");
		} else {
			livesLabel.setText("Lives = " + PlayerMaster.getInstance().getLives());
		}
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// save the game
		if (e.getSource() == saveButton) {
			rmp.stopTimer();
			LoadSaveHandler.getInstance().saveGame();

		}
		// pause the game, cancel all timer tasks
		if (e.getSource() == pauseButton) {
			if(RunningModePanel.gameStarted) {
				GameController.getInstance().getGameTime().stopTime();
				GameController.getInstance().getGameTime().addCurrentTime();
			}
			
			rmp.stopTimer();
			GameController.getInstance().cancelAllTasks();
			setFocusable(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			saveButton.setEnabled(true);
			helpButton.setEnabled(true);
			magicalHexButton.setEnabled(false);
			phantasmExpansionButton.setEnabled(false);
			unstoppableSphereButton.setEnabled(false);

		}
		// resume the game, reset all timer tasks
		if (e.getSource() == resumeButton) {
	
			if(RunningModePanel.gameStarted) {
				GameController.getInstance().getGameTime().startTime();	
			}	
			rmp.restartTimer();
			GameController.getInstance().initializeAllTasks();
			NoblePhantasm nb = GameController.getInstance().getPhantasm();
			Cannon cannon = GameController.getInstance().getCannon();
			if(cannon!= null) {
			GameObject obj = GameController.getInstance().getPhantasm();
			obj = PlayerMaster.getInstance().getObj();
			GameController.getInstance().initializeCannonBullets(obj, cannon.getCanonBulletList());
			}
			setFocusable(true);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			helpButton.setEnabled(false);

		}

		if (e.getSource() == helpButton) {
			rmp.stopTimer();
			HelpPanel.showHelpMessage();
		}
		
		
		if (e.getSource() == magicalHexButton ) {
			// activate Magical Hex ability
			if (PlayerMaster.getInstance().activateAbility(MAGICAL_HEX)) {
				magicalHexButton.setEnabled(false);
			}
			
			
		}
		if (e.getSource() == phantasmExpansionButton) {
			// activate Phantasm Expansion ability
			if (PlayerMaster.getInstance().activateAbility(PHANTASM_EXPENSION)) {
				phantasmExpansionButton.setEnabled(false);
			}

		}
		if (e.getSource() == unstoppableSphereButton) {
			// activate Unstoppable Enchanted Sphere ability
			if (PlayerMaster.getInstance().activateAbility(UNSTOPPABLE_SPHERE)) {
				unstoppableSphereButton.setEnabled(false);
			}

		}
	}
	public JLabel getLivesLabel() {
		return livesLabel;
	}
	public void setLivesLabel(JLabel livesLabel) {
		this.livesLabel = livesLabel;
	}

	public void activateAbilityButtons() {
		if (PlayerMaster.getInstance().getCurrentAbilities().contains("Magical Hex")) {
			magicalHexButton.setEnabled(true);
			
		} else {
			magicalHexButton.setEnabled(false);
		}	
			
		if (PlayerMaster.getInstance().getCurrentAbilities().contains("Phantasm Expension")) {
			phantasmExpansionButton.setEnabled(true);
		} else {
			phantasmExpansionButton.setEnabled(false);
		}	
			
		if (PlayerMaster.getInstance().getCurrentAbilities().contains("Unstoppable Sphere")) {
			unstoppableSphereButton.setEnabled(true);
		} else {
			unstoppableSphereButton.setEnabled(false);
		}
		setFocusable(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		rmp.key.keyPressed(arg0);
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		rmp.key.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		rmp.key.keyTyped(arg0);
		
	}
	

}
