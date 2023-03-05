package domain.timertask;

import java.util.Timer;
import java.util.TimerTask;

import domain.actors.ymir.YmirMaster;

public class YmirAbilityTask extends TimerTask{
	private Timer timer;
	private YmirMaster ymirController;
	
	public YmirAbilityTask(YmirMaster yc, Timer timer) {
		this.timer = timer;
		this.ymirController = yc;
	}
	
	@Override
    public void run() {
        ymirController.specifyYmirAbility();
    }


}
