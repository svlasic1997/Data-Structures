package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

/**
 * A jaguar eats a deer and competes against a puma. 
 */
public class Jaguar extends Animal
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Jaguar (Jungle j, int r, int c, int a) 
	{
		age = a;
		
		// From the Living abstract class
		jungle = j;
		row = r;
		column = c;
	}
	
	/**
	 * A jaguar occupies the square. 	 
	 */
	public State who()
	{
		return State.JAGUAR; 
	}
	
	/**
	 * A jaguar dies of old age or hunger, from isolation and attack by more numerous pumas.
	 *  
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle jNew)
	{
		
		int [] gridPopulation = new int [NUM_LIFE_FORMS];
		
		// Calls the census method to check the status of the grid
		census(gridPopulation);
		
		// If age of Jaguar is 5 return Empty
		if(age == JAGUAR_MAX_AGE){
			return new Empty(jNew, row, column);
		}
		
		// If there are at least twice as many Pumas as Jaguars return Puma
		else if(gridPopulation[JAGUAR]*2 <= gridPopulation[PUMA]){
			return new Puma(jNew, row, column, 0);
		}
		
		// If Jaguars and Pumas outnumber Deer then return Empty
		else if(gridPopulation[JAGUAR] + gridPopulation[PUMA] > gridPopulation[DEER]){
			return new Empty(jNew, row, column);
		}
		
		// If none of the above apply return Jaguar
		else{
			return new Jaguar(jNew, row, column, age + 1);
		}
		
		// See Living.java for an outline of the function. 
		// See Section 2.1 in the project description for the survival rules for a jaguar. 
	}
}
