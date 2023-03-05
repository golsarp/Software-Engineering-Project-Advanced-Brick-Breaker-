package domain.validity;

public class BuildingModeRequirement {
	private Requirement strategy;
	
	public BuildingModeRequirement (Requirement strategy) {
		this.strategy = strategy;
	}
	
	
	public boolean executeStrategy(int s ,int f, int e, int g) {
		return strategy.BuildingModePass(s, f, e, g);
	}


	public Requirement getStrategy() {
		return strategy;
	}


	public void setStrategy(Requirement strategy) {
		this.strategy = strategy;
	}
	

}
