package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 *
 * @author Scott Vlasic
 */
public class QuickSorter extends Sorter
{

  @Override
  public void sort(WordList toSort, Comparator<String> comp)throws NullPointerException
  {
    quickSortRec(toSort, comp, 0 , toSort.length()-1);
  }

  
  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
	  // Implemented using psuedocode code from Powerpoint slides
	  if(start >= end){
		  return;
	  }
	  
	  int p = partition(list, comp, start, end);
	  quickSortRec(list,comp, start, p-1);
	  quickSortRec(list, comp, p+1, end);
	  
  }

  private int partition(WordList list, Comparator<String> comp, int start, int end)
  {
    
	// Implemented using psuedocode code from Powerpoint slides
	String pivot = list.get(end); // Sets pivot value equal to last element 
    int i = start - 1;
    
    for(int j = start; j < end; j++){
    	if(comp.compare(list.get(j), pivot) <= 0){ // Compares so that element at j <= pivot
    		i++;
    		list.swap(i, j); // Swaps i and j elements
    	}
    }
    
    list.swap(i + 1, end); // Swaps i + 1 with the last element
    return i + 1;
  }
}
