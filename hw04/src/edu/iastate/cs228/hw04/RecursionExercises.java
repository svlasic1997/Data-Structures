package edu.iastate.cs228.hw04;

/**
 * 
 * @author Scott Vlasic
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 	  Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase (and separated by a space).
 * 1. In all of these methods implementations you are allowed
 *    to use the StringBuilder class. 
 * 2. You are allowed to create and use your own private helper methods.
 * 3. No data fields can be introduced.
 * 4. No custom classes of your own can be introduced or used.
 * 5. Import statements are not allowed.
 * 6. Fully qualified class names usage is not allowed.
 * 
 * 
 */
public final class RecursionExercises
{
 /**
  This method displays a given character the specified
  number of times on one line. For example, the call
   displayRowOfChacaters('+', 5);
  returns "+++++"
   
  Implement this method using recursion.
   
  @param toShow The character to display.
  @param n The number of times to display it; n>0.  
  @return String representations of n toShow chars.
  */
	 public static String displayRowOfCharacters(char toShow, int n)
	 {
	
		// If number of calls is 0 or less than 0, return empty string
	 	if(n == 0 || n <= 0){
	 		return  "";
	 	}
	 	
	 	else{
	 		
	 		// Recursively call the method to display the number of characters while decrementing n until it's 0
	 		return toShow + displayRowOfCharacters(toShow, n-1);
	 	}

	 }
	 
	 /**
	  * Recursively display an array backward starting at position n.
	  * E.g., displayBackwards(new Integer[]{1,2,3,4}, 2)
	  * would return "21".
	  * 
	  * Implement this method using recursion.
   * 
	  *  @param arr The array to display will have at least two elements.
	  *  @param n The number of entries to be returned; 1<=n<=arr.length.
	  *  @return String value of first n values of arr backwards.
	  */
	 public static String displayBackwards(int[] arr, int n)
	 {
		// If n is 0 or less than 0 return empty String since it is out of the range of values
	 	if(n == 0 || n <= 0){
	 		
	 		return "";
	 		
	 	}
	 	
	 	else{
	 		
	 		// Else recursively call to get the array by returning the  element at the first n array and recursively calling method
	 		return arr[n-1] + displayBackwards(arr,n-1);
	 	}
	 }
	 
	 /**
	  * A palindrome is a string that reads the same forward and backward.
	  * For example deed and level are palindromes. Implement this method
	  * using recursion.
	  * 
	  * @param aString A string under consideration; nonempty and 
	  * consists only of upper/lowercase letters and digits.
	  * @return true if the given string is a palindrome, and false otherwise.
	  */
	 public static boolean isPalindrome(String aString)
	 {
		// If a String is 0 characters or 1 character, it is already a palindrome
	 	if(aString.length() == 0 || aString.length() == 1){
	 		return true;
	 	}
	 	
	 	// If the first char is the same as the last char of the string, go into the statement
	 	if(aString.charAt(0) == aString.charAt(aString.length()-1)){
	 		
	 		// Recursively check the rest of the letters to see if they match at the position
	 		return isPalindrome(aString.substring(1, aString.length()-1));
	 	}
	 	
	 	return false;
	 }
	 
	 /**
	  * Recursive method that finds the second smallest object in an array of
	  * Comparable objects. E.g., getSecondSmallest(new Integer[]{-1,10,3,2},4)
	  * would return 2.
	  *  
	  * @param arr An array of Comparable objects; arr.length>=2.
	  * @param len Length/size of arr array.
	  * @return Second smallest (Comparable) object in this array.
	  */
	 public static <T extends Comparable<? super T>> T getSecondSmallest(T[] arr, int len)
	 {
	 	
		int i;
		for(i = 0; i < len - 1; i++){
			
			// Compares the first element to the next one and swaps them if smaller 
			if(arr[i].compareTo(arr[i+1]) > 0){
				
				T temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
				
			}
			
		}

		// Recursively calls method to move elements
		if(len - 1 > 1){
			
			getSecondSmallest(arr, len-1);
			
		}
		
		return arr[1]; // Second smallest element
	 }
	 
	 
}
