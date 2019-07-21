package edu.iastate.cs228.hw03;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;


// These tests won't directly check toArray, because it will be using that method to check everything else.
// They also won't directly test your constructor, because that doesn't do much and its used everywhere.
// If all the tests are failing, take a look at those.
//
// For part 2, I can't check the Big Oh of the functions, only that they work properly.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HW3_Tester {
	
	@Test
	public void test01_DoublyLinkedBagAdd() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		b.add("a");
		b.add("q");
		b.add("%");
		b.add("asdf");
		
		Object[] expected = {"a", "q", "%", "asdf"};
		Object[] result = b.toArray();
		
		Arrays.sort(expected);
		Arrays.sort(result);
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test02_DoublyLinkedBagIsEmpty() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		assertTrue(b.isEmpty());
		
		b.add("a");
		b.add("a");
		
		assertFalse(b.isEmpty());
		
		b.remove();
		
		assertFalse(b.isEmpty());
		
		b.remove();
		
		assertTrue(b.isEmpty());
	}
	
	@Test
	public void test03_DoublyLinkedBagGetCurrentSize() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		assertEquals(0, b.getCurrentSize());
		
		b.remove();
		
		assertEquals(0, b.getCurrentSize());
		
		b.add("a");
		b.add("q");
		
		assertEquals(2, b.getCurrentSize());
		
		b.add("%");
		b.add("asdf");
		
		assertEquals(4, b.getCurrentSize());
		
		b.remove();
		
		assertEquals(3, b.getCurrentSize());
		
		b.remove();
		b.remove();
		
		assertEquals(1, b.getCurrentSize());
	}
	
	@Test
	public void test04_DoublyLinkedBagGetFrequencyOf() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		b.add("a");
		b.add("q");
		b.add("%");
		b.add("asdf");
		b.add("a");
		
		assertEquals(0, b.getFrequencyOf("z"));
		assertEquals(1, b.getFrequencyOf("q"));
		assertEquals(1, b.getFrequencyOf("%"));
		assertEquals(1, b.getFrequencyOf("asdf"));
		assertEquals(2, b.getFrequencyOf("a"));
	}
	
	@Test
	public void test05_DoublyLinkedBagContains() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		b.add("a");
		b.add("q");
		b.add("%");
		b.add("asdf");
		b.add("a");
		
		assertFalse(b.contains("z"));
		assertTrue(b.contains("a"));
		assertTrue(b.contains("q"));
		assertTrue(b.contains("%"));
		assertTrue(b.contains("asdf"));
	}
	
	@Test
	public void test06_DoublyLinkedBagClear() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		b.add("a");
		b.add("q");
		b.add("%");
		b.add("asdf");
		b.add("a");
		
		b.clear();
		
		assertTrue(b.isEmpty());
	}
	
	@Test
	public void test07_DoublyLinkedBagRemove() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		assertEquals(null, b.remove());
		
		b.add("a");
		b.add("a");
		b.add("a");
		b.add("a");
		
		assertEquals("a", b.remove());
		assertEquals("a", b.remove());
		assertEquals("a", b.remove());
		assertEquals("a", b.remove());
		assertEquals(null, b.remove());
	}
	
	@Test
	public void test08_DoublyLinkedBagRemoveObject() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		assertFalse(b.remove("a"));
		
		b.add("a");
		b.add("a");
		b.add("b");
		b.add("c");
		
		assertTrue(b.remove("a"));
		assertTrue(b.remove("b"));
		assertTrue(b.remove("c"));
		assertTrue(b.remove("a"));
		assertFalse(b.remove("a"));
	}
	
	@Test
	public void test09_DoublyLinkedBagReplace() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		assertEquals(null, b.replace("b"));
		
		b.add("a");
		b.add("a");
		b.add("a");
		
		assertEquals("a", b.replace("b"));
		assertTrue(b.contains("b"));
		
		b.replace("c");
		assertTrue(b.contains("c"));
		
		b.replace("d");
		assertTrue(b.contains("d"));
		
		b.replace("e");
		assertTrue(b.contains("e"));
	}
	
	@Test
	public void test10_DoublyLinkedBagRemoveEvery() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		b.add("a");
		b.add("a");
		b.add("a");
		b.add("b");
		b.add("a");
		
		b.removeEvery("a");
		
		assertFalse(b.contains("a"));
		assertTrue(b.contains("b"));
		assertEquals(1, b.getCurrentSize());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void test11_DoublyLinkedBagEquals() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		DoublyLinkedBag<String> b2 = new DoublyLinkedBag<String>();
		DoublyLinkedBag<String> b3 = new DoublyLinkedBag<String>();
		
		assertTrue(b.equals(b2));
		
		b.add("a");
		b.add("b");
		b.add("c");
		b.add("d");
		b.add("e");
		
		assertFalse(b.equals(b2));
		
		b2.add("e");
		b2.add("b");
		b2.add("c");
		b2.add("d");
		b2.add("a");
		
		assertTrue(b.equals(b2));
		
		b3.add("a");
		b3.add("b");
		b3.add("c");
		b3.add("d");
		b3.add("e");
		b3.add("e");
		
		assertFalse(b.equals(b3));
		assertFalse(b3.equals(b));
		assertFalse(b2.equals(b3));
		assertTrue(b.equals(b));
		assertFalse(b.equals(null));
		
	}
	
	@Test
	public void test12_DoublyLinkedBagToString() {
		DoublyLinkedBag<String> b = new DoublyLinkedBag<String>();
		
		assertEquals("[]", b.toString());
		
		b.add("a");
		
		assertEquals("[a]", b.toString());
		
		b.add("a");
		
		assertEquals("[a, a]", b.toString());
		
		b.add("a");
		
		assertEquals("[a, a, a]", b.toString());
		
		b.remove();
		
		assertEquals("[a, a]", b.toString());
	}
	
	@Test
	public void test13_Part2MissingIntOn2() {
		int[] numbers = {3, 5, 6, 1, 4};
		assertEquals(2, HW03_Part2.findMissingInt_a_On2(numbers));
		
		int[] numbers2 = {6, 5, 4, 3, 2};
		assertEquals(1, HW03_Part2.findMissingInt_a_On2(numbers2));
		
		int[] numbers3 = {1, 2, 3, 4, 5};
		assertEquals(6, HW03_Part2.findMissingInt_a_On2(numbers3));
	}
	
	@Test
	public void test14_Part2MissingIntOn1() {
		int[] numbers = {3, 5, 6, 1, 4};
		assertEquals(2, HW03_Part2.findMissingInt_b_On1(numbers));
		
		int[] numbers2 = {6, 5, 4, 3, 2};
		assertEquals(1, HW03_Part2.findMissingInt_b_On1(numbers2));
		
		int[] numbers3 = {1, 2, 3, 4, 5};
		assertEquals(6, HW03_Part2.findMissingInt_b_On1(numbers3));
	}
	
	@Test
	public void test15_Part2RearrangeOn2() {
		int[] numbers = {1, -1, 1, -1, 1, -1, 1};
		int[] expected = {-1, -1, -1, 1, 1, 1, 1};
		HW03_Part2.rearrange_a_On2(numbers);
		assertArrayEquals(expected, numbers);
		
		int[] numbers2 = {-1, 1, 1, 1, 1, 1, -1};
		int[] expected2 = {-1, -1, 1, 1, 1, 1, 1};
		HW03_Part2.rearrange_a_On2(numbers2);
		assertArrayEquals(expected2, numbers2);
		
		int[] numbers3 = {-1, -1, -1, 1, 1, 1, 1};
		int[] expected3 = {-1, -1, -1, 1, 1, 1, 1};
		HW03_Part2.rearrange_a_On2(numbers3);
		assertArrayEquals(expected3, numbers3);
	}
	
	@Test
	public void test16_Part2RearrangeOn1() {
		int[] numbers = {1, -1, 1, -1, 1, -1, 1};
		int[] expected = {-1, -1, -1, 1, 1, 1, 1};
		HW03_Part2.rearrange_b_On1(numbers);
		assertArrayEquals(expected, numbers);
		
		int[] numbers2 = {-1, 1, 1, 1, 1, 1, -1};
		int[] expected2 = {-1, -1, 1, 1, 1, 1, 1};
		HW03_Part2.rearrange_b_On1(numbers2);
		assertArrayEquals(expected2, numbers2);
		
		int[] numbers3 = {-1, -1, -1, 1, 1, 1, 1};
		int[] expected3 = {-1, -1, -1, 1, 1, 1, 1};
		HW03_Part2.rearrange_b_On1(numbers3);
		assertArrayEquals(expected3, numbers3);
	}
	
}
