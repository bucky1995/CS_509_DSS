package iterator.v0;

import junit.framework.TestCase;

/**
 * Simple set of test cases
 * @author heineman
 *
 */
public class TestSet extends TestCase {
	
	public void testEmptySet() {
		MySet s = new MySet();
		assertTrue (s.size() == 0);
	}
	
	public void testRemove() {
		MySet s = new MySet();
		s.add(3);
		s.remove(3);
		assertEquals (0, s.size());
	}
	
	// this one fails. Hmmm. I wonder why.
	public void testMultiple () {
		MySet s = new MySet();
		s.add(3);
		s.add(3);
		s.remove(3);
		assertEquals (0, s.size());
		
	}
}
