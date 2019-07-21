package edu.iastate.cs228.hw01;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoreTests {
	
	static final double DELTA = 1e-6;
	
	@Test
	/**
	 * Test triangle's no-argument constructor
	 *  This will fail if there are errors in the getSide methods
	 */
	public void Test01_TriangleDefaultConstructor() {
		Triangle t = new Triangle();
		assertEquals(1.0, t.getSide1(), DELTA);
		assertEquals(1.0, t.getSide2(), DELTA);
		assertEquals(1.0, t.getSide3(), DELTA);
	}
	
	@Test
	/**
	 * Test triangle's constructor with some random numbers
	 *  This will fail if there are errors in the getSide methods
	 */
	public void Test02_TriangleCustomConstructor() {
		Triangle t = new Triangle(5.26, 8.31, 66.6);
		assertEquals(5.26, t.getSide1(), DELTA);
		assertEquals(8.31, t.getSide2(), DELTA);
		assertEquals(66.6, t.getSide3(), DELTA);
	}
	
	@Test
	/**
	 * Test triangle's getArea method
	 */
	public void Test03_TriangleGetArea() {
		Triangle t = new Triangle(3.0, 4.0, 5.0);
		assertEquals(6.0, t.getArea(), DELTA);
	}
	
	@Test
	/**
	 * Test triangle's getPerimeter Method
	 */
	public void Test04_TriangleGetPerimeter() {
		Triangle t = new Triangle(10.0, 6.5, 12.0);
		assertEquals(28.5, t.getPerimeter(), DELTA);
	}
	
	@Test
	/**
	 * Test MyString's constructor
	 */
	public void Test05_MyStringConstructor() {
		MyString s = new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray());
		assertArrayEquals("abcdefghijklmnopqrstuvwxyz".toCharArray(), s.toCharArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * Make sure MyString's constructor throws the right exception
	 * This will always pass if you haven't changed the given code
	 */
	public void Test06_MyStringConstructorException1() {
		new MyString(new char[0]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * Make sure MyString's constructor throws the right exception
	 * This will always pass if you haven't changed the given code
	 */
	public void Test07_MyStringConstructorException2() {
		new MyString(null);
	}
	
	@Test
	/**
	 * Test MyString's length method
	 */
	public void Test08_MyStringLength() {
		MyString s = new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray());
		assertEquals(26, s.length());
	}
	
	@Test
	/**
	 * Test MyString's charAt method
	 */
	public void Test09_MyStringCharAt() {
		MyString s = new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray());
		assertEquals('a', s.charAt(0));
		assertEquals('q', s.charAt(16));
		assertEquals('z', s.charAt(25));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/**
	 * Make sure charAt throws the right exception here
	 */
	public void Test10_MyStringCharAtException() {
		MyString s = new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray());
		assertEquals('a', s.charAt(27));;
	}
	
	@Test
	/**
	 * Test MyString's substring method
	 */
	public void Test11_MyStringSubstring() {
		MyString s = new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray());
		assertArrayEquals("abcde".toCharArray(), s.substring(0, 5).toCharArray());
		assertArrayEquals("mnopqrstuvwxyz".toCharArray(), s.substring(12, 26).toCharArray());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/**
	 * Make sure substring throws the right exception with a bad lower bound
	 */
	public void Test12_MyStringSubstringException1() {
		new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray()).substring(-1, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/**
	 * Make sure substring throws the right exception with a bad upper bound
	 */
	public void Test13_MyStringSubstringException2() {
		new MyString("abcdefghijklmnopqrstuvwxyz".toCharArray()).substring(0, 27);
	}
	
	@Test
	/**
	 * Test MyString's toLowerCase method
	 */
	public void Test14_MyStringToLowerCase() {
		MyString s = new MyString("AbcdeFghijklMnopqrstUvwXyz".toCharArray());
		assertArrayEquals("abcdefghijklmnopqrstuvwxyz".toCharArray(), s.toLowerCase().toCharArray());
		assertArrayEquals("Make sure this doesn't change the original MyString object", 
				"AbcdeFghijklMnopqrstUvwXyz".toCharArray(), s.toCharArray());
	}
	
	@Test
	/**
	 * Test MyString's valueOf Method
	 */
	public void Test15_MyStringValueOf() {
		MyString s = MyString.valueOf(1234567890);
		assertArrayEquals("1234567890".toCharArray(), s.toCharArray());
	}
	
	@Test
	/**
	 * Test Rational2's no-argument Constructor
	 */
	public void Test16_Rational2DefaultConstructor() {
		Rational2 r = new Rational2();
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(0)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(1)));
	}
	
	@Test
	/**
	 * Test Rational2's custom constructor using numbers with a GCD of 1
	 */
	public void Test17_Rational2CustomConstructorNoGCD() {
		Rational2 r = new Rational2(BigInteger.valueOf(3), BigInteger.valueOf(7));
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(3)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(7)));
	}
	
	@Test
	/**
	 * Test Rational2's custom constructor using numbers with a GCD of 20
	 */
	public void Test18_Rational2CustomConstructorWithGCD() {
		Rational2 r = new Rational2(BigInteger.valueOf(20), BigInteger.valueOf(40));
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(1)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(2)));
	}
	
	@Test
	/**
	 * Test Rational2's add method
	 */
	public void Test19_Rational2Add() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(37), BigInteger.valueOf(23));
		Rational2 r = r1.add(r2);
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(973)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(276)));
	}
	
	@Test
	/**
	 * Test Rational2's subtract method
	 */
	public void Test20_Rational2Subtract() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(37), BigInteger.valueOf(23));
		Rational2 r = r1.subtract(r2);
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(85)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(276)));
	}
	
	@Test
	/**
	 * Test Rational2's multiply method
	 */
	public void Test21_Rational2Multiply() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(37), BigInteger.valueOf(23));
		Rational2 r = r1.multiply(r2);
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(37)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(12)));
	}
	
	@Test
	/**
	 * Test Rational2's divide method
	 */
	public void Test22_Rational2Divide() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(37), BigInteger.valueOf(23));
		Rational2 r = r1.divide(r2);
		assertTrue(r.getNumerator().equals(BigInteger.valueOf(529)));
		assertTrue(r.getDenominator().equals(BigInteger.valueOf(444)));
	}
	
	@Test
	/**
	 * Test Rational2's toString method
	 */
	public void Test23_Rational2ToString() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(1));
		assertEquals(r1.getNumerator() + "/" + r1.getDenominator(), r1.toString());
		assertEquals(r2.getNumerator() + "", r2.toString());
	}
	
	@Test
	/**
	 * Test Rational2's equals method
	 */
	public void Test24_Rational2Equals() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(1));
		Rational2 r3 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(1));
		assertFalse(r1.equals(r2));
		assertTrue(r2.equals(r3));
		assertFalse(r2.equals(null));
	}
	
	@Test
	/**
	 * Test Rational2's intValue method
	 */
	public void Test25_Rational2IntValue() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(36), BigInteger.valueOf(13));
		assertEquals(2, r1.intValue());
	}
	
	@Test
	/**
	 * Test Rational2's floatValue method
	 */
	public void Test26_Rational2IntValue() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(36), BigInteger.valueOf(13));
		assertEquals(36.0/13.0, r1.floatValue(), DELTA);
	}
	
	@Test
	/**
	 * Test Rational2's doubleValue method
	 */
	public void Test27_Rational2DoubleValue() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(36), BigInteger.valueOf(13));
		assertEquals(36.0/13.0, r1.doubleValue(), DELTA);
	}
	
	@Test
	/**
	 * Test Rational2's doubleValue method
	 */
	public void Test28_Rational2LongValue() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(1000));
		assertEquals(Long.MAX_VALUE / 1000, r1.longValue(), DELTA);
	}
	
	@Test
	/**
	 * Test Rational2's doubleValue method
	 */
	public void Test29_Rational2CompareTo() {
		Rational2 r1 = new Rational2(BigInteger.valueOf(23), BigInteger.valueOf(12));
		Rational2 r2 = new Rational2(BigInteger.valueOf(25), BigInteger.valueOf(12));
		Rational2 r3 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(6));
		Rational2 r4 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(6));
		assertEquals(-1, r1.compareTo(r2));
		assertEquals(1, r1.compareTo(r3));
		assertEquals(0, r3.compareTo(r4));
	}
	

}
