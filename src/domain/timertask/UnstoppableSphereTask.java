package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.actors.player.PlayerMaster;
import domain.controller.GameController;

public class UnstoppableSphereTask extends TimerTask {
	private Timer timer;
	
	public UnstoppableSphereTask(Timer timer) {
		this.timer = timer;	
	}
	
	@Override
    public void run() {
        GameController.getInstance().getBall().setUnstoppable(false);
        PlayerMaster.getInstance().getPlayer().setUnstoppableActive(false);
        timer.cancel(); //Terminate the timer thread
    }
}
