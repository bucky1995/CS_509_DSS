package chain_of_responsibility;

public class Decrement extends Link {

	public Decrement(Dynamic ref, Link next) {
		super(ref, next);
	}

	@Override
	public boolean process(String s) {
		if (s.equals ("decrement")) {
			reference.state--;
			return true;
		}
		
		return next.process(s);
	}

}
