package domain.objects.sphere;

import constants.GameConstants;
import domain.actors.player.ability.GameObject;
import domain.controller.GameController;
import domain.helpers.DeltaCalculator;
import domain.helpers.Point;
import domain.objects.paths.BouncingMovingPath;

public class EnchantedSphere extends GameObject {

	private BouncingMovingPath myPath;
	private DeltaCalculator deltaCalculator = new DeltaCalculator();
	private double deltaX, deltaY;
	private static double x, y;
	private int radius = GameConstants.ENCHANTED_SPHERE_RADIUS;

	private boolean reflected = true;
	private boolean intersected = false;
	private boolean isUnstoppable = false;

	private static final int LEFT_DIRECTION = 1;
	private static final int RIGHT_DIRECTION = 2;
	private static final int ABOVE_DIRECTION = 3;
	private static final int BOTTOM_DIRECTION = 7;
	private static final int FROZEN_OBSTACLE = 100;

	private static final int LEFT_ABOVE_DIRECTION = 4;
	private static final int LEFT_BOTTOM_DIRECTION = 8;
	private static final int RIGHT_ABOVE_DIRECTION = 5;
	private static final int RIGHT_BOTTOM_DIRECTION = 9;

	public EnchantedSphere(int deltaX, int deltaY, int x, int y) {

		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.x = x;
		this.y = y;

		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);

	}

	public BouncingMovingPath getMyPath() {
		return myPath;
	}

	public void setMyPath(BouncingMovingPath myPath) {
		this.myPath = myPath;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		myPath = new BouncingMovingPath(x, y, deltaX, deltaY, radius);

	}

	public int getRadius() {
		return radius;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double d) {
		this.deltaX = d;
		myPath = new BouncingMovingPath(x, y, d, deltaY, radius);
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double d) {
		this.deltaY = d;
		myPath = new BouncingMovingPath(x, y, deltaX, d, radius);
	}

	public void move() {
		Point cpos = myPath.currentPosition();

		this.x = cpos.getX();
		this.y = cpos.getY();

		Point pos = myPath.nextPosition();

		int hitDirection = myPath.getHitDirection();
		if (this.deltaY == 0) {
			reflected = true;
		}
		// sphere reflects if
		// the Unstoppable Sphere is not activated or
		// the Unstoppable Sphere is activated and it hits a frozen obstacle
		// (hitDirection has a value greater than 4)
		if (!isUnstoppable() || (isUnstoppable() && (100 < hitDirection))) {
			if (LEFT_DIRECTION == hitDirection || LEFT_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				this.deltaX = -this.deltaX;
				myPath = new BouncingMovingPath(pos.getX() - 6, pos.getY() + 0, deltaX, deltaY, radius);

			} else if (RIGHT_DIRECTION == hitDirection || RIGHT_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				this.deltaX = -this.deltaX;
				myPath = new BouncingMovingPath(pos.getX() + 6, pos.getY() + 0, deltaX, deltaY, radius);

			} else if (ABOVE_DIRECTION == hitDirection || ABOVE_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				this.deltaY = -this.deltaY;
				myPath = new BouncingMovingPath(pos.getX(), pos.getY() - 5, deltaX, deltaY, radius);

			} else if (BOTTOM_DIRECTION == hitDirection || BOTTOM_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				this.deltaY = -this.deltaY;
				myPath = new BouncingMovingPath(pos.getX(), pos.getY() + 5, deltaX, deltaY, radius);

			}

			else if (LEFT_ABOVE_DIRECTION == hitDirection || LEFT_ABOVE_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				if (this.deltaX < 0 && this.deltaY > 0) {
					double temp = this.deltaX;
					this.deltaX = -this.deltaY;
					this.deltaY = temp;

				}

				else if (this.deltaX > 0 && this.deltaY < 0) {
					double temp = this.deltaX;
					this.deltaX = this.deltaY;
					this.deltaY = -temp;

				}

				else {

					if (this.deltaX > this.deltaY) {
						double temp = this.deltaX;
						this.deltaX = this.deltaY;
						this.deltaY = -temp;
					}

					else {
						double temp = this.deltaX;
						this.deltaX = -this.deltaY;
						this.deltaY = temp;
					}
				}
				myPath = new BouncingMovingPath(pos.getX() - 6, pos.getY() - 5, deltaX, deltaY, radius);
			}

			else if (RIGHT_ABOVE_DIRECTION == hitDirection || RIGHT_ABOVE_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				if (this.deltaX > 0 && this.deltaY > 0) {
					double temp = this.deltaX;
					this.deltaX = this.deltaY;
					this.deltaY = -temp;
				}

				else if (this.deltaX < 0 && this.deltaY < 0) {
					double temp = this.deltaX;
					this.deltaX = -this.deltaY;
					this.deltaY = temp;
				}

				else {

					if (Math.abs(x) > Math.abs(y)) {
						double temp = this.deltaX;
						this.deltaX = -this.deltaY;
						this.deltaY = temp;
					}

					else {
						double temp = this.deltaX;
						this.deltaX = this.deltaY;
						this.deltaY = -temp;
					}
				}
				myPath = new BouncingMovingPath(pos.getX() + 6, pos.getY() - 5, deltaX, deltaY, radius);
			}

			else if (RIGHT_BOTTOM_DIRECTION == hitDirection
					|| RIGHT_BOTTOM_DIRECTION + FROZEN_OBSTACLE == hitDirection) {
				reflected = true;

				if (this.deltaX < 0 && this.deltaY > 0) {
					double temp = this.deltaX;
					this.deltaX = this.deltaY;
					this.deltaY = -temp;
				} else if (this.deltaX > 0 && this.deltaY < 0) {
					double temp = this.deltaX;
					this.deltaX = -this.deltaY;
					this.deltaY = temp;
				}

				else {

					if (this.deltaY > this.deltaX) {
						double temp = this.deltaX;
						this.deltaX = this.deltaY;
						this.deltaY = -temp;

					} else {
						double temp = this.deltaX;
						this.deltaX = -this.deltaY;
						this.deltaY = temp;
					}
				}
				myPath = new BouncingMovingPath(pos.getX() + 6, pos.getY() + 5, deltaX, deltaY, radius);

			} else if (LEFT_BOTTOM_DIRECTION == hitDirection
					|| LEFT_BOTTOM_DIRECTION + FROZEN_OBSTACLE == hitDirection) {

				reflected = true;

				if (this.deltaX > 0 && this.deltaY > 0) {
					double temp = this.deltaX;
					this.deltaX = -this.deltaY;
					this.deltaY = temp;
				}

				else if (this.deltaX < 0 && this.deltaY < 0) {
					double temp = this.deltaX;
					this.deltaX = this.deltaY;
					this.deltaY = -temp;
				}
				else {
					
					if (Math.abs(this.deltaX) > Math.abs(this.deltaY)) {
						double temp = this.deltaX;
						this.deltaX = -this.deltaY;
						this.deltaY = temp;

					} else {
						double temp = this.deltaX;
						this.deltaX = this.deltaY;
						this.deltaY = -temp;
					}
				}
				
				myPath = new BouncingMovingPath(pos.getX() - 6, pos.getY() + 5, deltaX, deltaY, radius);
			}

		}
		if (pos.getY() <= GameConstants.BUTTON_PANEL_HEIGHT) {
			reflected = true;

			this.deltaY = -deltaY;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		} else if (pos.getY() >= GameConstants.RUNNING_MODE_FRAME_HEIGHT) {
			reflected = true;

			this.deltaY = 0;
			this.deltaX = 0;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), 0, 0, radius);

		} else if (intersected && reflected) {

			double ref_angle = deltaCalculator.Tan(this.deltaX, this.deltaY,
					GameController.getInstance().getPhantasm().getRot_measure());
			if (GameController.getInstance().getPhantasm().getRot_measure() > 0) {
				if (this.deltaX < 0) {
					this.deltaX = deltaCalculator.newDeltaX(ref_angle, this.deltaX);
					this.deltaY = -deltaCalculator.newDeltaY(ref_angle, this.deltaY);
				} else {
					this.deltaX = deltaCalculator.newDeltaX(ref_angle, this.deltaX);
					this.deltaY = -deltaCalculator.newDeltaY(ref_angle, this.deltaY);
				}

			} else if (GameController.getInstance().getPhantasm().getRot_measure() < 0) {
				if (this.deltaX > 0) {
					this.deltaX = -deltaCalculator.newDeltaX(ref_angle, this.deltaX);
					this.deltaY = -deltaCalculator.newDeltaY(ref_angle, this.deltaY);

				} else {
					this.deltaX = -deltaCalculator.newDeltaX(ref_angle, this.deltaX);
					this.deltaY = -deltaCalculator.newDeltaY(ref_angle, this.deltaY);
				}
			}

			if (GameController.getInstance().getPhantasm().getRot_measure() == 0) {

				this.deltaY = -this.deltaY;
				myPath = new BouncingMovingPath(pos.getX(), pos.getY(), this.deltaX, this.deltaY, radius);
			}

			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), this.deltaX, this.deltaY, radius);
			reflected = false;
		}

		else if (pos.getX()  <= 0) {
			reflected = true;

			deltaX = -deltaX;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		} else if (pos.getX() + radius >= GameConstants.RUNNING_MODE_FRAME_WIDTH) {
			reflected = true;

			deltaX = -deltaX;
			myPath = new BouncingMovingPath(pos.getX(), pos.getY(), deltaX, deltaY, radius);

		}
	}

	public boolean isUnstoppable() {
		return isUnstoppable;
	}

	public void setUnstoppable(boolean isUnstoppable) {
		this.isUnstoppable = isUnstoppable;
	}

	public boolean isIntersected() {
		return intersected;
	}

	public void setIntersected(boolean intersected) {
		this.intersected = intersected;
	}

	@Override
	public void enhance() {
		// TODO Auto-generated method stub
		setUnstoppable(true);
	}

}
