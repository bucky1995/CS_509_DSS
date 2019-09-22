package shapes.model;

import shapes.view.*;
import junit.framework.TestCase;

public class TestCopy extends TestCase {
	
	public void testEquals() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = s1.copy();
		
		assertEquals (s1, s2);
		assertEquals (s2, s1);
	}
	
	public void testEqualsCopy() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		WordShape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		PoemShape newPoem  = (PoemShape) s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", newPoem.toString());
		
		newPoem.extend(0, new WordShape[] { s3 });
		assertEquals ("other last\nsample\n", newPoem.toString());
		
		Shape intermediate = newPoem.copy();
		
		newPoem.extend(1, new WordShape[] { s3 });
		assertEquals ("other last\nsample last\n", newPoem.toString());
		
		newPoem.extend(0, new WordShape[] { s3 });
		assertEquals ("other last last\nsample last\n", newPoem.toString());
		
		Shape newPoem2 = newPoem.copy();
		
		assertEquals (newPoem, newPoem2);
		assertEquals (newPoem2, newPoem);
		
		assertFalse (newPoem.equals(s1));
		assertFalse (s2.equals(newPoem));
		assertFalse (intermediate.equals(newPoem));
		
		
	}
	
}
