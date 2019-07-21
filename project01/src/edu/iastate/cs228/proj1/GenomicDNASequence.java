package edu.iastate.cs228.proj1;

/**
 * 
 * @author Scott Vlasic
 *
 */

public class GenomicDNASequence extends DNASequence
{
  private boolean[] iscoding;

  /**
   If the character array argument has a character on which the method {@link #isValidLetter(char)} returns {@code false}, then it throws {@link java.lang.IllegalArgumentException} with the message {@code "Invalid sequence letter for class edu.iastate.cs228.proj1.GenomicDNASequence"}. Otherwise, the constructor saves a copy of the character array argument in the field of its superclass {@code Sequence}. And it create a {@code boolean} array of the same length as {@code gdnaarr}, saves its reference in the field {@code iscoding}, and sets the {@code boolean} array to {@code false} at each index.
   
   @param gdnaarr See {@link #GenomicDNASequence(char[])}   
   @throws IllegalArgumentException See {@link #GenomicDNASequence(char[])}   
  */  
  public GenomicDNASequence(char[] gdnaarr)
  {
     // Calls the super classes constructor method
	 super(gdnaarr);
	 
	 //Boolean array with length of gdnaarr
	 iscoding = new boolean[gdnaarr.length];
	 
	 // Populates iscoding with false at each index
	 for(int i = 0; i < iscoding.length; i++){
		 
		 iscoding[i] = false;
		 
	 }
	 
  }

  
  /**
   The method throws an {@link java.lang.IllegalArgumentException} exception with the message {@code "Coding border is out of bound"} if {@code first} is greater than {@code last}, or {@code first} is less than 0, or {@code last} is greater than or equal to the length returned by the {@link #seqLength()} method. Otherwise, it sets the {@code boolean} array {@code iscoding} to {@code true} at each index between {@code first} and {@code last} inclusive.
   
   @param first See {@link #markCoding(int, int)}
   @param last See {@link #markCoding(int, int)}
   @throws IllegalArgumentException See {@link #markCoding(int, int)}   
  */
  public void markCoding(int first, int last)
  {
    
	 // Checks for IllegalArgumentException
	 if(first > last){
		 
		 throw new IllegalArgumentException("Coding border is out of bounds");
		 
	 }
	 
	 // Checks for IllegalArgumentException
	 else if (first < 0){
		 
		 throw new IllegalArgumentException("Coding border is out of bounds");
		 
	 }
	 
	 // Checks for IllegalArgumentException
	 else if (last >= seqLength()){
		 
		 throw new IllegalArgumentException("Coding border is out of bounds");
		 
	 }
	 
	 else{
		 
		 // Sets boolean array equal to true at each index between first and last inclusive 
		 int i = first;
		 
		 while(i >= first && i <= last){
			 
			 iscoding[i] = true;
			 
			 i++;
			 
		 }
		 
	 }
  }

  
  /**
   The integer array argument {@code exonpos} is used to specify the start and end positions of every coding exon in the genomic sequence. The method throws an {@link java.lang.IllegalArgumentException} exception with the message {@code "Empty array or odd number of array elements"} if the length of {@code exonpos} is 0 or an odd number. It throws an {@link java.lang.IllegalArgumentException} with the message {@code "Exon position is out of bound"} if an element in the {@code exonpos} is less than 0 or is greater than or equal to the length returned by the {@link #seqLength()} method. It throws an {@link java.lang.IllegalArgumentException} exception with the message {@code "Exon positions are not in order"} if an element in {@code exonpos} is greater than its right neighbor element. It throws an {@link java.lang.IllegalStateException} exception with the message {@code "Noncoding position is found"} if the {@code boolean} array {@code iscoding} is {@code false} at a coding exon position. Otherwise, the method takes all the coding exons  specified by the array {@code exonpos}, concatenates them in order, and returns the resulting sequence in a new character array. Note that the array length is the length of the resulting sequence.

   For example, 
   <blockquote><pre>
   
    seqarr =   {A, A, T, G, C, C, A, G, T, C,  A,  G,  C,  A,  T,  A,  G,  C,  G,  T,  A}
    iscoding = {F, T, T, T, T, T, T, T, T, T,  T,  T,  T,  T,  T,  T,  T,  F,  F,  F,  F}
    index:      0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20
   
    Note that we use A for 'A', F for false, and T for true to save space.
   
    exonarr = {1, 5, 8, 10, 13, 16}
   
    The first exon is given by the subarray of seqarr 
	from index 1 to index 5, which is {A, T, G, C, C}.

    The second exon is given by the subarray of seqarr 
	from index 8 to index 10, which is {T, C, A}.

    The third exon is given by the subarray of seqarr 
	from index 13 to index 16, which is {A, T, A, G}.

    The returned array is the concatenation of the three 
	subarrays in order: {A, T, G, C, C, T, C, A, A, T, A, G}
   
   </pre></blockquote>
   @param exonpos See {@link #extractExons(int[])}
   @return See {@link #extractExons(int[])}
   @throws IllegalArgumentException See {@link #extractExons(int[])}
   @throws IllegalStateException See {@link #extractExons(int[])}
  */
  public char[] extractExons(int[] exonpos)
  {
	
	// Throws exception if length is 0 or length is an odd number
	if(exonpos.length == 0 || exonpos.length % 2 != 0){
		
		throw new IllegalArgumentException("Empty array or odd number of array elements");
		
	}
	
	// Throws exception if an element in the exonpos is less than 0 or is greater than or equal to the length returned by the Sequence.seqLength() method.
	for(int i = 0; i < exonpos.length; i++){
		
		if(exonpos[i] < 0 || exonpos[i] >= seqLength()){
			
			throw new IllegalArgumentException("Exon position is out of bound");
			
		}
	}
	
	// Throws exception if an element in exonpos is greater than its right neighbor element
	for(int i = 0; i < exonpos.length-1; i++){
		
		if(exonpos[i] > exonpos[i+1]){
			
			throw new IllegalArgumentException("Exon positions are not in order");
			
		}
	}
	
	// Throws exception if the boolean array iscoding is false at a coding exon position
	for(int i = 0; i < exonpos.length; i++){
		
		for(int j = exonpos[i]; j < exonpos.length; j++){
			
			if(iscoding[j] == false){
			
				throw new IllegalStateException("Noncoding position is found");
			
			}
		}
	
	}
	
	
	String temp = ""; // String to hold exons temporarily 
	
	
	for(int i = 0; i < exonpos.length; i++){
		
		// Loop that goes through the substring of the seqaar array
		for(int j = exonpos[i]; j <= exonpos[i+1]; j++){
			
			temp += seqarr[j]; // Populates the string variable with values of the substring in seqarr
		}
		
		i++; // Increments i so that it doesn't begin at the same i value again
	}
	
	
	return temp.toCharArray(); // Returns the exons
  }

}
