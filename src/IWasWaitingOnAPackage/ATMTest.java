package IWasWaitingOnAPackage;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ATMTest {
	Bank bank;
	ATM atm;

	@Before
	public void beforeTest() {
		bank = new Bank();
		atm = new ATM();
	}
	@Test
	public void testAccount1() {
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));
	}
	@Test
	public void testAccount2(){
		Card c = new Card(6789);
		assertEquals(c.getNumber(), 6789);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 6789);
		assertTrue(bank.validate(a, 4321));
	}
	@Test
	public void testWithdrawal() {
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));

		assertEquals(a.getBalance(), 80);

		a.withdraw(20);
		assertEquals(a.getBalance(), 60);

		a.withdraw(-20);
		assertEquals(a.getBalance(), 60);

		a.withdraw(60);
		assertEquals(a.getBalance(), 0);

		a.withdraw(20);
		assertEquals(a.getBalance(), 0);
	}
	@Test
	public void testDeposit() {
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));

		assertEquals(a.getBalance(), 80);

		a.deposit(20);
		assertEquals(a.getBalance(), 100);

		a.deposit(-20);
		assertEquals(a.getBalance(), 100);
	}
	@Test
	public void testDepositAndWithdraw() {
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));

		assertEquals(a.getBalance(), 80);

		a.deposit(20);
		assertEquals(a.getBalance(), 100);

		a.withdraw(50);
		assertEquals(a.getBalance(), 50);
	}
	@Test
	public void test1(){
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));

		assertEquals(a.getBalance(), 80);

		a.withdraw(20);
		assertEquals(a.getBalance(), 60);
	}
	@Test
	public void test2(){
		Card c = new Card(1234);
		assertEquals(c.getNumber(), 1234);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 1234);
		assertTrue(bank.validate(a, 6789));

		assertEquals(a.getBalance(), 80);

		a.withdraw(80);
		assertEquals(a.getBalance(), 0);
	}
	@Test
	public void test3(){
		Card c = new Card(6789);
		assertEquals(c.getNumber(), 6789);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 6789);
		assertFalse(bank.validate(a, 1234));
	}
	@Test
	public void test4(){
		Card c = new Card(6789);
		assertEquals(c.getNumber(), 6789);
		Account a = bank.findAccount(c);
		assertEquals(a.getAccountNumber(), 6789);
		assertTrue(bank.validate(a, 4321));

		assertEquals(a.getBalance(), 60);

		a.deposit(20);
		assertEquals(a.getBalance(), 80);
	}
}
