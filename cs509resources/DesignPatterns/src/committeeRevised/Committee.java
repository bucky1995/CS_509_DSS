package committeeRevised;

import java.util.Hashtable;
import java.util.Vector;
/**
 * Represents a Committee.
 * <p>
 * A committee is effective over many years, and its status is either open (still need members),
 * active (enough members to convene), or full (no more members to be allowed).
 * <p>
 * The toString() method for this class properly reflects the status of the committee.
 * <p>
 * For this scheme to work, we must ensure that each Committee has only one object.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Committee {
	/** Each committee has a name. [package private]. */
	String name;

/**
 * Every committee must have a name.
 * <p>
 * @param name   the <code>String</code> representing the name of the committee.
 */
public Committee(String name) {
	this.name = name;
}
/**
 * Return name of this committee.
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * Construct String object representing committee.
 * @return   String
 */
public String toString () {
	return name;
}
}
