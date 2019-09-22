package factory;

/** 
 * Common behavior for structures within windows to block light.
 * 
 * Specific implementations for the functions are delegated to the appropriate
 * subclass of Blind
 */
public abstract class Blind {

	/** Raise the blind. */
	public abstract void raise() throws Exception;
	
	/** Raise all. */
	public abstract void raiseAll() throws Exception;
	
	/** Lower the blind. */
	public abstract void lower() throws Exception;
	
	/** Lower all. */
	public abstract void lowerAll() throws Exception;
	
	/** is the blind currently in raised position? */
	public abstract boolean isRaised() throws Exception;
	
	/** is the blind currently in lowered position? */
	public abstract boolean isLowered() throws Exception;
}
