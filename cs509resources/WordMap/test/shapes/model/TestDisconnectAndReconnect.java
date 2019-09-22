package shapes.model;

import shapes.controller.moves.DisconnectState;
import shapes.view.*;
import junit.framework.TestCase;

public class TestDisconnectAndReconnect extends TestCase {
	
	
	
	public void testDisconnect() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		Shape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		s1  = s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", s1.toString());
		
		// now connect these
		s1 = s1.verticalConnect(s3, 12, 12);
		assertEquals ("other\nsample\nlast\n", s1.toString());
		
		
		// about half way down and should pull off 1st row (second one counting from 0)
		// and the 0th word in that row. Poem starts at (30,30)
		DisconnectState ds = s1.split(34, 52); 
		assertEquals (1, ds.originalRow);
		assertEquals (0, ds.originalOffset);
		
		Shape s = ds.getNewShape();
		assertEquals ("sample\nlast\n", s.toString());
		
		// reconnect!
		s1.reconnect(ds);
		
		assertEquals ("other\nsample\nlast\n", s1.toString());
		
	}
	
}
