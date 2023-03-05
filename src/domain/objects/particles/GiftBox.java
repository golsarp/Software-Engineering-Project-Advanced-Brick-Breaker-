package domain.objects.particles;

import java.util.Random;
import java.util.Timer;

import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.objects.paths.DownwardPath;
import domain.objects.phantasm.NoblePhantasm;
import domain.timertask.UpdateExplosiveParticleTask;

public class GiftBox {
	private int deltax, deltay, x, y;
	private int radius = 5;
	private boolean drawable;
	private String color;
	private int currentAbility;
	private boolean active= false;
	private Timer timer;
	private UpdateExplosiveParticleTask timerTask;
	private DownwardPath myPath;
	
	private static final Integer CHANCE_GIVING = 0;
	private static final Integer MAGICAL_HEX = 1;
	private static final Integer PHANTASM_EXPENSION = 2;
	private static final Integer UNSTOPPABLE_SPHERE = 3;

	public GiftBox() {
		this.myPath = new DownwardPath();
		this.drawable = true;
		Random rand = new Random();
		this.currentAbility = rand.nextInt(4) ;
	}

	//getters and setters
	public int getDeltaX() {
		return deltax;
	}

	public void setDeltaX(int deltaX) {
		this.deltax = deltaX;
	}

	public int getDeltaY() {
		return deltay;
	}
	
	public int getCurrentAbility() {
		return currentAbility;
	}

	public void setDeltaY(int deltaY) {
		this.deltay = deltaY;
	}

	public  int getRadius() {
		return radius;
	}
	public void setCurrentAbility(int currentAbility) {
		this.currentAbility = currentAbility;
	}

	public  void setRadius(int radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public UpdateExplosiveParticleTask getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(UpdateExplosiveParticleTask timerTask) {
		this.timerTask = timerTask;
	}

	public DownwardPath getMyPath() {
		return myPath;
	}

	public void setMyPath(DownwardPath myPath) {
		this.myPath = myPath;
	}

	public void move() {
		NoblePhantasm nb =	GameController.getInstance().getPhantasm();
		
		if(this.getX() + this.getRadius() >= nb.getX_coor() && this.getX()<= nb.getX_coor() + nb.getLength() 
		&& this.getY() + this.getRadius() ==nb.getY_coor()) {
			nb.setColor("red");
			active=true;
			if(currentAbility== CHANCE_GIVING) {
				PlayerMaster.getInstance().increaseLife();
			} else {			
				PlayerMaster.getInstance().addAbility(currentAbility);			
			}
		}
			
	double[] p = myPath.nextPosition();
	this.x = (int) p[0];
	this.y = (int) p[1];
		
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setUpwardPathY(int y) {
		myPath.setY(y);
	}

	public void setUpwardPathX(int x) {
		myPath.setX(x);
	}
	public boolean isDrawable() {
		return drawable;
	}

	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}


}