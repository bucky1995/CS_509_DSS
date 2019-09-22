package visitor;

/**
 * Merge two sets into one according to the following semantics:
 * <p>
 * b={1, 2, {3, 4}} and ms = {4, 5}<p>
 * merge (b,ms) = {{1, 4, 5}, {2, 4, 5}, {3, 4, 5}}
 * <p>
 * @author: Administrator
 */
public class MergeVisitor implements IVisitor {
	/** Base set */
	protected Set base;
	
	/** Result to return. */
	protected Set result;

	/**
	 * When > 0 we are recursing into the Set elements.
	 */
	protected int level = 0;

	/**
	 * Constructor. Note that we must assume that the incoming set S is never altered.
	 * it would be better for us to make a copy.
	 */
	public MergeVisitor(Set s) {
		base = s;

		result = new Set();
	}
	
	public Set getResult () {
		return result;
	}
	
	/**
	 * Accept a concrete visitor at this Element. 
	 */
	public void visitNumber(Number n) {
		// if here then we are a set, so we just merge ourselves with base
		Set newSet = new Set();

		// copy base
		for (Element e : base) {
			newSet.add(e);
		}

		// merge ourselves
		newSet.add(n);

		// add to our output.
		result.add(newSet);
	}
	
	/**
	 * Merge a set by totally taking control of the logic.
	 * When level = 0 we are the first set being visited; thereafter, we know we
	 * are in progress.
	 */
	public void visitSet(Set s) {
		if (level++ == 0) {
			for (Element e : s) {
				e.accept (this);
			}
		} else {
			// if here then we are a set, so we just merge ourselves with base
			Set newSet = new Set();

			// this copies s
			for (Element e : s) {
				newSet.add(e);
			}

			// now we merge in base
			for (Element e : base) {
				newSet.add(e);
			}

			// add to our output.
			result.add (newSet);
		}
	}
}
