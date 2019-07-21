package edu.iastate.cs228.hw02;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;


public class ResizableArrayBagTest {
    /** Variables used with testing. */
    private String str;
    private ResizableArrayBag<String> r;

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    private ResizableArrayBag<Integer> bag1;
    private ResizableArrayBag<Integer> bag2;

    /**
     * Set up variables used throughout testing methods.
     */
    @Before
    public void commonVariables() {
        str = "Hello";
        r = new ResizableArrayBag<>();
    }

    /**
     * Set up variables used with union, intersection, and difference.
     */
    @Before
    public void bagVariables() {
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;

        bag1 = new ResizableArrayBag<>();

        bag1.add(a);
        bag1.add(b);
        bag1.add(c);

        bag2 = new ResizableArrayBag<>();

        bag2.add(b);
        bag2.add(b);
        bag2.add(d);
        bag2.add(e);
    }

    /**
     * Test constructor(int size).
     */
    @Test
    public void constructor() {
        ResizableArrayBag<Integer> x = new ResizableArrayBag<>(2);

        x.add(1);
        x.add(3);

        assertEquals(2, x.getCurrentSize());
    }

    /**
     * Test constructor(T[] arr).
     */
    @Test
    public void constructor1() {
        Integer[] arr = new Integer[] {1, 2, 5};

        ResizableArrayBag<Integer> x = new ResizableArrayBag<>(arr);

        Object[] ret = x.toArray();
        Arrays.sort(ret);

        assertArrayEquals(arr, ret);
    }

    /**
     * Test constructor and maximum capacity.
     */
    @Test(expected = IllegalStateException.class)
    public void constructor2() {
        new ResizableArrayBag<Integer>((int) 1e9);
    }

    /**
     * Note: cannot test private method checkInitialization().
     */

    /**
     * Test isEmpty().
     */
    @Test
    public void isEmpty() {
        assertTrue(r.isEmpty());

        r.add(str);

        assertFalse(r.isEmpty());
    }

    /**
     * Test getCurrentSize().
     */
    @Test
    public void getCurrentSize() {
        r.add(str);
        assertEquals(1, r.getCurrentSize());
    }

    /**
     * Test add().
     */
    @Test
    public void add() {
        assertTrue(r.add(str));
    }

    /**
     * Test toArray().
     */
    @Test
    public void toArray() {
        r.add(str);
        r.add(str);
        r.add(str);

        final String[] array = {str, str, str};

        assertArrayEquals(array, r.toArray());
    }

    /**
     * Test getFrequencyOf().
     */
    @Test
    public void getFrequencyOf() {
        final int freq = 102;
        for (int i = 0; i < freq; ++i) {
            r.add(str);
        }

        r.add("Test");

        assertEquals(freq, r.getFrequencyOf(str));
    }

    /**
     * Test contains().
     */
    @Test
    public void contains() {
        r.add("Test");
        r.add(str);

        assertTrue(r.contains(str));
        assertFalse(r.contains("Class"));
    }

    /**
     * Test clear().
     */
    @Test
    public void clear() {
        r.add(str);
        r.add(str);
        r.clear();

        assertTrue(r.isEmpty());
    }

    /**
     * Test remove().
     */
    @Test
    public void remove() {
        r.add(str);
        r.add(str);

        assertEquals(str, r.remove());
        assertArrayEquals(new String[]{str}, r.toArray());

        assertEquals(null, new ResizableArrayBag<String>().remove());
    }

    /**
     * Test remove(T entry).
     */
    @Test
    public void remove1() {
        r.add(str);
        r.add("Test");

        assertTrue(r.remove(str));
        assertArrayEquals(new String[]{"Test"}, r.toArray());

        assertFalse(new ResizableArrayBag<String>().remove(str));
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
        r.add(str);
        r.add(str);
        String ret = r.replace("Test");

        assertEquals(str, ret);
        assertEquals(2, r.getCurrentSize());
        assertEquals(1, r.getFrequencyOf(str));
        assertEquals(1, r.getFrequencyOf("Test"));

        assertEquals(null, new ResizableArrayBag<String>().replace(str));
    }

    /**
     * Test removeEvery().
     */
    @Test
    public void removeEvery() {
        r.add(str);
        r.add(str);
        r.add("Test");

        r.removeEvery(str);

        assertArrayEquals(new String[] {"Test"}, r.toArray());
    }

    /**
     * Test equals().
     */
    @Test
    public void equals() {
        final ResizableArrayBag<Integer> one = new ResizableArrayBag<>();
        one.add(1);
        one.add(3);
        one.add(5);

        final ResizableArrayBag<Integer> two = new ResizableArrayBag<>();
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
