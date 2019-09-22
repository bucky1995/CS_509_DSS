package visitor;


/**
 * Visitor to determine whether a set simply contains another set.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class SetWithinSetVisitor implements IVisitor {

	/** Number of sets seen so far. */
	protected int countSets = 0;

	/** Number of numbers seen so far */
	protected int countNumbers = 0;

	/** Determines whether set is just {{someset}}. */
	public boolean isSetWithinSet() {
		return (countNumbers == 0) && (countSets == 2);
	}

	/**
	 * Accept a concrete visitor for this Set.
	 */
	public void visitSet (Set s) {
		System.err.println ("VisitSet!");
		countSets++;
	}

	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitNumber (Number n) {
		System.err.println ("VisitNumbers.");
		countNumbers++;
	}
}
