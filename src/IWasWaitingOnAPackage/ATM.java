package IWasWaitingOnAPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM implements ActionListener{
	private Bank bank;
	private Account account;
	private boolean running;

	public ATM(Bank bank) {
		running = true;
		this.bank = bank;
		this.account = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
