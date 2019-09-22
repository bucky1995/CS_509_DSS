package factory.luxmate;

import factory.LightBulb;

public class LuxMateBulb extends LightBulb {

	/** Stored in software. */
	private boolean onState = false;
	
	public boolean isOn() {
		return onState;
	}

	public void turnOn() {
		onState = true;
	}

	public void turnOff() {
		onState = false;		
	}

}
