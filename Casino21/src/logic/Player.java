package logic;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private int points;
	private ArrayList<Card> heap;
	
	public Player(String name) {
		super();
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.points = 0;
		this.heap = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Card> getHeap() {
		return heap;
	}

	public void setHeap(ArrayList<Card> heap) {
		this.heap = heap;
	}
	
	public Card playCard(Card card) {
		Card aux = null;
		aux = hand.remove(hand.indexOf(card));
		return aux;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	
	
	
	
	

}
