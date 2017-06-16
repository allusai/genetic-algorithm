package genetics;

public class PopulationRunner 
{
	public static void main(String[] args)
	{
		Population test = new Population();
		
		/* Test each method in the class */
		
		//int n = 28;
		
		//System.out.println(test.getMember(n));
		test.assignFitnessInitial();
		//System.out.println(test.getMember(n).getFitness());
		test.sortByFitness(0, 99);
		
		//System.out.println(test.getMember(70).getFitness());
		Phrase p = new Phrase("hello iowjf");
		test.addMemberWithSort(p);
		System.out.println(p.getFitness());
		//System.out.println(test.getMember(100).getFitness());
		
		//for(int j = 0; j < 100; j++)
		//System.out.println(test.getMember(j));
		
	} 
	
}
