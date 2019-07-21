package edu.iastate.cs228.hw02;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedBagTest {
    /** Variables used with testing. */
    private String str;
    private LinkedBag<String> l;

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    private LinkedBag<Integer> bag1;
    private LinkedBag<Integer> bag2;

    /**
     * Set up variables used throughout testing methods.
     */
    @Before
    public void commonVariables() {
        str = "Hello!";
        l = new LinkedBag<>();
    }

    /**
     * Set up variables used with union, intersect, and difference.
     */
    @Before
    public void bagVariables() {
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;

        bag1 = new LinkedBag<>();

        bag1.add(a);
        bag1.add(b);
        bag1.add(c);

        bag2 = new LinkedBag<>();

        bag2.add(b);
        bag2.add(b);
        bag2.add(d);
        bag2.add(e);
    }

    /**
     * Test constructor and isEmpty().
     */
    @Test
    public void isEmpty() {
        assertTrue(l.isEmpty());

        l.add(str);

        assertFalse(l.isEmpty());
    }

    /**
     * Test getCurrentSize().
     */
    @Test
    public void getCurrentSize() {
        l.add(str);

        assertEquals(1, l.getCurrentSize());
    }

    /**
     * Test add().
     */
    @Test
    public void add() {
        assertTrue(l.add(str));
    }

    /**
     * Test toArray().
     */
    @Test
    public void toArray() {
        l.add(str);
        l.add(str);
        l.add(str);

        final String[] array = {str, str, str};

        assertArrayEquals(array, l.toArray());
    }

    /**
     * Test getFrequencyOf().
     */
    @Test
    public void getFrequencyOf() {
        final int freq = 102;
        for (int i = 0; i < freq; ++i) {
            l.add(str);
        }

        l.add("Test");

        assertEquals(freq, l.getFrequencyOf(str));
    }

    /**
     * Test contains().
     */
    @Test
    public void contains() {
        l.add("Test");
        l.add(str);

        assertTrue(l.contains(str));
        assertFalse(l.contains("Class"));
    }

    /**
     * Test clear().
     */
    @Test
    public void clear() {
        l.add(str);
        l.add(str);
        l.clear();

        assertTrue(l.isEmpty());
    }

    /**
     * Test remove().
     */
    @Test
    public void remove() {
        l.add(str);
        l.add(str);

        assertEquals(str, l.remove());
        assertArrayEquals(new String[]{str}, l.toArray());

        assertEquals(null, new LinkedBag<String>().remove());
    }

    /**
     * Test remove(T entry).
     */
    @Test
    public void remove1() {
        l.add(str);
        l.add("Test");

        assertTrue(l.remove(str));
        assertArrayEquals(new String[]{"Test"}, l.toArray());

        assertFalse(new LinkedBag<String>().remove(str));
    }

    /**
     * Test union().
     */
    @Test
    public void union() {
        BagInterface<Integer> union = bag1.union(bag2);

        assertEquals(1, union.getFrequencyOf(a));
        assertEquals(3, union.getFrequencyOf(b));
        assertEquals(1, union.getFrequencyOf(c));
        assertEquals(1, union.getFrequencyOf(d));
        assertEquals(1, union.getFrequencyOf(e));
    }

    /**
     * Test intersection().
     */
    @Test
    public void intersection() {
        BagInterface<Integer> intersection = bag1.intersection(bag2);

        assertEquals(0, intersection.getFrequencyOf(a));
        assertEquals(1, intersection.getFrequencyOf(b));
        assertEquals(0, intersection.getFrequencyOf(c));
        assertEquals(0, intersection.getFrequencyOf(d));
        assertEquals(0, intersection.getFrequencyOf(e));

        bag1.add(b);

        intersection = bag1.intersection(bag2);

        assertEquals(0, intersection.getFrequencyOf(a));
        assertEquals(2, intersection.getFrequencyOf(b));
        assertEquals(0, intersection.getFrequencyOf(c));
        assertEquals(0, intersection.getFrequencyOf(d));
        assertEquals(0, intersection.getFrequencyOf(e));
    }

    /**
     * Test difference().
     */
    @Test
    public void difference() {
        BagInterface<Integer> difference = bag1.difference(bag2);

        assertEquals(1, difference.getFrequencyOf(a));
        assertEquals(0, difference.getFrequencyOf(b));
        assertEquals(1, difference.getFrequencyOf(c));
        assertEquals(0, difference.getFrequencyOf(d));
        assertEquals(0, difference.getFrequencyOf(e));

        difference = bag2.difference(bag1);

        assertEquals(0, difference.getFrequencyOf(a));
        assertEquals(1, difference.getFrequencyOf(b));
        assertEquals(0, difference.getFrequencyOf(c));
        assertEquals(1, difference.getFrequencyOf(d));
        assertEquals(1, difference.getFrequencyOf(e));
    }

    /**
     * Test replace().
     */
    @Test
    public void replace() {
        l.add(str);
        l.add(str);
        String ret = l.replace("Test");

        assertEquals(str, ret);
        assertEquals(2, l.getCurrentSize());
        assertEquals(1, l.getFrequencyOf(str));
        assertEquals(1, l.getFrequencyOf("Test"));

        assertEquals(null, new LinkedBag<String>().replace(str));
    }

    /**
     * Test removeEvery().
     */
    @Test
    public void removeEvery() {
        l.add(str);
        l.add(str);
        l.add("Test");

        l.removeEvery(str);

        assertArrayEquals(new String[] {"Test"}, l.toArray());
    }

    /**
     * Test equals(). Overrides from Object.
     */
    @Test
    public void equals() {
        final LinkedBag<Integer> one = new LinkedBag<>();
        one.add(1);
        one.add(3);
        one.add(5);

        final LinkedBag<Integer> two = new LinkedBag<>();
        two.add(5);
        two.add(3);
        two.add(1);

        assertTrue(one.equals(two));
        assertTrue(two.equals(one));

        one.add(1);

        assertFalse(one.equals(two));
        assertFalse(two.equals(one));

        assertTrue(one.equals(one));
        assertFalse(one.equals(null));
    }
}