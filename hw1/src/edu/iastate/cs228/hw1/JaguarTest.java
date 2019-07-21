package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;


public class JaguarTest {
	
	@Test
	public void jaguarTest(){
		Jungle j = new Jungle(4);
		
		Jaguar jag = new Jaguar(j, 1, 1, 2);
		
		jag.myAge();
		assertEquals("Should return 2", jag.myAge(), 2);
	}
	
	@Test 
	public void whoTest(){
		Jungle j = new Jungle(4);
		
		Jaguar jag = new Jaguar(j, 1, 1, 3);
		
		jag.who();
		assertEquals("Should return State.Jaguar", jag.who(), State.JAGUAR);
	}
	
	@Test
	public void nextTest(){
		Jungle j = new Jungle(1);
		j.randomInit();
		Jaguar jag = new Jaguar(j, 0, 0, 5);
		Jungle jNew = new Jungle(1);
		
		assertEquals("Should return new Empty object", State.EMPTY, j.grid[0][0].next(jNew).who());
		
	}
	
	@Test
	public void nextTest2() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/JaguarTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should return new Puma", State.PUMA, j.grid[0][1].next(jNew).who());
		
	}
	
	@Test
	public void nextTest3() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/JaguarTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should return new Empty", State.EMPTY, j.grid[0][0].next(jNew).who());
	}
	
}
