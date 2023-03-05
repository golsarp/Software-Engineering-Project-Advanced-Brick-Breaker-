package domain.helpers;

public class Point {
	// OVERVIEW: This class is implemented to imitate a point in an x-y plane
	//			It is designed by Pure Fabrication
	
	private double x;
	private double y;
	
	public Point(double d, double e) {
		this.x = d;
		this.y = e;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

}
