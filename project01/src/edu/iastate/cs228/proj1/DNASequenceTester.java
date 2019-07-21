package edu.iastate.cs228.proj1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DNASequenceTester {
	
	
	// Char array full of valid chars
	char[] validTest = {'A', 'g', 'C', 't'};
	
	DNASequence v = new DNASequence(validTest);
	
	
	/**
	 * Checks to see if the letters are valid
	 */
	@Test
	public void isValidLetterTest(){
		
		assertEquals(true, v.isValidLetter('A'));
		assertEquals(false, v.isValidLetter('?'));
		assertEquals(true, v.isValidLetter('g'));
		
	}
	
	
}
