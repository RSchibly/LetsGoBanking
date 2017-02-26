package IWasWaitingOnAPackage;

public class CardReader {
	private int accountNumber;

	public CardReader() {

	}

	public void readCard(int accountNumber){
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
}

