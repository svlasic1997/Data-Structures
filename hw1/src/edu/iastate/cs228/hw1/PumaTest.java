package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class PumaTest {

	@Test
	public void pumaTest(){
		Jungle j = new Jungle(4);
		Puma p = new Puma(j, 1, 1, 2);
		
		p.myAge();
		assertEquals("Should return age of 2", p.myAge(), 2);
		
	}
	
	@Test 
	public void whoTest(){
		Jungle j = new Jungle (4);
		Puma p = new Puma(j, 1, 1, 2);
		
		p.who();
		assertEquals("Should return State.PUMA", p.who(), State.PUMA);
	}
	
	@Test
	public void nextTest1(){
		Jungle j = new Jungle(1);
		j.randomInit();
		Puma puma = new Puma(j, 0, 0, 4);
		Jungle jNew = new Jungle(1);
		
		assertEquals("Should return new Empty object", State.EMPTY, j.grid[0][0].next(jNew).who());
	}
	
	@Test
	public void nextTest2() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/PumaTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should be a new Jaguar", State.JAGUAR, j.grid[0][1].next(jNew).who());
	}
	
	@Test
	public void nextTest3() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/PumaTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should be new Empty", State.EMPTY, j.grid[1][0].next(jNew).who());
	}
}

