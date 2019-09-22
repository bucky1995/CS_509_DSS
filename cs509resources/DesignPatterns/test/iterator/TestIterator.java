package iterator;

import junit.framework.TestCase;

public class TestIterator extends TestCase {
	public void testEmpty() {
		ArrayIterator ai = new ArrayIterator (new int[0], 0);
		assertFalse (ai.hasNext());
	}

	public void testOne() {
		ArrayIterator ai = new ArrayIterator (new int[]{13}, 1);
		assertTrue (ai.hasNext());
		assertEquals (new Integer(13), ai.next());
		assertFalse (ai.hasNext());
	}

	public void testMany() {
		ArrayIterator ai = new ArrayIterator (new int[]{13,15,18}, 3);
		int total = 0;
		while (ai.hasNext()) {
			total += ai.next();
		}

		assertEquals (46, total);
	}

	public void testError() {
		ArrayIterator ai = new ArrayIterator (new int[]{13,15,18}, 3);
		try {
			ai.remove();
			fail("Shouldn't support remove.");
		} catch (Exception e) {

		}
	}
}
