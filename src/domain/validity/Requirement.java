package domain.validity;

import java.util.List;

import domain.listeners.ValidityListener;

public interface Requirement {
	//OVERVIEW: Requirement interface is implemented by different Building Mode requirements that should be satisfied
	//			It is designed by Strategy and Publish-Subscribe patterns

	// method for strategy GoF pattern
	public boolean BuildingModePass(int s,int f,int e,int g);
	
	// methods for publish-subscribe GoF pattern
	public void publishValidityEvent(String message);
	
	public void addValidityListener(ValidityListener l) ;

	public List<ValidityListener> getValidityListeners();

	public void setValidityListeners(List<ValidityListener> validityListeners);

}
