package edu.iastate.cs228.hw05;


/**
 * 
 * @author Scott Vlasic
 * 
 * NOTE:
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. In all of these methods implementations you are allowed
 *    to use the StringBuilder class. 
 * 2. You are allowed to create and use your own private helper methods.
 * 3. No data fields can be introduced.
 * 4. No custom classes of your own can be introduced or used.
 * 5. Import statements are not allowed.
 * 6. Fully qualified class names usage is not allowed.
 * 7. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes.
 * 
 */


public class SortingExercises
{
	/**
	 * Recursive implementation of selection sort.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Rec(int[] arr)
 {
	 
	 if(arr == null) throw new NullPointerException();
	 if(arr.length == 0) throw new IllegalArgumentException();
	 if(arr.length == 1) return;
	
	 // Recursive call of the helper method to sort the array
	 indexHelper(arr, 0);
 }
 
 /**
  * 
  * @param arr
  * @param index
  */
 private static void indexHelper(int[] arr, int index){
	 
	 // If the index is greater than the length of the array, stop 
	 if(index >= arr.length){
		 return;
	 }
	 
	 // Smallest index is the local variable
	 int smallestIndex = index;
	 
	 // Updates the smallest index of the array
	 for(int i = index + 1; i < arr.length; i++){
		 
		 if(arr[i] < arr[smallestIndex]){
			 
			 smallestIndex = i;
			 
		 }
		 
	 }
	 
	 // Swaps the values based on the smallest index
	 int temp = arr[index];
	 arr[index] = arr[smallestIndex];
	 arr[smallestIndex] = temp;
	 
	 // Calls the method to update the index 
	 indexHelper(arr, index+1);
	 
 }
 
 /**
  * Recursive implementation of insertion sort.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void insertionSort_Rec(int[] arr)
 {
 	if(arr == null) throw new NullPointerException();
 	if(arr.length == 0) throw new IllegalArgumentException();
 	if(arr.length == 1) return;
  	
 	// Length of the array
 	int n = arr.length;
  	
 	// Calls private method recursively to sort the array
 	Swapper(arr, n);
 }
 
 /**
  * Performs the swaps in the insertion sort
  * @param arr array of values
  * @param n Length of the array
  */
 private static void Swapper(int arr[],int n){
	 
	 // If n is greater than or equal to 1, stop
	 if(n<=1){
		 return;
	 }
	 
	 // Call the method recursively to update n
	 Swapper(arr, n-1);
	 
	 
	 int last = arr[n-1]; 
	 int j = n-2;
	 
	 while(j >= 0 && arr[j] > last){
		 // Changes value of arr[j+1] based on above parameters
		 arr[j+1] = arr[j];
		 j--;
	 }
	 
	 // Sets the updated value equal to last
	 arr[j+1] = last;
 }
 
 /**
	 * Iterative implementation of selection sort with modifications as follows.
	 * On each pass in this case the method finds both the largest and smallest
	 * values in the unsorted portion of the array, and places them in the correct
	 * locations.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Itr(int[] arr)
 {
	 if(arr == null) throw new NullPointerException();
	 if(arr.length == 0) throw new IllegalArgumentException();
	 if(arr.length == 1) return;
	 
	 for(int i = 0; i < arr.length - 1; i++){

		 int minInd = i;
		 
		 for(int j = i+1; j < arr.length; j++){
			 
			 if(arr[j] < arr[minInd]){
				 
				 minInd = j;
			 }
		 }
		 
		 // Swaps values
		 int temp = arr[i];
		 arr[i] = arr[minInd];
		 arr[minInd] = temp;
	 }

 }
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * making n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort iteratively.
  * 
  * @param arr Array of objects (with specific bounds) to be sorted in nondecreasing order.
  */
 public static <T extends Comparable<? super T>> void bubbleSort_Itr(T[] arr)
 {
 	if(arr == null) throw new NullPointerException();
 	if(arr.length == 0) throw new IllegalArgumentException();
 	if(arr.length == 1) return;
 	
 	for(int i = 0; i < arr.length-1; i++){
 		
 		for(int j = 0; j < arr.length-i-1; j++){
 			
 			if(arr[j].compareTo(arr[j+1]) > 0){
 				
 				T temp = arr[j];
 				arr[j] = arr[j+1];
 				arr[j+1] = temp;
 				
 			}
 		}
 	}
 }
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * making n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort recursively.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void bubbleSort_Rec(int[] arr)
 {
 	if(arr == null) throw new NullPointerException();
 	if(arr.length == 0) throw new IllegalArgumentException();
 	if(arr.length == 1) return;
  
 	int n = arr.length;
 	bubbleHelper(arr, n);
 	
 } 
 
 private static void bubbleHelper(int[] arr, int index){
	 
	 // If the index is 1, return 
	 if(index == 1){
		 return;
		 
	 }
	 
	 // For loop to update the array
	 for(int i = 0; i < index - 1; i++){
		 
		 // If the array at i is greater than array at i+1, swap values
		 if(arr[i] > arr[i+1]){
			 
			 int temp = arr[i];
			 arr[i] = arr[i+1];
			 arr[i+1] = temp;
		 }
	 }
	 
	 // Calls the method recursively until the array is sorted
	 bubbleHelper(arr, index-1);
 }
}
