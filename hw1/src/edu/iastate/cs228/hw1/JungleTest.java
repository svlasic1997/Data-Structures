package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class JungleTest {
	
	@Test
	public void widthTest() throws FileNotFoundException{
		Jungle j = new Jungle("C://Users/svlas/Downloads/proj1S18 public tests (3)/public/public1-3x3.txt");
		j.getWidth();
		
		assertEquals("Width of the jungle should be 3", 3, j.getWidth());
		
		Jungle j2 = new Jungle(5);
		j2.getWidth();
		
		assertEquals("Width of the Jungle should be 5", 5, j2.getWidth());
	}
	
	@Test public void randomInitTest(){
		Jungle j = new Jungle(3);
		j.randomInit();
		
		assertNotNull(j);
	}
	
	@Test public void toStringTest(){
		Jungle j = new Jungle(4);
		j.randomInit();
		j.toString();
		
		assertEquals("Should print the Jungle toString", j.toString(), j.toString());
	}
	
}
