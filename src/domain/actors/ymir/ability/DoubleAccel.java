package domain.actors.ymir.ability;

import java.util.Timer;
import java.util.TimerTask;

import domain.controller.GameController;
import domain.timertask.DoubleAccelTask;

public class DoubleAccel implements YmirAbility{
	private String name = "DOUBLE ACCEL";
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		GameController.getInstance().changeSphereSpeed(GameController.getInstance().getSpherePeriod()*2);
		
		Timer timer = new Timer(true);
	    TimerTask timerTask = new DoubleAccelTask(timer);  
	    
	    //running timer task as daemon thread
	    timer.schedule(timerTask, 15*1000);
		
	}
	@Override
	public String getName() {
		return name;
	}

}
