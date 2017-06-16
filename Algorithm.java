package genetics;

public class Algorithm 
{
	/* The generation variable keeps track of how many times
	 * the breeding process has happened and will be useful to
	 * visualize the data of the fitness scores increase over time.
	 * The mutation rate variable provides variety in the children/phrases
	 */
	private int currentGeneration;
	private final double MUTATION_RATE;
	private String targetString;
	
	public Algorithm()
	{
		currentGeneration = 0;
		MUTATION_RATE = 0.05;
		targetString = "hello world";
	}
	
	public Algorithm(double m)
	{
		currentGeneration = 0;
		MUTATION_RATE = m;
		targetString = "hello world";
	}
	
	public Algorithm(double m, String s)
	{
		currentGeneration = 0;
		MUTATION_RATE = m;
		targetString = s;
	}
	
	//Controls how many times the breeding process happends
	public void runBreedingNTimes(int n, Population p)
	{
		for(int i = 0; i < n; i++)
		{
			p.addMemberWithSort(breed(chooseParents(p)));
		}
		incrementCurrentGeneration();
	}
	
	
	/** Selects two parents to breed with, higher fitness scores
	 * will mean greater likelihood of being selected.
	 * 
	 * @param Population  we need this parameter to use the population's getters
	 *                    since that is the only way to access them
	 * @return Phrase[] the two parents that are going to breed
	 */
	public Phrase[] chooseParents(Population p)
	{
		//The data structure to return the two parents is an array of size 2
		Phrase[] parents = new Phrase[2];
		
		//Since giving a weight to each parent will be time-consuming
		//We will split up the members of the population into three classes
		//low, middle, and high. Math.random() will give us a 20% chance of choosing low
		//30% chance of choosing middle, and 50% chance of choosing high
		
		int low = p.getSize() / 5;
		int medium = p.getSize() / 2;
		int high = p.getSize();
		
		double n = Math.random();
		
		if(n < 0.2)
			parents = choose(p, 0, low);
		else if(n >= 0.2 && n < 0.5)
			parents = choose(p, low+1, medium);
		else
			parents = choose(p, medium+1, high);
		
		return parents;
	}
	
	/* Helper method to choose the parents*/
	private Phrase[] choose(Population p, int first, int last)
	{
		Phrase[] selectedParents = new Phrase[2];
		
		int index = (int) (Math.random() * (last-first+1)) + first;
		selectedParents[0] = p.getMember(index);
		
		index = (int) (Math.random() * (last-first+1)) + first;
		selectedParents[1] = p.getMember(index);
		
		return selectedParents;
	}
	
	/** Breeds two parents to produce a child phrase with the best qualities
	 * of both parents and maybe a mutation
	 * 
	 * @param Phrase[]  the parents that are going to breed
	 * @return Phrase  the child phrase with the best qualities from
	 * 					both parents
	 */
	public Phrase breed(Phrase[] parents)
	{
		String s = "";
		Phrase parent1 = parents[0];
		Phrase parent2 = parents[1];
		
		for(int i = 0; i < parents[0].getSize(); i++)
		{
			//If one of the letters match, give the child that letter
			if(parent1.getLetter(i) == targetString.charAt(i))
				s+= parent1.getLetter(i);
			else if(parent2.getLetter(i) == targetString.charAt(i))
				s+= parent2.getLetter(i);
			//If neither match, randomly pick one of the letters
			else		
			{
				double n = Math.random();
				
				if(n < 0.5)
					s+= parent1.getLetter(i);
				else
					s+= parent2.getLetter(i);
			}
			
		}
		
		Phrase child = new Phrase(s);
		mutate(child);
		return child;
		
	}
	
	private void mutate(Phrase p)
	{
		double n = Math.random();
		//Repeat for each character in the child phrase
		for(int i = 0; i < p.getSize(); i++)
		{
			n = Math.random();
			
			if(n < MUTATION_RATE)
			{
				char c = ' ';
				//Same code drom constructor to generate a-z and space " "
				int charCode = (int) (Math.random()*27) + 97;
				if(charCode == 123)
					c = ' ';
				else
					c = (char) (charCode);
				
				p.setChar(i, c);
			}
		}
	}
	
	public void incrementCurrentGeneration()
	{
		currentGeneration++;
	}
	
	public void printCurrentGeneration()
	{
		System.out.println(currentGeneration);
	}
	
	
	
	/** Here in this main method we just do two generations of this
	 * genetic algorithm process using a bit of procedural programming
	 * this can definitely be automated to run until a certain condition
	 * defined by the user is met (for example creating the target phrase)
	 * or achieving a certain average fitness. From here though, we can
	 * see that the fitness of the population increases over generations,
	 * albeit slowly at first. This genetic algorithm performs better than
	 * other ones (faster improvement) because of a few design choices such
	 * as using an eliminateLeastFit method that quickly fills the population
	 * pool with mostly fit members, but also the method of breeding where
	 * only the best chars are taken from each parent (though in reality this 
	 * does not always happen).
	 * 
	 */
	
	public static void main(String[] args)
	{
		Population test = new Population();
		Algorithm a = new Algorithm();
		
		test.assignFitnessInitial();

		test.sortByFitness(0, 99);
		
		a.printCurrentGeneration();
		
		test.printAllMembers();
		
		//System.out.println(test.getSize());
		
		test.eliminateLeastFit();
		
		//System.out.println(test.getSize());
		
		a.runBreedingNTimes(24, test);
		
		//System.out.println(test.getSize());
		
		a.printCurrentGeneration();
		
		test.printAllMembers();
	} 
	
}
