package visitor;

/**
 * An Element is a member of a set (which includes either Numbers or Sets).
 */
public abstract class Element {
	
	public Element() { }
	
	/**
	 * Accept a visitor at this Element. Note that all the logic of WHAT
	 * the visitor does is properly enclosed within the visitor.
	 */
	public abstract void accept (IVisitor v);
}
