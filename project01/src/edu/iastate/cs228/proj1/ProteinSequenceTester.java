package edu.iastate.cs228.proj1;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProteinSequenceTester {

	char[] proteinArray = {'A', 'y', 'I', 'P'};
	
	ProteinSequence p = new ProteinSequence(proteinArray);
	
	/**
	 * Tests to see if the letter is valid for a protein sequence
	 */
	@Test
	public void isValidLetterTest(){
		
		assertEquals(true, p.isValidLetter('A'));
		assertEquals(true, p.isValidLetter('E'));
		assertEquals(false, p.isValidLetter('B'));
		
	}
	
}
