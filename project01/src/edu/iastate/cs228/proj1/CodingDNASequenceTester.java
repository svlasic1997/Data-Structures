package edu.iastate.cs228.proj1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CodingDNASequenceTester {

	
	char [] a = {'a', 'T', 'G', 'a','G','C'};
	
	CodingDNASequence c = new CodingDNASequence(a);
	
	@Test
	/**
	 * Tests the Codon method
	 */
	public void checkStartCodonTest(){
		
		char[] a2 = {'a', 't'}; // Too short
		CodingDNASequence c2 = new CodingDNASequence(a2);
		
		char[] a3 = {'t', 't', 'g'}; // Doesn't start with a 
		CodingDNASequence c3 = new CodingDNASequence(a3);
		
		assertEquals(true, c.checkStartCodon());
		assertEquals(false, c2.checkStartCodon());
		assertEquals(false, c3.checkStartCodon());
	}
	
	@Test
	/**
	 * Tests the translate method
	 */
	public void checkTranslate(){
		
		c.checkStartCodon();
		
		assertEquals("MS", c.translate());
		
	}
	
	
}
