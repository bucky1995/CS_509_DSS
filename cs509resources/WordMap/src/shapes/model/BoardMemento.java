package shapes.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Records board state for persistent storage.
 * 
 * Note that Board entity objects are not responsible for their persistent
 * storage. However by marking these classes as implementing java.io.Serializable
 * you take advantage of the built-in Java mechanism.
 * 
 * @author heineman
 */
public class BoardMemento implements Serializable {
	ArrayList<Shape> stored = new ArrayList<Shape>();
	
	/**
	 * Has special permissions to be able to inspect all attributes of Shape objects
	 * and make copy as needed.
	 * 
	 * @param shapes
	 */
	public BoardMemento(ArrayList<Shape> shapes) {
		for (Shape s : shapes) {
			stored.add(s.copy());
		}
	}

	/**
	 * Unique tag for memento objects on disk
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 7708517838167328419L;


}
