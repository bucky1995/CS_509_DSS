package iterator;

import java.util.*;

public class Aggregate implements Iterable<Integer> {
	// option 1 uses a raw array
    int[] ar;
	
	// option 2 uses ArrayList<Integer>
	// ArrayList<Integer> ar = new ArrayList<Integer>();
	
	public Aggregate() {
		// option 1 is set this way
		 ar = new int[] { 1, 5, 8};
		
		// option 2 is set this way
//		ar = new ArrayList<Integer>();
//		ar.add(1);
//		ar.add(5);
//		ar.add(8);
	}

	@Override
	public Iterator<Integer> iterator() {
		// option 1
		return new ArrayIterator(ar, ar.length);
		
		// option 2
		//return ar.iterator();
	}
}
