package oneToOne;

public class Account {
	private Advertiser owner;
	
	public Account(Advertiser owner) {
		this.owner = owner;
	}
	
	public Advertiser getOwner() {
		return owner;
	}
}
