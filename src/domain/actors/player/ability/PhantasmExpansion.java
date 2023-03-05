package domain.actors.player.ability;

import java.util.Timer;
import java.util.TimerTask;

import domain.objects.phantasm.NoblePhantasm;
import domain.timertask.PhantasmExpansionTask;

public class PhantasmExpansion extends GameObjectDecorator{
	
	String name = "phantasmExpansion";

	public PhantasmExpansion(GameObject object) {
		this.object = object;
	}
	
	public void enhance() {
		NoblePhantasm n = (NoblePhantasm) object;
		n.setLength(2*n.getLength());
		Timer timer = new Timer(true);
	    TimerTask timerTask = new PhantasmExpansionTask(timer);  
	    
	    //running timer task as daemon thread
	    timer.schedule(timerTask, 30*1000);
	}
}

