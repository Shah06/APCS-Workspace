
public class Hand {

    private int handsize;
    private Card[] cards;

    public Hand(Card[] cards) {
        this.cards = cards;
        handsize = 2;
    }
    
    public Card[] getCards() {
    	return cards;
    }

}
