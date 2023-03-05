package ui.helpers;

import java.awt.Shape;

public class Intersect {
	//OVERVIEW: Intersect class is responsible for checking intersection of two objects
	
	public Intersect() {}
	
	public boolean collision(Shape shape1 , Shape shape2) {
		if(shape1.intersects(shape2.getBounds2D())) {
			return true;
		}else return false;
	}

}
