package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

/**
 * Grass may be eaten out or taken over by deers. 
 *
 */
public class Grass extends Living 
{
	public Grass (Jungle j, int r, int c) 
	{
		// From the Living abstract class
		jungle = j;
		row = r;
		column = c;
	}
	
	public State who()
	{ 
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many deers in the neighborhood. Deers may also 
	 * multiply fast enough to take over Grass. 
	 * 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle jNew)
	{
		
		int[] gridPopulation = new int[NUM_LIFE_FORMS];
		
		// Calls the census method to check the status of the grid
		census(gridPopulation);
		
		// If there are at least 3 times as many Deer as grass, then return Empty
		if(gridPopulation[DEER] >= gridPopulation[GRASS]*3){
			return new Empty(jNew, row, column);
		}
		
		// If there are at least 4 Deer in the grid return Deer
		else if(gridPopulation[DEER] >= 4){
			return new Deer(jNew, row, column, 0);
		}
		
		// If nothing above applies return Grass
		else{
			return new Grass(jNew, row, column);
		}
		// See Living.java for an outline of the function. 
		// See Section 2.4 in the project description for the survival rules for grass.  
	}
}
