/**
 * Code used in this class was from:"Programming Example: Card, Hand, Deck"
 * URL: http://math.hws.edu/javanotes/c5/s4.html
 */
public class Deck {
	
	
	
	private Cards[] deck;
	private int usedCards;
	
	
	/**
	 * Constructor. When called, creates a deck of cards.
	 */
	public Deck()
	{ 
		deck = new Cards[52];
		int cardCount =0;
		
		for(int suit =0; suit <=3; suit++)
		{
			for(int value =1; value <=13; value++)
			{
				deck[cardCount] = new Cards(value,suit);
				cardCount++;
			}
		
		}
		
		usedCards =0;
	}
	
   /**
     * Shuffles the deck.
     */
	public void shuffleDeck()
	{
		for(int i = deck.length-1; i >0; i--)
		{
			
			int rand=(int)(Math.random()*(i+1)); //Generating a random number between 1-52.
			Cards temp = deck[i]; //Temporary holding the Suit|Value object from element i in the deck array.
			deck[i] = deck[rand]; // Assigns i the random number between 1-52.
			deck[rand] = temp; // Stores the temporary object in a different location in the deck array.
		}
		usedCards =0;
		
	}
	
	/**
	 * 
	 * @returns What is left in the deck.
	 */
	public int leftInDeck()
	{
		return deck.length-usedCards;
		
	}
	
	/**
	 * 
	 * @return deals a card.
	 */
	public Cards dealCard()
	{
		usedCards++;
		return deck[usedCards -1];
		
	}
	

}
