package shapes;

import java.awt.Dimension;

import shapes.model.Board;
import shapes.model.Shape;
import junit.framework.TestCase;

public class TestLaunchWordMap extends TestCase {
	
	public void testLoad() {
		Dimension dim = new Dimension (1000, 1000);
		Board b = LaunchWordMap.initializeWords(dim);
		
		// just verify that words are present
		int count = 0;
		for (Shape s : b) {
			count++;
		}
		
		assertEquals (330, count);
	}
}
