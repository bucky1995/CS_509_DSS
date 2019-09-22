import java.util.ArrayList;
import java.util.Iterator;
import iterator.ArrayIterator;


public class OtherClass implements Iterable<Integer> {
	static int a[] = {1, 3, 54, 6, 7, 8};
	//static ArrayList<Integer> a = new ArrayList<Integer>();
	
	static void init() {
		//a.add(3);
		///a.add(1);
		//a.add(56);
	}

	public Iterator<Integer> iterator() {
		//return a.iterator();
		return new ArrayIterator(a, a.length);
	}
	
}
