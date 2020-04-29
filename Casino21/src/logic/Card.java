package logic;

public class Card {
	private int number;
	private String suit;
	private float value;
	
	

	public Card(int number, String suit) {
		super();
		//suit can be: Spades, Clubs, Diamonds or Hearts
		this.number = number;
		this.suit = suit;
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
			this.value = this.value + (float) 0.15;
		}
		

		
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
	



}
