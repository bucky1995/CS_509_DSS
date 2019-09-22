package chain_of_responsibility;

public abstract class Link implements IProcess {

	/** Needed for example. */
	Dynamic reference;
	
	/** Next one in the chain. */
	Link next;
	
	/** Terminal in the chain. */
	public Link (Dynamic ref) {
		this (ref, null);
	}
	
	/** Just another link in the chain. */
	public Link(Dynamic ref, Link next) {
		this.reference = ref;
		this.next = next;
	}
	
	@Override
	public abstract boolean process(String s);

}
