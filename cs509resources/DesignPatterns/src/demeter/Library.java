package demeter;

import java.util.Hashtable;

public class Library {

	/** The books. */
	Hashtable<String, Book> books = new Hashtable<String, Book>();
	
	public Library () {
		
	}
	
	/** Add book with given id. */
	public void addBook (String id, Book b) {
		books.put (id, b);
	}
	
	/** Return book from library. */
	public Book getBook (String id) { 
		return books.get(id);
	}
	
	/** Prefered method to change the authorship for a book. */
	public boolean changeAuthor (String id, Author newAuthor) {
		Book b = getBook(id);
		if (b == null) { return false; }
		
		b.setAuthor(newAuthor);
		return true;
	}
}
