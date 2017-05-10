/**
 * Code used in this class was from:"Programming Example: Card, Hand, Deck"
 * URL: http://math.hws.edu/javanotes/c5/s4.html
 */
public class Cards {
	
	
	
	private final static int SPADES = 0;
	private final static int CLUBS = 1;
	private final static int DIAMONDS = 2;
	private final static int HEARTS = 3;
	
	private final static int JACK = 11;
	private final static int QUEEN = 12;
	private final static int KING = 13;
	private final static int ACE = 1;
	
	
	private int suit;
	private int value;
	
	
	/**
	 * Constructor
	 */
	public Cards(int newValue, int newSuit)
	{
		if(newSuit != SPADES && newSuit != CLUBS && newSuit != DIAMONDS && newSuit != HEARTS )
			throw new IllegalArgumentException("Please Use Correct Suits: Diamonds, Hearts, Spades, Clubs.");
		if(newValue<0||newValue > 14)
			throw new IllegalArgumentException("Please use values between 1 and 13");
		suit = newSuit;
		value = newValue;
	}
	
	/**
	 * Returns the suit
	 */
	public int getSuit()
	{
		return suit;
	}
	
	/**
	 * Returns the value of the card
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Converts the number that corresponds to the specific suit and returns the suit name.
	 */
	public String getSuitAsString()
	{
		switch ( suit )
		{
		case SPADES:   return "Spades";
		case HEARTS:   return "Hearts";
		case DIAMONDS: return "Diamonds";
		case CLUBS:    return "Clubs";
		default:	   return "-1";
		}
	
	}
	
	/**
	 * Converts the number that corresponds to the specific value and returns the face value.
	 */
	public String getValueAsString()
	{
		switch ( value ){
		case 1: return "Ace";
		case 2: return "2";
		case 3: return "3";
		case 4: return "4";
		case 5: return "5";
		case 6: return "6";
		case 7: return "7";
		case 8: return "8";
		case 9: return "9";
		case 10: return "10";
		case 11: return "Jack";
		case 12: return "Queen";
		default:  return "King";
		
		}
		
	}
	
	

}
