package edu.iastate.cs228.proj2;

import java.util.Comparator;

public class QuickSort extends SorterWithStatistics {

	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// Implemented using code from lecture slides
		if(words == null || words.length == 0){
			
			throw new RuntimeException("Null pointer or zero size");
		}
		
		if(words.length == 1){
			
			return;
		}
		
		quickSort(words, 0, words.length - 1, comp);
	}
	
	/**
	 * Sorts the two halves of the arrays 
	 * 
	 * @param list
	 * List of Strings 
	 * @param first
	 * First element of the array
	 * @param last
	 * Last element of the array
	 * @param comp
	 * Comparator used to compare the two Strings 
	 */
	private static void quickSort(String[] list, int first, int last, Comparator<String> comp){
		
		if(last > first){
			int pivotIndex = partition(list, first, last, comp);
			quickSort(list, first, pivotIndex - 1, comp);
			quickSort(list, pivotIndex + 1, last, comp);
		}
	}
	
	/**
	 * Partitions the array into two halves with the left half less than the pivot and the right half larger than the 
	 * pivot.  Pivot is initially the first element in the array
	 * 
	 * @param list
	 * List of Strings
	 * @param first
	 * First element in the array after the pivot
	 * @param last
	 * Last element in the array
	 * @param comp
	 * Comparator used to compare the Strings 
	 * @return
	 * Partition of the array
	 */
	private static int partition(String[] list, int first, int last, Comparator<String> comp){
		
		String pivot = list[first];		
		int low = first + 1;
		int high = last;
		
		while(high > low){
			//list[low].compareTo(pivot)
			while(low <= high && comp.compare(list[low], pivot) <= 0){
				low ++;
			}
			//list[high].compareTo(pivot)
			while(low <= high && comp.compare(list[high], pivot) > 0){
				high --;
			}
			
			if(high > low){
				String temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}
		//list[high].compareTo(pivot)
		while(high > first && comp.compare(list[high], pivot) >= 0){
			high --;
		}
		//pivot.compareTo(list[high])
		if(comp.compare(pivot, list[high]) > 0){
			list[first] = list[high];
			list[high] = pivot;
			return high;
		}
		
		else{
			return first;
		}
	}
}
