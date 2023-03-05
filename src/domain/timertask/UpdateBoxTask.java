package domain.timertask;

import java.util.ConcurrentModificationException;
import java.util.TimerTask;

import domain.objects.particles.GiftBox;
import domain.objects.particles.GiftBoxList;

public class UpdateBoxTask extends TimerTask {

	public UpdateBoxTask() {
	}

	@Override
	public void run() {
		// update gift box position
		try {
			for (GiftBox box : GiftBoxList.getInstance().getList()) {
				box.move();
			}
		} catch (ConcurrentModificationException e) {

		}

	}

}