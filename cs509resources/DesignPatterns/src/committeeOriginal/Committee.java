package committeeOriginal;

import java.util.Hashtable;
import java.util.Vector;
/**
 * Represents a Committee.
 * <p>
 * <ol>
 * <li>A person can serve on as many as two committees only in any given year.</li>
 * <li>Each committee can have three to five persons as members in any given year.</li>
 * <li>A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * <p>
 * While a committee is being formed, its status is "open". Once Three or Four Persons
 * are assigned to a committee, it is "active". Once Five Persons are assigned to a
 * committee, it is "full".
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
	 * Each Committee has a series of post pairs. [package private].
	 * <p>
	 * Entries in post are Person [0] and Year [1]
	 */	
	Object posts[][] = new Object[0][2];

	/**
	 * Status of this committee: "open", "active", or "full". [package private].
	 * Note that this must be indexed by year. Initially, there are no years, so
	 * by default all years are open.
	 */
	String  status[] = {};
	/**
	 * Years correlated with status index. [package private].
	 * Note that this must be indexed by year. Initially, there are no years, so
	 * by default all years are open.
	 */
	Year years[] = {};

	/** Active status. */	
	public static final String active = "active";

	/** Open status. */
	public static final String open = "open";

	/** Full status. */
	public static final String full = "full";
/**
 * Every committee must have a name.
 * <p>
 * @param name   the <code>String</code> representing the name of the committee.
 */
public Committee(String name) {
	this.name = name;
}
/**
 * Add a Post to the committee for the given Person and Year.
 * <p>
 * This is the core operation for the Committee, Person, and Year classes.
 * <p>
 * We must ensure the constraints regarding (Person,Committee,Year):
 * <p>
 * <ol>
 * <li>[C1] A person can serve on as many as two committees only in any given year.</li>
 * <li>[C2] Each committee can have three to five persons as members in any given year.</li>
 * <li>[C3] A person can serve between one to four years on any specific committee.</li>
 * </ol>
 * <p>
 * As the core operation for these classes, we are responsible for all constraints, but
 * we verify them through delegation to the appropriate classes: Person does [C1] and
 * [C3].
 * <p>
 * @param person   the <code>Person</code> object for the person joining the committee
 *                 in the given year.
 * @param year     the <code>Year</code> object for the year during which the person is
 *                 joining the committee.
 * @return true if the Person/Year was added to the postings; false otherwise (because already existed or other constraint violated).
 */
public boolean addPost(Person person, Year year) {

	// IF WE ARE FULL for given YEAR then we can't be added.
	if (isFull (year)) {
		System.err.println ("Committee is full for year \"" + year.getYear() + "\".");
		return false;
	}
	
	// Assume we can add it.
	Object newPosts[][] = new Object[posts.length+1][2];

	// Now the element to be added could be in the array at any location. This enables
	// us to search and, when found, return false.
	for (int i = 0; i < posts.length; i++) {
		Person existingPerson = (Person) posts[i][0];
		Year   existingYear   = (Year)   posts[i][1];
		
		if (person.getName().equals (existingPerson.getName())) {
			if (year.getYear() == existingYear.getYear()) {
				return false;  // nothing to do since already in the list.
			}
		}

		newPosts[i][0] = posts[i][0];
		newPosts[i][1] = posts[i][1];
	}

	// If we get here, then we aren't in the array so we can add at the end.
	newPosts[newPosts.length-1][0] = person;
	newPosts[newPosts.length-1][1] = year;

	// keep old one in case we run into difficulties in adding Post to Person and Year.
	Object oldPosts [][] = posts;
	posts = newPosts;

	// Now that we have added to this committee, we add to the Person and then Year.
	// Take special care to ensure no constraints are violated, and we clean up after
	// ourselves if we must terminate the computation.
	if (person.addPost (this, year) == false) {
		posts = oldPosts;
		return false;
	}
	if (year.addPost (this, person) == false) {
		person.removePost (this, year);
		posts = oldPosts;
		return false;
	}

	// update our status and then return true.
	updateStatus ();
	
	return true;
}
/**
 * Return name of this committee.
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * Return the posts for this Committee.
 * <p>
 * <pre>
 * posts[*][0] = Person
 * posts[*][1] = Year
 * </pre>
 * @return java.lang.Object[][]
 */
public Object[][] getPosts() {
	return posts;
}
/**
 * Determine whether committee is full for the given year.
 * @return boolean
 * @param year jan13.Year
 */
public boolean isFull(Year year) {
	for (int i = 0; i < years.length; i++) {
		if (years[i].getYear() == year.getYear()) {
			if (status[i].equals (full)) {
				return true;
			}
		}
	}

	return false;
}
/**
 * Remove Person/Year from this committee postings.
 * <p>
 * @param person   the <code>Person</code> object for the person being remove from the committee
 *                 in the given year.
 * @param year     the <code>Year</code> object for the year during which the person is
 *                 being removed from the committee.
 * @return true if the Person/Year was successfully removed from the set of postings; false otherwise.
 */
public boolean removePost(Person person, Year year) {
	// assume we can delete
	Object tmpPosts[][] = new Object[posts.length][2];

	// In this algorithm, we remove a post by replacing the entry with the last entry
	// and then truncating the array.
	System.arraycopy (posts, 0, tmpPosts, 0, posts.length);

	boolean found = false;
	for (int i = 0; i < tmpPosts.length; i++) {
		Person existingPerson = (Person) posts[i][0];
		Year   existingYear   = (Year)   posts[i][1];
		
		if (person.getName().equals (existingPerson.getName())) {
			if (year.getYear() == existingYear.getYear()) {
				// swap with last position
				tmpPosts[i] = tmpPosts[tmpPosts.length-1];
				found = true;
				break;    // THIS WAS CONTINUE. Nice Defect!
			}
		}
	}

	// not removed, so leave now
	if (!found) return false;
	
	// Truncate
	Object newPosts[][] = new Object[posts.length-1][2];
	System.arraycopy (tmpPosts, 0, newPosts, 0, tmpPosts.length-1);   // had copied from posts. NICE DEFECT!
	
	posts = newPosts;

	// Now must remove from Person and Year
	person.removePost (this, year);
	year.removePost (this, person);
	
	// update our status and then return true.
	updateStatus ();
	return true;
}
/**
 * Construct String object representing committee.
 * @return   String
 */
public String toString () {
	// no assignments yet? Open.
	if (status.length == 0) {
		return name + "[" + open + "]";
	}

	// optimization: If all status values are the same, return that as the status
	boolean allSame = true;
	for (int i = 1; i < status.length; i++) {
		if (!status[i].equals (status[0])) {
			allSame = false;
			break;
		}
	}
	if (allSame)
		return name + "[" + status[0] + "]";
	
	// append together all years. 
	String retValue = name + "[";
	for (int i = 0; i < years.length; i++) {
		retValue = retValue + years[i].getYear() + ":" + status[i];
		if (i != years.length-1) retValue = retValue + " ";
	}
	retValue = retValue + "]";

	return retValue;
}
/**
 * Update the status for this committee.
 * <p>
 * Recalculates 'years' and 'status' arrays each time.
 */
public void updateStatus() {
	Hashtable yearCount = new Hashtable();
	for (int i = 0; i < posts.length; i++) {
		Year y = ((Year) posts[i][1]);

		Integer count = (Integer) yearCount.get (y);
		if (count == null) {
			count = new Integer (1);   // HAH! This was actually ZERO. Nice Defect
		} else {
			count = new Integer (count.intValue() + 1);
		}
		
		yearCount.put (y, count);
	} 

	// construct array of Year objects from the keys of the hashtable
	Object objs[] = yearCount.keySet().toArray();
	years = new Year [objs.length];
	System.arraycopy (objs, 0, years, 0, objs.length);

	// construct array of Status objects to be open/active/full
	objs = yearCount.values().toArray();
	Integer totalCount[] = new Integer [objs.length];
	System.arraycopy (objs, 0, totalCount, 0, objs.length);
	
	status = new String [totalCount.length];
	for (int i = 0; i < status.length; i++) {
		Integer count = totalCount[i];
		if (count.intValue() < 3) {
			status[i] = open;
		} else if (count.intValue() < 5) {
			status[i] = active;
		} else {
			status[i] = full;
		}
	}
}
}
