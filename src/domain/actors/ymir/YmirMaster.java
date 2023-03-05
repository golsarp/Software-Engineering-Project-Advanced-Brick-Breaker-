package domain.actors.ymir;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import domain.actors.ymir.ability.InfiniteVoid;
import domain.actors.ymir.ability.NoAbility;
import domain.actors.ymir.ability.YmirAbility;
import domain.listeners.YmirListener;
import domain.timertask.YmirAbilityTask;

public class YmirMaster {
	//OVERVIEW: YmirController is responsible for handling events related to Ymir
	
	private Ymir ymir;
	private YmirCoin coin;
	private Timer timer;
	private TimerTask timerTask;
	private boolean infiniteVoidWasActive;  //indicates that InfiniteVoid was active when the tasks were cancelled
	private ArrayList<YmirListener> listeners = new ArrayList<YmirListener>();
	
	public YmirMaster() {
		ymir = new Ymir();
		ymir.setAbility(new NoAbility());
		coin = new YmirCoin();
		infiniteVoidWasActive = false;
	};
	
	public void initializeTimer() {
		timer = new Timer();
		timerTask = new YmirAbilityTask(this, timer);  
		if (infiniteVoidWasActive) {
			InfiniteVoid ability = (InfiniteVoid) ymir.getAbility();
			ability.scheduleTasks(ability.getSavedTaskList());
		}
		timer.scheduleAtFixedRate(timerTask, 30*1000, 30*1000);
	}

	public void specifyYmirAbility() {
		ymir.setAbility(new NoAbility());
		int success = coin.flip();
		if (success == 1) {
			ymir.chooseAbility();
		} 
		publishYmirAbilityEvent(ymir.getAbility());
	}
	public void cancelTask() {
		if(timerTask != null) {
		if (ymir.getAbility().getName() == "INFINITE VOID") {
			InfiniteVoid ability = (InfiniteVoid) ymir.getAbility();
			ability.cancelTasks(ability.getTimerTaskList());
			infiniteVoidWasActive = true; 
		}
			timerTask.cancel();
		}
	}
	public void addYmirListener(YmirListener lis) {
		listeners.add(lis);
	}
	public void publishYmirAbilityEvent(YmirAbility ability) {
		for(YmirListener lis: listeners) {
			lis.ymirActivatedEvent(ability);
		}
	}

	public boolean isInfiniteVoidWasActive() {
		return infiniteVoidWasActive;
	}

	public void setInfiniteVoidWasActive(boolean infiniteVoidWasActive) {
		this.infiniteVoidWasActive = infiniteVoidWasActive;
	}
	
	
}
