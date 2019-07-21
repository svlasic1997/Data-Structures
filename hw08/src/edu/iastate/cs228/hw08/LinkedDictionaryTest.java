//package edu.iastate.cs228.hw08;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Arrays;
//import java.util.Iterator;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * @author Kyle Vetsch
// */
//public class LinkedDictionaryTest {
//    /**
//     * LinkedDictionary.
//     */
//    private LinkedDictionary<String, Integer> map;
//
//    /**
//     * Set up test fixture.
//     */
//    @Before
//    public void setUp() {
//        map = new LinkedDictionary<>();
//    }
//
//    /**
//     * Tear down test fixture.
//     */
//    @After
//    public void tearDown() {
//        map = null;
//    }
//
//    /**
//     * Test add.
//     */
//    @Test
//    public void add() {
//        assertNull(map.add("red", 1 << 1));
//        assertNull(map.add("ora", 1 << 2));
//        assertNull(map.add("gre", 1 << 4));
//        assertNull(map.add("yel", 1 << 3));
//
//        assertTrue(map.contains("red"));
//        assertTrue(map.contains("ora"));
//        assertTrue(map.contains("gre"));
//        assertTrue(map.contains("yel"));
//
//        assertEquals(Integer.valueOf(2), map.add("red", 1 << 5));
//
//        assertEquals(Integer.valueOf(32), map.getValue("red"));
//
//        assertEquals(4, map.getSize());
//    }
//
//    /**
//     * Test remove.
//     */
//    @Test
//    public void remove() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//        map.add("yel", 1 << 3);
//        map.add("gre", 1 << 4);
//
//        assertEquals(Integer.valueOf(8), map.remove("yel"));
//        assertNull(map.remove("blu"));
//    }
//
//    /**
//     * Test getValue.
//     */
//    @Test
//    public void getValue() {
//        map.add("yel", 1 << 3);
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//
//        assertEquals(Integer.valueOf(8), map.getValue("yel"));
//        assertEquals(Integer.valueOf(2), map.getValue("red"));
//        assertEquals(Integer.valueOf(4), map.getValue("ora"));
//    }
//
//    /**
//     * Test contains.
//     */
//    @Test
//    public void contains() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//
//        assertTrue(map.contains("red"));
//        assertFalse(map.contains("blu"));
//    }
//
//    /**
//     * Test isEmpty.
//     */
//    @Test
//    public void isEmpty() {
//        assertTrue(map.isEmpty());
//
//        map.add("red", 1 << 1);
//
//        assertFalse(map.isEmpty());
//    }
//
//    /**
//     * Test getSize.
//     */
//    @Test
//    public void getSize() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//
//        assertEquals(2, map.getSize());
//    }
//
//    /**
//     * Test clear.
//     */
//    @Test
//    public void clear() {
//        map.add("red", 1 << 1);
//
//        map.clear();
//
//        assertTrue(map.isEmpty());
//    }
//
//    /**
//     * Test toString.
//     */
//    @Test
//    public void testToString() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//
//        assertTrue(map.toString().equals("[(ora:4), (red:2)]")
//                || map.toString().equals("[(red:2), (ora:4)]"));
//
//        map.add("red", 1 << 3);
//
//        assertTrue(map.toString().equals("[(ora:4), (red:8)]")
//                || map.toString().equals("[(red:8), (ora:4)]"));
//    }
//
//    /**
//     * Test getKeyIterator.
//     */
//    @Test
//    public void getKeyIterator() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//        map.add("yel", 1 << 3);
//        map.add("gre", 1 << 4);
//
//        final String[] keys = new String[]{"gre", "ora", "red", "yel"};
//
//        final Iterator iter = map.getKeyIterator();
//
//        while (iter.hasNext()) {
//            assertTrue(Arrays.stream(keys).anyMatch(iter.next()::equals));
//        }
//    }
//
//    /**
//     * Test getValueIterator.
//     */
//    @Test
//    public void getValueIterator() {
//        map.add("red", 1 << 1);
//        map.add("ora", 1 << 2);
//        map.add("yel", 1 << 3);
//        map.add("gre", 1 << 4);
//
//        final Integer[] values = new Integer[]{16, 4, 2, 8};
//
//        final Iterator it = map.getValueIterator();
//
//        while (it.hasNext()) {
//            assertTrue(Arrays.stream(values).anyMatch(it.next()::equals));
//        }
//    }
//}
package edu.iastate.cs228.hw08;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Cory Smith
 * 
 * I Use LinkedDictionaries getSize and contains methods to test out most of the other methods, so if you fail everything, try checking those
 *  
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedDictionaryTest {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_SortedVectorDictionaryAdd() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		assertNull(d.add(0, "A"));
		assertEquals("[(0:A)]", d.toString());
		assertNull(d.add(2, "B"));
		assertEquals("[(0:A), (2:B)]", d.toString());
		assertNull(d.add(1, "C"));
		assertEquals("[(0:A), (1:C), (2:B)]", d.toString());
		assertEquals("A", d.add(0, "D"));
		assertEquals("[(0:D), (1:C), (2:B)]", d.toString());
		assertEquals("B", d.add(2, "E"));
		assertEquals("[(0:D), (1:C), (2:E)]", d.toString());
		assertEquals("C", d.add(1, "F"));
		assertEquals("[(0:D), (1:F), (2:E)]", d.toString());
	}
	
	@Test
	public void test02_SortedVectorDictionaryRemove() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertEquals("[(0:A), (1:B), (2:C), (3:D), (4:E), (5:F)]", d.toString());
		assertEquals("F", d.remove(5));
		assertEquals("[(0:A), (1:B), (2:C), (3:D), (4:E)]", d.toString());
		assertEquals("A", d.remove(0));
		assertEquals("[(1:B), (2:C), (3:D), (4:E)]", d.toString());
		assertEquals("D", d.remove(3));
		assertEquals("[(1:B), (2:C), (4:E)]", d.toString());
		assertNull(d.remove(6));
		assertEquals("[(1:B), (2:C), (4:E)]", d.toString());
	}
	
	@Test
	public void test03_SortedVectorDictionaryGetValue() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertEquals("[(0:A), (1:B), (2:C), (3:D), (4:E), (5:F)]", d.toString());
		assertEquals("A", d.getValue(0));
		assertEquals("B", d.getValue(1));
		assertEquals("C", d.getValue(2));
		assertEquals("D", d.getValue(3));
		assertEquals("E", d.getValue(4));
		assertEquals("F", d.getValue(5));
		assertEquals("[(0:A), (1:B), (2:C), (3:D), (4:E), (5:F)]", d.toString());
	}
	
	@Test
	public void test04_SortedVectorDictionaryContains() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertTrue(d.contains(0));
		assertTrue(d.contains(1));
		assertTrue(d.contains(2));
		assertTrue(d.contains(3));
		assertTrue(d.contains(4));
		assertTrue(d.contains(5));
		assertFalse(d.contains(6));
	}
	
	@Test
	public void test05_SortedVectorDictionarySize() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		assertTrue(d.isEmpty());
		assertEquals(0, d.getSize());
		d.add(0, "A");
		assertFalse(d.isEmpty());
		assertEquals(1, d.getSize());
		d.add(1, "B");
		assertFalse(d.isEmpty());
		assertEquals(2, d.getSize());
		d.remove(0);
		assertFalse(d.isEmpty());
		assertEquals(1, d.getSize());
		d.remove(1);
		assertTrue(d.isEmpty());
		assertEquals(0, d.getSize());
	}
	
	@Test
	public void test06_SortedVectorDictionaryClear() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		d.clear();
		assertEquals("[]", d.toString());
	}
	
	@Test
	public void test07_SortedVectorDictionaryKeyIterator() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		Iterator<Integer> i = d.getKeyIterator();
		assertTrue(i.hasNext());
		assertEquals(Integer.valueOf(0), i.next());
		assertTrue(i.hasNext());
		assertEquals(Integer.valueOf(1), i.next());
		assertTrue(i.hasNext());
		assertEquals(Integer.valueOf(2), i.next());
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test08_SortedVectorDictionaryValueIterator() {
		SortedVectorDictionary<Integer, String> d = new SortedVectorDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "C");
		d.add(2, "B");
		Iterator<String> i = d.getValueIterator();
		assertTrue(i.hasNext());
		assertEquals("A", i.next());
		assertTrue(i.hasNext());
		assertEquals("C", i.next());
		assertTrue(i.hasNext());
		assertEquals("B", i.next());
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test09_LinkedDictionaryAdd() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		assertNull(d.add(0, "A"));
		assertEquals(1, d.getSize());
		assertNull(d.add(2, "B"));
		assertEquals(2, d.getSize());
		assertNull(d.add(1, "C"));
		assertEquals(3, d.getSize());
		assertTrue(d.contains(0));
		assertTrue(d.contains(1));
		assertTrue(d.contains(2));
		assertEquals("A", d.add(0, "D"));
		assertEquals(3, d.getSize());
		assertEquals("B", d.add(2, "E"));
		assertEquals(3, d.getSize());
		assertEquals("C", d.add(1, "F"));
		assertEquals(3, d.getSize());
		assertTrue(d.contains(0));
		assertTrue(d.contains(1));
		assertTrue(d.contains(2));
	}
	
	@Test
	public void test10_LinkedDictionaryRemove() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertEquals(6, d.getSize());
		assertEquals("F", d.remove(5));
		assertEquals(5, d.getSize());
		assertEquals("A", d.remove(0));
		assertEquals(4, d.getSize());
		assertEquals("D", d.remove(3));
		assertEquals(3, d.getSize());
		assertNull(d.remove(6));
		assertEquals(3, d.getSize());
		assertFalse(d.contains(0));
		assertTrue(d.contains(1));
		assertTrue(d.contains(2));
		assertFalse(d.contains(3));
		assertTrue(d.contains(4));
		assertFalse(d.contains(5));
	}
	
	@Test
	public void test11_LinkedDictionaryGetValue() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertEquals(6, d.getSize());
		assertEquals("A", d.getValue(0));
		assertEquals("B", d.getValue(1));
		assertEquals("C", d.getValue(2));
		assertEquals("D", d.getValue(3));
		assertEquals("E", d.getValue(4));
		assertEquals("F", d.getValue(5));
		assertEquals(6, d.getSize());
	}
	
	@Test
	public void test12_LinkedDictionaryContains() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertTrue(d.contains(0));
		assertTrue(d.contains(1));
		assertTrue(d.contains(2));
		assertTrue(d.contains(3));
		assertTrue(d.contains(4));
		assertTrue(d.contains(5));
		assertFalse(d.contains(6));
	}
	
	@Test
	public void test13_LinkedDictionarySize() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		assertTrue(d.isEmpty());
		assertEquals(0, d.getSize());
		d.add(0, "A");
		assertFalse(d.isEmpty());
		assertEquals(1, d.getSize());
		d.add(1, "B");
		assertFalse(d.isEmpty());
		assertEquals(2, d.getSize());
		d.remove(0);
		assertFalse(d.isEmpty());
		assertEquals(1, d.getSize());
		d.remove(1);
		assertTrue(d.isEmpty());
		assertEquals(0, d.getSize());
	}
	
	@Test
	public void test14_LinkedDictionaryClear() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertEquals(6, d.getSize());
		d.clear();
		assertEquals(0, d.getSize());
	}
	
	@Test
	public void test15_LinkedDictionaryToString() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		d.add(3, "D");
		d.add(4, "E");
		d.add(5, "F");
		assertTrue(d.toString().contains("(0:A)"));
		assertTrue(d.toString().contains("(1:B)"));
		assertTrue(d.toString().contains("(2:C)"));
		assertTrue(d.toString().contains("(3:D)"));
		assertTrue(d.toString().contains("(4:E)"));
		assertTrue(d.toString().contains("(5:F)"));
		assertEquals('[', d.toString().charAt(0));
		assertEquals(']', d.toString().charAt(d.toString().length() - 1));
		assertEquals(42, d.toString().length());
	}
	
	@Test
	public void test16_LinkedDictionaryKeyIterator() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "B");
		d.add(2, "C");
		Iterator<Integer> i = d.getKeyIterator();
		assertTrue(i.hasNext());
		Integer a = i.next();
		assertTrue((a.equals(0) || a.equals(1) || a.equals(2)));
		assertTrue(i.hasNext());
		Integer b = i.next();
		assertTrue((b.equals(0) || b.equals(1) || b.equals(2) && !b.equals(a)));
		assertTrue(i.hasNext());
		Integer c = i.next();
		assertTrue((c.equals(0) || c.equals(1) || c.equals(2) && !c.equals(a) && !c.equals(b)));
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test17_LinkedDictionaryKeyIteratorEx() {
		ex.expect(NoSuchElementException.class);
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		Iterator<Integer> i = d.getKeyIterator();
		i.next();
	}
	
	@Test
	public void test18_LinkedDictionaryValueIterator() {
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		d.add(0, "A");
		d.add(1, "C");
		d.add(2, "B");
		Iterator<String> i = d.getValueIterator();
		assertTrue(i.hasNext());
		String a = i.next();
		assertTrue((a.equals("A") || a.equals("B") || a.equals("C")));
		assertTrue(i.hasNext());
		String b = i.next();
		assertTrue((b.equals("A") || b.equals("B") || b.equals("C") && !b.equals(a)));
		assertTrue(i.hasNext());
		String c = i.next();
		assertTrue((c.equals("A") || c.equals("B") || c.equals("C") && !c.equals(a) && !c.equals(b)));
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test19_LinkedDictionaryValueIteratorEx() {
		ex.expect(NoSuchElementException.class);
		LinkedDictionary<Integer, String> d = new LinkedDictionary<Integer, String>();
		Iterator<String> i = d.getValueIterator();
		i.next();
	}
}
 
