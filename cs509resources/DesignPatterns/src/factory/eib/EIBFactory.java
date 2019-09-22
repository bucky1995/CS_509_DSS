package factory.eib;
import factory.*;

public class EIBFactory extends HouseFactory {

	/** Details of the specific bulbs */
	public LightBulb createBulb() {
		return new EIBBulb();
	}

	/** Details of the specific blinds */
	public Blind createBlind() {
		return new EIBBlind();
	}

}
