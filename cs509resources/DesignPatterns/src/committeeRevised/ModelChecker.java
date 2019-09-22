package committeeRevised;

/**
 * When a model has changed, those entities wishing to check the
 * updates to the model are notified.
 * <p>
 * If any of these model checking entities wishes to reject a particular
 * change, then it throws an exception in response to the request.
 * <p>
 * @author: George T. Heineman (heineman@cs.wpi.edu)
 */
public interface ModelChecker {
	/**
	 * Request Model Checker to verify the integrity of the model.
	 * <p>
	 * Note that no information is passed along this method because the
	 * model checker is responsible for "pulling" this information from
	 * the model. In this way, the ModelChecker interface is completely
	 * generic.
	 */
	public boolean checkModel () throws ConstraintException;
}
