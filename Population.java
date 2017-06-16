package genetics;

import java.util.ArrayList;

public class Population 
{
	/*The Population class contains an array list of Phrase objects.
	 * The fitnessScores array list will be a parallel array which describes
	 * the fitness of each phrase
	 *populationSize limits the number of individuals to prevent
	 *infinite growth of the population
	 *eliminationPercent is the constant used to eliminate the
	 *least fit members of the population (bottom 30% for example)
	 */
	
	private ArrayList<Phrase> phrases;
	private final int populationSize;
	private final double eliminationPercent;
	private String targetPhrase;
	
	
	//Design choice: multiple constructors will simplify testing later
	public Population()
	{
		//By default, we will include 100 phrases in the population
		populationSize = 100;
		
		//Here we will eliminate the bottom 30% of the population
		eliminationPercent = 0.3;
		phrases = new ArrayList<Phrase> (populationSize);
		targetPhrase = "hello world";
		addPhrases();
	}
	
	public Population(int size)
	{
		populationSize = size;
		eliminationPercent = 0.3;
		phrases = new ArrayList<Phrase> (populationSize);
		targetPhrase = "hello world";
		addPhrases();
	}
	
	public Population(int size, String target)
	{
		populationSize = size;
		eliminationPercent = 0.3;
		phrases = new ArrayList<Phrase> (populationSize);
		targetPhrase = target;
		addPhrases();
	}
	
	public Population(int size, String target, double elimPer)
	{
		populationSize = size;
		eliminationPercent = elimPer;
		phrases = new ArrayList<Phrase> (populationSize);
		targetPhrase = target;
		addPhrases();
	}
	
	/** Adds phrases into the array list until the number of elements
	 * equals the maximum population size allowed (helper method)
	 */
	private void addPhrases() //Design choice: private since helper method
	{
		for(int i = 0; i < populationSize; i++)
		{
			phrases.add(new Phrase());
		}
	}
	
	/** Sets the initial fitness scores for the members of the population
	 * by comparing the phrase to the target string
	 */
	public void assignFitnessInitial() //Design choice: public because main method needs to access
	{
		//Repeat for each phrase in the array list
		for(Phrase p : phrases)
		{
			//Check each corresponding character and if they match,
			//increase the fitness score
			double fitScore = 0.0;
			
			for(int i = 0; i < targetPhrase.length(); i++)
			{
				if(targetPhrase.charAt(i) == p.getLetter(i))
					fitScore = fitScore + 1.0/(targetPhrase.length());
			}
			p.setFitness(fitScore);
		}
	}
	
	/** Implements a quick sort to arrange the initial array list
	 * by fitness scores for the purpose of eliminating the bottom 30%
	 * or a part of the least fit scores
	 */
	public void sortByFitness(int low, int high)
	{

	        int i = low, j = high;
	        
	        double pivot = phrases.get(low+(high-low)/(2)).getFitness();

	        // Divide into two lists
	        while (i <= j) {
	            // If the current value from the left list is smaller than the pivot
	            // element then get the next element from the left list
	            while (phrases.get(i).getFitness() < pivot) {
	                i++;
	            }
	            // If the current value from the right list is larger than the pivot
	            // element then get the next element from the right list
	            while (phrases.get(j).getFitness() > pivot) {
	                j--;
	            }

	            // If we have found a value in the left list which is larger than
	            // the pivot element and if we have found a value in the right list
	            // which is smaller than the pivot element then we exchange the
	            // values.
	            // As we are done we can increase i and j
	            if (i <= j) {
	                exchange(i, j);
	                i++;
	                j--;
	            }
	        }
	        // Recursion
	        if (low < j)
	            sortByFitness(low, j);
	        if (i < high)
	            sortByFitness(i, high);
	    }
	
	//Helper method to swap two elements in an array list
	private void exchange(int i, int j) 
	{
	     double temp = phrases.get(i).getFitness();
	     phrases.get(i).setFitness(phrases.get(j).getFitness());
	     phrases.get(j).setFitness(temp);
	    
	}
	
	/** This method removes the bottom X percent of the population
	 * where X is defined by the constant field eliminationPercent
	 */
	public void eliminateLeastFit()
	{
		//Remove the zeroth element a certain number of times
		for(int i = 0; i < eliminationPercent*phrases.size(); i++)
		{
			phrases.remove(0);
		}
	}
	
	/** This method adds the child/phrase into the existing population
	 * but also sorts it into the appropriate location to make the eliminating
	 * process easier for future generations since the list is already sorted.
	 * 
	 * @param Phrase the child phrase to be added
	 */
	public void addMemberWithSort(Phrase p)
	{
		boolean sorted = false;
		int i = 0;
		
		p.assignFitness(targetPhrase);
		
		while(i < phrases.size() && !sorted)
		{
			// Tip*: It is more efficient to check every third or fourth element
			//and then check locally later
			if(phrases.get(i).getFitness() >= p.getFitness())
			{
				phrases.add(i,p);
				sorted = true;
			}
			
			i++;
		}
		
		if(sorted == false)
			phrases.add(p);
	}
	
	/* Getters */
	public Phrase getMember(int i)
	{
		return phrases.get(i);
	}
	
	public int getSize()
	{
		return phrases.size();
	}
	
	public int getPopulationSize()
	{
		return populationSize;
	}
	
	public int getNumEliminated()
	{
		return (int) (eliminationPercent*populationSize);
	}
	
	public void printAllMembers()
	{
		for(Phrase p : phrases)
		{
			System.out.println(p + " " + p.getFitness());
		}
		System.out.println("------");	
	}
	
}
