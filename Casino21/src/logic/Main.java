package logic;

public class Main {

	public static void main(String[] args) {
		Game g1 = new Game("Gus");
		
		
	/*	for (Card card : g1.getDeck()) {
			System.out.println(card.getValue()+" "+card.getSuit());
		}*/
		
		g1.deal();
		g1.deal();
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
