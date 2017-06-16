package genetics;

import java.lang.Math;

public class Phrase 
{
	//The phrase has field which is an array of chars
	//This field will be compared to the target phrase to determine fitness
	
	private char[] myLetters;
	private final int TARGET_PHRASE_SIZE;
	private double fitness;
	
	public Phrase()
	{
		//In this example, "hello world" is 11 chars
		TARGET_PHRASE_SIZE = 11;
		
		//Don't forget to initialize the char array!
		myLetters = new char[TARGET_PHRASE_SIZE];
		
		//Fill the character array
		populate();
		
		fitness = -1.0;
	}
	
	//The special constructor allows for the construction of a
	//phrase object whose char[] is specified
	public Phrase(String phrase)
	{
		TARGET_PHRASE_SIZE = 11;
		myLetters = new char[TARGET_PHRASE_SIZE];
		
		for(int i = 0; i < TARGET_PHRASE_SIZE; i++)
		{
			myLetters[i] = phrase.charAt(i);
		}
		
		fitness = -1.0;
	}
	
	public Phrase(int n, String target)
	{
		TARGET_PHRASE_SIZE = target.length();
		myLetters = new char[TARGET_PHRASE_SIZE];
		populate();
		fitness = -1.0;
	}
	
	public void populate()
	{
		//myLetters is initiatlized with random characters
		//from a to z (lowercase) and a space (see ASCII table for reference)
		for(int i = 0; i < TARGET_PHRASE_SIZE; i++)
		{
			int newChar = (int) (Math.random()*27) + 97;
					
			//Since ' ' (ascii 32) is not adjacent to the lowercase
			//We add one more number to the 97-122 range and if that
			//number is picked, we add a space
			if(newChar == 123)
				myLetters[i] = ' ';
			else
				myLetters[i] = (char) (newChar);
					
		}
	}
	
	public void assignFitness(String targetPhrase)
	{
		double fitScore = 0.0;
		
		for(int i = 0; i < targetPhrase.length(); i++)
		{
			if(targetPhrase.charAt(i) == getLetter(i))
				fitScore = fitScore + 1.0/(targetPhrase.length());
		}
		setFitness(fitScore);
	}
	
	/* Getters and setters */
	
	public char[] getLetters()
	{
		return myLetters;
	}
	
	public char getLetter(int i)
	{
		return myLetters[i];
	}
	
	public int getSize()
	{
		return myLetters.length;
	}
	
	public double getFitness()
	{
		return fitness;
	}
	
	public void setFitness(double fitScore)
	{
		fitness = fitScore;
	}
	
	public void setChar(int i, char c)
	{
		myLetters[i] = c;
	}
	
	public String toString()
	{
		String s = "";
		
		for(char c : myLetters)
		{
			s += c;
		}
		
		return s;
	}
	
}
