package ui.helpers;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class ShapeRotator {
	// OVERVIEW: ShapeRotator is a class responsible for generating the rotated image of a shape
	//			 It is designed by Pure Fabrication

	private AffineTransform at;

	public ShapeRotator() {}

	// returns the rotated version of the shape
	public Shape rotate(double angle, int x_coor, int y_coor, int length, int width, Shape shape) {

		at = AffineTransform.getRotateInstance(angle, x_coor + length / 2, y_coor + width / 2);
		shape = at.createTransformedShape(shape);

		return shape;
	}

}
