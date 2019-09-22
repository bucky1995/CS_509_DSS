package delegation;

public class BadDriver {
	public static void main(String[] args) {
		Book b = new Book("978-0439023528", "Hunger Games", "Suzanne Collins");
		Book b2 = new Book("978-0545586177", "Catching Fire", "Suzanne Collins");
		
		MyLibraryBad bad = new MyLibraryBad();
		
		bad.put("978-0439023528", b);
		bad.put("978-0545586177", b2);
		
		for (Book book : bad.values()) {
			System.out.println(book);
		}
		
		// store book in hashtable not using proper ISBN. Also
		// book appears twice in the hashtable with two different ids
		bad.put("horrible", b);
		
		for (Book book : bad.values()) {
			System.out.println(book);
		}
	}
}
