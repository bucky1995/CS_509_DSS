import java.util.Iterator;


public class Sample {
	public static void main(String[] args) {
		OtherClass.init();
		
		OtherClass oc = new OtherClass();
		
		Iterator<Integer> it = oc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		/*
		for (int i = 0; i < 6; i++) {
			System.out.println(OtherClass.a[i]);
		}
		*/
	}
}
