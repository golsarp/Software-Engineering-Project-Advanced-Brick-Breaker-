package domain.helpers;

import constants.GameConstants;

public class DeltaCalculator {
	//OVERVIEW: DeltaCalculator is the class responsible for calculating reflection angles using rules of trigonometry
	//			It is designed by Pure Fabrication

	public DeltaCalculator() {}

	public double Tan(double DeltaX, double DeltaY, int count) {

		double degree = Math.PI / 180 * count;
		double converted_in_degrees = -Math.toDegrees(degree);
		double tan = DeltaY / DeltaX;
		double result = Math.toDegrees(Math.atan(tan));
		return converted_in_degrees + result;
	}

	public double getDeltaX(double DeltaX, double DeltaY, int count) {
		double result = Tan(DeltaX, DeltaY, count);

		return (1 * Math.cos(result));

	}

	public double getDeltaY(double DeltaX, double DeltaY, int count) {
		double result = Tan(DeltaX, DeltaY, count);
		return (1 * Math.sin(result));
	}

	public double newDeltaX(double degree, double DeltaX) {
		return GameConstants.ball_start_speed * (Math.abs(Math.cos(degree)));
	}

	public double newDeltaY(double degree, double DeltaY) {
		return Math.abs(GameConstants.ball_start_speed * Math.sin(degree));
	}

}
