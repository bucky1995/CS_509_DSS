package oneToOne;

public class Advertiser {
	private Account account;
	
	/**
	 * Takes the lead in instantiation.
	 */
	public Advertiser() {
		account = new Account (this);
	}
	
	public Account getAccount() {
		return account;
	}
}
