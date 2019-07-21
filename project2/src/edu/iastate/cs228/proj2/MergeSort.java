package edu.iastate.cs228.proj2;

import java.awt.List;
import java.util.Comparator;

@SuppressWarnings("unused")
public class MergeSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		
		// Implemented using code from the lecture slides
		if(words == null || words.length == 0){
			
			throw new IllegalArgumentException("Null pointer or zero size");
		}
		
		if(words.length == 1){
			return;
		}
		
		// Merge sort the first half
		String[] firstHalf = new String[words.length/2];
		System.arraycopy(words, 0, firstHalf, 0, words.length/2);
		sortHelper(firstHalf, comp);
		
		// Merge sort the second half
		int secondHalfLength = words.length - words.length/2;
		String[] secondHalf = new String[secondHalfLength];
		System.arraycopy(words, words.length/2, secondHalf, 0, secondHalfLength);
		sortHelper(secondHalf, comp);
		
		// Merge firstHalf with secondHalf into list
		merge(firstHalf, secondHalf, words, comp);
	}

	
	/**
	 * Merges the two halves of the list into one sorted list
	 * 
	 * @param list1
	 * First half of the list
	 * @param list2
	 * Second half of the list
	 * @param temp
	 * Combined list after the merge
	 * @param comp
	 * Comparator used to compare String values
	 */
	public static void merge(String[] list1, String[] list2, String[] temp, Comparator<String>comp){
		
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;
		
		while(current1 < list1.length && current2 < list2.length){
			
			if(comp.compare(list1[current1], list2[current2]) < 0){
				
				temp[current3++] = list1[current1++];
			}
			
			else{
				
				temp[current3++] = list2[current2++];
			}
			
		}
		
		while(current1 < list1.length){
			
			temp[current3++] = list1[current1++];
		}
		
		while(current2 < list2.length){
			
			temp[current3++] = list2[current2++];
		}
		
	}
}
