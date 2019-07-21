package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.*;


public class AnimalTest {

	@Test
	public void testmyAge(){
		Jungle jNew = new Jungle(3);
		
		Jaguar j = new Jaguar(jNew, 1, 1, 3);
		j.myAge();
		assertEquals("Should return age 3", 3 , j.myAge() );
		
		Deer d = new Deer(jNew, 2, 2, 4);
		d.myAge();
		assertEquals("Should return age 4", 4, d.myAge());
		
		Puma p = new Puma(jNew, 1, 2, 2);
		p.myAge();
		assertEquals("Should return age 2", 2, p.myAge());
	}
}
