package bridge.old.core;

import java.awt.Point;


/**
 * Common class for all circles.
 * 
 * @author heineman
 */
public abstract class Circle extends Shape {

	/** Radius. */
	int radius;
	
	/**
	 * Rectangle knows where it is located.
	 * @param p
	 */
	public Circle(int x, int y, int r) {
		super(new Point (x,y));
		
		this.radius = r;
	}
	
	@Override
	public void draw() {
		Point p = getLocation();
		int x = p.x;
		int y = p.y;
		
		drawCircle (x, y, radius);
	}

	/**
	 * Implementation deferred to subclasses.
	 * 
	 * @param x1
	 * @param y1
	 * @param r
	 */
	protected abstract void drawCircle(int x1, int y1, int r);

}
