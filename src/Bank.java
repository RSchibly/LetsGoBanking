import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;

	public Bank() {
		accounts = new ArrayList<Account>();
	}

	public void loadAccounts() {
		accounts.add(new Account(1234, 6789, 80));
		accounts.add(new Account(6789, 4321, 60));
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
