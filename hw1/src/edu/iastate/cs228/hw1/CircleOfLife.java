package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Scott Vlasic
 *
 */

/**
 * 
 * The CircleOfLife class performs simulation over a grid jungle 
 * with squares occupied by deers, jaguars, pumas, grass, or none. 
 *
 */
public class CircleOfLife 
{
	/**
	 * Update the new jungle from the old jungle in one cycle. 
	 * @param jOld  old jungle
	 * @param jNew  new jungle 
	 */
	public static void updateJungle(Jungle jOld, Jungle jNew)
	{
		for(int i = 0; i < jOld.grid.length; i++){
			
			for(int j = 0; j < jOld.grid[0].length; j++){
				
				jNew.grid[i][j] = jOld.grid[i][j].next(jNew);
				
			}
		}
		
		// For every life form (i.e., a Living object) in the grid jOld, generate  
		// a Living object in the grid jNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
	}
	
	/**
	 * Repeatedly generates jungles either randomly or from reading files. 
	 * Over each jungle, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		System.out.println("Circle of Life in the Amazon Jungle");
		System.out.println("keys: 1 (random jungle) 2 (file input) 3 (exit)\n");
		
		int trial = 1;
		Scanner reader = new Scanner(System.in);
		System.out.println("Trial " + trial + " :"   );
		int n = reader.nextInt();
			
		if(n == 1){
			System.out.println("Random jungle");
			System.out.println("Enter grid width:");
			int n2 = reader.nextInt();
			System.out.println("Enter the number of cycles:");
			int n3 = reader.nextInt();
			reader.close();
			
			Jungle jOld = new Jungle(n2);
			jOld.randomInit();
			Jungle jNew = new Jungle(n2);
			jNew.randomInit();
			updateJungle(jOld, jNew);
			
			System.out.println("Initial jungle:\n");
			System.out.println(jOld.toString().replace("]", "\n").replace("[", "\n").replace(",", ""));
			
			
			System.out.println("Final jungle: \n");
			System.out.println(jNew.toString().replace("]", "\n").replace("[", "\n").replace(",", ""));
			
			trial = trial + 1;
		}
		else if(n == 2){
			System.out.println("Jungle input from a file");
			System.out.println("File name: ");
			String file = "C://Users/svlas/Downloads/proj1S18 public tests (3)/public/public3-10x10.txt";
			System.out.println("Enter the number of cycles:");
			int n3 = reader.nextInt();
			reader.close();
			
			Jungle jOld = new Jungle(file);
			Jungle jNew = new Jungle(file);
			
			updateJungle(jOld, jNew);
			
			System.out.println("Initial jungle:\n");
			System.out.println(jOld.toString().replace("[", "\n").replace("]", "\n").replace(",", ""));
			
			System.out.println("Final jungle: \n");
			System.out.println(jNew.toString().replace("[", "\n").replace("]", "\n").replace(",", ""));
			
			trial = trial + 1;
			System.out.println("Trial " + trial + " :" );
			
		}
		
		else if (n !=1 || n!= 2){
			System.exit(0);
		}
		
		
		
		
		
		// Generate CircleOfLife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random jungle, 2 to read a jungle from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two jungles even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the jungle 
		//    odd from the jungle even; in an odd numbered cycle, generate even 
		//    from odd. 
		
//		Jungle even;   				 // the jungle after an even number of cycles 
//		Jungle odd;                  // the jungle after an odd number of cycles
//		
		// 4. Print out initial and final jungles only.  No intermediate jungles should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate jungles.)
		// 
		// 5. You may save some randomly generated jungles as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
	}
}
