package edu.iastate.cs228.hw01;

/**
 * @author Scott Vlasic
 *
 */
public class MyString
{
	/**
	 * Instance variable to store the chars in an array
	 */
	private char[] chars;

	/**
	 * One argument constructor which creates MyString object
	 * @param chars parameter which contains an array of chars
	 */
	public MyString(char[] chars)
	{
		if(chars == null || chars.length == 0) throw new IllegalArgumentException();
		
		// Sets instance variable equal to a new char with the size of the local variable
		this.chars = new char[chars.length];
		
		// Fills in the instance variable with the values of the local variable 
		for(int i = 0; i < chars.length; i++){
			this.chars[i] = chars[i];
		}
	}

	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	/**
	 * Returns the length of the char array
	 * @return the length of the char array 
	 */
	public int length()
	{
		return chars.length; // Returns the length of the array
	}
	
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	/**
	 * Given an index, returns the char in the element 
	 * @param index the element in the array
	 * @return the char at the specified element
	 */
	public char charAt(int index)
	{
		
		return chars[index]; // Simply returns char at the specified element
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	/**
	 * Given a beginning and ending position, returns a substring of the object
	 * @param begin the beginning element 
	 * @param end the last element 
	 * @return the substring between the two elements
	 */
	public MyString substring(int begin, int end)
	{
		
		// Throws error if beginning > end since it would create a negative sized array
		if(begin > end){
			throw new IndexOutOfBoundsException();
		}
		
		// Local char array with size of the substring 
		char c[] = new char [end-begin];
		
		// Variable to make the values be filled at 0 index instead of the begin index
		int j = 0;
		
		for(int i = begin; i < end; i++){
			
			// Fills the local variable with the values in the instance variable and increments j
			c[j] = chars[i];
			j++;
			
		}
		
		return new MyString(c);
	}

	//It is ok to use
	//https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	/**
	 * Takes all the elements and makes them lowercase
	 * @return the elements as lowercase chars
	 */
	public MyString toLowerCase()
	{
		// Local variable with size of the char array
		char lowerCase[] = new char[chars.length];
		
		
		for(int i = 0; i < lowerCase.length; i++){
			
			// Sets the value of the element at the local variable equal to the lowercase value of the instance at the element
			lowerCase[i] = Character.toLowerCase(chars[i]); // Character allowed per homework specification
			
		}
		
		return new MyString(lowerCase);
	}

	
	//You can assume only positive values.
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	/**
	 * Returns the string representation of a given int 
	 * @param i given int to be turned into a string
	 * @return the string representation of an int
	 */
	public static MyString valueOf(int i)
	{
		// Can assume only positive values so if i < 0, throw argument
		if(i < 0){
			throw new IllegalArgumentException();
		}
		
		// Stores i in local variable x 
		int x = i;
		 
		// Local variable for the length 
		int len = 0;
		
		// X must be greater than 0.  WHile it's greater than 0, increment length and divide x by 10
		while(x > 0){
			
			len++;
			
			x /= 10; // Used to find the nth digit of an integer
		}
		
		x = i; // Sets i equal to the new x value calculated in the while loop
		
		char[] c = new char[len]; // Local char array variable with length of the local variable
		
		for(int k = c.length-1; k >= 0; k--){
			
			c[k] = (char)((x % 10) + '0'); // Converts value at c[k] to a char from a single digit
			
			x /= 10; // Update x value
		}
		
		return new MyString(c);
	}

	
	public char[] toCharArray()
	{
		return chars;
	}
}