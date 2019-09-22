package chain_of_responsibility;

public class Static implements IProcess {
	int state = 0;
	
	public boolean process(String s) {
		if (s.equals("increment")) {
			state++;
			return true;
		} else if (s.equals ("decrement")) {
			state--;
			return true;
		} else if (s.equals ("print")) {
			System.out.println ("state = " + state);
			return true;
		}
		
		// unknown command
		return false;
	}
	
}
