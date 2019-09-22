package bridge.old.awt;

import java.awt.Graphics;

import bridge.old.core.Circle;

/**
 * Specialized class that knows how to draw the rectangle on the AWT.
 * 
 * @author heineman
 *
 */
public class AWT_circle extends Circle {
	
	/** Keep track of the graphics object into which we are drawn. */
	Graphics graphics;
	
	/**
	 * Constructor for AWT circles must keep graphics object handy for drawing later.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param r
	 */
	public AWT_circle(Graphics g, int x, int y, int r) {
		super(x, y, r);
		
		graphics = g;
	}

	/**
	 * Draw the circle.
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawCircle(int x, int y, int radius) {
		graphics.drawOval(x-radius, y-radius, x + radius, y + radius);
	}
}
