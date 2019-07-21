package edu.iastate.cs228.proj4;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Scott Vlasic
 * 
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestEntryTree{

	// Creates a new Entry Tree with key of type Integer and value of type String
	EntryTree<Integer, String> testTree = new EntryTree<Integer, String>();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	/**
	 * Test the initial conditions of the constructor
	 */
	@Test
	public void NodeConstructor(){
		assertEquals(testTree.root.key, null);
		assertEquals(testTree.root.value, null);
		assertEquals(testTree.root.parent, null);
		assertEquals(testTree.root.child, null);
		assertEquals(testTree.root.prev, null);
		assertEquals(testTree.root.next, null);
	}
	
	@Test
	public void searchTest()
	{
		Integer[] compare = { 4, 5, 6, 7, 8 };
		Integer[] intree = { 4, 5, 6, 8 , 9 };
		
		testTree.add(compare, "test");
		assertEquals("test", testTree.search(compare));
		assertEquals(null, testTree.search(intree));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void prefixTest()
	{
		Integer[] compare = {4, 5, 6, 7, 8};
		Integer[] intree = {4, 5, 7, 8};
		Integer[] prefixof = {4, 5};
		
		testTree.add(compare, "test");
		assertEquals(prefixof, testTree.prefix(intree));
	}
	
	@Test
	public void addTest()
	{
		Integer[] intarr = new Integer[1];
		intarr[0] = (Integer) 1;
		testTree.add(intarr, "test");		

		assertEquals(testTree.root.child().value(), "test");
		assertEquals(testTree.root.child().key(), (Integer) 1);
	}
	
	@Test
	public void addSiblingTest()
	{
		Integer[] intarr = new Integer[1];
		intarr[0] = (Integer) 1;
		testTree.add(intarr, "test");
		intarr[0] = (Integer) 5;
		testTree.add(intarr, "test1");

		assertEquals(testTree.root.child().value(), "test");
		assertEquals(testTree.root.child().key(), (Integer) 1);
		assertEquals(testTree.root.child().next().key(), (Integer) 5);
		assertEquals(testTree.root.child().next().value(), "test1");
	}

	@Test
	public void removeTest()
	{
		
		Integer[] intarr = new Integer[1];
		intarr[0] = (Integer) 1;
		testTree.add(intarr, "hi");
		testTree.remove(intarr);
		assertEquals(null, testTree.search(intarr));
	}
	
	@Test
	public void getAllTest(){
		
		Integer[] intarr = new Integer[1];
		intarr[0] = (Integer)1;
		testTree.add(intarr, "test1");
		intarr[0] = (Integer)5;
		testTree.add(intarr, "test2");
		assertEquals(true, testTree.getAll());
		
	}
}