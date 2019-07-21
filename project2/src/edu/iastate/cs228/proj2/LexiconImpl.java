package edu.iastate.cs228.proj2;

import java.util.Comparator;

/**
 * 
 * @author Scott Vlasic
 *
 */
public class LexiconImpl implements Lexicon, Comparator<String> {

    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
	public CharacterValue[] characterOrdering; 

    /***
     * Creates an array of CharacterValue from characterOrdering.  Sorts
     * it using java.util.Arrays.sort().
     * @param characterOrdering character order from configuration file
     */	
	public LexiconImpl(char[] characterOrdering) {
		
		// Creates a array of CharacterValue from CharacterOrdering
		this.characterOrdering = new CharacterValue[characterOrdering.length];
		
		// Populates characterOrdering with values of local variable
		for(int i =0; i < characterOrdering.length; i++){
			
			char c = characterOrdering[i];
			this.characterOrdering[i] = new CharacterValue(i, c);
		}
		
		// Comparator used to sort 
		Comparator<CharacterValue> comp = (CharacterValue beginning, CharacterValue end) -> Character.compare(beginning.character, end.character);
		java.util.Arrays.sort(this.characterOrdering, comp);
		
	}


    /***
     * Compares two words based on the configuration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, positive if a>b
     */
	@Override
	public int compare(String a, String b) {
		
		// Loops through the two Strings 
		for(int i = 0; i < a.length() && i < b.length(); i++){
			
			// Checks the char at each index in both the Strings
			if(a.charAt(i) != b.charAt(i)){
				
				// If a > b, return 1
				if(getCharacterOrdering(a.charAt(i)) > getCharacterOrdering(b.charAt(i))){
					
					return 1;
				}
				
				// If a < b, return -1
				else if(getCharacterOrdering(a.charAt(i)) < getCharacterOrdering(b.charAt(i))){
					
					return -1;
				}
				
			}
			
		}
		
		// If length of a is greater than length of b, return 1
		if(a.length() > b.length()){
							
			return 1;
		}
						
		// If length of b is greater than length of a, return -1 
		if(a.length() < b.length()){
							
			return -1;
		}
		
		return 0; // Both string are equal
		
	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		
		// Gets the character ordering based on the key value, implemented using code from lecture slides
		int low = 0;
		int high = characterOrdering.length - 1;
		
		while(high >= low){
			
			int mid = (low+high)/2;
			
			if(key < characterOrdering[mid].character){
				
				high = mid - 1;
			}
			
			else if(key == characterOrdering[mid].character){
				
				return characterOrdering[mid].value;
			}
			
			else{
				
				low = mid + 1;
			}
		}
		
		
		low = -low - 1;
		
		// Checks to see if character is invalid 
		if(low < 0){
			
			return -1;
		}
		
		return low;

	}

	public static class CharacterValue {
		public int value;
		public char character;
		
		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}
		
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}
	
	/**
	 * Returns whether or not word is valid according to the alphabet
	 * known to this lexicon. 
	 * 
	 * @param word word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		
		// Loops through the String and characterOrdering to see if characters are valid
		for(int i = 0; i < word.length(); i++){
			
			// If the getCharacterOrdering is -1, character is not valid
			if(getCharacterOrdering(word.charAt(i)) == -1){
				
				return false;
			}
		}
		
		return true; // Else all characters are valid

	}
	
}
