package bridge.old.core;

import java.awt.Point;

/** 
 * Shapes know how to draw themselves.
 * 
 * @author heineman
 */
public abstract class Shape {
	
	/** Location. */
	public Point location;	

	/**
	 * Default constructor to record position.
	 * 
	 * @param p
	 */
	public Shape (Point p) {
		this.location = new Point(p);
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
	 * Draw self.
	 */
	public abstract void draw();
}
