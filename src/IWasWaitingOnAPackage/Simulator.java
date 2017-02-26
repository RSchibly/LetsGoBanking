package IWasWaitingOnAPackage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {

	public static void main(String[] args) throws InterruptedException {
		String fileFile = "transactions.txt";
		Scanner scan = new Scanner (System.in);
		System.out.println("Are you entering a file for this simulation? (Y/N)");
		String resp = scan.nextLine();

		if(resp.toLowerCase().charAt(0) == 'y'){
			try {
				scan = new Scanner(new File(fileFile));
			} catch (FileNotFoundException e) {
				System.err.println("File Not Found");
				scan = new Scanner(System.in);
			}
		}

		ATM controller = new ATM(this);

		// Main event loop
		while (controller.isRunning()) {


			int id = 0;

			System.out.println("Please enter a command: ");
			ActionEvent cmd = new ActionEvent(scan, id, scan.nextLine());
			id++;
			controller.actionPerformed(cmd);
		}

	}

}
