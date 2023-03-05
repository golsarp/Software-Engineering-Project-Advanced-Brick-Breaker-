package domain.timertask;

import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;

import domain.actors.player.ability.GameObject;
import domain.objects.phantasm.CannonBullet;
import domain.objects.phantasm.CannonBulletList;

public class UpdateCannonBulletsTask extends TimerTask {
	private int counter;
	private CannonBulletList list;
	private GameObject obj;
	private Timer timer;

	public UpdateCannonBulletsTask(CannonBulletList list, GameObject ob) {
		this.list = list;
		this.obj = ob;
		counter = 0;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (counter % 30 == 2) {
			obj.enhance();
		}

		try {
			for (CannonBullet bullets : list.getCanonBulletList()) {
				bullets.move();
				if (bullets.getCollided())
					list.getCanonBulletList().remove(bullets);
			}
			counter++;
		} catch (ConcurrentModificationException e) {

		}

	}
}