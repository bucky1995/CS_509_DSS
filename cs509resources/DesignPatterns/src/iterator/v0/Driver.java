package iterator.v0;

public class Driver {
	public static void main(String[] args) {
		MySet s = new MySet();
		
		s.add(3);
		s.add(10);
		s.add(7);
		
		// output them one at a time.
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.getIth(i));
		}
		
	}
}
