package chain_of_responsibility;

public class Trial {

	public static void main(String []args) {
		// Show the Static output
		trial(new Static());
		System.out.println(" * Now Dynamic. *");

		// Show the Dynamic Chain-of-Responsibilities output
		Dynamic d = new Dynamic();
		TerminalLink end = new TerminalLink(d);
		Increment inc = new Increment(d, end);
		Decrement dec = new Decrement(d, inc);
		Print print = new Print(d, dec);
		d.setHead(print);
		
		trial(d);
	}

	private static void trial(IProcess proc) {
		proc.process("increment");
		proc.process("print");
		proc.process("decrement");
		proc.process("print");
	}
	
}
