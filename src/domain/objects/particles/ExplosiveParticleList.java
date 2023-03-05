package domain.objects.particles;

import java.util.ArrayList;
import java.util.List;

import domain.objects.obstacle.types.Obstacle;

public class ExplosiveParticleList {
	private List<ExplosiveParticle> particlesList = new ArrayList<>();
	private static ExplosiveParticleList instance;

	private ExplosiveParticleList() {
	}

	public List<ExplosiveParticle> getList() {
		return particlesList;
	}

	public static ExplosiveParticleList getInstance() {
		if (instance == null) {
			instance = new ExplosiveParticleList();
		}
		return instance;
	}

	public void initilizeList() {
		particlesList = new ArrayList<>();

	}

	public void addParticles(Obstacle obstacle) {
		ExplosiveParticle ExplosiveParticle = new ExplosiveParticle();
		ExplosiveParticle.setX(obstacle.getX_position());
		ExplosiveParticle.setY(obstacle.getY_position());
		ExplosiveParticle.setDownwardPathX(obstacle.getX_position());
		ExplosiveParticle.setDownwardPathY(obstacle.getY_position());
		particlesList.add(ExplosiveParticle);
	}

	public void addParticles(ExplosiveParticle ep) {

		particlesList.add(ep);
	}

}
