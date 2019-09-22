package bridge.done.core;

public abstract class Drawer {

	/**
	 * Overridden by appropriate concrete drawer.
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public abstract void drawLine(int x, int y, int x2, int y2);
	
	/**
	 * Overriden by appropriate concrete drawer.
	 * 
	 * @param x
	 * @param y
	 * @param r
	 */
	public abstract void drawCircle(int x, int y, int r);

}
