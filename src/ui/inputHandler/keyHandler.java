package ui.inputHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import constants.GameConstants;
import domain.controller.GameController;
import ui.runningmode.RunningModePanel;

public class keyHandler implements KeyListener {
	//OVERVIEW: keyHandler is the class responsible for handling keyboard events
 
	public static boolean RIGHT = false;
	public static boolean LEFT = false;
	public static boolean T = false;
	public static boolean U = false;
	public static boolean X = false;
	public static boolean A = false;
	public static boolean D = false;
	public static boolean W = false;
	public static boolean first = true;
	public static boolean second = false;
	public static boolean A_rel = false;
	public static boolean D_rel = false;
	
	private RunningModePanel rp;
	
	
	public keyHandler(RunningModePanel rp) {
		this.rp = rp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			RIGHT = true;
			
			

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = true;
		}
		 
		if (e.getKeyCode() == 87) {
			W = true;
			
             //start the game 
			 if (rp.isFirst()) { 
				
				    RunningModePanel.gameStarted = true;
				    RunningModePanel.new_game_start=false;
					GameController.getInstance().getGameTime().startTime();

					GameController.getInstance().initializeAllTasks();
					GameController.getInstance().getBall().setDeltaX(0);
					GameController.getInstance().getBall().setDeltaY(-GameConstants.ball_start_speed);
					
					rp.getTimer().start();
					first = false;
					rp.setFirst(false);
					
				}

				if (rp.isSecond()) {
					RunningModePanel.gameStarted = true;
					
					GameController.getInstance().getGameTime().startTime();
					GameController.getInstance().getBall().setDeltaX(0);
					GameController.getInstance().getBall().setDeltaY(-GameConstants.ball_start_speed);
					rp.getTimer().restart();
					second = false; 
					rp.setSecond(false);
				}
		}
		
		if (e.getKeyCode() == 65 ) {
			A_rel = false;
			A = true;

		}
		
		if (e.getKeyCode() == 84) {
			T = true;
			
		}
		
		if (e.getKeyCode() == 85) {
			U = true;
			
		}
		
		if (e.getKeyCode() == 88) {
			X = true;
			
		}
		
		if(e.getKeyCode() == 68) {
			D_rel = false;
			D = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			RIGHT = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = false;
		}
		
		if (e.getKeyCode() == 87) {
			W =false;
		}
		
		if (e.getKeyCode() == 65 ) {
			A_rel = true;
			A = false;

		}
		
		if (e.getKeyCode() == 84) {
			T = false;
			
		}
		
		if (e.getKeyCode() == 85) {
			U = false;
			
		}
		
		if (e.getKeyCode() == 88) {
			X = false;
			
		}
		
		if(e.getKeyCode() == 68) {
			D_rel = true;
			D = false;
		}
	}
	
	//getters and setters
	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}


	public boolean isSecond() {
		return second;
	}


	public void setSecond(boolean second) {
		this.second = second;
	}


	public static boolean isA() {
		return A;
	}


	public static void setA(boolean a) {
		A = a;
	}


	public static boolean isD() {
		return D;
	}


	public static void setD(boolean d) {
		D = d;
	}


	public static boolean isA_rel() {
		return A_rel;
	}


	public static void setA_rel(boolean a_rel) {
		A_rel = a_rel;
	}


	public static boolean isD_rel() {
		return D_rel;
	}


	public static void setD_rel(boolean d_rel) {
		D_rel = d_rel;
	}
	

	
	

}