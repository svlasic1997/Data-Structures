package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Jungle j, int r, int c) 
	{
		// From the Living abstract class
		jungle = j;
		row = r;
		column = c;
	}
	
	public State who()
	{ 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Deer, Grass, Jaguar, or Puma, or 
	 * remain empty. 
	 * @param jNew     jungle in the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Jungle jNew)
	{
		
		int [] gridPopulation = new int [NUM_LIFE_FORMS];
		
		// Calls the census method to check the status of the grid
		census(gridPopulation);
		
		// If more than one neighboring Deer, return Deer
		if(gridPopulation[DEER] > 1){
			return new Deer(jNew, row, column, 0);
		}
		
		// If more than one neighboring Puma, return Puma
		else if(gridPopulation[PUMA] > 1){
			return new Puma(jNew, row, column, 0);
		}
		
		// If more than one neighboring Jaguar, return Jaguar
		else if (gridPopulation[JAGUAR] > 1){
			return new Jaguar(jNew, row, column, 0);
		}
		
		// If more than one neighboring grass, return Grass
		else if(gridPopulation[GRASS] >= 1){
			return new Grass(jNew, column, row);
		}
		
		// If nothing above applies return Empty
		else{
			return new Empty(jNew, row, column);
		}
		
		// See Living.java for an outline of the function. 
		// See Section 2.5 in the project description for corresponding survival rules. 
	}
}
