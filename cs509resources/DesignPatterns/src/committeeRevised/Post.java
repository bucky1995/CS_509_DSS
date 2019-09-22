package committeeRevised;

import java.util.*;

/**
 * The post relationship among the different classes is elevated to
 * the nature of an object.
 * <p>
 * This class if fully responsible for storing all information about Person/Year/Committee
 * relationships. It expects a new ConstraintManager to be responsible for ensuring whether
 * any constraints are violated. In this way, we separate the mechanism (storing of Post
 * information) from the Policy (as to be found in the ConstraintManager class).
 * <p>
 * The Committee/Person/Year classes delegate to this Post class all responsibilty for storing
 * information about the postings.
 * <p>
 * The primary change, thus, is that all classes remove their internal post responsibilities
 * and this Post class, together with ConstraintManager, handles it all.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Post {

	/** Active status when a committee is active in a given year. */	
	public static final String active = "active";

	/** Open status when a committee is open in a given year. */
	public static final String open = "open";

	/** Full status when a committee is full in a given year. */
	public static final String full = "full";

	
	/** Singleton instance. [protected access] */
	protected static Post _instance = null;

	/** 
	 * Model Checker for the Post instance. 
	 * The following creates an object of an anonymous class whose sole purpose in life
	 * is to act as a ModelChecker that always returns true.
	 * <p>
	 * In doing so, we avoid some boundary cases in the addPost/removePost code.
	 */
	protected ModelChecker modelChecker = new ModelChecker () {
		public boolean checkModel() {return true;}
	};

	
	/** 
	 * All post information is maintained in an ArrayList of objects.
	 * Elements of this ArrayList are of (local) type Posting.
	 */
	java.util.ArrayList postings = new java.util.ArrayList();
	
/**
 * To protect against the inadvertent creation of multiple Post objects, we restrict
 * access to the Post constructor and impose a SINGLETON design pattern on this class.
 * <p>
 * In a singleton, only one object of a class is instantiated, and the class maintains
 * total control over instantiating and giving this object out to other classes. Use
 * the <code>getInstance()</code> method, a static method, to retrieve the instance of
 * the Post class.
 */
protected Post() {
	
}
/**
 * Adds a new (Committee, Person, Year) relationship.
 * <p>
 * This method is also responsible for updating the status (open,closed,active) for
 * each committee, for those years during which the committee is active.
 * <p>
 * If the posting is already part of the set, then we return false
 * <p>
 * @return true if successfully added to the set of postings; false if the posting is already in the set.
 * @exception ConstraintException    if the new addition violates an existing constraint.
 */
public boolean addPost (Committee committee, Person person, Year year) throws ConstraintException {
	Posting newPost = new Posting (committee, person, year);

	if (postings.contains (newPost)) {
		return false;
	}
	
	// Create new Posting and add to our set.
	postings.add (newPost);

	// Check the model. If this throws an exception, then we must remove posting and
	// throw the exception back out to our caller.
	try {
		return modelChecker.checkModel();
	} catch (ConstraintException ce) {
		// remove the newly added post and simply throw the exception back on out.
		postings.remove (newPost);
		throw ce;
	}
}
/**
 * Return singleton instance of the Post class as managed by this class.
 * <p>
 * @return Post
 */
public static Post getInstance() {
	if (_instance == null) {
		_instance = new Post();
	}

	return _instance;
}
/**
 * Return an Iterator associated with all postings to date.
 */
public Iterator getPostings () {
	return postings.iterator();
}
/**
 * Removes a new (Committee, Person, Year) relationship.
 * <p>
 * I can't immediately think of a good use of throwing an exception, but this doesn't
 * mean one doesn't exist! I keep it here to be symmetric with addPost.
 * <p>
 * @return true if successfully removed; false if not in the set of postings.
 * @exception ConstraintException    if the removal violates an existing constraint.
 */
public boolean removePost (Committee committee, Person person, Year year) throws ConstraintException {
	Posting oldPost = new Posting (committee, person, year);

	// Create new Posting and add to our set.
	return postings.remove (oldPost);
}
/**
 * Each Post has an associated model checker to verify the integrity of the postings.
 * <p>
 * Since this is a member function, this can only be invoked after the object has
 * been requested via getInstance().
 */
public void setModelChecker (ModelChecker mc) {
	modelChecker = mc;
}
}
