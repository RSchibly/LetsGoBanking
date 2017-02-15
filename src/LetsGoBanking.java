public class LetsGoBanking {

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.addAccount(new Account(1234, 6789, 80));
		bank.addAccount(new Account(6789, 4321, 60));
		ATM at = new ATM(bank);
		at.start();
	}
}
