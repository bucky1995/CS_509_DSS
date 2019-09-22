package singleton;

/**
 * Sample Singleton
 * 
 * @author George Heineman
 */
public class ContextManager {

	/** Singleton instance. */
	static ContextManager inst;
	
	/** Configured information. */
	final String userid;
	
	/** Lock down access to this constructor. */
	ContextManager(String userid) {
		this.userid = userid;
	}
	
	/** Retrieve user id context. */
	public String userid() {
		return userid;
	}
	
	/** Ability to grab context manager on demand. */
	public static ContextManager instance(String userid) {
		if (inst == null) {
			inst = new ContextManager(userid);
			return inst;
		}
		
		throw new IllegalStateException("Singleton already configured.");
	}	
	
	/** Ability to grab context manager on demand. */
	public static ContextManager instance() {
		if (inst == null) {
			throw new IllegalStateException("Singleton not yet configured.");
		}
		
		return inst;
	}	
	
}
