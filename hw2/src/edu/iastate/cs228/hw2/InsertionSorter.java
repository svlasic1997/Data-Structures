package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 *
 * @author Scott Vlasic
 */
public class InsertionSorter extends Sorter
{
	
  @Override
  public void sort(WordList toSort, Comparator<String> comp)throws NullPointerException
  {
	
	// Implemented using pseudocode code from Powerpoint slides
    int n = toSort.length();
    
    for(int i = 1; i < n; i++){
    	
    	String temp = toSort.get(i); // Sets temp to element of toSort at i
    	int j = i - 1;
    	
    	while(j > -1 && comp.compare(toSort.get(j), temp) > 0){ // Compares so that element at j > temp
    		
    		toSort.set(j + 1, toSort.get(j)); // Sets j + 1 equal to element at j
    		j = j-1;
    		
    	}
    	
    	toSort.set(j + 1, temp); // Sets j + 1 equal to temp
    }
  }
}
