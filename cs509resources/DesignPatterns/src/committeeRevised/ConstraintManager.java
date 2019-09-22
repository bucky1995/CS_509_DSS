package committeeRevised;

import java.util.*;
/**
 * Enforces a specific set of constraints over the Post object.
 * <p>
 * The constraints to be enforced are:
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class ConstraintManager implements ModelChecker {

	/** The Post object containing all information. */
	protected Post post = null;
	
/**
 * ConstraintManager must be constructed with a valid Post object otherwise it will
 * not be able to fullfil its responsibilities.
 */
public ConstraintManager(Post post) {
	this.post = post;
}
/**
 * Request Model Checker to verify the integrity of the model.
 * <p>
 * Note that no information is passed along this method because the
 * model checker is responsible for "pulling" this information from
 * the model. In this way, the ModelChecker interface is completely
 * generic.
 * <p>
 * All the magic happens here!
 * @return true if the model checks out.
 * @exception ConstraintException if a particular constraint is violated.
 */
public boolean checkModel() throws ConstraintException {
	Iterator it;
	String key;
		
    // [C1] A person can serve on as many as two committees only in any given year.
    Hashtable counts = new Hashtable();  // key = person+year, value=integer
	for (it = post.getPostings(); it.hasNext(); ) {
		Posting posting = (Posting) it.next();
		key = posting.person.getName() + posting.year.getYear();
		
		// increment
		Integer count = (Integer) counts.get (key);
		if (count == null) {
			count = new Integer (1);
		} else {
			count = new Integer (count.intValue() + 1);
		}
		
		counts.put (key, count);

		// too much?
		if (count.intValue() > 2) {
			throw new ConstraintException (posting.person + " is serving on more than two committees in year " + posting.year);
		}
	}
    
    // [C2] Each committee can have three to five persons as members in any given year.
    counts = new Hashtable();  // key = committee+year, value=integer
	for (it = post.getPostings(); it.hasNext(); ) {
		Posting posting = (Posting) it.next();
		key = posting.committee.getName() + posting.year.getYear();
		
		// increment
		Integer count = (Integer) counts.get (key);
		if (count == null) {
			count = new Integer (1);
		} else {
			count = new Integer (count.intValue() + 1);
		}
		
		counts.put (key, count);

		// too much?
		if (count.intValue() > 5) {
			throw new ConstraintException ("committee \"" + posting.committee + "\" has too many members in year " + posting.year);
				
		}
	}
    
    // [C3] A person can serve between one to four years on any specific committee.
    counts = new Hashtable();  // key = person+committee, value=integer
	for (it = post.getPostings(); it.hasNext(); ) {
		Posting posting = (Posting) it.next();
		key = posting.person.getName() + posting.committee.getName();
		
		// increment
		Integer count = (Integer) counts.get (key);
		if (count == null) {
			count = new Integer (1);
		} else {
			count = new Integer (count.intValue() + 1);
		}
		
		counts.put (key, count);

		// too much?
		if (count.intValue() > 4) {
			throw new ConstraintException ("person \"" + posting.person+ "\" serves more than four years on committee \"" + posting.committee + "\".");
		}
	}
    // fine!
    return true;
}
}
