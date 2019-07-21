package edu.iastate.cs228.hw07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle Vetsch
 * Test the LinkedBag class.
 */
public class LinkedBagTester {
    /**
     * Bag used throughout.
     */
    private LinkedBag<String> b;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        b = new LinkedBag<>();
        b.add("A");
        b.add("B");
        b.add("C");
        b.add("A");
    }

    /**
     * Tear down test fixture.
     */
    @After
    public void tearDown() {
        b = null;
    }

    /**
     * Test empty constructor.
     * Relies on correctness of next().
     */
    @Test
    public void iteratorForLinkedBag() {
        final Iterator<String> it = b.iterator();

        assertEquals("A", it.next());
    }

    /**
     * Test hasNext().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void hasNext() {
        final Iterator<String> it = b.iterator();

        assertTrue(it.hasNext());
        it.next(); // "A"
        assertTrue(it.hasNext());
        it.next(); // "C"
        assertTrue(it.hasNext());
        it.next(); // "B"
        assertTrue(it.hasNext());
        it.next(); // "A"
        assertFalse(it.hasNext());
    }

    /**
     * Test next().
     * Relies on correctness of constructor.
     */
    @Test
    public void next() {
        final Iterator<String> it = b.iterator();

        assertEquals("A", it.next());
        assertEquals("C", it.next());
        assertEquals("B", it.next());
        assertEquals("A", it.next());
    }

    /**
     * Test next() exception.
     * Relies on correctness of hasNext() and constructor.
     */
    @Test(expected = NoSuchElementException.class)
    public void nextEnd() {
        final Iterator<String> it = b.iterator();

        while (it.hasNext()) {
            it.next();
        }

        it.next(); // exception
    }

    /**
     * Test remove().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void remove() {
        final Iterator<String> it = b.iterator();
        final int size = b.getCurrentSize();
        for (int i = 0; i < b.getCurrentSize(); ++i) {
            assertEquals(i, size - b.getCurrentSize());
            it.next();
            it.remove();
        }
    }

    /**
     * Test remove().
     * Relies on correctness of constructor.
     */
    @Test(expected = IllegalStateException.class)
    public void removeWithoutNext() {
        final Iterator<String> it = b.iterator();
        it.remove();
    }
}
