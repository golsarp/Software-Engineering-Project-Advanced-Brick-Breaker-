package ui.inputHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import constants.GameConstants;
import domain.controller.GameController;
import ui.runningmode.RunningModePanel;

public class mouseHandler implements MouseListener,MouseMotionListener {
	//OVERVIEW: mouseHandler is the class responsible for handling mouse events
	
	public static boolean MOUSE_CLICKED = false;
	public static boolean MOUSE_DRAGED = false;
	public static boolean MOUSE_PRESSED = false;
	public static boolean MOUSE_RELEASED = false;
	public static boolean first = true;
	public static boolean second = false;
	
	public static int x;
	public static int y;
	RunningModePanel rp;
	
   public mouseHandler(RunningModePanel rp) {
	   this.rp = rp;
   }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
			RunningModePanel. gameStarted = true;

			
			GameController.getInstance().getGameTime().startTime();
			GameController.getInstance().getBall().setDeltaX(0);
			GameController.getInstance().getBall().setDeltaY(-GameConstants.ball_start_speed);
			rp.getTimer().restart();
			second = false;
			rp.setSecond(false);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}