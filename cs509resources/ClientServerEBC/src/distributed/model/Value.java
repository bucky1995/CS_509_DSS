package distributed.model;


/**
 * Represents a piece of integer information.
 * 
 * Value can only be incremented and decremented from its initial value.
 */
public class Value {

	/** smallest value allowed. */
	int min;
	
	/** largest value allowed. */
	int max;
	
	/** current value. */
	int value;
	
	/** 
	 * Construct entity capable of retaining value in [min,max] range.
	 * 
	 * Initial value is given.
	 * @param min    min value of the allowed range
	 * @param max    max value of the allowed range
	 * @param v      current value
	 */
	Value (int min, int max, int v) {
		this.min = min;
		this.max = max;
		this.value = v;
	}
	
	/** 
	 * Construct entity capable of retaining value in [min,max] range.
	 * 
	 * Initial value is minimum in the range.
	 * 
	 * @param min    min value of the allowed range
	 * @param max    max value of the allowed range
	 */
	Value (int min, int max) {
		this (min, max, min);
	}
	
	/** Reduce value up until minimum. If attempt to decrease too much, exception occurs. */
	public void decrement () {
		if (value == min) { throw new RuntimeException ("Value cannot be decreased!"); }
		
		value--;
	}
	
	/** Increase value up until maximum. If attempt to increase too much, exception occurs. */
	public void increment () throws RuntimeException {
		if (value == max) { throw new RuntimeException ("Value cannot be increased!"); }
		
		value++;
	}
	
	/** Return current value. */
	public int getValue() { return value; }
	
	/** Return minimum allowed value. */
	public int getMinimum () { return min; }
	
	/** Return maximum allowed value. */
	public int getMaximum () { return max; }
	
	/** Return value as string. */
	public String toString() { return "" + value; }

}







