package domain.objects.paths;

public class UpwardLinearPath implements LinearPath {
	
	private double deltaY;
	private double deltaX;
	private double x;
	private double y;
	
	public UpwardLinearPath(double deltaX,double deltaY) {
		 this.deltaY=deltaY;
		 this.deltaX=deltaX;
	}
	
	@Override
	public double[] nextPosition() {
		// TODO Auto-generated method stub
		
		double[] listPoint= new double[2];
		x= x + deltaX;
		y= y  +deltaY;
		listPoint[0]=x;
		listPoint[1]=y;
            return listPoint;
	}

	@Override
	public double[] currentPosition() {
		// TODO Auto-generated method stub
		double[] listPoint= new double[2];
		listPoint[0]=x;
		listPoint[1]=y;
            return listPoint;
	}
	
	//getters and setters
	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
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

}


