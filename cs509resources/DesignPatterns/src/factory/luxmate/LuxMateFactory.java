package factory.luxmate;
import factory.*;

public class LuxMateFactory extends HouseFactory {

	/** Details of the specific bulbs */
	public LightBulb createBulb() {
		return new LuxMateBulb();
	}

	/** Details of the specific blinds */
	public Blind createBlind() {
		return new LuxMateBlind();
	}

}
