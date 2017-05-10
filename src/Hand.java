/**
 * Code used in this class was from:"Programming Example: Card, Hand, Deck"
 * URL: http://math.hws.edu/javanotes/c5/s4.html
 */
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Cards> hand;

	/**
	 * Constructor creates new hand.
	 */
	public Hand()
	{
		hand = new ArrayList<Cards>();
		
	}
	
	/**
	 * Clears the hand
	 */
	public void clearHand()
	{
		hand.clear();
		
	}
	
	/**
	 * Add card to the hand
	 */
	public void addCard(Cards card)
	{
		hand.add(card);
		
	}
	
	
	/**
	 * Remove card from hand.
	 */
	public void removeCard(Cards card)
	{
		hand.remove(card);
		
	}
	
	/**
	 * @return Size of the hand.
	 */
	public int getCardSize()
	{
		
		return hand.size();	
		
	}
	
	/**
	 * 
	 * @param choice
	 * @author Seth Bonacquisti
	 * @return What is in the hand.
	 */
	public int whatIsInHand(int choice)
	{
		int	currentValue, nextValue, counter=0;
		
		
		for(int i =0; i<hand.size(); i++)
		{
			currentValue = hand.get(i).getValue();

          for(int j = hand.size()-1; j>i; j--)
			  {
				  nextValue = hand.get(j).getValue();
				  
				  if(currentValue == nextValue)
				  	  counter++;  	  
			  }
		}
		
		return counter;
		
		
		
	}
	

	
	
		
		
	}

