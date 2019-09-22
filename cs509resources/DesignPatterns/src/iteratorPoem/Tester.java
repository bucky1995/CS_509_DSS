package iteratorPoem;

import java.util.Iterator;

import junit.framework.TestCase;

public class Tester extends TestCase {
	public void testIterator() {
		String s = "With Time's injurious hand crush'd and o'erworn;";

		Iterator<String> it = new PoemIterator(s);

		assertEquals ("with", it.next());
		assertEquals ("time's", it.next());
		assertEquals ("injurious", it.next());
		assertEquals ("hand", it.next());
		assertEquals ("crush'd", it.next());
		assertEquals ("and", it.next());
		assertEquals ("o'erworn", it.next());
	}
}
