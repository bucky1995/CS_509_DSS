package demeter;

public class Author {

	/** Author. */
	String fname;
	String lname;
	
	/** Construct the book. */
	public Author (String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	public void setFirstName(String f) {
		this.fname = f;
	}
	
	public void setLastName(String l) {
		this.lname = l;
	}
	
	
}
