package bridge.done.core;

import java.awt.Point;


/**
 * Common class for all rectangles.
 * 
 * @author heineman
 */
public class Rectangle extends Shape {

	/** Width. */
	int width;
	
	/** Height. */
	int height;
	
	/**
	 * Rectangle knows where it is located.
	 * @param d   Drawer to do the work.
	 * @param x,y,x2,y2
	 */
	public Rectangle(Drawer d, int x, int y, int x2, int y2) {
		super(new Point (x,y), d);
		
		this.width = (x2 - x);
		this.height =(y2 - y);
	}

	/**
	 * Draw the rectangle by using the available drawLine methods
	 *
	 */
	public void draw() {
		Point p = getLocation();
		int x = p.x;
		int y = p.y;
		
		// draw four lines
		drawLine (x, y, x + width, y);
		drawLine (x + width, y, x + width, y + height);
		drawLine (x + width, y + height, x, y + height);
		drawLine (x, y + height, x, y);
	}


}
