package shapes.model;

import shapes.view.*;
import junit.framework.TestCase;

public class TestExtend extends TestCase {
	
	public void testPrepend() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		WordShape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		PoemShape newPoem  = (PoemShape) s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", newPoem.toString());
		
		newPoem.prepend(0, new WordShape[] { s3 });
		assertEquals ("last other\nsample\n", newPoem.toString());
		
		newPoem.prepend(1, new WordShape[] { s3 });
		assertEquals ("last other\nlast sample\n", newPoem.toString());
		
		newPoem.prepend(0, new WordShape[] { s3 });
		assertEquals ("last last other\nlast sample\n", newPoem.toString());
		
	}
	
	public void testExtend() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		WordShape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		PoemShape newPoem  = (PoemShape) s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", newPoem.toString());
		
		newPoem.extend(0, new WordShape[] { s3 });
		assertEquals ("other last\nsample\n", newPoem.toString());
		
		newPoem.extend(1, new WordShape[] { s3 });
		assertEquals ("other last\nsample last\n", newPoem.toString());
		
		newPoem.extend(0, new WordShape[] { s3 });
		assertEquals ("other last last\nsample last\n", newPoem.toString());
		
	}
	
}
