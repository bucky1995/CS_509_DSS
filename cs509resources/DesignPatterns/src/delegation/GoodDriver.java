package delegation;

public class GoodDriver {
	public static void main(String[] args) {
		Book b = new Book("978-0439023528", "Hunger Games", "Suzanne Collins");
		Book b2 = new Book("978-0545586177", "Catching Fire", "Suzanne Collins");
		
		MyLibraryGood good = new MyLibraryGood();
		good.add(b);
		good.add(b2);
		
		for (Book book : good) {
			System.out.println(book);
		}
		
		// store book in hashtable not using proper ISBN. Also
		// book appears twice in the hashtable with two different ids
		good.add(b);
		
		for (Book book : good) {
			System.out.println(book);
		}
	}
}
