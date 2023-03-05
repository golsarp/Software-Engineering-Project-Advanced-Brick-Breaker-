package domain.listeners;

import domain.objects.obstacle.types.Obstacle;

public interface  HitListener {
	void hitHappened(Obstacle o);
}
