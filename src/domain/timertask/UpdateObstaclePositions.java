package domain.timertask;

import java.util.TimerTask;

import domain.objects.obstacle.types.Obstacle;

public class UpdateObstaclePositions extends TimerTask {
	private Obstacle obstacle;
	  
    public UpdateObstaclePositions(Obstacle o) {
    	 this.obstacle= o;
    }
    @Override
    public void run() {
        // update obstacle position
    	obstacle.tryToMove(obstacle);
    }
}