package edu.iastate.cs228.proj1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeonomicDNASequenceTester {

	
	char[] a = {'a', 'c', 't', 'g', 'a', 'c', 'c','t', 'g', 'a','g','t','t','c','a'};
	int[] exonposTest = {1,3,8,9};
	
	GenomicDNASequence d = new GenomicDNASequence(a);
	
	@Test
	public void testExtractExons(){
		
		char[] returnedExons = {'c','t','g','a', 'c', 'c','t', 'g', 'a'};
		
		assertEquals (d.extractExons(exonposTest), returnedExons);
		
	}
}
