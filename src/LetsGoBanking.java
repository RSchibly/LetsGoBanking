public class LetsGoBanking {

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.loadAccounts();
		ATM at = new ATM(bank);
		at.start();

	}
}
