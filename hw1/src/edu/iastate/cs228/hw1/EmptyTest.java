package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class EmptyTest {

	@Test
	public void whoTest(){
		Jungle j = new Jungle(4);
		Empty e = new Empty(j, 1, 1);
		
		e.who();
		assertEquals("Should return State.Empty",e.who(), State.EMPTY);
	}
	
	@Test
	public void nextTest() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/EmptyTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should be new Deer", State.DEER, j.grid[0][0].next(jNew).who());
		assertEquals("Should be new Puma", State.PUMA, j.grid[2][2].next(jNew).who());
		assertEquals("Should be new Jaguar", State.JAGUAR, j.grid[0][2].next(jNew).who());
	}
	
}
