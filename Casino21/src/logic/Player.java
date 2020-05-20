package logic;

import java.util.ArrayList;

public abstract class Player {
	
	protected ArrayList<Card> hand;
	protected int points;
	protected ArrayList<Card> heap;
	
	public Player() {
		super();
		this.hand = new ArrayList<Card>();
		this.points = 0;
		this.heap = new ArrayList<Card>();
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
	
	public void playCard(Card card) {
		hand.remove(hand.indexOf(card));
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	public abstract String getName();
	
	
	
	
	
	
	

}
