package logic;

import java.util.ArrayList;

public class Table {
	private ArrayList<Card> cards;
	private ArrayList<ArrayList<Card>> groups;
	
	public Table() {
		super();
		this.cards = new ArrayList<Card>();
		this.groups = new ArrayList<ArrayList<Card>>();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<ArrayList<Card>> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<ArrayList<Card>> groups) {
		this.groups = groups;
	}
	public void addCard(Card card) {
		cards.add(card);
	}
	
	
	
	
}
