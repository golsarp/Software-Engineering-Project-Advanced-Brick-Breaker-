package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.controller.GameController;
import domain.objects.obstacle.types.Obstacle;

public class InfiniteVoidTask extends TimerTask {
	private Timer timer;
	private Obstacle obstacle;
	public InfiniteVoidTask(Timer timer, Obstacle o) {
		this.timer = timer;
		this.obstacle = o;
	}
	
	@Override
    public void run() {
        obstacle.setFrozen(false);
        GameController.getInstance().terminateInfiniteVoid();
        timer.cancel(); //Terminate the timer thread
    }

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle o) {
		this.obstacle = o;
	}
    
}
