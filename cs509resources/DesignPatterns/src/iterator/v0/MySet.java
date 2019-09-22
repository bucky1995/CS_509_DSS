package iterator.v0;

public class MySet {

	/** Store set as array. */
	int elements[] = new int [10];
	
	/** count. */
	int count;
	
	
	/** Add element to set. */
	public void add(int n) {
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

	// how do I return the elements of this set ???
	public int getIth (int i) {
		return elements[i];
	}
}
