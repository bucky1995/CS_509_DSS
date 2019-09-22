package bridge.done.core;

import java.awt.Point;


/**
 * Common class for all circles.
 * 
 * @author heineman
 */
public class Circle extends Shape {

	/** Radius. */
	int radius;
	
	/**
	 * Rectangle knows where it is located.
	 * @param d   Drawer for this object
	 * @param x,y,r  information needed
	 */
	public Circle(Drawer d, int x, int y, int r) {
		super(new Point (x,y), d);
		
		this.radius = r;
	}
	
	/**
	 * Draw object using available drawCircle method.
	 *
	 */
	public void draw() {
		Point p = getLocation();
		int x = p.x;
		int y = p.y;
		
		drawCircle (x, y, radius);
	}


}
