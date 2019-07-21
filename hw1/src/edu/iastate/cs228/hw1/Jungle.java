package edu.iastate.cs228.hw1;

/**
 *  
 * @author Scott Vlasic
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random; 

/**
 * 
 * The jungle is represented as a square grid of size width X width. 
 *
 */
public class Jungle 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Jungle(String inputFileName) throws FileNotFoundException
	{	
		try{
			 
			Scanner in = new Scanner(new File(inputFileName));
			
			while(in.hasNext()){
				String l = in.nextLine();
				width = width + 1; // Updates the width
				//in.next();
			}
			
			System.out.println(width); // Prints the width of the grid
			
			in.close(); 
		}
		catch(Exception e){
			
			System.out.println(e.getMessage()); // Catches the exception
		}
		
		this.grid = new Living [width][width]; // New grid object
		
		Scanner scan = new Scanner(new File(inputFileName));
		
		for(int row = 0; row < width; row++){
			
			String s  = scan.nextLine();
			Scanner scan2 = new Scanner(s);
			
			for(int column = 0; column < width; column++){
				
				String s2 = scan2.next();
				char c = s2.charAt(0);
				
				if(c == 'J'){
					grid[row][column] = new Jaguar(this, row, column, 0);
				}
				
				else if(c == 'D'){
					grid[row][column] = new Deer(this, row, column, 0);
				}
				
				else if(c == 'P'){
					grid[row][column] = new Puma(this, row, column, 0);
				}
				
				else if (c == 'G'){
					grid[row][column] = new Grass(this, row, column);
				}
				
				else{
					grid[row][column] = new Empty(this, row, column);
				}
				
			}
			scan2.close();
		}
		
		scan.close();
		
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid jungle in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
	}
	
	/**
	 * Constructor that builds a w X w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Jungle(int w)
	{
		width = w; // w now equal to grid size of w X w
		grid = new Living[width][width]; // Grid of size w X w
		
	}
	
	
	public int getWidth()
	{ 
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the jungle by randomly assigning to every square of the grid  
	 * one of Deer, Empty, Grass, Jaguar, or Puma.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		
		Random generator = new Random(); 
		
		// Loop to fill the grid with random values from 0 to 4 which 
		// correspond to the five life forms
		for(int row = 0; row < grid.length; row++){
			
			for(int column = 0; column < grid[row].length; column++){
				
				int number = generator.nextInt(5);
				 
				if(number == Living.DEER){
					grid[row][column] = new Deer(this, width, width, 0);
				}
				
				 
				else if (number == Living.PUMA){
					grid[row][column] = new Puma(this, width, width, 0);
				}
				
				 
				else if (number == Living.JAGUAR){
					grid[row][column] = new Jaguar(this, width, width, 0);
				}
				
				 
				else if(number == Living.GRASS){
					grid[row][column] = new Grass(this, width, width);
				}
				
				
				else{
					grid[row][column] = new Empty(this, width, width);
				}
			}
		}
		
		 
	}
	
	
	/**
	 * Output the jungle grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		// 2D String array to store jungle grid 
		String [][] s = new String[width][width];
		
		
		for(int row = 0; row < s.length; row++){
			
			for(int col = 0; col < s[0].length; col++){
				
				// If state of grid at this call is Deer, fill with letter D and age of Deer
				if(grid[row][col].who() == State.DEER){
					s[row][col] = "D" + ((MyAge)grid[row][col]).myAge() + " ";
				}
				
				// If state of grid at this call is Jaguar, fill with letter J and age of Jaguar
				else if (grid[row][col].who() == State.JAGUAR){
					s[row][col] = "J" + ((MyAge)grid[row][col]).myAge() + " ";
				}
				
				// If state of grid at this call is Puma, fill with letter P and age of Puma
				else if (grid[row][col].who() == State.PUMA){
					s[row][col] = "P" + ((MyAge)grid[row][col]).myAge() + " ";
				}
				
				// If state of grid at this call is Grass, fill with letter G 
				else if(grid[row][col].who() == State.GRASS){
					s[row][col] = "G" + " " + " ";
				}
				
				// If state of grid at this call is Empty, fill with letter E
				else{
					s[row][col] = "E" + " " + " ";
				}
					
				}
			}
		
		
		return Arrays.deepToString(s);
	}
	

	/**
	 * Write the jungle grid to an output file.  Also useful for saving a randomly 
	 * generated jungle for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		
		// Used to write to the file
		PrintWriter writer = new PrintWriter(new File(outputFileName));
		
		for(int i =0; i < width; i++){
			
			for(int j=0; j< width; j++){
				
				writer.println(this.toString());
			}
		}
		
		// Closes the file
		writer.close();
		
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    D, E, G, J, P. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
	}			
}
