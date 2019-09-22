package shapes.model;

import shapes.view.*;
import junit.framework.TestCase;

public class TestShape extends TestCase {
	
	public void testBoard() {
		Board b = new Board();
		try {
			b.add(null);
			fail ("MISSED Exception");
		} catch (Exception e) {
			// success
		}
	}
	
	public void testConstruction() {
		Shape s = new WordShape(10, 10, new WordDrawer(), "sample");
		assertEquals ("sample", s.getContents());
	}
	
	public void testMove() {
		Shape s = new WordShape(10, 10, new WordDrawer(), "sample");
		s.setLocation(30, 40);
		assertEquals (30, s.getX());
		assertEquals (40, s.getY());
	}
}
