import java.util.Scanner;

public class ATM {
	private Bank bank;
	private Account current;

	public ATM(Bank bank) {
		this.bank = bank;
		this.current = null;
	}

	public void start() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			Card card;
			while (current == null) {
				System.out.println("Insert card");
				card = new Card(scan.nextInt());
				current = bank.findAccount(card);
			}
			int PIN = -1;
			while (PIN == -1) {
				System.out.print("Please enter your PIN: ");
				PIN = scan.nextInt();
				if (!bank.validate(current, PIN)) {
					PIN = -1;
				}
			}
			char userAction = ' ';
			while (userAction == ' ') {
				System.out.print("Would you like to withdraw (W), deposit (D), or check balance (B)? ");
				String ans = scan.next();
				userAction = ans.toUpperCase().charAt(0);
				if (userAction == 'W') {
					System.out.print("How much would you like to withdraw? ");
					int withdraw = scan.nextInt();
					scan.nextLine();
					if (!current.withdraw(withdraw)) {
						System.out.println("ERROR-Withdrawl");
					}
				} else if (userAction == 'D') {
					System.out.print("How much would you like to deposit? ");
					int deposit = scan.nextInt();
					scan.nextLine();
					if (!current.deposit(deposit)) {
						System.out.println("ERROR-Deposit");
					}
				} else if (userAction == 'B') {
					
				} else {
					System.out.println("There was an error with the input, please try again.");
					userAction = ' ';
				}
			}
			
			System.out.println("Your balance is " + current.getBalance());
			current = null;
			PIN = -1;
			userAction = ' ';
		}
	}
}
