package edu.iastate.cs228.hw07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle Vetsch
 * Test the ResizableArrayBag class.
 */
public class ResizeableArrayTester {
    /**
     * Bag used throughout.
     */
    private ResizableArrayBag<String> b;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        b = new ResizableArrayBag<>();
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
    public void listIteratorForResizableArrayBag() {
        final ListIterator<String> it = b.listIterator();

        assertEquals("A", it.next());
    }

    /**
     * Test constructor with index.
     * Relies on correctness of next().
     */
    @Test
    public void listIteratorForResizableArrayBagIndex() {
        final ListIterator<String> it = b.listIterator(2);

        assertEquals("C", it.next());
    }

    /**
     * Test hasNext().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void hasNext() {
        final ListIterator<String> it = b.listIterator(3);

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
        final ListIterator<String> it = b.listIterator();

        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
        assertEquals("A", it.next());
    }

    /**
     * Test next() exception.
     * Relies on correctness of constructor.
     */
    @Test(expected = NoSuchElementException.class)
    public void nextAtEnd() {
        final ListIterator<String> it = b.listIterator(4);
        it.next();
    }

    /**
     * Test hasPrevious().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void hasPrevious() {
        final ListIterator<String> it = b.listIterator();

        assertFalse(it.hasPrevious());
        it.next();
        assertTrue(it.hasPrevious());
    }

    /**
     * Test previous().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void previous() {
        final ListIterator<String> it = b.listIterator();

        it.next();
        it.next();

        assertEquals("B", it.previous());
        assertEquals("A", it.previous());
    }

    /**
     * Test previous() exception.
     * Relies on correctness of constructor.
     */
    @Test(expected = NoSuchElementException.class)
    public void previousAtStart() {
        final ListIterator<String> it = b.listIterator();
        it.previous();
    }

    /**
     * Test nextIndex().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void nextIndex() {
        final ListIterator<String> it = b.listIterator();

        for (int i = 0; i < b.getCurrentSize(); ++i) {
            assertEquals(i, it.nextIndex());
            it.next();
        }
    }

    /**
     * Test previousIndex().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void previousIndex() {
        final ListIterator<String> it = b.listIterator();

        for (int i = -1; i < b.getCurrentSize() - 1; ++i) {
            assertEquals(i, it.previousIndex());
            it.next();
        }
    }

    /**
     * Test remove().
     * Relies on correctness of next() and constructor.
     */
    @Test
    public void remove() {
        final ListIterator<String> it = b.listIterator();
        final int size = b.getCurrentSize();
        final Object[] array = b.toArray();
        for (int i = 0; i < b.getCurrentSize(); ++i) {
            assertEquals(i, size - b.getCurrentSize());

            final String ret = it.next();

            if (i > 0) {
                assertEquals(array[i - 1], ret);
            }

            it.remove();
        }
    }

    /**
     * Test remove() exception.
     * Relies on correctness of constructor.
     */
    @Test(expected = IllegalStateException.class)
    public void removeWithoutNext() {
        final ListIterator<String> it = b.listIterator();
        it.remove();
    }

    /**
     * Test set().
     * Relies on correctness of next(), hasnext(), and constructor
     */
    @Test
    public void set() {
        final ListIterator<String> it = b.listIterator();

        while (it.hasNext()) {
            it.next();
            it.set("Z");
        }

        final String[] expected = new String[] {"Z", "Z", "Z", "Z"};

        assertArrayEquals(expected, b.toArray());
    }

    /**
     * Test set() exception.
     * Relies on correctness of constructor.
     */
    @Test(expected = IllegalStateException.class)
    public void setWithoutNext() {
        final ListIterator<String> it = b.listIterator();
        it.set("Z");
    }

    /**
     * Test add().
     * Relies on correctness of next(), hasNext() and constructor.
     */
    @Test
    public void add() {
        final ListIterator<String> it = b.listIterator();

        while (it.hasNext()) {
            it.add("Z");
            it.next();
        }

        final String[] expected = new String[] {
                "Z", "A", "Z", "B", "Z", "C", "Z", "A"
        };

        assertArrayEquals(expected, b.toArray());
    }
}