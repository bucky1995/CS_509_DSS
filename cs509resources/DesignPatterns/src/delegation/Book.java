package delegation;

public class Book {
	final String isbn;
	final String author;
	final String title;
	
	public Book (String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public String toString() {
		return title + ", " + author + "[" + isbn + "]";
	}
}
