package shapes.model;

import shapes.view.*;
import junit.framework.TestCase;

public class TestVerticalConnections extends TestCase {
	
	public void testVerticalConnection() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		Shape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		Shape s4  = s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", s4.toString());
		
		// same thing happens here...
		s4 = s3.verticalConnect(s4, 12, 12);
		assertEquals ("other\nsample\nlast\n", s4.toString());
		
	}
	
	public void testVerticalConnectionPoems() {
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
		
		// now connect these
		s3 = s3.verticalConnect(s1, 12, 12);
		assertEquals ("night\nlast\nother\nsample\n", s3.toString());
		
	}
	
	public void testVerticalConnectionPoemToWord() {
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
		
	}
	
	public void testVerticalConnectionWordToPoem() {
		Shape s1 = new WordShape(10, 10, new WordDrawer(), "sample");
		Shape s2 = new WordShape(30, 30, new WordDrawer(), "other");
		Shape s3 = new WordShape(30, 30, new WordDrawer(), "last");
		
		// hard to control the behavior of vertical connection. Idea is to 
		// connect s2 vertically to s1, and s2 is placed at the head...
		s1  = s1.verticalConnect(s2, 12, 12);
		assertEquals ("other\nsample\n", s1.toString());
		
		// now connect these
		s3 = s3.verticalConnect(s1, 12, 12);
		assertEquals ("other\nsample\nlast\n", s3.toString());
		
	}
}
