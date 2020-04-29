package logic;

import java.util.ArrayList;

public class Table {
	private ArrayList<Card> cards;
	private ArrayList<Group> groups;
	
	public Table() {
		super();
		this.cards = new ArrayList<Card>();
		this.groups = new ArrayList<Group>();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	public void addCard(Card card) {
		cards.add(card);
	}
	public void removeCard(Card card) {
		cards.remove(cards.indexOf(card));
	}
	
	
	
	
}
