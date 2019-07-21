package edu.iastate.cs228.proj1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;

/**
 * 
 * @author Scott Vlasic
 *
 */
public class SequenceTester {

	// Char array to be used for testing
	char[] seqarrTest = {'A', 'B', 'C', 'D'};
	
	// Another char array for testing (more for equals)
	char[] combined = {'A', 'b', 'C', 'd'};
	
	// Sequence variable to be used for testing
	Sequence s = new Sequence(seqarrTest);
	
	// Sequence variable to be used for testing
	Sequence combinedSequence = new Sequence(combined);
	
	/**
	 * Tests the length of a char array
	 */
	@Test
	public void seqLengthTest(){
		
		assertEquals(4, s.seqLength());
		assertEquals(false, s.seqLength() == 10);
		
	}
	
	/**
	 * Tests to see if the method creates and returns a copy of the array
	 */
	@Test
	public void getSeqTest(){
		
		char[] copyS = Arrays.copyOf(s.getSeq(), s.getSeq().length); 
		
		for(int i = 0; i < copyS.length; i++) 
		{
			assertEquals(copyS[i], s.getSeq()[i]);
		}
			
	}
	
	/**
	 * Tests to see if the letter is valid
	 */
	@Test
	public void isValidLetterTest(){
		
		assertEquals(true, s.isValidLetter('x'));
		assertEquals(true, s.isValidLetter('X'));
		assertEquals(false, s.isValidLetter('?'));
		
	}
	
	/**
	 * Tests to see if the two Sequences are equal
	 */
	@Test
	public void equalsTest(){
		
		Sequence combinedChars = new Sequence(combined);
		Sequence a = new Sequence(seqarrTest);
		seqarrTest[1] = '?';
		Sequence b = new Sequence(seqarrTest);
		
		assertEquals(true, s.equals(combinedChars)); 
		assertEquals(true, s.equals(a));
		assertEquals(false, s.equals(b));
		
	}
	
	/**
	 * Tests to see if the string representation is correctly returned
	 */
	@Test
	public void toStringTest(){
		
		assertEquals("ABCD", s.toString());
		assertEquals("AbCd", combinedSequence.toString());
		
	}
	
}
