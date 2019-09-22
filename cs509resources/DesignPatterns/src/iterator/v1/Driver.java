package iterator.v1;

import java.util.Iterator;

public class Driver {
	public static void main(String[] args) {
		MySet s = new MySet();
		
		s.add(3);
		s.add(10);
		s.add(7);
		
		// Use iterator to access
		for (Iterator<Integer> it = s.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
	}
}
