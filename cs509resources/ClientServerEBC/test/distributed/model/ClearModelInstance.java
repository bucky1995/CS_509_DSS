package distributed.model;

/**
 * Helper class to enable test cases to silently (and secretly) eliminate instances created by the
 * singleton invocation of {@link Model#getInstance()}.
 */
public class ClearModelInstance {
	
	/**
	 * Singleton elimination. One of the problematic issues regarding a Singleton object occurs 
	 * during testing. Using the {@link #clearInstance()} method, the tester can eliminate a singleton
	 * and "start fresh".
	 * <p>
	 * Avoid using this method during routine development.
	 */
	public static void clearInstance() {  
		Model.model = null;
	}
}
