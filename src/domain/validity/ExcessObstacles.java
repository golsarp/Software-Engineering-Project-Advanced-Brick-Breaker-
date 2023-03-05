package domain.validity;
import java.util.ArrayList;
import java.util.List;

import constants.GameConstants;
import domain.listeners.ValidityListener;

public class ExcessObstacles implements Requirement {
	
	private int sum_max = GameConstants.MAX_NUMBER_OF_OBSTACLES;
	public static long sum ;
	private List<ValidityListener> validityListeners = new ArrayList<ValidityListener>();
	
    
	@Override
	public boolean BuildingModePass(int s, int y, int e, int g) {
		//EFFECTS: If sum of the parameters exceed max number of obstacles or is negative returns false 
		//         else returns true
		
		long ss=s;
		long yy=y;
		long ee=e;
		long gg =g;
		
		if(s < 0|| y <0 || e <0 || g<0) { //contains negative number of obstacles
			return false;
		}

		sum =ss + yy + ee + gg;
        
		if(sum > Integer.MAX_VALUE) { //Overflow
			return false;
		}
		if(sum < Integer.MIN_VALUE) { //Underflow

			return false;
		}
		
		if(sum >= sum_max + 1) { // Max number of obstacles exceeded
			publishValidityEvent("Maximum number of obstacles is "+ sum_max + ".");
			return false;
		}else 
			return true;
		
		
	}
	@Override
	public void publishValidityEvent(String message) {
		// TODO Auto-generated method stub
		for (ValidityListener listener: validityListeners) {
			listener.onValidityEvent(message);
		}
		
	}
	@Override
	public void addValidityListener(ValidityListener l) {
		getValidityListeners().add(l);
	}

	@Override
	public List<ValidityListener> getValidityListeners() {
		return validityListeners;
	}

	@Override
	public void setValidityListeners(List<ValidityListener> validityListeners) {
		this.validityListeners = validityListeners;
	}

}