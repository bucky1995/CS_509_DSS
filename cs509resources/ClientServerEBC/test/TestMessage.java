import xml.Message;
import junit.framework.TestCase;


public class TestMessage extends TestCase {
	// show how to pass strings with single quotes in them.
	public void testMessage() {
		Message.configure("distributedEBC.xsd");
		
		// demonstrate problem with single quotes...
		try {
			String xmlString = Message.responseHeader("lkfjlkf", "won't work properly") + "<lockResponse secured='true'/></response>";
			new Message(xmlString);
			
			fail ("surprised to see that single quotes are now working.");
		} catch (Exception e) {
			// success
		}
		
		// must use double quotes when text contains a single quote. THen use \" as the way to embed
		// double quotes.
		// note nice article explaining what should be done instead.
		// http://www.w3schools.com/xml/xml_attributes.asp
		
	}
}
