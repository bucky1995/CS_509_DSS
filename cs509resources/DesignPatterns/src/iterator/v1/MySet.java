package iterator.v1;

import java.util.Iterator;

/**
 * Declare intention for set to support Iterator as specified in JDK collections
 * @author heineman
 *
 */
public class MySet {

	/** Store set as array. */
	int elements[] = new int [10];
	
	/** count. */
	int count;
	
	
	/** Add element to set. */
	public void add(int n) {
		// defect fix: Deny attempts to insert same element.
		if (contains(n)) { return; }
		elements[count++] = n;
	}
	
	/** Return the size of the array. */
	public int size() {
		return count;
	}
	
	/** Remove element from set, if it exists. */
	public boolean remove (int n) {
		for (int i = 0; i < count; i++) {
			if (elements[i] == n) {
				eliminate(i);
				return true;
			}
		}
		
		return false;
	}
	
	/** Helper method to reduce array. */
	void eliminate(int i) {
		elements[i] = elements[count-1];
		count--;
	}

	public boolean contains (int n) {
		for (int i = 0; i < count; i++) {
			if (elements[i] == n) {
				return true;
			}
		}
		
		return false;
	}

	/** Expose iterator over all elements. */
	public Iterator<Integer> iterator() {
		return new ArrayIterator(elements, count);
	}
}
