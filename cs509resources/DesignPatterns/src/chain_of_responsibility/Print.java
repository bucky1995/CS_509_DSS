package chain_of_responsibility;

public class Print extends Link {

	public Print(Dynamic ref, Link next) {
		super(ref, next);
	}

	@Override
	public boolean process(String s) {
		if (s.equals ("print")) {
			System.out.println ("state = " + reference.state);
			return true;
		}
		
		return next.process(s);
	}

}
