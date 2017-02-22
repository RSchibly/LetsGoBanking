package IWasWaitingOnAPackage;
public class Account {
	private int accountNumber;
	private int PIN;
	private int balance;

	public Account(int accountNumber, int PIN, int balance) {
		this.accountNumber = accountNumber;
		this.PIN = PIN;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getPIN() {
		return PIN;
	}

	public boolean withdraw(int with) {
		if (with < 0 || with > balance)
			return false;
		balance -= with;
		return true;
	}

	public boolean deposit(int depo) {
		if (depo < 0)
			return false;
		balance += depo;
		return true;
	}

	public int getBalance() {
		return balance;
	}
}
