package distributed.view;

/**
 * A view needs to be told to refresh itself when the model changes.
 */
public interface IModelUpdated {
	
	/** The value object has changed its value. */
	public void modelChanged ();
}
