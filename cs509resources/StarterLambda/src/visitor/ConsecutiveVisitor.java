package visitor;

import java.util.*;

/**
 * Determine whether a set contains consecutive numbers.
 * <p>
 * Min contains the low end of the range and Max contains the high end of
 * the range.
 * <p>
 * If <code>whole</code> is true, then the entire range is represented; if
 * <code>whole</code> is false, then <code>missingValue</code> contains one
 * value that is missing from this range.
 * <p>
 */
public class ConsecutiveVisitor implements IVisitor {
	/** Min. */
	protected Number min;

	/** Determines that this is the first visitor called. */
	private boolean first = true;

	/** Max. */
	protected Number max;

	/** Missing Values. */
	protected ArrayList<Range> missingValues = new ArrayList<Range>();

	/** Is whole. */
	protected boolean whole;

	/** Maintains a range. */
	class Range {
		/** low end. */
		Number  low;

		/** high end. */
		Number  high;

		public Range (Number low, Number high) {
			this.low = low;
			this.high = high;
		}

		/** Does this range represent a single number. */
		public boolean singleton () {
			return (low.value == high.value);
		}

		/** Contains a number. */
		public boolean contains (Number n) {
			if (n == null) return false;
			return (n.value >= low.value) &&
					(n.value <= high.value);
		}

		/** Split range on the left (return null if none exists). */
		public Range leftSplit (Number n) {
			if (n.value == low.value) return null;

			return new Range (low, new Number (n.value - 1));
		}

		/** Split range on the right (return null if none exists). */
		public Range rightSplit (Number n) {
			if (n.value == high.value) return null;

			return new Range (new Number (n.value + 1), high);
		}

		// Two ranges are equal if low,high matches.
		public boolean equals (Object o) {
			if (o == null) return false;
			if (o instanceof Range) {
				Range r = (Range) o;

				return (r.low == low) && (r.high == high);
			}

			return false;
		}
	}

	/** Determine max of range found. */
	protected Number getMax() {
		return max;
	}
	
	/** Determine min of range found. */
	protected Number getMin() {
		return min;
	}
	
	/** Determine if visitor found the set to contain consecutive integers. */
	protected boolean isWhole () {
		return whole;
	}

	/**
	 * Accept a concrete visitor for this Set by visiting all individual elements.
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
		// Is this the first value we've seen?
		if (first) {
			first = false;

			min = n;
			max = n;
			whole = true;
			return;
		}

		// if we get here, we have already seen some numbers, and [min,max] 
		// may be whole or incomplete. Handle special cases
		if (n.value == min.value - 1) {
			min = n;
			return;
		} else if (n.value == max.value + 1) {
			max = n;
			return;
		}

		// we need to maintain all holes and plug them up one at a time		
		// extend min range.
		if (n.value < min.value) {
			missingValues.add (new Range (new Number (n.value + 1), 
					new Number (min.value-1)));
			min = n;
			whole = false;
			return;
		} else if (n.value > max.value) {
			missingValues.add (new Range (new Number (max.value+1), 
					new Number (n.value - 1)));
			max = n;
			whole = false;
			return;
		} else {
			// much too complicated inner loop; find in existing range and
			// split and reinsert if non-singleton
			for (Iterator<Range> it = missingValues.iterator(); it.hasNext(); ) {
				Range rg = it.next();
				if (rg.contains (n)) {
					missingValues.remove (rg);

					if (!rg.singleton()) {
						// we can split this range, so do so.
						Range l = rg.leftSplit (n);
						Range r = rg.rightSplit (n);
						if (l != null) missingValues.add (l);
						if (r != null) missingValues.add (r);
					} else {
						// determine if we are whole!
						if (missingValues.size() == 0) {
							whole = true;
						}
					}

					return; // DONE!
				}
			}
		}

	}
}
