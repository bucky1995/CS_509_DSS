package shapes.model;

import shapes.view.*;
import junit.framework.TestCase;

public class TestHorizontalConnections extends TestCase {
	
	public void testHorizontalConnection() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		Shape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		Shape s4  = s1.horizontalConnect(s2, 12, 12);
		assertEquals ("other sample\n", s4.toString());
		
		// same thing happens here...
		s4 = s3.horizontalConnect(s4, 12, 12);
		assertEquals ("other sample last\n", s4.toString());
		
	}
	
	public void testHorizontalConnectionPoemsFails() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		
		Shape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		Shape s4 = new WordShape(30, 30, new WordDrawer(), "night");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		s1  = s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", s1.toString());
		
		s3  = s3.verticalConnect(s4, 12, 12);
		assertEquals ("night\nlast\n", s3.toString());
		
		// now demonstrate no luck
		Shape badOne = s3.horizontalConnect(s1, 10, 10);
		assertTrue (badOne == null);
	}
	
	public void testHorizontalConnectionPoemMiddleRow() {
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
		
		Shape s4 = new WordShape(30, 30, new WordDrawer(), "hello");
		s1 = s1.horizontalConnect(s4, 35, 42);
		assertEquals ("hello other\nsample\nlast\n", s1.toString());
		
		s4 = new WordShape(30, 30, new WordDrawer(), "hello");
		s1 = s1.horizontalConnect(s4, 35, 52);
		assertEquals ("hello other\nhello sample\nlast\n", s1.toString());
	}
	
	
}
