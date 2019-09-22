package committeeRevised;

import java.util.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.*;

/**
 * Driver to test Committee, Year, and Person classes.
 * <p>
 * Note: this driver sends update requests directly to the model, which in this
 * case includes the Committee/Year/Person and Post classes.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public class Driver {
	/**
	 * Post to maintain all integrity constraints. This field is static because
	 * all functioning within the Driver occurs within static methods. [package private]. */
	static Post post;
	
/**
 * Driver constructor comment.
 */
public Driver() {
	super();
}
/**
 * Test out the Committee, Person, Year constrain example.
 *
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
    /** Create a sample set of year objects. */
    Year years[] =
        {
            new Year(2002),
            new Year(2003),
            new Year(2004),
            new Year(2005),
            new Year(2006),
            new Year(2007)};

    /** Create some person objects. */
    Person persons[] =
        {
            new Person("George Bush"),
            new Person("Donald Rumsfeld"),
            new Person("Dick Cheney"),
            new Person("Bill Frist"),
            new Person("Condaleezza Rice"),
            new Person("Ari Fleischer")};

    /** Create some committee objects. */
    Committee committees[] =
        {
            new Committee("Ways and Means"),
            new Committee("Foreign Affairs"),
            new Committee("Homeland Security")};

    /** Get the Post object to manage all constraints. */
    post = Post.getInstance();

    /** Ensure that Post knows of the constraintManager we are going to use. */
	post.setModelChecker (new ConstraintManager (post));
    
    // Assign People to committees
    while (true) {
        outputAssignment(post);

        // select committee
        Committee com = selectCommittee(committees);
        if (com == null)
            break;

        // select remove or add
        boolean doAdd = selectAddRemove();
        Year y;
        Person p;
        if (doAdd) {
            System.out.println("Select the Person and Year to add to this committee.");
            if ((p = selectPerson(persons)) == null)
                break;
            if ((y = selectYear(years)) == null)
                break;

            // add the person/year to the committee
            try {
                if (post.addPost(com, p, y)) {
                    System.out.println("ADD SUCCESSFUL.");
                } else {
                    System.out.println("ADD FAILED!");
                }
            } catch (ConstraintException ce) {
                System.out.println("Unable to add posting:" + ce.toString());
            }
        } else {
            System.out.println("Select the Person and Year on this committee to remove.");
            if ((p = selectPerson(persons)) == null)
                break;
            if ((y = selectYear(years)) == null)
                break;

            try {
                if (post.removePost(com, p, y)) {
                    System.out.println("REMOVE SUCCESSFUL.");
                } else {
                    System.out.println("REMOVE FAILED!");
                }
            } catch (ConstraintException ce) {
                System.out.println("Unable to remove posting:" + ce.toString());
            }

        }
    }
}
/**
 * Output the current assignment set.
 * <p>
 * @param post   Post object containing all postings.
 */
public static void outputAssignment(Post post) {
	System.out.println ("Current committees:");

	// group all postings by Committee
	Hashtable comGroup = new Hashtable();
	Iterator it = post.getPostings();
	while (it.hasNext()) {
		Posting posting = (Posting) it.next();
		
		Committee com = posting.committee;

		// find the existing set of postings and create or add to the set as appropriate.
		ArrayList oldList = (ArrayList) comGroup.get (com.getName());
		if (oldList == null) {
			oldList = new ArrayList();
			comGroup.put (com.getName(), oldList);
		}
		oldList.add (posting);
	}

	// now go through the comGroup hashtable and output each committee one at a time.
	for (it = comGroup.keySet().iterator(); it.hasNext(); ) {
		String comName = (String) it.next();
		ArrayList al = (ArrayList) comGroup.get (comName);

		System.out.println (comName);
		for (Iterator innerIt = al.iterator(); innerIt.hasNext(); ) {
			Posting posting = (Posting) innerIt.next();

			System.out.println ("   " + posting.year + " " + posting.person);
		}
	}
}
/**
 * Read an integer from the user (or return -1 on invalid input).
 * @return  int
 */
public static int readInteger() {
	String s = readString();
	if (s == null) return -1;

	// convert string into an integer
	try {
		int ival = Integer.valueOf (s).intValue();
		return ival;
	} catch (NumberFormatException nfe) {
		// if invalid input, return -1
		return -1;
	}
}
/**
 * Read a string from the user (return null if no input).
 * @return String
 */
public static String readString() {
	// Believe it or not, this is the simplest way to read input
	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	try {
		String sval = br.readLine();
		return sval;
	} catch (IOException ioe) {
		return null;
	}
}
/**
 * Select Add or Remove
 * <p>
 * @return true  if add Selected; false if remove or invalid selection.
 */
public static boolean selectAddRemove () {
	System.out.println ("\n===============SELECT ADD or REMOVE=================\n");
	System.out.println ("1. Add");
	System.out.println ("2. Remove");
	int i = readInteger ();
	if (i == 1) return true;
	return false;
}
/**
 * Select a committee from the set or return null.
 * <p>
 * @param committees Committee[]
 */
public static Committee selectCommittee (Committee committees[]) {
	System.out.println ("\n===============SELECT COMMITTEE=================\n");
	for (int i = 0; i < committees.length; i++) {
		System.out.println (i + ". " + committees[i].getName());
	}
	
	System.out.println ("\nselect number from 0.."+(committees.length-1)+" (or -1 to quit).");
	int val = readInteger();
	if ((val < 0) || (val > committees.length)) return null;

	return committees[val];
}
/**
 * Select a person from the set or return null.
 * <p>
 * @param committees Committee[]
 */
public static Person selectPerson (Person persons[]) {
	System.out.println ("\n===============SELECT PERSON=================\n");
	for (int i = 0; i < persons.length; i++) {
		System.out.println (i + ". " + persons[i].getName());
	}
	
	System.out.println ("\nselect number from 0.."+(persons.length-1)+" (or -1 to quit).");
	int val = readInteger();
	if ((val < 0) || (val > persons.length)) return null;

	return persons[val];
}
/**
 * Select a person from the set or return null.
 * <p>
 * @param committees Committee[]
 */
public static Year selectYear (Year years[]) {
	System.out.println ("\n===============SELECT YEAR=================\n");
	for (int i = 0; i < years.length; i++) {
		System.out.println (i + ". " + years[i].getYear());
	}
	
	System.out.println ("\nselect number from 0.."+(years.length-1)+" (or -1 to quit).");
	int val = readInteger();
	if ((val < 0) || (val > years.length)) return null;

	return years[val];
}
}
