package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.objects.phantasm.Cannon;

public class CancelBulletTimerTask extends TimerTask {
	private Timer timer;
	private Timer timer2;
	private Cannon canon;
	public CancelBulletTimerTask(Timer tim,Timer timer,Cannon canon) {
		this.timer = timer;
		this.timer2 = tim;
		this.canon= canon;
	}
	
	
	@Override
    public void run() {
		GameController.getInstance().initializeCanon();
		PlayerMaster.getInstance().getPlayer().setHexActive(false);
        timer.cancel(); //Terminate the timer thread
        timer2.cancel();
    }
}
