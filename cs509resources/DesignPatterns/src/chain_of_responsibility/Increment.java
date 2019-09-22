package chain_of_responsibility;

public class Increment extends Link {

	public Increment(Dynamic ref, Link next) {
		super(ref, next);
	}

	@Override
	public boolean process(String s) {
		if (s.equals ("increment")) {
			reference.state++;
			return true;
		}
		
		return next.process(s);
	}

}
