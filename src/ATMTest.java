import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ATMTest {
	
	@Before
	public void beforeTest() {
		ATM atm;
		atm = new ATM();
		Account a;
		a = new Account();
		Bank b;
		b = new Bank();
		
	}
	@Test
	public void test1() {
		assertTrue(b.validate(1234), 6789);
		assertEquals(b.balance(1234), 80);
		b.withdrawal(1234, 20);
		assertEquals(b-.balance(1234), 60);
	}
	@Test
	public void test2(){
		assertTrue(b.validate(1234), 6789);
		assertEquals(b.balance(1234), 80);
		b.withdrawal(1234, 80);
		assertEquals(b.balance(1234), 0);
	}
	@Test
	public void test3(){
		assertFalse(b.validate(6789), 4321);
	}
	@Test
	public void test4(){
		assertTrue(b.validate(6789), 4321);
		assertEquals(b.balance(6789), 60);
		b.deposit(6789, 20);
		assertEquals(b.balance(6789), 80);
	}
	/*public void testDepositAndWithdraw() {
		a = new BankAccount("Bill Gates");
		boolean success = a.deposit(-100); // Nope!
		assertFalse(success);
		success = a.deposit(100); // OK
		assertTrue(success);
		double b = a.getBalance(); // should be 100
		assertTrue(b == 100);
		success = a.withdraw(200); // Nope!
		assertFalse(success);
		success = a.withdraw(75);
		assertTrue(success);
		b = a.getBalance();
		assertTrue(b == 25);
	}*/
}
