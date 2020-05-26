package logic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		
		//Game.getInstance().onTable();
		/*for (Card card : Game.getInstance().getTable().getCards()) {
			System.out.println(card.toString());
		}*/
		int i=1;
		for (Set sets : Game.getInstance().getTable().allGroupsAviable()) {
			System.out.print(i+"__ ");
			for (Card card : sets.getCards()) {
				System.out.println(card.toString());
			}
			i++;
		}
		/*g1.deal();
		g1.deal();
		g1.deal();
		g1.deal();
		g1.deal();

		for (Card card : g1.getDeck()) {
			System.out.println(card.getValue()+" "+card.getSuit());
		}
		System.out.println(g1.getDeck().size());
		for (Card card : g1.getOriginalDeck()) {
			System.out.println(card.getValue()+" "+card.getSuit());
		}
		if(g1.getDeck().get(0)==g1.getOriginalDeck().get(0))
			System.out.println("Son iguales");
		/*for (Card card : g1.getDeck()) {
			System.out.println(card.getValue()+" "+card.getSuit());
		}*/
	//	System.out.println(g1.getPC().getHand().get(0).getSuit());
		//System.out.println(g1.getDeck().size());

	}

}
