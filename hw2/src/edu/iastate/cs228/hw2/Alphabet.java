package edu.iastate.cs228.hw2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 *
 * @author Scott Vlasic
 */
public class Alphabet 
{
  /**
   * A lookup table containing characters and their positions.
   * Sorted by the character of each entry.
   */
  private CharAndPos[] lookup;
  
  /**
   * Comparator method used to sort the CharAndPos so that binarySearch can be invoked
   * upon it (T.A. assisted with construction)
   * @param arr
   * CharAndPos variable to be sorted
   */
  private void lookupSorter(CharAndPos[] a){
	  Comparator<CharAndPos> comp = (CharAndPos charAndPosBeginning, CharAndPos charAndPosNext)->Character.compare(charAndPosBeginning.character, charAndPosNext.character);
	  Arrays.sort(a,comp);
  }
  
  /**
   * Constructs and initializes the ordering to have exactly the ordering of
   * the elements in the given array.
   *
   * @param ordering
   *   the array containing the characters, in the ordering desired
   * @throws NullPointerException
   *   if {@code ordering} is {@code null}
   *   
   */
  public Alphabet(char[] ordering)throws NullPointerException
  {
	  try{
	  	lookup = new CharAndPos[ordering.length];
		for(int i = 0; i < ordering.length; i++){
			char c = ordering[i];
			lookup[i] = new CharAndPos(c, i);
		}
		
	  }
	  catch(NullPointerException e){
		  System.out.println(e.getMessage());
	  }
	  
	  lookupSorter(lookup);
  }

  /**
   * Constructs and initializes the ordering by reading from the indicated
   * file. The file is expected to have a single character on each line, and
   * the ordering in the file is the order that will be used.
   *
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public Alphabet(String filename)throws NullPointerException,FileNotFoundException
  {
	  
	  Scanner scan = new Scanner(new File(filename));
	  String line = "";
	  
	  ArrayList<String> temp = new ArrayList<String>();
	  
	  while(scan.hasNextLine()){
		  line = scan.nextLine();
		  temp.add(line);
	  }
	  
	  scan.close();
	  
	  lookup = new CharAndPos[temp.size()];
	  
	  for(int i = 0; i < temp.size(); i++){
		  
		  lookup[i] = new CharAndPos(temp.get(i).charAt(0), i);
	  }
	  
	  lookupSorter(lookup);
  }
  
  /**
   * Returns true if and only if the given character is present in the
   * ordering.
   *
   * @param c
   *   the character to test
   * @return
   *   true if and only if the given character is present in the ordering
   */
  public boolean isValid(char c)
  {
	  for(int i = 0; i < lookup.length; i++){
		  
		  if(lookup[i].character == c){
			 
			  return true;
			  
		  }
	  }
	  
	  return false;
  }

  /**
   * Returns the position of the given character in the ordering.
   * Returns a negative value if the given character is not present in the
   * ordering.
   *
   * @param c
   *   the character of which the position will be determined
   * @return
   *   the position of the given character, or a negative value if the given
   *   character is not present in the ordering
   */
  public int getPosition(char c)
  {
	if(binarySearch(c) >= 0){
		
		int index = binarySearch(c); // For testing purposes
		
		return lookup[binarySearch(c)].position;
	}
	
    return -1;
  }

  /**
   * Returns the index of the entry containing the given character within the
   * lookup table {@link #lookup}.
   * Returns a negative value if the given character does not have an entry in
   * the table.
   *
   * @param toFind
   *   the character for which to search
   * @return
   *   the index of the entry containing the given character, or a negative
   *   value if the given character does not have an entry in the table
   */
  private int binarySearch(char toFind)
  {
    /*
     * note: for testing, you can perform a simple search through the array
     * instead of a binary search, allowing you to test other components with a
     * working (but slower) implementation
     */

	// Implemented using skeleton code from the Powerpoint slides
	int n = lookup.length;
	int left = 0;
	int right = n-1;
	
	while(left <= right){
		
		int mid = (left+right)/2;
		
		if(lookup[mid].character == toFind){
			
			return mid;
			
		}
		
		if(lookup[mid].character > toFind){
			
			right = mid - 1;
			
		}
		
		else{
			
			left = mid + 1;
			
		}
	}

    return -1;
  }


  /**
   * A PODT class containing a character and a position.
   * Used as the entry type within {@link Alphabet#lookup lookup}.
   */
  /* already completed */
  private static class CharAndPos
  {
    /**
     * The character of the entry.
     */
    public char character;

    /**
     * The position of the entry in the ordering.
     */
    public int position;


    /**
     * Constructs and initializes the entry with the given values.
     *
     * @param character
     *   the character of the entry
     * @param position
     *   the position of the entry
     */
    public CharAndPos(char character, int position)
    {
      this.character = character;
      this.position = position;
    }


    @Override
    public boolean equals(Object obj)
    {
      if (null == obj || this.getClass() != obj.getClass())
      {
        return false;
      }

      CharAndPos o = (CharAndPos) obj;

      return this.character == o.character && this.position == o.position;
    }

    @Override
    public int hashCode()
    {
      return character ^ position;
    }

    @Override
    public String toString()
    {
      return "{" + character + ", " + position + "}";
    }
  }
}
