import java.util.ArrayList;
import java.util.Comparator;

public class InterfaceExample {
	int val;
	
	public InterfaceExample(int val) {
		this.val = val;
	}
	
	public InterfaceExample() { this ((int) (Math.random()*Integer.MAX_VALUE));	}
	
	public int getValue() { return val; }
	public String toString() { return "InterfaceExample@" + val; }
	
	public static void main(String[] args) {
		
		ArrayList<InterfaceExample> acl = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			acl.add(new InterfaceExample());
		}
		
		// old approach to sorting 
		acl.sort(new Comparator<InterfaceExample>() {

			@Override
			public int compare(InterfaceExample o1, InterfaceExample o2) {
				return o1.val - o2.val;
			}
		});
		
		
		// functional approach to printing each value in the list
		acl.forEach(System.out::println);
		
		acl.sort(Comparator.comparing(InterfaceExample::getValue).reversed());
		
		// functional approach to printing each value in the list
		acl.forEach(System.out::println);
	}
}
