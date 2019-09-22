package bridge.done.awt;

import java.awt.Graphics;

import bridge.done.core.Drawer;


public class AWT_Drawer extends Drawer {
	
	/** Keep track of the graphics object into which we are drawn. */
	Graphics graphics;

	/**
	 * Constructor for AWT drawer needs the AWT Graphics object.
	 * 
	 * @param g
	 */
	public AWT_Drawer (Graphics g) {
		this.graphics = g;
	}
	
	/**
	 * Draw the circle.
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawCircle(int x, int y, int r) {
		graphics.drawOval(x-r, y-r, x + r, y + r);
	}
	
	/**
	 * Draw a line in postscript.
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
