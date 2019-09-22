package shapes;

import java.io.File;
import java.util.ArrayList;

import shapes.model.*;
import shapes.view.*;
import junit.framework.TestCase;

public class TestInitialize extends TestCase {
	
	static String testStorage = "sample-test";	
	
	public void testWriteAndLoad() {
		Board b = new Board();
		Shape s = new WordShape (0, 0, new WordDrawer(), "sample");
		Shape s2 = new WordShape (1, 4, new WordDrawer(), "another");
		
		b.add(s);
		b.add(s2);
		
		File tmpStorage = null;
		try {
			tmpStorage = File.createTempFile("memento", ".bin");
			System.out.println("created " + tmpStorage.getAbsolutePath());
		} catch (Exception e) {
			fail ("unable to create temporary file");
		}
		
		LaunchWordMap.storeState(b, tmpStorage.getAbsolutePath());
		
		Board b2 = LaunchWordMap.loadState(tmpStorage.getAbsolutePath());
		
		tmpStorage.delete();

		// recover state and compare
		ArrayList<Shape> bState = new ArrayList<Shape>();
		ArrayList<Shape> b2State = new ArrayList<Shape>();
		
		for (Shape sh : b) { bState.add(sh); }
		for (Shape sh : b2) { b2State.add(sh); }
		
		assertEquals(bState, b2State);
	}
}
