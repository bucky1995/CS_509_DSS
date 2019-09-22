package iterator;

import java.util.Iterator;

public class IteratorExample {
	public static void main(String[] args) {
		Aggregate ag = new Aggregate();
		
		// standard iterator usage for aggregate
		Iterator<Integer> it = ag.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		// use enhanced for loop for even easier access to Iterable objects
		for (int i : ag) {
			System.out.println(i);
		}
	}
}
