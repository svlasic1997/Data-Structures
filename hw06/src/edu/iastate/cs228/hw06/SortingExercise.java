package edu.iastate.cs228.hw06;


/**
 * 
 * @author Scott Vlasic
 * 
 * NOTE:
 * 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 2. No data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes, which do not violate any of above.
 *    
 *    
 * 
 */


public class SortingExercise
{
	/**
	 * Modified implementation of in class provided quick sort code.
	 * 
	 * 
	 * The implementation of our original quick sort needs to be
	 * revised as follows in this implementation. If the array has 
	 * 23 entries, choose the middle entry as the pivot. For arrays
	 * between 24 - 50 use the last element as the pivot value. For 
	 * arrays larger than 50 entries, use the median-of-three 
	 * pivot-selection scheme described below. For arrays fewer than 
	 * 23 entries, use insertion sort instead of quick sort.
	 * 
	 * Median-of-three pivot selection chooses as pivot the median of
	 * three entries in the array, i.e., the first entry, the middle 
	 * entry, and the last entry. We will use specific version of it
	 * as follows. 
	 * 
	 * For example, let's say original array is as follows
	 * 
	 *  5, 8, 6, 4, 9, 3, 7, 1, 2
	 * 
	 * first entry = 5
	 * middle entry = 9 // index is (0+8)/2=4
	 * last entry = 2
	 * 
	 * Median of 5, 9, 2, would be 5.
	 * Check: https://en.wikipedia.org/wiki/Median
	 * 
	 * Now our array would look as follows after positioning the pivot:
	 * 
	 *  2, 8, 6, 4, 5, 3, 7, 1, 9
	 * 
	 * Now our array would look as follows just before partitioning:
	 * 
	 *  2, 5, 6, 4, 8, 3, 7, 1, 9
	 *  
	 * Our pivot is at position 1 of array, i.e., value 5.
	 * Both low and high start as shown in source code of quick sort under
	 * lecture notes, i.e., 
	 * 
	 * int low = first + 1;
	 * int high = last;
	 * 
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void modifiedQuickSort(int[] arr)
 {

  if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  
  // If arr.length is less than 23, use insertion sort
  if(arr.length < 23){
	  
	  for(int i = 1; i < arr.length; i++){
		  int val = arr[i];
		  int k;
		  for(k = i - 1; k >= 0 && arr[k] > val; k--){
			  
			  arr[k+1] = arr[k];
		  }
		  
		  arr[k+1] = val;
	  }
  }
  
  // If arr.length equals 23, use middle value as pivot
  if(arr.length == 23){
	  
	  quickSort2(arr, 0, arr.length - 1);
  }
  
  // If arr.length is between 24-50 use last element as pivot
  if(arr.length >= 24 && arr.length <=50){
	 
	  quickSort(arr, 0, arr.length - 1);
	  
  }
  
  // If arr.length is greater than 50, use median-of-three pivot value selection
  if(arr.length > 50){
	  
	  quickSort(arr,0,arr.length-1);
  }
  
  
 }
 
 /**
  * Sorts both sides of the array
  * @param list Array to be sorted
  * @param first First element 
  * @param last Last element 
  */
 private static void quickSort(int[] list, int first, int last){
	 
	 if(last > first){
		 
		 int pivotIndex = partition(list, first, last);
		 
		 quickSort(list, first, pivotIndex - 1);
		 quickSort(list, pivotIndex, last);
	 }
 }
 
 /**
  * Sorts both sides of the arrays 
  * @param list Array to be sorted
  * @param first First element
  * @param last Last element 
  */
 private static void quickSort2(int list[], int first, int last){
	 
	 int pivotIndex = partition2(list, first, last);
	 
	 if(first < pivotIndex - 1){
		 quickSort(list, first, pivotIndex-1);
	 }
	 if(pivotIndex < last){
		 quickSort(list, pivotIndex, last);
	 }
 }
 
 /**
  * Helper method for when the pivot is located as the last element 
  * @param list Array of ints to be sorted
  * @param first First element 
  * @param last Last element
  * @return Partition of the array
  */
 private static int partition(int list[], int first, int last){
	 
	 int pivot = list[last]; // Pivot is the last element
	 
	 int high = last;
	 
	 while(first < high){
		 
		 while(first < high && list[first] < pivot){
			 first++;
		 }
		 
		 while(first < high && list[high] >= pivot){
			 high --;
		 }
		 
		 int temp = list[first];
		 list[first] = list[high];
		 list[high] = temp;
	 }
	 
	 int temp = list[first];
	 list[first] = list[last];
	 list[last] = temp;
	 
	 return first;
 }
 
 /**
  * Partition with the middle element as the pivot 
  * @param list Array of ints to be sorted
  * @param first First element
  * @param last Last element 
  * @return Paritioned array
  */
 private static int partition2(int list[], int first, int last){
	 
	 int pivot = list[(first+last)/2]; // Middle element is the pivot
	 
	 while(first <= last){
		 
		 while(list[first] < pivot){
			 first++;
		 }
		 
		 while(list[last] > pivot){
			 last--;
		 }
		 
		 if(first <= last){
			 int temp = list[first];
			 list[first] = list[last];
			 list[last] = temp;
			 
			 first++;
			 last--;
		 }
	 }
	 
	 return first;
	 
 }
 
}
