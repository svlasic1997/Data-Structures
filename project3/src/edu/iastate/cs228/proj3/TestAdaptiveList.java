package edu.iastate.cs228.proj3;

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

public class TestAdaptiveList{
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	/**
	 * Tests the AdaptiveList Constructor
	 */
	@Test
	public void test01_AdaptiveListIteratorConstructor() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		
		b.add("A");
		b.add("B");
		
		i = b.listIterator();
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
	}
	
	/**
	 * Tests the AdaptiveList Constructor with a position
	 */
	@Test
	public void test02_AdaptiveListIteratorConstructorWithIndex() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator(0);
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		
		b.add("A");
		b.add("B");
		
		i = b.listIterator(1);
		assertEquals(0, i.previousIndex());
		assertEquals(1, i.nextIndex());
	}
	
	/**
	 * Tests for an exception in the AdaptiveList Constructor with a position
	 */
	@Test
	public void test03_AdaptiveListIteratorConstructorWithIndexEx1() {
		ex.expect(IndexOutOfBoundsException.class);
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator(-1);
	}
	
	/**
	 * Tests for an exception in the AdaptiveList Constructor with a position
	 */
	@Test
	public void test04_AdaptiveListIteratorConstructorWithIndexEx2() {
		ex.expect(IndexOutOfBoundsException.class);
		AdaptiveList<String> b = new AdaptiveList<String>();
		b.add("A");
		b.add("B");
		ListIterator<String> i = b.listIterator(3);
	}
	
	/**
	 * Tests the hasNext for the AdaptiveList Iterator
	 */
	@Test
	public void test05_AdaptiveListHasNext() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		i.next();
		assertTrue(i.hasNext());
		assertTrue(i.hasNext());
		i.next();
		assertFalse(i.hasNext());
	}
	
	/**
	 * Tests the hasPrevious for the AdaptiveList Iterator
	 */
	@Test
	public void test06_AdaptiveListHasPrevious() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(3);
		assertTrue(i.hasPrevious());
		i.previous();
		assertTrue(i.hasPrevious());
		i.previous();
		assertTrue(i.hasPrevious());
		assertTrue(i.hasPrevious());
		i.previous();
		assertFalse(i.hasPrevious());
	}
	
	/**
	 * Tests next for AdaptiveList Iterator 
	 */
	@Test
	public void test07_AdaptiveListNext() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		assertEquals("A", i.next());
		assertEquals("B", i.next());
		assertEquals("C", i.next());
	}
	
	/**
	 * Tests for NoSuchElementException in next
	 */
	@Test
	public void test08_AdaptiveListNextEx() {
		ex.expect(NoSuchElementException.class);
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		i.next();
		i.next();
		i.next();
		i.next();
	}
	
	/**
	 * Tests previous for the AdaptiveList Iterator 
	 */
	@Test
	public void test09_AdaptiveListPrevious() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(3);
		assertEquals("C", i.previous());
		assertEquals("B", i.previous());
		assertEquals("A", i.previous());
	}
	
	/**
	 * Tests for NoSuchElementException in previous 
	 */
	@Test
	public void test10_AdaptiveListPreviousEx() {
		ex.expect(NoSuchElementException.class);
		AdaptiveList<String> b = new AdaptiveList<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		ListIterator<String> i = b.listIterator(3);
		i.previous();
		i.previous();
		i.previous();
		i.previous();
	}
	
	/**
	 * Tests remove for AdaptiveList Iterator 
	 */
	@Test
	public void test11_AdaptiveListRemove() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		b.add("A");
		b.add("B");
		b.add("C");
		i.next();
		String s = i.next();
		i.remove();
		assertFalse(b.contains(s));
	}
	
	/**
	 * Tests for IllegalStateException in remove 
	 */
	@Test
	public void test12_AdaptiveListRemoveEx() {
		ex.expect(IllegalStateException.class);
		AdaptiveList<String> b = new AdaptiveList<String>();
		ListIterator<String> i = b.listIterator();
		i.remove();
	}
	
	/**
	 * Tests set in AdaptiveList Iterator 
	 */
	@Test
	public void test13_AdaptiveListSet() {
		 
		AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 b.add("D");
		 final ListIterator<String> it = b.listIterator();

	        while (it.hasNext()) {
	            it.next();
	            it.set("Z");
	        }

	        final String[] expected = new String[] {"Z", "Z", "Z", "Z"};

	        assertArrayEquals(expected, b.toArray());
	}
	
	/**
	 * Tests add in AdaptiveList Iterator 
	 */
	@Test
	public void test14_AdaptiveListAdd() {
		AdaptiveList<String> b = new AdaptiveList<String>();
		b.add("A");
		b.add("B");
		b.add("C");
		b.add("D");
		final ListIterator<String> it = b.listIterator();

	        while (it.hasNext()) {
	            it.add("Z");
	            it.next();
	        }

	        final String[] expected = new String[] {
	                "Z", "A", "Z", "B", "Z", "C", "Z", "D"
	        };

	        assertArrayEquals(expected, b.toArray());
	    }
	
	 /**
	  * Tests add method for AdaptiveList
	  */
	 @Test
	 public void test15_AdaptiveListAdd2(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 String[] expected = new String[] {"A", "B", "C"};
		 
		 assertArrayEquals(expected, b.toArray());
	 }
	 
	 /**
	  * Returns the size of an AdaptiveList
	  */
	 @Test
	 public void test16_AdaptiveListSize(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 assertEquals(b.size(), 3);
	 }
	 
	 /**
	  * Tests whether an AdaptiveList is empty
	  */
	 @Test
	 public void test17_AdaptiveListisEmpty(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 assertTrue(b.isEmpty());
		 
		 b.add("A");
		 assertFalse(b.isEmpty());
	 }
	 
	 /**
	  * Tests remove method of AdaptiveList
	  */
	 @Test
	 public void test18_AdaptiveListRemove2(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 b.remove("C");
		 
		 String[] result = new String[]{"A","B"};
		 
		 assertArrayEquals(result, b.toArray());
	 }
	 
	 /**
	  * Tests whether you can add at a position in an AdaptiveList
	  */
	 @Test
	 public void test19_AdaptiveListAddPos(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 b.add(1, "Z");
		 
		 String[] result = new String[]{"A", "Z", "B", "C"};
		 
		 assertArrayEquals(result, b.toArray());
	 }
	 
	 /**
	  * Tests get method of the AdaptiveList
	  */
	 @Test public void test20_AdaptiveListGet(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 assertEquals("B", b.get(1));
		 
	 }
	 
	 /**
	  * Tests the set method at a position in an AdaptiveList
	  */
	 @Test
	 public void test21_AdaptiveListSet2(){
		
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 b.set(1, "Z");
		 String[] result = new String[]{"A", "Z","C"};
		 
		 assertArrayEquals(result, b.theArray);
		 
	 }
	 
	 /**
	  * Tests whether an AdaptiveList contains an object
	  */
	 @Test
	 public void test22_AdaptiveListContains(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 assertFalse(b.contains("C"));
		 assertFalse(b.contains(null));
		 
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 assertTrue(b.contains("C"));
	 }
	 
	 /**
	  * Tests to find the index of an object in an AdaptiveList
	  */
	 @Test
	 public void test23_AdaptiveListIndexOf(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 assertEquals(-1, b.indexOf("Z"));
		 
		 assertEquals(1, b.indexOf("B"));
	 }
	 
	 /**
	  * Tests to find the last possible index of an object in an AdaptiveList
	  */
	 @Test
	 public void test24_AdaptiveListLastIndexOf(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 b.add("B");
		 
		 assertEquals(-1, b.lastIndexOf("Z"));
		 
		 assertEquals(3, b.lastIndexOf("B"));
	 }
	 
	 /**
	  * Tests the toArray method of AdaptiveList
	  */
	 @Test
	 public void test25_AdaptiveListToArray(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 String[] array = new String[]{"A","B","C"};
		 
		 assertArrayEquals(array, b.toArray());
	 }
	 
	 /**
	  * Tests the remove at a position method for AdaptiveList
	  */
	 @Test
	 public void test26_AdaptiveListRemovePos(){
		 
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		
		 b.remove(2);
		 
		String[] result = new String[]{"A","B"};
		assertArrayEquals(result,b.toArray());
		
		b.remove(0);
		
		String[] result2 = new String[]{"B"};
		assertArrayEquals(result2, b.toArray());
	 }
	 
	 /**
	  * Tests the addAll method for the AdaptiveList
	  */
	 @Test
	 public void test27_AdaptiveListAddAll(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 b.addAll(collection);
		 
		 String[] result = new String[]{"A","B","C","X","Y","Z"};
		 
		 assertArrayEquals(result, b.toArray());
	 }
	 
	 /**
	  * Tests the addAll method for the AdaptiveList at a specified position
	  */
	 @Test
	 public void test28_AdaptiveListAddAllPos(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 b.addAll(1, collection);
		 
		 String[] result = new String[]{"A","X","Y","Z","B","C"};
		 
		 assertArrayEquals(result, b.toArray());
		 
	}
	 
	/**
	 * Tests the containsAll method for the AdaptiveList
	 */
	@Test
	public void test29_AdaptiveListContainsAll(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 b.addAll(2, collection);
		 assertTrue(b.containsAll(collection));
	}
	
	/**
	 * Tests the removeAll method for the AdaptiveList
	 */
	@Test
	public void test30_AdaptiveListRemoveAll(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 b.addAll(collection);
		 b.removeAll(collection);
		 String[] result = new String[]{"A","B","C"};
		 assertArrayEquals(result, b.toArray());
	}
	
	/**
	 * Tests the toArray method for AdaptiveList with parameter T[] arr
	 */
	@Test
	public void test31_AdaptiveListToArray2(){
		
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 
		 String[] result = new String[]{"A","B","C"};
		 
		 assertArrayEquals(result, b.toArray(result));
	}
	
	/**
	 * Tests the reverse method for AdaptiveList 
	 */
	@Test
	public void test32_AdaptiveListReverse(){
		 
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 b.add("D");
		 b.add("E");
		 b.add("F");
		 b.add("G");
		 b.add("H");
		 b.reverse();
		 
		 String[] result = new String[]{"H","G","F","E","D","C","B","A"};
		 assertArrayEquals(result, b.toArray());
	}
	
	/**
	 * Tests to swap the even index with subsequent odd index of theArray 
	 */
	@Test
	public void test33_AdaptiveListReorderEvenOdd(){
		
		 AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 b.add("D");
		 b.add("E");
		 b.add("F");
		 b.reorderOddEven();
		 
		 String[] result = new String[]{"B","A","D","C","F","E"};
		 assertArrayEquals(result, b.theArray);
		 
		 AdaptiveList<String> c = new AdaptiveList<String>();
		 c.add("A");
		 c.add("B");
		 c.add("C");
		 c.add("D");
		 c.add("E");
		 c.reorderOddEven();
		 
		 String[] result2 = new String[]{"B","A","D","C","E"};
		 assertArrayEquals(result2, c.theArray);
				 
		 AdaptiveList<String> d = new AdaptiveList<String>();
		 d.add("A");
		 assertFalse(d.reorderOddEven());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void test34_AdaptiveListRetainAll(){
		 
		AdaptiveList<String> b = new AdaptiveList<String>();
		 b.add("A");
		 b.add("B");
		 b.add("C");
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 b.addAll(collection);
		 b.retainAll(collection);
		 String[] result = new String[]{"X","Y","Z"};
		 assertArrayEquals(result, b.toArray());
	}
	
	/**
	 * Tests the main constructor in AdaptiveList 
	 */
	@Test
	public void test35_AdaptiveListConstructor(){
		 ArrayList<String> collection = new ArrayList<String>();
		 collection.add("X");
		 collection.add("Y");
		 collection.add("Z");
		 
		 AdaptiveList<String> b = new AdaptiveList<String>(collection);
		 
		 assertEquals(collection, b);
	}
}
