package factory;

/** 
 * Common behavior of the encapsulated concept of a light bulb. 
 *
 * Specific implementations for the functions are delegated to the appropriate
 * subclass of LightBulb.
 */
public abstract class LightBulb {
	/** query to see if bulb is currently on. */
	public abstract boolean isOn() throws Exception;
	
	/** Turn the bulb on. */
	public abstract void turnOn() throws Exception;
	
	/** Turn the bulb off. */
	public abstract void turnOff() throws Exception;
}
