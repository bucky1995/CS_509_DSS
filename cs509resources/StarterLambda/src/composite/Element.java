package composite;

/**
 * An Element is a member of a set (which includes either Numbers or Sets).
 */
public abstract class Element {
	
	/**
	 * Element constructor comment.
	 */
	public Element() {
		
	}
	
	/** Each subclass must make this determination. */
	public abstract boolean equals (Object o);
	
	/** If you want to add a new method, you have to modify each class directly. */
	public abstract boolean doesContain (Element e);
	
}
