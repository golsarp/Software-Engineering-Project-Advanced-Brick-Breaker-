package domain.timertask;

import java.util.TimerTask;

import domain.objects.particles.ExplosiveParticle;
import domain.objects.particles.ExplosiveParticleList;

public class UpdateExplosiveParticleTask extends TimerTask {

    public UpdateExplosiveParticleTask() {}
    
    @Override
    public void run() {
		for (ExplosiveParticle particle: ExplosiveParticleList.getInstance().getList()) {
			particle.move();
		}
    }

}