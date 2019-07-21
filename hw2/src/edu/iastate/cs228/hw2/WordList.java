package edu.iastate.cs228.hw2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple list of Strings.
 *
 * @author Scott Vlasic
 */
public class WordList implements Cloneable
{
  /**
   * The array holding all of the elements of the list.
   */
  private String[] words;


  /**
   * Constructs and initializes the list to have exactly the same contents as
   * the given array.
   *
   * @param contents
   *   the array with the contents of the new list
   * @throws NullPointerException
   *   if {@code contents} is {@code null}
   */
  public WordList(String[] contents)throws NullPointerException
  {
	  try{
		  words = new String[contents.length];
		  
		  for(int i = 0; i < words.length; i++){
			  words[i] = contents[i];
		  }
	  }
	  catch(Exception e){
		  System.out.println(e.getMessage());
	  }
	  
	  
  }

  /**
   * Constructs and initializes the list by reading from the indicated file.
   * The file is read assuming that each line contains a word. The ordering in
   * the file is the order that will be used by the list.
   *
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public WordList(String filename)throws NullPointerException, FileNotFoundException
  {
	  Scanner scan = new Scanner(new File(filename));
	  String line = "";
	  
	  ArrayList<String> temp = new ArrayList<String>();
	  
	  while(scan.hasNextLine()){
		  line = scan.nextLine();
		  temp.add(line);
	  }
	  
	  scan.close();
	  
	  words = temp.toArray(new String[temp.size()]);
	  
  }


  /**
   * Returns the number of elements in the list.
   *
   * @return
   *   the number of elements in the list
   */
  public int length()
  {
    return words.length;
  }

  /**
   * Returns the element of the list at the indicated index.
   *
   * @param idx
   *   the index of the element to retrieve
   * @return
   *   the element at the indicated index
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of
   *   the list
   */
  public String get(int idx)throws IndexOutOfBoundsException
  {
	  return words[idx];
  }

  /**
   * Sets the element of the list at the indicated index to the given value.
   *
   * @param idx
   *   the index of the element to set
   * @param newValue
   *   the new value of the element
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of the
   *   list
   */
  public void set(int idx, String newValue)throws IndexOutOfBoundsException
  {
    words[idx] = newValue;
  }

  /**
   * Swaps the indicated elements in the list.
   *
   * @param idxA
   *   the index of one of the elements to swap
   * @param idxB
   *   the index of the other element to swap
   * @throws IndexOutOfBoundsException
   *   if either of {@code idxA} or {@code idxB} is negative or greater than or
   *   equal to the length of the list
   */
  public void swap(int idxA, int idxB) throws IndexOutOfBoundsException
  {
    String temp = words[idxA];
    words[idxA] = words[idxB];
    words[idxB] = temp;
  }

  /**
   * Returns the array used by the list to store its elements.
   *
   * @return
   *   the array used by the list to store its elements
   */
  public String[] getArray()
  {
    return words;
  }

  /**
   * Performs a deep copy of the list.
   */
  @Override
  public WordList clone()
  {
    /*
     * note: since Strings are immutable, you don't need to clone them
     */
	try{
		
		WordList copy = (WordList) super.clone();
		copy.words = new String[words.length];
		
		for(int i = 0; i < words.length; i++){
			copy.words[i] = words[i];
		}
		return copy;
	}
	
	catch(CloneNotSupportedException e){
		return null;
	}
  }
}
