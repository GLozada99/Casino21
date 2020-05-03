package logic;

public class Card {
	private int number;
	private String suit;
	private float value;
	private String addressName;
	
	

	public Card(int number, String suit, String addressName) {
		super();
		//suit can be: Spades, Clubs, Diamonds or Hearts
		this.number = number;
		this.suit = suit;
		this.setAddressName(addressName);
		if(this.number==1) {
			this.value = 1;
		}
		else if(this.number==10&&this.suit.equalsIgnoreCase("Diamonds")) {
			this.value = 2;
		}
		else if(this.number==2&&this.suit.equalsIgnoreCase("Spades")) {
			this.value = 1;
		}
		else if(this.number==2&&this.suit.equalsIgnoreCase("Spades")) {
			this.value = 1;
		}else {
			this.value = 0;
		}
		if(this.suit.equalsIgnoreCase("Spades")) {
			this.value += (float) 0.05;
		}
		this.value += (float) 0.01;
	}
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	



}
