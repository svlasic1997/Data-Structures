package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

/*
 * A deer eats grass and lives no more than six years.
 */
public class Deer extends Animal 
{	
	/**
	 * Creates a Deer object.
	 * @param j: jungle  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Deer (Jungle j, int r, int c, int a) 
	{
		age = a;
		
		// From the Living abstract class
		jungle = j;
		row = r;
		column = c;
	}
		
	// Deer occupies the square.
	public State who()
	{ 
		return State.DEER; 
	}
	
	/**
	 * @param jNew     jungle in the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Jungle jNew)
	{
	
		int [] gridPopulation = new int[NUM_LIFE_FORMS];
		
		// Calls the census method to check the status of the grid
		census(gridPopulation);
		
		// If Deer's age is 6 return empty
		if(age == DEER_MAX_AGE){
			return new Empty(jNew, row, column);
		}
		
		// If there is no grass return empty
		else if(gridPopulation[GRASS] == 0){
			return new Empty(jNew, row, column);
		}
		
		// If more Pumas and Jaguars than Deer, and if there are twice as many Pumas to Jaguars then return Puma
		else if((gridPopulation[PUMA] + gridPopulation[JAGUAR] > gridPopulation[DEER]) && (gridPopulation[PUMA] >= gridPopulation[JAGUAR]*2)){
				return new Puma(jNew, row, column, 0);
		}
		
		// If more Pumas and Jaguars than Deer, and if there are at least as many Jaguars as Pumas then return Jaguar
		else if((gridPopulation[PUMA] + gridPopulation[JAGUAR] > gridPopulation[DEER]) && (gridPopulation[PUMA] <= gridPopulation[JAGUAR])){
			return new Jaguar(jNew, row, column, 0);
		}
		
		// If nothing above applies return Deer
		else{
			return new Deer(jNew, row, column, age + 1);
		}
		// 
		// See Living.java for an outline of the function. 
		// See Section 2.3 in the project description for the survival rules for a deer. 
		 
	}
}
