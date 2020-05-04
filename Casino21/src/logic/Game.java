package logic;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private ArrayList<Card> deck;
	private ArrayList<Card> originalDeck;
	private Player player;
	private Player PC;
	private Table table;
	private static Game game = null;

	private Game() {
		super();
		this.deck = createDeck();
		this.setOriginalDeck((ArrayList<Card>) deck.clone());
		this.player = new Player("Player");
		this.PC = new Player("PC");
		this.table = new Table();
	}

	public static Game getInstance() {
		if(game==null) {
			game = new Game();
		}

		return game;
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
		String addressName = "";
		Card aux = null;
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				suit="Spades";
				addressName="S.jpg"; 
				break;
			case 1:
				suit="Hearts";
				addressName="H.jpg";
				break;
			case 2:
				suit="Clubs";
				addressName="C.jpg";
				break;
			case 3:
				suit="Diamonds";
				addressName="D.jpg";
				break;
			}
			for (int j = 1; j < 14; j++) {
				aux = new Card(j, suit, Integer.valueOf(j).toString()+addressName);
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
		try {
			for (int i = 0; i < 4; i++) {
				player.addCard(takeCard());
				PC.addCard(takeCard());
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("There are no more elements on the array");
		}

	}

	public void onTable() {
		try {
			for (int i = 0; i < 4; i++) {
				table.addCard(takeCard());
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("There are no more elements on the array");
		}

	}

	public Card cardByNumberAndSuit(int number, String suit) {
		Card aux = null;
		boolean found = false;
		int i = 0;
		while(i < originalDeck.size()&&!found) {
			if(originalDeck.get(i).getNumber()==number && originalDeck.get(i).getSuit().equalsIgnoreCase(suit)) {
				found = true;
				aux = originalDeck.get(i);
			}
			i++;
		}
		return aux;
	}

	public boolean createGroup(Player player, Card cardP, ArrayList<Card> cards, int number) {
		boolean result = false;
		cards.add(cardP);
		int value = 0;
		for (Card card : cards) {
			value += card.getNumber(); 
		}
		if(value == number) {
			result = true;
			Set auxS = new Set((ArrayList<Card>) cards.clone(), number);
			table.getCards().removeAll(cards);
			ArrayList<Set> sets = new ArrayList<Set>();
			sets.add(auxS);
			Group auxG = new Group(player, sets, number);
			table.getGroups().add(auxG);
			player.playCard(cardP);
		}
		return result;
	}
	
	public boolean createGroup(Player player, ArrayList<Card> cards, int number) {
		boolean result = false;
		int value = 0;
		for (Card card : cards) {
			value += card.getNumber(); 
		}
		if(value == number) {
			result = true;
			Set auxS = new Set((ArrayList<Card>) cards.clone(), number);
			table.getCards().removeAll(cards);
			ArrayList<Set> sets = new ArrayList<Set>();
			sets.add(auxS);
			Group auxG = new Group(player, sets, number);
			table.getGroups().add(auxG);
		}
		return result;
	}
	
	public boolean createGroup(Player player, Card cardP, int number) {
		boolean result = false;
		int value = cardP.getNumber();
		if(value == 1 && number == 14) {
			result = true;
			ArrayList<Card> cards = new ArrayList<Card>();
			cards.add(cardP);
			Set auxS = new Set(cards, number);
			ArrayList<Set> sets = new ArrayList<Set>();
			sets.add(auxS);
			Group auxG = new Group(player, sets, number);
			table.getGroups().add(auxG);
			player.playCard(cardP);
		}
		return result;
	}

	public boolean addSetToGroup(ArrayList<Card> cards, int number, Group group) {
		boolean result = false;
		if(group.getMySets().size()==1) {
			int value = 0;
			for (Card card : cards) {
				value += card.getNumber(); 
			}
			if(value + group.getNumber() == number) {
				result = true;
				Set auxS = new Set((ArrayList<Card>) cards.clone(), number);
				group.getMySets().add(auxS);
				table.getCards().removeAll(cards);
			}	
		}
		return result;
	}

	public boolean takeCard(Player player, Card cardP, Card cardT) {
		boolean result=false;
		if(cardP.getNumber()==cardT.getNumber()) {
			player.getHeap().add(cardT);
			player.getHeap().add(cardP);
			player.playCard(cardP);
			table.removeCard(cardT);
			result = true;
		}
		return result;
	}

	public boolean takeGroup(Player player, Card card, Group group) {
		boolean result=false;
		if(card.getNumber()==group.getNumber()||(card.getNumber()==1 && group.getNumber()==14)) {
			player.getHeap().add(card);
			player.playCard(card);
			for (Set set : group.getMySets()) {
				for (Card card2 : set.getCards()) {
					player.getHeap().add(card2);	
				}
			}
			result = true;
		}
		return result;
	}

	public ArrayList<Card> getOriginalDeck() {
		return originalDeck;
	}

	public void setOriginalDeck(ArrayList<Card> originalDeck) {
		this.originalDeck = originalDeck;
	}

	public Card cardByAddress(String address) {
		Card aux = null;
		String auxAddress = address.split("/")[1];
		boolean found = false;
		int i = 0;
		while(i < originalDeck.size()&&!found) {
			if(originalDeck.get(i).getAddressName().equalsIgnoreCase(auxAddress)) {
				found = true;
				aux = originalDeck.get(i);
			}
			i++;
		}
		return aux;
	}






}
