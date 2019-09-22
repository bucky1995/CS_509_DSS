package visitor;

import java.util.*;

/**
 * Visitor to determine whether a set contains all odd numbers, and record the first
 * non-odd number found.
 * <p>
 * Note: We simply inherit the visitSet (Set s) operation because it provides
 *       all the functionality we need.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class AllOddVisitor implements IVisitor {
	/** First non-odd number. Valid only if allOdd is false. */
	protected Number firstNonOdd = null;

	/** Determines whether a set contains all odd numbers. Assume true. */
	protected boolean allOdd = true;

	/**
	 * Return non-odd number (if one exists) or null.
	 */
	public Number getNonOdd () {
		return firstNonOdd;
	}
	
	/** Determines whether set contains all odd number. */
	public boolean isAllOdd() {
		return allOdd;
	}

	/**
	 * Accept a concrete visitor for this Set.
	 */
	public void visitSet (Set s) {
		// To visit a set, we must visit each member of the set
		for (Element e : s) {
			e.accept (this);
		}	    
	}

	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitNumber (Number n) {
		// stop after first non-odd found.
		if (!allOdd) return;

		// determine whether is odd.
		if ((n.value % 2) == 0) {
			allOdd = false;
			firstNonOdd = n;
		}
	}
}
