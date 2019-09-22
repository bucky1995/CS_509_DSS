package bridge.old.awt;

import java.awt.Graphics;

import bridge.old.core.Rectangle;

/**
 * Specialized class that knows how to draw the rectangle on the AWT.
 * 
 * @author heineman
 *
 */
public class AWT_rectangle extends Rectangle {
	
	/** Keep track of the graphics object into which we are drawn. */
	Graphics graphics;
	
	/**
	 * Constructor for AWT_rectangle object needs to know of the graphics.
	 * @param g
	 */
	public AWT_rectangle (Graphics g, int x, int y, int x2, int y2) {
		super (x, y, x2, y2);
		
		this.graphics = g;
	}
	
	/**
	 * Finally! The concrete implementation
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x, int y, int x2, int y2) {
		graphics.drawLine(x, y, x2, y2);
	}
}
