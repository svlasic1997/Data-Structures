package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

/**
 * A puma eats deers and competes against a jaguar. 
 */
public class Puma extends Animal 
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Puma (Jungle j, int r, int c, int a) 
	{
		age = a;
		
		// From the Living abstract class
		jungle = j;
		row = r;
		column = c;
		
		
	}
		
	/**
	 * A puma occupies the square. 	 
	 */
	public State who()
	{
		return State.PUMA; 
	}
	
	/**
	 * A puma dies of old age or hunger, or from attack by a jaguar. 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle wNew)
	{ 
		
		int [] gridPopulation = new int [NUM_LIFE_FORMS];
		
		// Calls the census method to check the status of the grid
		census(gridPopulation);
		
		// If Puma's age is 4 return Empty
		if(age == PUMA_MAX_AGE){
			return new Empty(wNew, row, column);
		}
		
		// If number of Jaguars outnumber number of Pumas return Jaguar
		else if(gridPopulation[JAGUAR] > gridPopulation[PUMA]){
			return new Jaguar(wNew, row, column, 0);
		}
		
		// If number of Jaguars and Pumas outnumber Deer return Empty
		else if((gridPopulation[JAGUAR] + gridPopulation[PUMA]) > gridPopulation[DEER]){
			return new Empty(wNew, row, column);
		}
		
		// If nothing above applies return the Puma
		else{
			return new Puma(wNew, row, column, age + 1);
		}
		
		// See Living.java for an outline of the function. 
		// See Section 2.2 in the project description for the survival rules for a puma.  
	}
}
