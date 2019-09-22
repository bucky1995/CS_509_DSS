package factory.luxmate;
import java.io.*;
import factory.Blind;

/**
 * Blind can only be UP or DOWN. 
 * Communication with blinds device is throw specific IO port. 
 *   Write byte '33' to "ioDevice.77" to raise blind
 *   Write byte '34' to "ioDevice.77" to lower blind
 *   Query state by Reading byte from "ioDevice.78" where 0=raised, 1=lowered
 */
public class LuxMateBlind extends Blind {
	/** Communicate via File-software protocol. */
	File out = new File ("ioDevice.77");
	File in = new File ("ioDevice.78");
	
	/** Raise All. */
	public void raise() throws Exception {
		FileOutputStream fos = new FileOutputStream (out);
		fos.write(33);
		fos.close();
	}

	/** Lower All. */
	public void lower() throws Exception {
		FileOutputStream fos = new FileOutputStream (out);
		fos.write(34);
		fos.close();
	}

	/** Are we all the raised? */
	public boolean isRaised() throws Exception {
		FileInputStream fis = new FileInputStream (out);
		int stat = fis.read();
		fis.close();
		return (stat == 0);
	}

	/** Bring all the way up. */
	public void raiseAll() throws Exception { raise(); }
	
	/** Bring all the way down. */
	public void lowerAll() throws Exception { lower(); }

	public boolean isLowered() throws Exception {
		FileInputStream fis = new FileInputStream (out);
		int stat = fis.read();
		fis.close();
		return (stat == 1);
	}
}