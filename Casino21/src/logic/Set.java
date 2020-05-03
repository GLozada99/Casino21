package logic;

import java.util.ArrayList;

public class Set {
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
	
}
