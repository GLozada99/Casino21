package logic;

import java.util.ArrayList;

public class PCPlayer extends Player {
	private final String name = "PC";
	private String action = "";


	public PCPlayer() {
		super();
	}

	public String getName() {
		return name;
	}

	public Set evaluateBestPlay(Table table) {
		ArrayList<Set> theSets = setsToTake(table);
		Set playSet = null;
		float value = 0;
		for (Set set : theSets) {
			if(value<set.getSetValue()) {
				value = set.getSetValue();
				playSet = set;
			}
		}
		return playSet;
	}
	/*public ArrayList<Set> cardsToTake(Table table) {
		ArrayList<Set> sets = new ArrayList<Set>(); 
		for (Card cardHand : hand) {
			for (Card cardTable : table.getCards()) {
				if(cardHand.getNumber()==cardTable.getNumber()) {
					ArrayList<Card> cards = new ArrayList<Card>();
					cards.add(cardHand);
					cards.add(cardTable);
					Set set = new Set(cards, cardHand.getNumber());
					sets.add(set);
				}
			}
		}
		return sets;
	}*/

	public ArrayList<Set> setsToTake(Table table) {
		ArrayList<Set> allSets = Game.getInstance().getTable().allGroupsAviable(); 
		ArrayList<Set> setsToTake = new ArrayList<Set>(); 

		for (Set set : allSets) {
			for (Card card : hand) {
				if(set.getNumber()==card.getNumber()) {
					set.getCards().add(card);
					setsToTake.add(set);
				}		
			}
		}
		return setsToTake;
	}




}
