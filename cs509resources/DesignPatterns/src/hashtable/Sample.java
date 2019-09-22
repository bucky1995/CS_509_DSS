package hashtable;

import java.util.Hashtable;

public class Sample {
	public static void main(String[] args) {
		Hashtable<Key, String> vals = new Hashtable<Key, String>();
		
		Key k1 = new Key(10, 5);
		vals.put(k1, "Some Value");
		
		Key k2 = new Key (10,5);
		System.out.println(vals.get(k2));
	}
}
