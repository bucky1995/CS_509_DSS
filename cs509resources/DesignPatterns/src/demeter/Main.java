package demeter;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Library l = new Library();
		
		Book b1 = new Book(new Author("George", "Heineman"), "CBSE: Putting the Pieces Together");
		Book b2 = new Book(new Author("Dr.", "Phil"), "Why I am Better than you");
		
		l.addBook("10", b1);
		l.addBook("20", b2);
		
		// change author of CBSE to William Councill.
		// Do You Like This???? What if book can't be found? What if multi-threaded
		// access in between these two methods? What if exception thrown at second 
		// method request? Implies a DEEP understanding of library
		l.getBook("10").getAuthor().setFirstName("William");
		l.getBook("10").getAuthor().setFirstName("Councill");
	}

}
