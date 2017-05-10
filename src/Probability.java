/**
 * 
 * @author Seth Bonacquisti
 * Description: The class Probability calculates the probability of getting a one pair, two pair,
 * 				three of a kind and four of a kind.
 *
 */
public class Probability {
	
	private int deckSize = 52;

	public Probability()
	{
	}
	
	
	public double getOnePairProb(int num)
	{
		
		double probability =0;
		
	    probability = (combination(13,1)*combination(4,2)*combination(12,(num-2))*Math.pow(4,(num-2)))/combination(52,num);
	    
		return probability;
		
	}
	
	public double getTwoPairProb(int num)
	{
		
		double probability =0;
		
	    probability = (combination(13,2)*Math.pow(combination(4,2),2)*combination(11,(num-4))*Math.pow(4, (num-4)))/combination(52,num);
	    
		return probability;
		
	}
	
	public double getThreeOfAKindProb(int num)
	{
		
		double probability =0;
		
	    probability = (combination(13,1)*combination(4,3)*combination(12,(num-3))*Math.pow(4, (num-3)))/combination(52,num);
	    
		return probability;
		
	}
	
	public double getFourOfAKindProb(int num)
	{
		
		double probability =0;
		
	    probability = (13*1*combination(12,(num-4))*Math.pow(4, (num-4)))/combination(52,num);
	    
		return probability;
		
	}
	
	public double factorial(int f)
	{
		double result = 1;
		int temp =0;
		
		if(f != 1)
			result = f * factorial(f-1);
		
		return result;
			
	}
	public double combination(int n, int r)
	{
		double outcome = 0;
		
		outcome = factorial(n)/(factorial(r)*factorial(n-r));
		
		return outcome;
	} 

}
