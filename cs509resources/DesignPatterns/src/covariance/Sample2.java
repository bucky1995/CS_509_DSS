package covariance;

/**
 * Compute ln (1+x) using Maclaurin Series
 * 
 * @author George Heineman (heineman@cs.wpi.edu)
 *
 */
public class Sample2 implements ICalculation {

	/** Number of terms. */
	public static final int numTerms = 16;
	
	/**
	 * Using Maclaurin Series, we know that
	 * 
	 *  ln(1+x) = x - x^2/2 + x^3/3 - x^4/4 + ...
	 *  
	 *   Precondition: 1 > x > -1
	 */
	public double compute(float x) {
		double result = x;
		
		double numerator = x;
		for (int term = 2; term < numTerms; term++) {
			numerator *= -x;   // alternates
			result += numerator/term;
		}
		
		return result;
	}

	

}