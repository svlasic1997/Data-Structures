package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class GrassTest {

	@Test
	public void whoTest(){
		Jungle j = new Jungle(4);
		Grass g = new Grass(j, 1, 1);
		
		g.who();
		assertEquals("Should return State.Grass", g.who(), State.GRASS);
	}
	
	@Test
	public void nextTest() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/GrassTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should return new Empty", State.EMPTY, j.grid[2][0].next(jNew).who());
		
	}
	
	@Test
	public void nextTest2() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/GrassTester.txt");
		Jungle jNew = new Jungle(j.getWidth());
		
		assertEquals("Should return new Deer", State.DEER, j.grid[0][1].next(jNew).who());
	}
}
