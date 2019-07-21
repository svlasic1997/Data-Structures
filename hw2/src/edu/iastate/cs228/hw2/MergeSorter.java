package edu.iastate.cs228.hw2;


import java.util.Arrays;
import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 *
 * @author Scott Vlasic
 */
public class MergeSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp)throws NullPointerException
  {
	  mergeSortRec(toSort, comp, 0, toSort.length()-1);
  }

  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
	  
	 if(start < end){
		 // Splits the list, sorts the halves, and then merges them together
		 int mid = (start+end)/2;
		 mergeSortRec(list,comp, start, mid);
		 mergeSortRec(list, comp, mid+1, end);
		 merge(list.getArray(), comp, start, end);
	 }
	  
  }
  
 /**
  * Helper method which merges the left array and right array into one 
  * sorted array
  * @param list
  * The sorted array
  * @param comp
  * Comparator to compare strings
  * @param list2
  * The left array
  * @param list3
  * The right array
  */
  
  private void merge(String[] a, Comparator<String> comp, int start, int end){
	 
//	  int mid = (start + end)/2;
//	  int l = mid - start; // Length of the left side
//	  int r = end - (mid + 1); // Length of the right side            FAILED MERGE ATTEMPT
//	  
//	  String[] left = new String[l];
//	  String[] right = new String[r];
	  
	  int mid = (start+end)/2;
	  int left = mid - start + 1;
	  int right = end - mid;
	  
	  String[] leftArray = new String[left]; // Left string array
	  String[] rightArray = new String[right]; // Right string array 
	  
	  for(int i = 0; i < left; i++){
		  leftArray[i] = a[start + i];
	  }
	  for(int j = 0; j < right; j++){
		  rightArray[j] = a[mid + 1 + j];
	  }
	  
	  int i = 0, j = 0, index = start;
	  
	  // Implemented using pseudocode from PowerPoint slides
	  while(i < left && j < right){
		  
		  if(comp.compare(leftArray[i], rightArray[j]) <= 0){
			  a[index] = leftArray[i]; // Append a[index] to leftArray
			  i++;
		  }
		  
		  else{
			  a[index] = rightArray[j]; // Append a[index] to rightArray
			  j++;
		  }
		  
		  index++;
	  }
	  
	  while(i < left){
		  a[index] = leftArray[i];
		  i++;
		  index++;
	  }
	  
	  while(j < right){
		  a[index] = rightArray[j];
		  j++;
		  index++;
	  }
  }

}
