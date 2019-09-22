package oneToMany;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Advertiser {
	
	private Set<Account> accounts;
	
	/**
	 * Takes the lead in instantiation.
	 */
	public Advertiser() {
		accounts = new HashSet<Account>();
	}
	
	
	public void addAccount (Account a) {
		accounts.add (a);
		a.setOwner (this);
	}
	
	
	public void removeAccount(Account a) {
		accounts.remove(a);
		a.setOwner (null);
	}
	
	
	public Iterator<Account> accounts() {
		return accounts.iterator();
	}
}
