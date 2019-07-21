package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class CircleOfLifeTest {

	@Test
	public void updateJungleTest(){
		Jungle j = new Jungle(2);
		j.randomInit();
		Jungle j2 = new Jungle(2);
		
		CircleOfLife.updateJungle(j, j2);
		assertEquals(j, j2);
	}
}
