package bridge.old.core;

import java.awt.Point;


/**
 * Common class for all rectangles.
 * 
 * @author heineman
 */
public abstract class Rectangle extends Shape {

	/** Width. */
	int width;
	
	/** Height. */
	int height;
	
	/**
	 * Rectangle knows where it is located.
	 * @param p
	 */
	public Rectangle(int x, int y, int x2, int y2) {
		super(new Point (x,y));
		
		this.width = (x2 - x);
		this.height =(y2 - y);
	}

	@Override
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

	/**
	 * This underlying implementation is provided by the respective subclass.
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	protected abstract void drawLine(int x, int y, int x2, int y2);

}
