# genetic-algorithm
Hello there!

The following project models a genetic algorithm which is a tool to improve the "fitness" of the population through the process of natural selection. 


About writing the program:

After reading about genetic algorithms and watching a Youtube video of a tutorial in JavaScript (rather than Java which I used for this project), I implemented most of this program from scratch using the ideas provided from these sources. I did use a quicksort implementation from "Vogella" just to save some time, but it was a sorting algorithm for an integer array which I had to adapt to a Phrase array list using fitness scores which was more complex. The main skills I was practicing in this project were object-oriented programming techniques (such as abstraction and encapsulation) as well as some procedural programming for the actual algorithm used.


The basic steps are the following:

1) Initial Population: We first need a set of members in the population (in this implementation I used 100 by default though you can use any number depending on your specific requirements of speed and memory)

2) Selection: We assign a "fitness score" to each member of the population by defining what we consider as a good characteristic. We then eliminate the least fit members of the population (here I chose to eliminate the bottom 30%). Typically, some genetic algorithms don't include this step, but I thought this would be a good way of incorporating the biology idea of a carrying capacity where due to a constrainment of natural resources, only the best survive and get to reproduce.

3) Breeding: In this process, the best traits from two members of the population who act as parents are passed down to the child. In the program, I decided to exclusively pass down the best traits though in reality this does not always happen due to dominant and recessive genes.

4) Mutation: Just like how DNA acquires mutations between generations to add variety into the population, I made sure that there was a small probability that this could occur (5% was here to improve the speed of the evolution algorithm, but 1-2% would be more realistic).


The specific problem I was designing this algorithm for is to generate phrases which would match "hello world." The phrases would all be of length 11 and undergo the process described above. The phrases would "have a" array of characters including a-z (lowercase) and a space.The fitness score would be the number of characters in corresponding positions that matched. For example, "hello venus" would have 6 letters that matched and were in the correct positions already and would have a fitness score of 6/11 (stored as a double so 0.545 approximately). The phrases in the population (implemented as an ArrayList<Phrase> ) with the bottom 30% of fitness scores would be eliminated. To do this efficiently ( O(1) ), we first sorted the array list using QuickSort ( O(nlogn) ). This was also especially helpful because when we added children into the array list later, we could just place them in the correct sorted position.

In the breeding process, if one of the parents had a character that was already in the correct position, then the child would get that character. If not, then the child would get a random character. For example, if you had parent 1 as "hello venus" and "abcdefworld," the child would get the "hello " from parent 1 and "world" from parent 2 to become "hello world." 

The mutation process meant that any given character in the child's char array at the time of generation had a 5% chance of being mutated. This gave mutations a relatively high chance of occurring.

The algorithm was run for two generations in the main method, showing that the average fitness score of the population increases over time. This process could be automated to run until a certain end condition was met such as creating the "hello world" phrase or until the average fitness reached a certain value.
