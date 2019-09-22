package committeeRevised;

/**
 * Represents a year during which a committee is in session.
 * <p>
 * For this scheme to work, we must ensure that each Year has only one object
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Year {
	/** Each Year has an integer year. [package private]. */
	int year;
/**
 * Each Year represents a given calendar year.
 * @param year   the int representing the year. 
 */
public Year(int year) {
	super();

	// Can you believe that I actually left this out and had to add it in
	// during my debugging!  Goes to show that we must always be careful when
	// writing ANY code. [12-Jan-2003 3:55 PM]
	this.year = year;
}
/**
 * Return the value of this year.
 * <p>
 * @return int
 */
public int getYear() {
	return year;
}
/**
 * Return the string representation of this object.
 * @return java.lang.String
 */
public String toString() {
	return ""+year;
}
}
