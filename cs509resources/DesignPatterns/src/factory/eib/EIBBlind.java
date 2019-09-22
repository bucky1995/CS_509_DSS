package factory.eib;

import factory.Blind;

/**
 * EIB implementation is capable of avoid all exceptions by 
 * handling state in memory.
 */
public class EIBBlind extends Blind {

	/** Store in software. 0=all Down, 100=all Up*/
	int position = 0;
	
	/** Raise 10% */
	public void raise() {
		position += 10;
		if (position > 100) {
			position = 100;
		}
	}

	/** Lower 10% */
	public void lower() {
		position -= 10;
		if (position < 0) {
			position = 0;
		}
	}

	/** Are we all the raised? */
	public boolean isRaised() {
		return (position == 100);
	}

	/** Bring all the way up. */
	public void raiseAll() {
		position = 100;		
	}
	
	/** Bring all the way down. */
	public void lowerAll() {
		position = 0;		
	}

	public boolean isLowered() {
		// TODO Auto-generated method stub
		return false;
	}

}
