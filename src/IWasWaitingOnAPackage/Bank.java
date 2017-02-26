package IWasWaitingOnAPackage;
import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;

	public Bank() {
		accounts = new ArrayList<Account>();
		loadInitialData();
	}

	private void loadInitialData(){
		addAccount(new Account(1234, 6789, 80));
		addAccount(new Account(6789, 4321, 60));
	}

	public boolean addAccount(Account account) {
		for(int i = 0; i < accounts.size(); i++) {
			if(account.getAccountNumber() == accounts.get(i).getAccountNumber()) return false;
		}

		return accounts.add(account);
	}

	public Account findAccount(int accountNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber() == accountNumber) {
				return accounts.get(i);
			}
		}
		return null;
	}

	public boolean validate(Account acc, int PIN) {
		return acc.getPIN() == PIN;
	}
}
