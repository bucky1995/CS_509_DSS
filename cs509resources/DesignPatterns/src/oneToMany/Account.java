package oneToMany;

public class Account {
	private Advertiser owner;
	
	public Account(Advertiser owner) {
		this.owner = owner;
	}
	
	public void setOwner (Advertiser newOwner) {
		if (owner != newOwner) {
			// replace
			Advertiser oldOwner = owner;
			owner = newOwner;
			
			if (newOwner != null) {
				newOwner.addAccount(this);
			}
			
			if (oldOwner != null) {
				oldOwner.removeAccount(this);
			}
		}
	}
	
	public Advertiser getOwner() {
		return owner;
	}
}
