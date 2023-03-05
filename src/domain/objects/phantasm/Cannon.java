package domain.objects.phantasm;

public class Cannon {
	private int x_positionLeft;
	private int y_positionLeft;
	private int x_positionRight;
	private int y_positionRight;
	private int width_cannons;
	private int width;
	private int length;
	private CannonBulletList canonBulletList;
	private boolean Active = false;
	private int left_rot_count = 0;
	private int right_rot_count = 0;
	private int rot_measure = 0;

	public Cannon(int x, int y, int width, int length) {
		this.x_positionLeft = x;
		this.y_positionLeft = y;
		this.x_positionRight = x + width;
		this.y_positionRight = y;
		this.length = 20;
		this.width = 10;
		this.width_cannons = width;
		this.canonBulletList = new CannonBulletList();
	}

	public void rotateRight() {
		right_rot_count++;
		rot_measure++;
	}

	public void rotateLeft() {
		left_rot_count++;
		rot_measure--;
	}
	
	//getters and setters
	public int getLeft_rot_count() {
		return left_rot_count;
	}

	public void setLeft_rot_count(int left_rot_count) {
		this.left_rot_count = left_rot_count;
	}

	public int getRight_rot_count() {
		return right_rot_count;
	}

	public void setRight_rot_count(int right_rot_count) {
		this.right_rot_count = right_rot_count;
	}

	public int getRot_measure() {
		return rot_measure;
	}

	public void setRot_measure(int rot_measure) {
		this.rot_measure = rot_measure;
	}
	
	public boolean getActive() {
		return Active;
	}

	public void setActive(boolean bo) {
		this.Active = bo;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public CannonBulletList getCanonBulletList() {
		return canonBulletList;
	}

	public void setCanonBulletList(CannonBulletList canonBulletList) {
		this.canonBulletList = canonBulletList;
	}

	public int getX_positionRight() {
		return x_positionRight;
	}

	public void setX_positionRight(int x_positionRight, int widthPhantasm) {
		this.x_positionRight = x_positionRight + widthPhantasm;
	}

	public int getY_positionRight() {
		return y_positionRight;
	}

	public void setY_positionRight(int y_positionRight) {
		this.y_positionRight = y_positionRight;
	}

	public int getX_positionLeft() {
		return x_positionLeft;
	}

	public void setX_positionLeft(int x_position) {
		this.x_positionLeft = x_position;
	}

	public int getY_positionLeft() {
		return y_positionLeft;
	}

	public void setY_positionLeft(int y_position) {
		this.y_positionLeft = y_position;
	}
}
