package domain.timertask;

import java.util.TimerTask;

import domain.objects.sphere.EnchantedSphere;

public class UpdateSphereTask extends TimerTask {
    private EnchantedSphere ball;
  
    public UpdateSphereTask(EnchantedSphere ball) {
    	 this.ball= ball;
    }
    @Override
    public void run() {
        // update ball position
    	ball.move();
 
    }

}

