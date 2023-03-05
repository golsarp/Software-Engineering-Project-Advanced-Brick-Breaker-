package domain.validity;

import java.util.List;

import constants.GameConstants;

import java.util.ArrayList;

import domain.listeners.ValidityListener;

public class MinRequirements implements Requirement {
	private int simple_req = GameConstants.MIN_SIMPLE_OBS_NUM;
	private int firm_req = GameConstants.MIN_FIRM_OBS_NUM;
	private int exp_req = GameConstants.MIN_EXP_OBS_NUM;
	private int gift_req = GameConstants.MIN_GIFT_OBS_NUM;
	private List<ValidityListener> validityListeners = new ArrayList<ValidityListener>();

	@Override
	public boolean BuildingModePass(int s, int y, int e, int g) {
		//if the minimum requirements are not satisfied, publish validity event
		if( s < simple_req || y < firm_req || e < exp_req || g < gift_req) {
			publishValidityEvent("Minimum requirements not satisfied.");
			return false;
		}
		else return true;
	}

	@Override
	public void publishValidityEvent(String message) {
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