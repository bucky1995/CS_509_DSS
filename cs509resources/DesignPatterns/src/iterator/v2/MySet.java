package iterator.v2;

import java.util.Hashtable;
import java.util.Iterator;

/** Delegate actions to the hashtable object. */
public class MySet implements Iterable<Integer> {

	/** Store set as Hashtable. */
	Hashtable<Integer,Boolean> elements = new Hashtable<Integer,Boolean>();
	
	/** Add element to set. */
	public void add(int n) {
		if (elements.containsKey(n)) { return; }
		elements.put(n, true);
	}
	
	/** Return the size of the array. */
	public int size() {
		return elements.size();
	}
	
	/** Remove element from set, if it exists. */
	public boolean remove (int n) {
		return elements.remove(n);
	}

	public boolean contains (int n) {
		return elements.containsKey(n);
	}

	@Override
	public Iterator<Integer> iterator() {
		return elements.keySet().iterator();
	}
	
}
