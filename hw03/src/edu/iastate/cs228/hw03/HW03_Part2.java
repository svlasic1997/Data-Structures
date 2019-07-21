package edu.iastate.cs228.hw03;

/**
 * 
 * @author
 *
 */
public class HW03_Part2
{
 /*
  * Answers to short questions:
  * 
  * 1. O(n)
  * 
  * 2. O(log n)
  * 
  * 3. O(n^3) --> n^3 order complexity 
  * 
  * 4. O(n^2)
  * 
  * 5. O(n^2)
  * 
  */
	
	/*
	 In all of the following methods you can assume that
	 array will always have elements (ints) in it.
	 And will have proper integers as defined in the 
	 description of HW03, i.e., in first two it will be
	 in the range, and in last two it will be composed of
	 negative and positive values only.
	*/
	
	public static int findMissingInt_a_On2(int [] array)
	{
		
		int missing = 0;
		int sum = 0;
		int total = 1 + array.length; // End of the range from 1 to n+1
		int newSum = 0;
		
		// O(n^2) because of the two for loops incrementing by 1
		for(int i = 0; i < 1; i++){
			
			sum = (total*(total+1))/2; // Sum of the numbers in the array including the missing one
			
			for(int j = i; j < array.length; j++){
				
				newSum = newSum + array[j]; // Sum of only the numbers in the array
			}
		}
		
		missing = sum-newSum; // Subtracts the expected from the actual to get the missing number
		
		
		return missing;
	}
	
	public static int findMissingInt_b_On1(int [] array)
	{
		int missing = 0;
		int total = 1 + array.length; // End of the range from 1 to n+1
		int sum = (total*(total+1))/2; // Sum of numbers including the missing value
		int newSum = 0;
		
		// O(n) because of the one for loop incrementing by 1
		for(int i = 0; i < array.length; i++){
			
			newSum = newSum + array[i]; // Sum of the numbers in the array
			
		}
		
		missing = sum - newSum; // Subtracts expected from actual to find the missing value
		
		return missing;
	}
	
	public static void rearrange_a_On2(int [] array)
	{
		
		// Use selection sorting algorithm, time complexity of O(n^2)
		for(int i = 0; i < array.length - 1; i++){
			
			int min = i;
			
			for(int j = i+1; j < array.length; j++){
				
				if(array[j] < array[min]){
					
					min = j;
					
				}
				
			}
			
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;
			
		}	
	
	}
	
	
	public static void rearrange_b_On1(int [] array)
	{
		
		int[] answer = new int[array.length]; // Array to be sorted with negative on one half, positive on the other
		int currentPos = 0; // Position to be used for the answer array
		
		for(int i = 0; i < array.length; i++){
			
			// If value at the array is negative adds the value to the answer array in the specified index
			if(array[i] < 0){
				
				answer[currentPos] = array[i];
				currentPos++;
			}
			
			
		}
		
		for(int i = 0; i < array.length; i++){
			
			// If value at the array is positive adds the value to the answer array in the specified index
			if(array[i] > 0){
				
				answer[currentPos] = array[i];
				currentPos++;
			}
			
			
		}
		
		for(int i = 0; i < array.length; i++){
			
			array[i] = answer[i]; // Sets the answer array equal to the local variable array
		}
		
	}
	
}
