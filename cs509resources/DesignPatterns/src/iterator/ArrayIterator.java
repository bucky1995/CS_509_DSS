package iterator;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Integer> {

	/** Store array reference. */
	int ar[];

	/** State of iteration. */
	int idx;

	/** max. */
	final int max;

	/** Construct iterator object. */
	public ArrayIterator (int[] ar, int max) {
		this.ar = ar;

		idx = 0; // start at 0th element
		this.max = max; 
	}

	@Override
	public boolean hasNext() {
		return (idx < max);
	}

	@Override
	public Integer next() {
		int val = ar[idx++];
		return val;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Unable to remove values from underlying array.");
	}

}
