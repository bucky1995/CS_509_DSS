package bridge.done.core;

import java.awt.Point;
import java.io.IOException;

/** 
 * Shapes know how to draw themselves.
 * 
 * @author heineman
 */
public abstract class Shape {
	
	/** Location. */
	public Point location;	

	/** Drawing is delegated the responsibility. */
	Drawer myDrawing;
	
	/**
	 * Default constructor to record position.
	 * 
	 * @param p
	 */
	public Shape (Point p, Drawer d) {
		this.location = new Point(p);
		this.myDrawing = d;
	}
	
	/**
	 * Return the location of the shape.
	 * 
	 * @return
	 */
	public Point getLocation () {
		return location;
	}

	/**
	 * Have the subclass take this request.
	 */
	public abstract void draw ();
	
	/**
	 * Underlying implementation is delegated to drawer.
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x, int y, int x2, int y2) {
		myDrawing.drawLine (x, y, x2, y2);
	}
	
	/**
	 * This underlying implementation is delegated to drawer.
	 * 
	 * @param x
	 * @param y
	 * @param r
	 */
	protected void drawCircle(int x, int y, int r) {
		myDrawing.drawCircle (x, y, r);
	}
	
}
