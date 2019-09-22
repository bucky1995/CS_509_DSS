package committeeRevised;

/**
 * When a constraint is violated this exception is thrown.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class ConstraintException extends Exception {
/**
 * ConstraintException constructor comment.
 */
public ConstraintException() {
	super();
}
/**
 * ConstraintException constructor.
 * <p>
 * Records the reason for the constraint being violated.
 * @param s java.lang.String
 */
public ConstraintException(String s) {
	super(s);
}
}
