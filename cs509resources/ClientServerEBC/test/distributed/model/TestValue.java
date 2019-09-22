package distributed.model;
import junit.framework.TestCase;

/**
 * Demonstration test case to validate Value class.
 */
public class TestValue extends TestCase {

	public void testValueAndIncrement() {
		Value v = new Value(1, 5, 3);
		assertEquals (3, v.getValue());
		assertEquals (1, v.getMinimum());
		assertEquals (5, v.getMaximum());
		
		v.increment();
		assertEquals (4, v.getValue());
		assertEquals ("4", v.toString());
		
		v.increment();
		assertEquals (5, v.getValue());
		
		try {
			v.increment();
			fail("Should throw exception.");
		} catch (RuntimeException re) {
			assertEquals (5, v.getValue());
		}
		
	}

	public void testValueAndDecrement() {
		Value v = new Value(1, 5);
		assertEquals (1, v.getValue());
		assertEquals (1, v.getMinimum());
		assertEquals (5, v.getMaximum());
		
		v.increment();
		assertEquals (2, v.getValue());
		
		v.decrement();
		assertEquals (1, v.getValue());
		
		try {
			v.decrement();
			fail("Should throw exception.");
		} catch (RuntimeException re) {
			assertEquals (1, v.getValue());
		}
		
	}
	
}