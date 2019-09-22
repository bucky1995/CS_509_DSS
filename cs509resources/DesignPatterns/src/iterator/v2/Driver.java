package iterator.v2;


import java.util.Iterator;

public class Driver {
	public static void main(String[] args) {
		MySet s = new MySet();
		
		s.add(3);
		s.add(10);
		s.add(7);
		
		// you may note that the elements appear in a different order 
		// than what was inserted (as opposed to the array implementation).
		// this is because hashtables store information without reference
		// (in most cases) to the order in which you inserted the items.
		for (Iterator<Integer> it = s.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
		// enhanced for loop.]
		System.out.println("And now for the enhanced for loop output...\n");
		for (int i : s) {
			System.out.println(i);
		}

		
	}
}
