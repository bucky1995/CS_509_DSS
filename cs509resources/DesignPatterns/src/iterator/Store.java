package iterator;


import java.util.Iterator;

public class Store {

	public static final int max = 10;

	/** storage. */
	int[] values = new int[max];

	/** count. */
	int idx = 0;

	public Store() { 

	}

	/** store. */
	public void add (int val) {
		values[idx++] = val;
	}

	/** return the ith. */
	public int getValue (int i) {
		return values[i];
	}

	/** Return the count. */
	public int getCount() {
		return idx;
	}

	public Iterator<Integer> iterator() {
		return new ArrayIterator (values, idx);
	}
}
