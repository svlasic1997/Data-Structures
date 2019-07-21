package edu.iastate.cs228.hw04;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

public class Tester {
	/**
	 * RecursionExercises tests
	 */
	@Test
	public void displayRowOfCharactersTest() {
		assertEquals("+++++", RecursionExercises.displayRowOfCharacters('+', 5));
		assertEquals(" ", RecursionExercises.displayRowOfCharacters(' ', 1));
	}
	
	@Test
	public void displayBackwardsTest() {
		assertEquals("21", RecursionExercises.displayBackwards(new int[] {1, 2, 3, 4}, 2));
		assertEquals("172-3", RecursionExercises.displayBackwards(new int[] {-3, 2, 7, 1}, 4));
		assertEquals("1", RecursionExercises.displayBackwards(new int[] {1, 2, 3, 4}, 1));
	}
	
	@Test
	public void isPalindromeTest() {
		assertTrue(RecursionExercises.isPalindrome("dad"));
		assertTrue(RecursionExercises.isPalindrome("LoL"));
		assertTrue(RecursionExercises.isPalindrome("Z"));
		assertFalse(RecursionExercises.isPalindrome("Dad"));
		assertFalse(RecursionExercises.isPalindrome("help"));
	}
	
	@Test
	public void getSecondSmallestTest() {
		assertEquals(2, (int)RecursionExercises.getSecondSmallest(new Integer[]{-1,10,3,2},4));
		assertEquals(2, (int)RecursionExercises.getSecondSmallest(new Integer[]{1,2,3,4,5,6,7,8,9}, 9));
		assertEquals(2, (int)RecursionExercises.getSecondSmallest(new Integer[]{1,2,3,4,5,6,7,8,9}, 1));
		assertEquals(2, (int)RecursionExercises.getSecondSmallest(new Integer[]{1,2,3,4,5,6,7,8,9}, 2));
	}
	
	/**
	 * CircularLinkedQueue tests
	 */
	@Test
	public void circularLinkedQueueEnqueueTest() {
		//no way to test this without also testing getFront
		CircularLinkedQueue<String> queue = new CircularLinkedQueue<String>();
		queue.enqueue("Oi");
		assertEquals("Oi", queue.getFront());
		queue.enqueue("Tudo bem?");
		assertEquals("Oi", queue.getFront());
	}
	
	@Test
	public void circularLinkedQueueDequeueTest() {
		CircularLinkedQueue<String> queue = new CircularLinkedQueue<String>();
		queue.enqueue("Hola");
		queue.enqueue("Como estas?");
		queue.enqueue("Muy bien, y tu?");
		assertEquals("Hola", queue.dequeue());
		assertEquals("Como estas?", queue.dequeue());
		assertEquals("Muy bien, y tu?", queue.dequeue());
		
		boolean wasThrown = false;
		try {
			queue.dequeue();
		} catch (EmptyQueueException e) {
			wasThrown = true;
		}
		assertTrue(wasThrown);	// if you fail this it means your exception is not being thrown correctly
	}
	
	@Test
	public void circularLinkedQueueGetFrontTest() {
		//was already test mostly in the Enqueue test
		// still need to check if the exception is thrown though.
		
		CircularLinkedQueue<Double> queue = new CircularLinkedQueue<Double>();
		boolean wasThrown = false;
		try {
			queue.getFront();
		} catch (EmptyQueueException e) {
			wasThrown = true;
		}
		assertTrue(wasThrown);	// if you fail this it means your exception is not being thrown correctly
	}
	
	
	@Test
	public void circularLinkedQueueIsEmptyTest() {
		CircularLinkedQueue<String> queue = new CircularLinkedQueue<String>();
		assertTrue(queue.isEmpty());
		queue.enqueue("Estoy estudiando informatica");
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void circularLinkedQueueClearTest() {
		CircularLinkedQueue<String> queue = new CircularLinkedQueue<String>();
		queue.enqueue("Te gustas informatica?");
		queue.enqueue("Mas o menos");
		queue.enqueue("Porque?");
		queue.clear();
		assertTrue(queue.isEmpty());
	}
	
	/**
	 * CircularDoublyLinkedDeque tests
	 * Assumes getBack and getFront work correctly and throw EmptyQueueExceptions
	 */
	@Test
	public void circDLDAddToFrontTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		boolean wasThrown = false;
		try {
			queue.getFront();
		} catch (EmptyQueueException e) {
			wasThrown = true;
		}
		assertTrue(wasThrown);
		
		queue.addToFront("Soy de Iowa");
		assertEquals("Soy de Iowa",queue.getBack());
		assertEquals("Soy de Iowa", queue.getFront());
		
		queue.addToFront("De donde eres?");
		assertEquals("Soy de Iowa",queue.getBack());
		assertEquals("De donde eres?", queue.getFront());
	}
	
	@Test
	public void circDLDAddToBackTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		boolean wasThrown = false;
		try {
			queue.getBack();
		} catch (EmptyQueueException e) {
			wasThrown = true;
		}
		assertTrue(wasThrown);
		
		queue.addToBack("Soy de Iowa");
		assertEquals("Soy de Iowa",queue.getBack());
		assertEquals("Soy de Iowa", queue.getFront());
		
		queue.addToBack("De donde eres?");
		assertEquals("De donde eres?", queue.getBack());
		assertEquals("Soy de Iowa",queue.getFront());
	}
	
	@Test
	public void circDLDRemoveFrontTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		queue.addToFront("Soy de Iowa");
		queue.addToFront("De donde eres?");
		assertEquals("De donde eres?", queue.removeFront());
		assertEquals("Soy de Iowa", queue.removeFront());
		
		boolean wasThrown = false;
		try {
			queue.removeFront();
		} catch (EmptyQueueException e) {
			wasThrown = true;
		}
		assertTrue(wasThrown);
	}
	
	@Test
	public void circDLDRemoveBackTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		queue.addToBack("Soy de Iowa");
		queue.addToBack("De donde eres?");
		assertEquals("De donde eres?", queue.removeBack());
		assertEquals("Soy de Iowa",queue.removeBack());
		
	}
	
	
	@Test
	public void circDLDIsEmptyTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		assertTrue(queue.isEmpty());
		
		queue.addToFront("Estas cansado?");
		queue.addToBack("No estoy cansado, tengo hambre, vamos a la playa");
		assertFalse(queue.isEmpty());
		queue.removeFront();
		assertFalse(queue.isEmpty());
		queue.removeBack();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void circDLDClearTest() {
		CircularDoublyLinkedDeque<String> queue = new CircularDoublyLinkedDeque<String>();
		queue.clear();
		
		queue.addToFront("Estas cansado?");
		queue.addToBack("No estoy cansado, tengo hambre, vamos a la playa");
		queue.clear();
		assertTrue(queue.isEmpty());
	}
}
