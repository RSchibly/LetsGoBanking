package IWasWaitingOnAPackage;

public class CardReader {
	private Card newCard;

	public CardReader() {
		
	}
	
	public void readCard(int cardNumber){
		this.newCard = new Card(cardNumber);
	}

	public int acctNumber() {
		return newCard.getNumber();
	}
}

