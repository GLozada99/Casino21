package logic;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private ArrayList<Card> deck;
	private ArrayList<Card> originalDeck;
	private Player player;
	private Player PC;
	private Table table;

	public Game(String playerName) {
		super();
		this.deck = createDeck();
		this.setOriginalDeck((ArrayList<Card>) deck.clone());
		this.player = new Player(playerName);
		this.PC = new Player("PC");
		this.table = new Table();
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPC() {
		return PC;
	}

	public void setPC(Player pC) {
		PC = pC;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public ArrayList<Card> createDeck(){
		ArrayList<Card> newDeck = new ArrayList<Card>();
		String suit = "";
		Card aux = null;
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				suit="Spades";
				break;
			case 1:
				suit="Hearts";
				break;
			case 2:
				suit="Clubs";
				break;
			case 3:
				suit="Diamonds";
				break;
			}
			for (int j = 1; j < 14; j++) {
				aux = new Card(j, suit);
				newDeck.add(aux);
			}
		}
		return newDeck;
	}

	public void shuffleDeck(){
		Collections.shuffle(deck);
	}
	public Card takeCard() {
		Card aux = null;
		aux = deck.remove(deck.size()-1);
		return aux;
	}

	public void deal() {
		for (int i = 0; i < 4; i++) {
			player.addCard(takeCard());
			PC.addCard(takeCard());
		}
	}
	public void onTable() {
		for (int i = 0; i < 4; i++) {
			table.addCard(takeCard());
		}
	}
	
	/*public Card cardByValueAndSuit(int value, String suit) {
		boolean found = false;
		int i = 0;
		while(i < deck.size()&&!found) {
			
			
			i++;
		}
		
	}*/
	
	public void makeGroup(Player play, Card cardP, Card cardT, boolean addOrCouple) {
		//if addOrCouple is true, the value of Group wold be cardP+cardT, else, value would be cardP
		
		
	}

	public ArrayList<Card> getOriginalDeck() {
		return originalDeck;
	}

	public void setOriginalDeck(ArrayList<Card> originalDeck) {
		this.originalDeck = originalDeck;
	}






}
