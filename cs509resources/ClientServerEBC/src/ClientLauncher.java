import java.awt.event.*;

import distributed.controller.client.QuitController;

import client.ServerAccess;
import distributed.model.Model;
import distributed.view.Application;
import xml.Message;

/** Launch command-line Client to show minimal access needs. */
public class ClientLauncher {

	// If requested by ClientLauncher (pass in '-server' as argument).
	public static final String serverHost = "72.249.186.243";
	
	/**
	 * Note that to simplify the coding of this command-client, it declares that it can throw an Exception,
	 * which is typically the failed connection to a server.
	 */
	public static void main(String[] args) throws Exception {
		// FIRST thing to do is register the protocol being used. There will be a single class protocol
		// that will be defined and which everyone will use. For now, demonstrate with skeleton protocol.
		if (!Message.configure("distributedEBC.xsd")) {
			System.exit(0);
		}
		
		// select dedicated server with '-server' options
		String host = "localhost";
		if (args.length > 0 && args[0].equals("-server")) {
			host = serverHost;
		}
		
		// try to connect to the server. Once connected, messages are going to be processed by 
		// SampleClientMessageHandler. For now we just continue on with the initialization because
		// no message is actually sent by the connect method.
		ServerAccess sa = new ServerAccess(host, 9371);
		if (!sa.connect(new SampleClientMessageHandler())) {
			System.out.println("Unable to connect to server (" + host + "). Exiting.");
			System.exit(0);
		}
		System.out.println("Connected to " + host);
		
		// subtle nuances because app is being referred to inside the anonymous class,
		// we have to tell the java compiler that this 'app' variable is not going
		// to be changed once created.
		final Application app = Application.getInstance();
		
		// This controller is an anonymous class who responds to closing events.
		// by exiting the application.
		app.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				if (new QuitController(app).confirm()) {
					app.dispose();
				}
			}
			
			/** Once window is activated, start off by forcing views to update. */
			public void windowActivated(WindowEvent e) {
				app.modelChanged();
			}
		});
		
		// Give application the object by which it can contact the server.
		app.setServerAccess(sa);
		
		// initially client can't do anything. Start from premise that model is locked
		app.allowAccess(false);
		
		Model model = Model.getInstance();
		model.setLocked(true);
		
		// send an introductory connect request now that we have created (but not made visible!)
		// the GUI
		String xmlString = Message.requestHeader() + "<connectRequest/></request>";
		Message m = new Message (xmlString);
		sa.sendRequest(m);
		
		// at this point, we need to make app visible, otherwise we would terminate application
		app.setVisible(true);
	} 
}
