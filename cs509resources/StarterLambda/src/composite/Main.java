package composite;

public class Main {
	public static void main (String args[]) {
		Set s = new Set ();
		s.add(new Number(5));
		s.add(new Number(10));
		
		Set s2 = new Set ();
		s2.add(new Number(1));
		s2.add(new Number(3));
		
		Set s3 = new Set();
		s3.add(s);
		s3.add(s2);
		
		System.out.println(s3.doesContain(new Number(3)));
		
	}
}
