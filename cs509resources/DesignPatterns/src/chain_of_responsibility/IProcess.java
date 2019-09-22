package chain_of_responsibility;

public interface IProcess {

	/** 
	 * Given a non-empty string, attempt to process the requested action.
	 * 
	 * @param action   action to be processed
	 * @return true if action has been processed; false otherwise
	 */
	boolean process (String action);
}
