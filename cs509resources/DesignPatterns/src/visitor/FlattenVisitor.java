package visitor;

/**
 * Collapse a set of arbitrary structure into a single flattened list of all
 * numbers contained in original set.
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class FlattenVisitor implements IVisitor {
	/**
	 * Resulting flattened set.
	 */
	protected Set flattenedSet = new Set();
	
	/**
	 * Return flattened set result.
	 */
	public Set getResult() {
		return flattenedSet;
	}
	
	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitNumber(visitor.Number n) {
		flattenedSet.add (n);
	}
	
	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitSet(Set s) {
		// do nothing.
	}
}
