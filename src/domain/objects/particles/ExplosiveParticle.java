package domain.objects.particles;

import java.util.ArrayList;
import java.util.List;

import domain.actors.player.PlayerMaster;
import domain.controller.GameController;
import domain.listeners.PlayerLivesListener;
import domain.objects.paths.DownwardPath;
import domain.objects.phantasm.NoblePhantasm;

public class ExplosiveParticle {
	private double deltax, deltay, x, y;
	private static int radius = 5;
	private boolean drawable;
	private boolean active = false;
	private String color;
	private static List<PlayerLivesListener> playerLivesListeners = new ArrayList<>();
	private DownwardPath myPath;

	public ExplosiveParticle() {
		this.color = "RED";
		this.myPath = new DownwardPath();
		this.drawable = true;
	}

	public static void addPlayerLivesListener(PlayerLivesListener lis) {
		playerLivesListeners.add(lis);
	}

	public void publishDecreaseLivesEvent() {
		for (PlayerLivesListener l : playerLivesListeners) {
			l.updatePlayerLives();
		}
	}

	public void setMyPath(DownwardPath myPath) {
		this.myPath = myPath;
	}

	public void move() {
		NoblePhantasm nb = GameController.getInstance().getPhantasm();

		if (this.getX() + this.getRadius() >= nb.getX_coor() && this.getX() <= nb.getX_coor() + nb.getLength()
				&& this.getY() + this.getRadius() == nb.getY_coor()) {

			nb.setColor("red");
			PlayerMaster.getInstance().decreaseLife();
			publishDecreaseLivesEvent();
			active = true;
		}

		double[] p = myPath.nextPosition();
		this.x = p[0];
		this.y = p[1];
	}

	public void setDownwardPathY(double e_Y) {
		myPath.setY(e_Y);
	}

	public void setDownwardPathX(double e_X) {
		myPath.setX(e_X);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean getDrawable() {
		return drawable;
	}

	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}

	public double getDeltaX() {
		return deltax;
	}

	public void setDeltaX(double deltaX) {
		this.deltax = deltaX;
	}

	public double getDeltaY() {
		return deltay;
	}

	public void setDeltaY(int deltaY) {
		this.deltay = deltaY;
	}

	public static int getRadius() {
		return radius;
	}

	public static void setRadius(int radius) {
		ExplosiveParticle.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public DownwardPath getMyPath() {
		return myPath;
	}

}
