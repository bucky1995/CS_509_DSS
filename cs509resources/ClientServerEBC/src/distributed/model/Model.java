package distributed.model;

/**
 * The model which contains all entity objects.
 * 
 * Often there is a need for a single class that becomes the container of all model
 * elements. This is such a class.
 * 
 * To ensure there is only a single Model object in a running application, this class implements the
 * Singleton design pattern [http://en.wikipedia.org/wiki/Singleton_pattern].
 * 
 * This model has a id that determines whether the model is locked for exclusive access by another user.
 */
public class Model {

	/** Height of rectangle. */
	Value height;
	
	/** Width of rectangle. */
	Value width;
	
	/** Color in which to color the rectangle (grayscale). */
	Value color;
	
	/** Is model locked? */
	boolean locked;
	
	/** Only instance to be created. */
	static Model model = null; 
	
	/** 
	 * This static method ensures there will never be more than a single Model object
	 * constructed in the application.
	 */
	public static Model getInstance() {
		if (model == null) {
			model = new Model();
		}
		
		return model;
	}
	
	Model () {
		height = new Value(10, 120);
		width = new Value(10, 120);
		color = new Value (0, 255);
	}
	
	/** Return height element. */
	public Value getHeight() { return height; }

	/** Return width element. */
	public Value getWidth () { return width; }

	/** Return color element. */
	public Value getColor() { return color; }
	
	/** Is the model locked? */
	public boolean isLocked() { return locked; }
	
	/** Set the lock. */
	public void setLocked(boolean b) { locked = b; }
	
}
