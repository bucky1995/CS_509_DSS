package bidirectional;

import junit.framework.TestCase;

public class TestCases extends TestCase {
	public void tests() {
		// 1. Verify that Rectangle constructor throws exception with width or height <= 0
		int[][] bad = new int[][] { {-10, 4}, {4, 0} };
		for (int[] b : bad) {
			try {
				new Rectangle(2, 5, b[0], b[1]);
				fail("illegal width,height");
			} catch (Exception e) {
				// success
			}
		}			
		
		// 2. Verify that Point constructor throws exception with Rectangle r is null
		try {
			new Point (1, 20, null);
			fail ("Must have valid rectangle in point constructor");
		} catch (Exception e) {
			// success
		}
		
		// 3. normal execution
		Rectangle r = new Rectangle (10, 20, 30, 40);
		assertEquals (10, r.topLeft.x);
		assertEquals (20, r.topLeft.y);
		assertEquals (10+30, r.bottomRight.x);
		assertEquals (20+40, r.bottomRight.y);
		 
		//4. Verify that once Rectangle r is constructed, r.topLeft.parent == r and r.bottomLeft.parent == r.
		assertEquals (r, r.topLeft.parent);
		assertEquals (r, r.bottomRight.parent);
		
		// 5. Verify that Point cannot be constructed with
		try {
			Point p = new Point (2, 3, r);
			fail ("must detect Point construction with non-associated rectangle");
		} catch (Exception e) {
			// success
		}

	}
}
