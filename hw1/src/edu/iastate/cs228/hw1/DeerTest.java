package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;


public class DeerTest {

	@Test
	public void deerTest(){
		Jungle jNew = new Jungle(3);
		Deer d  = new Deer(jNew, 1, 1, 3);
		
		d.myAge();
		assertEquals("Age should be 3", 3 , d.myAge() );
		
	}
	
	@Test
	public void whoTest(){
		Jungle j = new Jungle(4);
		Deer d = new Deer (j, 1, 1, 2);
		
		d.who();
		assertEquals("Should return State.Deer", State.DEER, d.who());
	}
	
	@Test 
	public void nextTest1(){
		Jungle j = new Jungle(2);
		j.randomInit();
		j.grid[1][1] = new Deer(j,1,1,6);
		Jungle jNew = new Jungle(2);
		
		assertEquals("Should return new Empty object", new Empty(jNew, 1, 1).who(), j.grid[1][1].next(jNew).who());
	}
	
	@Test
	public void nextTest2(){
		Jungle j = new Jungle(1);
		j.randomInit();
		j.grid[0][0] = new Deer(j, 1, 1, 2);
		Jungle jNew = new Jungle(1);
		
		// Test for when no Grass
		assertEquals("Should return new Empty object", new Empty(jNew, 1, 1).who(), j.grid[0][0].next(jNew).who());
	}
	
	@Test 
	public void nextTest3() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/DeerTester1.txt");
		Jungle jNew = new Jungle (j.getWidth());
		
		assertEquals("Should return a new Puma object", State.PUMA, j.grid[0][1].next(jNew).who());
		
	}
	
	public void nextTest4() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/DeerTester1.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should return new Jaguar object", State.JAGUAR, j.grid[3][2].next(jNew).who());
	}
	
	
}
