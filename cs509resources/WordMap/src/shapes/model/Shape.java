package shapes.model;

import shapes.controller.moves.DisconnectState;
import shapes.view.ShapeDrawer;

/**
 * Any entity being placed on the board must extend this base class.
 * 
 * A shape is located at a specific (x,y) coordinate. It has a drawer that is 
 * responsible for rendering it graphically
 * 
 * This class defines the shared behavior for extensions.
 * 
 * @author heineman
 */
public abstract class Shape implements java.io.Serializable {
	/**
	 * Unique ID for serialized Shape objects. 
	 */
	private static final long serialVersionUID = 9038630607217373669L;
	
	ShapeDrawer drawer;
	int x;
	int y;
	
	public Shape (int x, int y, ShapeDrawer drawer) {
		this.x = x;
		this.y = y;
		this.drawer = drawer;
	}

	public void setDrawer(ShapeDrawer drawer) {
		this.drawer = drawer;
	}

	public ShapeDrawer getDrawer() {
		return drawer;
	}
	
	/** Determine whether (px,py) intersects shape. */
	public abstract boolean intersect(int px, int py);

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return drawer.getWidth(this); }
	public int getHeight() { return drawer.getHeight(this); }
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Determine whether intersects given shape. */
	public abstract boolean intersects(Shape other);
	
	/** Returns copy of shape. */
	public abstract Shape copy();

	/** Return contents of the shape as a string. */
	public abstract String getContents();
	
	/** Determine number of rows. */
	public abstract int getNumberRows();	
	
	/** Return contents of shape as 2d-array of WordShape[], one for each row. */
	public abstract WordShape[][] getState();

	/** 
	 * Return state needed to disconnect, and later reconnect, shape.
	 * 
	 * Disconnect shape at (x,y) into two shapes, leaving original behind that is modified
	 * and the new shape to be extracted as a new poem is returned.
	 * 
	 * @param shape
	 * @param x
	 * @param y
	 * @return  new DisconnectState object that represents change to be made
	 */
	public abstract DisconnectState split(int x, int y);
	
	
	
	/** 
	 * Reverse the process of a disconnect operation.
	 * 
	 * Make sure this is properly matched with the disconnect operation
	 * 
	 * @param  DisconnectState object that represents change that was made
	 */
	public abstract boolean reconnect(DisconnectState ds);
	
	/** 
	 * Connects shape to this shape horizontally, and the release point of the request
	 * is (x,y).
	 * 
	 * @param shape
	 * @param x
	 * @param y
	 * @return  new Shape object that represents connection (typically a Poem Shape)
	 */
	public abstract Shape horizontalConnect(Shape shape, int x, int y);
	
	/** 
	 * Connects two shapes Vertically, and the release point of the request
	 * is (x,y).
	 * 
	 * @param shape
	 * @param x
	 * @param y
	 * @return   new Shape object that represents connection (typically a Poem Shape)
	 */
	public abstract Shape verticalConnect(Shape shape, int x, int y);

	
}
