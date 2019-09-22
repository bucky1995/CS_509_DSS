package visitor;

/**
 * Determines whether a set is either null {} or a nesting of null {{{{}}}}
 * <p>
 * Note: This Visitor has problems in differentiating sets
 * that are more involved structures, such as:
 * <p>
 * {{}, {{}}}
 * <p>
 * which currently also evaluate to true. 
 *
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class NullSetVisitor implements IVisitor {
	/**
	 * Determines whether this is a null {} or nested null set {{...}}
	 */
	protected boolean nullStatus = true;

	/**
	 * Accept a concrete visitor at this Element.
	 *
	 * If this method is called, we have been visited on a Number of the set, hence
	 * we can't be null.
	 */
	public void visitNumber(visitor.Number n) {
		nullStatus = false;
	}
	
	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitSet(Set s) {
		// do nothing...
	}

	/**
	 * Return determination.
	 */
	public boolean isNull() {
		return nullStatus;
	}
}
