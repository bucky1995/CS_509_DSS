package iterator;

import java.util.Iterator;

public class Driver {
	public static void main(String[] args) {
		Store s = new Store();

		s.add(13);
		s.add(17);

		System.out.println("From Scratch");
		for (int i = 0; i < s.getCount(); i++) {
			System.out.println(s.getValue(i));
		}
		
		
		// proper way
		System.out.println("Via Iterator");
		Iterator<Integer> it = s.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}

