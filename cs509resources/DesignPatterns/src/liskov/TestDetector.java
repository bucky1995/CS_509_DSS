package liskov;

import junit.framework.TestCase;

public class TestDetector extends TestCase {
	public void testInteger() {
		int f1x = 1;
		int f1y = 1;
		int f2x = 2;
		int f2y = 2;
		int f3x = 3;
		int f3y = 3;
		
		IntRightHandDetector rhd = new IntRightHandDetector();
		rhd.append(f1x, f1y);
		rhd.append(f2x, f2y);
		rhd.append(f3x, f3y);
		
		assertFalse (rhd.isLeftTurn());
	}
	
	// big number check
	public void testBigFloat() {
		int c = 1823767;
		float k = 3517293.8f;
		
		float f1x = -1.0f/30000.0f;
		float f1y = -c/30000.0f;
		float f2x = 33;
		float f2y = 33*c;
		float f3x = 12345679*k;
		float f3y = 12345679*c*k;		
		FloatRightHandDetector rhd = new FloatRightHandDetector();
		rhd.append(f1x, f1y);
		rhd.append(f2x, f2y);
		rhd.append(f3x, f3y);
		
		assertTrue (rhd.isCollinear());
	}
	
	public void testBigDouble() {
		int c = 1823767;
		double k = 3517293.8;
		
		double f1x = -1.0/30000.0;
		double f1y = -c/30000.0;
		double f2x = 33;
		double f2y = 33*c;
		double f3x = 12345679*k;
		double f3y = 12345679*c*k;		
		DoubleRightHandDetector rhd = new DoubleRightHandDetector();
		rhd.append(f1x, f1y);
		rhd.append(f2x, f2y);
		rhd.append(f3x, f3y);
		
		assertTrue (rhd.isCollinear());
	}
}
 
