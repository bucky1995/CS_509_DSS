package chain_of_responsibility;

/** One way to terminate the chain. */
public class TerminalLink extends Link {

	public TerminalLink(Dynamic ref) {
		super(ref);
	}

	@Override
	public boolean process(String s) {
		return false;
	}

}
