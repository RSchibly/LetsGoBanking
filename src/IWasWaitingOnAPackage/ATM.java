package IWasWaitingOnAPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ATM implements ActionListener{
	//Waiting for cardread
	//Waiting for PIN
	//Waiting for Transaction Type (Withdraw/Deposit)
	//Waiting for Amount entered
	public enum ATMstate {IDLE, WPIN, WCOMMAND, WAMOUNT}


	private Display m_display;
	private Printer m_printer;
	private CardReader m_cardReader;
	private CashDispensor m_cashDispensor;
	private Bank m_bank;
	private Account m_account;
	private ATMstate m_state;
	private ArrayList<String> m_transactions;
	private boolean running;

	public ATM(Display display, Printer printer, CardReader cardReader, CashDispensor cashDispensor) {
		this.m_display = display;
		this.m_printer = printer;
		this.m_cardReader = cardReader;
		this.m_cashDispensor = cashDispensor;
		this.m_bank = new Bank();
		this.m_state = ATMstate.IDLE;
		this.m_account = null;
		this.m_transactions = new ArrayList<String>();
		running = false;
	}

	public void start(){
		running = true;
		m_display.display("Please enter a card.");
	}

	public void cmd_error(String errorMessage) {
		cmd_error(errorMessage, true);
	}
	public void cmd_error(String errorMessage, boolean ignored) {
		//Add to event log and/or do something with error
		System.err.println(errorMessage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().startsWith("CARDREAD")){
			//CARDREAD <num>
			String[] cmdArgs = e.getActionCommand().split(" ");
			if(cmdArgs.length < 2) {
				cmd_error("Incorrect number of arguments in command \""+e.getActionCommand()+"\"");
				return;
			}
			try{
				cardread(Integer.parseInt(cmdArgs[1]));
			}
			catch(NumberFormatException ex) {
				cmd_error("Could not parse arguments in command \""+e.getActionCommand()+"\"");
				return;
			}
		}
		else if(e.getActionCommand().startsWith("NUM")){
			//NUM <num>
			String[] cmdArgs = e.getActionCommand().split(" ");
			if(cmdArgs.length < 2) {
				cmd_error("Incorrect number of arguments in command \""+e.getActionCommand()+"\"");
				return;
			}
			try{
				num(Integer.parseInt(cmdArgs[1]));
			}
			catch(NumberFormatException ex) {
				cmd_error("Could not parse arguments in command \""+e.getActionCommand()+"\"");
				return;
			}
		}
		else if(e.getActionCommand().startsWith("BUTTON W")){
			//BUTTON W
			button_w();
		}
		else if(e.getActionCommand().startsWith("BUTTON CB")){
			//BUTTON CB
			button_cb();
		}
		else if(e.getActionCommand().startsWith("BUTTON CANCEL")){
			//BUTTON CANCEL
			button_cancel();
		}
		else if(e.getActionCommand().startsWith("SALT")){
			//TODO Remove this whole thing since we are turning it in and both the professor and TA will see this.
			System.out.println("A LONG LONG TIME AGO, IN A FAR OFF PLACE, RICK ONCE SAID, \"I LOVE ACTION LISTENERS\", " +
			" OUR T.A. WALKED AWAY CHEEZING. PIPEEEEEEEEE-DADDY");
			//To be more accurate, I pretty much said "HAVE YOU EVEN HEARD OF ACTION LISTENERS" to which he replied something about this not being a GUI
			//Apparently he thinks you can't use ActionListener's outside of a GUI which is just sad and wrong.
		}
		else {
			//Error: Unknown command
			cmd_error("Unknown command \""+e.getActionCommand()+"\"");
		}

	}

	public boolean isRunning() {
		return running;
	}

	private void cardread(int num){
		if(m_state != ATMstate.IDLE){
			cmd_error("Card read during incorrect state.");
			return;
		}
		m_cardReader.readCard(num);
		m_account = m_bank.findAccount(m_cardReader.getAccountNumber());
		m_display.display("Enter PIN");
		m_state = ATMstate.WPIN;
	}

	private void num(int num){
		if(m_state == ATMstate.IDLE){
			cmd_error("Num entered during incorrect state.");
			return;
		}
		else if (m_state == ATMstate.WPIN){
			if(m_bank.validate(m_account, num)){
				m_display.display("Choose Transaction");
				m_state = ATMstate.WCOMMAND;
			}
			else {
				m_display.display("Invalid PIN, please re-enter PIN or press Cancel");
			}
		}
		else if (m_state == ATMstate.WCOMMAND){
			//We should recieve the command via the button,
			//so we should error here
			cmd_error("Num entered during incorrect state.");
			return;
		}
		else if (m_state == ATMstate.WAMOUNT){
			//This is where we try to withdraw the amount and dispense
			if(m_account.withdraw(num)){
				//Success
				m_display.display("Dispensing $"+num);
				m_cashDispensor.dispense(num);
				
				//LocalDate
				//TODO Actually format my own date and time string
				m_transactions.add(LocalDateTime.now().toString()+" withdrawal "+ num);
				m_display.display("Choose Transaction");
				m_state = ATMstate.WCOMMAND;
			}
			else {
				//Failed
				m_display.display("Failed to withdraw the amount entered");
				//TODO Actually format my own date and time string
				m_transactions.add(LocalDateTime.now().toString()+" withdrawal 0");
				m_display.display("Choose Transaction");
				m_state = ATMstate.WCOMMAND;
			}
		}
	}

	private void button_w(){
		m_display.display("Amount?");
		m_state = ATMstate.WAMOUNT;
	}

	private void button_cb(){
		//This is where we will just display the balance
		m_display.display("Your balance is $" + m_account.getBalance());
		m_transactions.add(LocalDateTime.now().toString()+" check balance");
	}

	private void button_cancel(){
		if(m_state == ATMstate.IDLE){
			cmd_error("Cancel pressed during incorrect state.");
			return;
		}
		else if (m_state == ATMstate.WPIN){
			//Don't print receipt, reset to initial state
			m_display.display("Please enter a card");
			m_state = ATMstate.IDLE;
			m_account = null;
			m_transactions = new ArrayList<String>();
		}
		else if (m_state == ATMstate.WCOMMAND) {
			//Print receipt and reset to initial state
			m_display.display("Printing Receipt:");
			for(int i=0; i<m_transactions.size(); i++) m_printer.print(m_transactions.get(i));
			m_display.display("Please enter a card");
			m_state = ATMstate.IDLE;
			m_account = null;
			m_transactions = new ArrayList<String>();
		}
		else if (m_state == ATMstate.WAMOUNT) {
			//Cancel the withdraw and go back to WCOMMAND state
			m_display.display("Choose Transaction");
			m_state = ATMstate.WCOMMAND;
		}
	}

}
