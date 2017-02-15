import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;

	public Bank() {
		accounts = new ArrayList<Account>();
	}
	
	public boolean addAccount(Account account) {
		for(int i = 0; i < accounts.size(); i++) {
			if(account.getAccountNumber() == accounts.get(i).getAccountNumber()) return false;
		}
		
		return accounts.add(account);
	}

	public Account findAccount(Card c) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == c.getNumber()) {
				return accounts.get(i);
			}
		}
		return null;
	}

	public boolean validate(Account acc, int PIN) {
		return acc.getPIN() == PIN;
	}
}
