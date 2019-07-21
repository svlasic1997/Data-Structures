package edu.iastate.cs228.proj1;

/**
 * 
 * @author Scott Vlasic
 *
 */

public class Sequence
{
  protected char[] seqarr;

  /**
   The constructor first uses the {@link #isValidLetter(char)} method to check
   if every character in the array {@code sarr} is valid. If so, it makes and
   keeps a copy of the array {@code sarr} in the field {@code seqarr} of type {@code char[]} with {@code protected} access. Otherwise, it throws an   {@link java.lang.IllegalArgumentException} with the message {@code "Invalid sequence letter for class X"} where {@code X} denotes {@code  'edu.iastate.cs228.proj1.Sequence'} or the name of a subclass of which an object is created. Examples are given in the project description page to 
   illustrate what exactly is denoted by {@code X} after the subclasses of class
   {@code Sequence} are defined. Note that the length of the field {@code seqarr} is equal to the length of the array {@code sarr}. This constructor should be implemented in such a way that it is unnecessary to have more than one line of code in the body of the constructor of any subclass of this class.
   
   @param sarr See {@link #Sequence(char[])}.
   @throws IllegalArgumentException See {@link #Sequence(char[])}.
  */  
  public Sequence(char[] sarr)
  {
	
	  
	
	// Local variable to keep a copy of the array if isValidLetter evaluates to true
	seqarr = new char[sarr.length];
	
	// Try to make the copy if the letters are valid
	try{
		
		for(int i = 0; i < sarr.length; i++){
			
			// Checks to see if isValidLetter is true 
			if(isValidLetter(sarr[i]) == true){ 
				  
				seqarr[i] = sarr[i]; 
			}
			
		}
	}
	
	
	catch(IllegalArgumentException e){
		
		System.out.println("Invalid sequence letter for class edu.iastate.cs228.proj1" + this.getClass().getName()); // Catches the IllegalArgumentException() and prints the message
		
	}
	
	
  }

  
  /**
   The method returns the length of the character array {@code seqarr}.
   @return See {@link #seqLength()}.
  */
  public int seqLength()
  {
    
	  return seqarr.length; // Returns length of the char array
  }
  
  /**
   The method creates and returns a copy of {@code char} array {@code seqarr}.
   @return See {@link #getSeq()}.
  */
  public char[] getSeq()
  {
    // Local variable to create copy of the seqarr variable
	char[] copy = new char[seqarr.length];
	
	// Loop to copy values into local variable
	for(int i = 0; i < copy.length; i++){
		
		copy[i] = seqarr[i]; // Sets the value of the copy to the value at the index in seqarr
		
	}
	
	return copy; // Returns the copy
  }

  /**
   The method returns the string representation of the {@code char} arrar {@code seqarr}.
   @return See {@link #toString()}.
  */
  public String toString()
  {
   
	// Local variable to return string representation
	String s = new String();
	
	// Loops through the array
	for(int i = 0; i < seqarr.length; i++){
		
		// Populates s with the values of the array
		s += seqarr[i] + "";
		
	}
	
	return s; // Return string of the array
  }

  /**
   The method returns {@code true} if the arguments {@code obj} is not {@code null} and is of the same type as this object such that both objects represent the identical sequence of characters in a case insensitive mode ("ACgt" is identical to "AcGt"). The {@link #equals(Object)} method should be defined in such a way that there is no need to define it again in any subclass of class {@code Sequence}. In other words, when an object of the subclass calls the {@link #equals(Object)} method, it should return the right answer.
   
   @return See {@link #equals(Object)}
  */
  public boolean equals(Object obj)
  { 
	  
	// If object is null or not of the same type return false
	if(obj == null || obj.getClass() != this.getClass()){
		
		return false;
		
	}
	
	// Casts object as a sequence
	Sequence casted = (Sequence)obj;
	
	// Checks to see if length is the same and if it isn't returns false
	if(this.seqLength() != casted.seqLength()){
		
		return false;
		
	}
	
	// Checks to see that the values of both arrays match
	return (this.toString().toLowerCase().equals(casted.toString().toLowerCase())) && (this.toString().toUpperCase().equals(casted.toString().toUpperCase()));
  }

  
  /**
   The method returns {@code true} if the character {@code let} is an 
   uppercase (e.g., invoking {@link java.lang.Character#isUpperCase(char)} with {@code let} is {@code true}) or lowercase ((e.g., invoking {@link java.lang.Character#isLowerCase(char)} with {@code let} is {@code true})). Otherwise,
	it returns {@code false}.
   @param let See {@link #isValidLetter(char)}.
   @return See {@link #isValidLetter(char)}.
  */
  public boolean isValidLetter(char let)
  {
    
	// Checks to see if the value is an uppercase letter based on ASCII values
	if(let >= (char)65 && let <= (char)90){
		return true;
	}
	
	// Checks to see if the value is a lowercase letter based on ASCII values
	if(let >= (char)97 && let <= (char)122){
		return true;
	}
	
	return false;
  }

}
