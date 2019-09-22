package oval.model;

import java.awt.Point;

import junit.framework.TestCase;

public class TestOval extends TestCase {
	public void testConstructor() {
		Point p = new Point (30, 60);
		Oval o = new Oval(p, 10, 20);
		
		assertEquals (40, o.height);
		assertEquals (20, o.width);		
	}
}
