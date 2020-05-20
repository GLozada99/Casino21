package logic;

import java.util.ArrayList;

public class PCPlayer extends Player {
	private final String name = "PC";
	

	public PCPlayer() {
		super();
	}

	public String getName() {
		return name;
	}
	
	public Set evaluateBestPlay(Table table) {
		ArrayList<Set> theSets = new ArrayList<Set>(); 
		for (Card cardHand : hand) {
			for (Card cardTable : table.getCards()) {
				if(cardHand.getNumber()==cardTable.getNumber()) {
					ArrayList<Card> cards = new ArrayList<Card>();
					cards.add(cardHand);
					cards.add(cardTable);
					Set set = new Set(cards, cardHand.getNumber());
					theSets.add(set);
				}
			}
		}
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
	
	
	

	
	
	
}
