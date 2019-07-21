package edu.iastate.cs228.hw10;

import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

/**
 * @author Cory Smith
 *  
 * test01_Add uses levelOrderTraverse as part of its testing, since there isn't a very good way to look at the internal
 * data of the tree. If you have problems with your output, try checking that as well.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tester {
	
	@Test
	public void test01_Add() {
		XYTree<Point> xy = new XYTree<>();
		xy.addAllPoints(new Point[]{ new Point(5, 3), new Point(5, 2), new Point(10, 7), 
				new Point(9, 1), new Point(6, 8), new Point(6, 8), new Point(10, 1) });
		
		assertEquals("[(5, 3), (5, 2), (10, 7), (9, 1), (6, 8), (10, 1)]", xy.levelOrderTraverse().toString());
	}
	
	@Test
	public void test02_Contains() {
		XYTree<Point> xy = new XYTree<>();
		xy.addAllPoints(new Point[]{ new Point(5, 3), new Point(5, 2), new Point(10, 7), 
				new Point(9, 1), new Point(6, 8), new Point(6, 8), new Point(10, 1) });
		
		assertTrue(xy.contains(new Point(5, 3)));
		assertTrue(xy.contains(new Point(5, 2)));
		assertTrue(xy.contains(new Point(10, 7)));
		assertTrue(xy.contains(new Point(9, 1)));
		assertTrue(xy.contains(new Point(6, 8)));
		assertTrue(xy.contains(new Point(10, 1)));
		
		assertFalse(xy.contains(new Point(9, 2)));
		assertFalse(xy.contains(new Point(7, 8)));
		assertFalse(xy.contains(new Point(2, 5)));
	}
}