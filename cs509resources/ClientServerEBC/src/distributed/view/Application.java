package distributed.view;

import java.awt.Frame;

import xml.Message;

import client.ServerAccess;
import distributed.model.Model;

/**
 * The containing Frame for the application. This class is nothing more 
 * than a GUI shell, and the real logic happens in the ApplicationPanel.
 */
public class Application extends Frame implements IModelUpdated {
	
	/** Inner GUI panel containing everything. */
	ApplicationPanel panel;
	
	/** Singleton instance for this GUI Application class. */
	static Application instance = null;
	
	/** Connection to server. */
	ServerAccess access;
	
	/** Singleton access to this Application object. */
	public static Application getInstance() {  
		if (instance == null) {
			instance = new Application();
		}
		
		return instance;
	}
	
	/**
	 * Singleton elimination. One of the problematic issues regarding a Singleton object occurs 
	 * during testing. Using the {@link #clearInstance()} method, the tester can eliminate a singleton
	 * and "start fresh".
	 * <p>
	 * Avoid using this method during routine development.
	 */
	public static void clearInstance() {  
		instance = null;
	}
	
	/** 
	 * A very useful trick to enable JUnit testing without difficulties. Whenever
	 * the Frame is disposed, we throw away the Singleton object. In this way, there
	 * never will be more than one visible Application Frame, but the JUnit test cases
	 * are easier to write. Quite cool.
	 */
	public void dispose () {
		super.dispose();
		
		instance = null;
	}
	
	/**
	 * This is the default constructor
	 */
	Application() {
		super();
		
		setTitle("Sample Application");
		setSize(633, 490);
		
		panel = new ApplicationPanel();
		
		add (panel);
	}

	/** Retrieve the inner panel. */
	public ApplicationPanel getInnerPanel() {
		return panel;
	}
	
	/** Delegate this request to the inner panel. */
	public void modelChanged() {
		panel.modelChanged();
	}

	/** Return the server access object. */
	public ServerAccess getServerAccess() {
		return access;
	}
	
	/** Application needs to know the access object for contacting server. */
	public void setServerAccess(ServerAccess sa) {
		this.access = sa;
	}

	/** Send Message request to the server to update model (and release lock). */
	public static void updateModelOnServer() {
		Model model = Model.getInstance();
		
		String xmlString = Message.requestHeader() + "<updateRequest " + 
				"height = '" + model.getHeight() + "' " +
				"width = '" + model.getWidth() + "' " + 
				"color = '" + model.getColor() + "'/>" +
				"</request>";
				
		Message m = new Message(xmlString);
		getInstance().access.sendRequest(m);
		
		// after every request, reset controllers
		xmlString = Message.requestHeader() + "<lockRequest grab='false'/></request>";
		m = new Message(xmlString);
		getInstance().access.sendRequest(m);
	}

	/** Determine whether client has access to modifying the model. If the button is DISABLED then WE HAVE LOCK. */
	/** FOUND THIS DEFECT DURING TESTING **/
	public boolean hasAccess() {
		return !panel.getServerPanel().getExclusiveButton().isEnabled();
	}
	
	/**
	 * Freeze ability to manipulate model via GUI controllers if the server model is locked.
	 * 
	 * @param ableToAccess
	 */
	public void allowAccess(boolean allowGUIControls) {
		panel.getControlPanel().setEnabled(allowGUIControls);
		panel.getTextView().setEnabled(allowGUIControls);
		panel.getBoxView().setEnabled(allowGUIControls);
	}	
} 
