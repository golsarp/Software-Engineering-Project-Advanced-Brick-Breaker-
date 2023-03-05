package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.controller.GameController;

public class DoubleAccelTask extends TimerTask {
	private Timer timer;

	public DoubleAccelTask(Timer timer) {
		this.timer = timer;
		
	}	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		GameController.getInstance().changeSphereSpeed(GameController.getInstance().getSpherePeriod()/2);
        timer.cancel(); //Terminate the timer thread
	}

}