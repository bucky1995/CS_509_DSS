package delegation;

import java.util.Hashtable;
import java.util.Iterator;

/** Store books. */
public class MyLibraryGood implements Iterable<Book> { 
	Hashtable<String, Book> books = new Hashtable<String, Book>();
	
	public boolean add (Book b) {
		if (books.containsKey(b.isbn)) {
			return false;
		}
		
		books.put(b.isbn, b);
		return true;
	}

	public Iterator<Book> iterator() {
		return books.values().iterator();
	}
	
}
