package shapes.model;

import junit.framework.TestCase;

public class TestBoard extends TestCase {
	
	public void testPreventNull() {
		Board b  = new Board();
		try {
			b.add(null);
			fail ("Must detect null insertion");
		} catch (Exception e) {
			
		}
	}
}
