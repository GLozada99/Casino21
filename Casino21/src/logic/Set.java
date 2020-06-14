package logic;

import java.util.ArrayList;

public class Set implements Comparable{
	private ArrayList<Card> cards;
	private int number;
	public Set(ArrayList<Card> cards, int number) {
		super();
		this.cards = cards;
		this.number = number;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public float getSetValue() {
		float value = 0;
		for (Card card : cards) {
			value += card.getValue();
		}
		return value;
	}
	
	public boolean coupleWorth() {
		boolean result = true;
		int auxNumb = cards.get(0).getNumber();
		if(cards.size()!=1) {
			
			for (Card card : cards) {
				if(auxNumb != card.getNumber()) {
					result = false;
				}
			}
		}else {
			result = false;
		}
		if(result==true) {
			this.number = auxNumb;
			System.out.println("");
		}
		return result;
	}
	@Override
	public int compareTo(Object setComp) {//Para sortear los sets por numero
		int compareNumber = ((Set) setComp).getNumber();
		
		return this.number-compareNumber;
	}
	
	
	
	
	
	/*public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	
}
