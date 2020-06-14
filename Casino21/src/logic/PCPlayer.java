package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
		int i = 0;
		ArrayList<Set> allSets = Game.getInstance().getTable().allGroupsAviable(); 
		ArrayList<Set> setsToTake = new ArrayList<Set>(); 
		int cardsFromHand = 0;
		for (Set set : allSets) {
			for (Card card : hand) {
				if(set.getNumber()==card.getNumber()||((card.getNumber()==1)&&(set.getNumber()==14))) {
					setsToTake.add(set);
				}		
			}
		}

		Collections.sort(setsToTake);

		for (i = 0; i < setsToTake.size(); i++) {//eliminate duplicates
			Set set1 = setsToTake.get(i);
			for (int j = 1; j < setsToTake.size(); j++) {
				Set set2 = setsToTake.get(j);
				if(set1.getCards().containsAll(set2.getCards())&&set2.getCards().containsAll(set1.getCards())) {
					setsToTake.remove(set1);
				}
			}	
		}
		i = 1;
		for (Set set : setsToTake) {
			System.out.println(i+"           Numero:"+set.getNumber());

			for (Card card : set.getCards()) {
				System.out.println("	"+card.toString());
			}
			i++;
		}

		System.out.println("\n\n\n");
		
		for (i = 0; i < setsToTake.size(); i++) {//join equal number sets together
			Set set1 = setsToTake.get(i);
			for (int j = 1; j < setsToTake.size(); j++) {
				Set set2 = setsToTake.get(j);
				if(set1.getNumber()==set2.getNumber()) {
					boolean shares = false;
					for (Card card1 : set1.getCards()) {
						for (Card card2 : set2.getCards()) {
							if(card1.equals(card2)) {
								shares = true;
							}
						}
					}
					if(!shares) {
						set2.getCards().addAll(set1.getCards());
						setsToTake.remove(set1);
					}
				}
			}	
		}
		

		i = 1;
		for (Set set : setsToTake) {
			System.out.println(i+"           Numero:"+set.getNumber());

			for (Card card : set.getCards()) {
				System.out.println("	"+card.toString());
			}
			i++;
		}

		System.out.println("\n\n\n");


		return setsToTake;
	}




}
