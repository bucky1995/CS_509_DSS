package factory;

public abstract class HouseFactory {

	/** Create Bulb. */
	public abstract LightBulb createBulb();
	
	/** Create Blinds. */
	public abstract Blind createBlind();

}
