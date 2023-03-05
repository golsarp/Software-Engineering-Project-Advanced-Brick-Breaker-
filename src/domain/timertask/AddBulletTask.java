package domain.timertask;

import java.util.TimerTask;

import domain.actors.player.ability.GameObject;

public class AddBulletTask extends TimerTask {
	private GameObject ob;

	public AddBulletTask(GameObject ob) {
		// TODO Auto-generated constructor stub
		this.ob = ob;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ob.enhance();
	}

}
