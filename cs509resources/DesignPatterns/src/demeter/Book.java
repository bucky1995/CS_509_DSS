package demeter;

public class Book {

	/** Author. */
	Author author;
	
	/** title information. */
	String title;
	
	/** Construct the book. */
	public Book (Author author, String title) {
		this.author = author;
		this.title = title;
	}
	
	public void setAuthor(Author a) {
		this.author = a;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public Author getAuthor () {
		return author;
	}
	
	public String getTitle () {
		return title;
	}
	
}
