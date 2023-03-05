package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import constants.GameConstants;
import domain.actors.player.PlayerMaster;
import domain.controller.GameController;

public class PhantasmExpansionTask extends TimerTask {
	private Timer timer;
	
	public PhantasmExpansionTask(Timer timer) {
		this.timer = timer;	
	}
	
	@Override
    public void run() {
        GameController.getInstance().getPhantasm().setLength(GameConstants.RUNNING_MODE_FRAME_WIDTH/10);
        PlayerMaster.getInstance().getPlayer().setExpansionActive(false);
        timer.cancel(); //Terminate the timer thread
    }
    
}
