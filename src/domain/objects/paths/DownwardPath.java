package domain.objects.paths;

public class DownwardPath implements LinearPath {

	private int deltay;
	private int deltax;
	private double x;
	private double y;

	public DownwardPath() {
		this.deltay = 1;		//initializes the ball to move upwards
		this.deltax = 0;
	}

	public double getX() {
		return x;
	}

	public void setX(double e_X) {
		x = e_X;
	}

	public double getY() {
		return y;
	}

	public void setY(double e_Y) {
		y = e_Y;
	}

	@Override
	public double[] nextPosition() {
		// TODO Auto-generated method stub
		x = x + deltax;
		y = y + deltay;
		double[] listPoint = new double[2];
		listPoint[0] = x;
		listPoint[1] = y;
		return listPoint;
	}

	@Override
	public double[] currentPosition() {
		// TODO Auto-generated method stub
		double[] listPoint = new double[2];
		listPoint[0] = x;
		listPoint[1] = y;
		return listPoint;

	}

}
