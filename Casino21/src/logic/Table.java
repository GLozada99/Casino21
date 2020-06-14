package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Sets;


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
	public ArrayList<logic.Set> allGroupsAviable(){
		List<Card> theCards = cards;
		Set<Card> theSet = new HashSet<Card>(theCards);
		Set<Set<Card>>theSets = Sets.powerSet(theSet);
		ArrayList<logic.Set> retSets = new ArrayList<logic.Set>();
		for (Set<Card> sets : theSets) {
			ArrayList<Card> aux = new ArrayList<Card>();
			int number = 0;

			for (Card card : sets) {
				aux.add(card);
				number+=card.getNumber();
			}
			if(!aux.isEmpty()) {
				logic.Set retCards = new logic.Set(aux, number);
				retSets.add(retCards);
				if(retCards.coupleWorth()) {
					retCards = new logic.Set(aux, number/aux.size());
					retSets.add(retCards);
				}
			}
		}
		int i = 1;
		for (logic.Set set : retSets) {
			System.out.println(i+"         "+set.getNumber());
			for (Card card : set.getCards()) {
				System.out.println("	"+card.toString());
			}
			i++;
		}
		
		System.out.println("\n\n\n");
		return retSets;
	}
}
