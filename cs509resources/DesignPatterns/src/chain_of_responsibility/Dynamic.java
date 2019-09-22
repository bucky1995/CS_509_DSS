package chain_of_responsibility;

public class Dynamic implements IProcess {
	int state = 0;
	
	/** First in the chain. */
	Link head;
	
	/** Process request. */
	public boolean process(String s) {
		if (head == null) { return false; }
		
		return head.process(s);
	}

	/** Determine starting point. */
	public void setHead(Link link) {
		this.head = link;
	}
}
