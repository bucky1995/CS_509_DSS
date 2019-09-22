package shapes.controller;

public interface Listener {
	/** 
	 * @see{Board} state has changed so concrete listeners
	 * must be notified.
	 */
	void update();
}
