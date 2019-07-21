package edu.iastate.cs228.hw01;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class Rational2 extends Number implements Comparable<Rational2>
	{
		// Data fields for numerator and denominator
		BigInteger numerator;
		BigInteger denominator;

		/** Default constructor */
		public Rational2()
		{
			numerator = BigInteger.ZERO; // Initial value for numerator
			denominator = BigInteger.ONE; // Initial value for denominator
		}

		/** Construct a rational with specified numerator and denominator */
		public Rational2(BigInteger numerator, BigInteger denominator)
		{
			// Declares gcd as type BigInteger with 2 BigInteger arguments
			BigInteger gcd = gcd(numerator, denominator);
			
			// Checks the statement ((denominator > 0) ? 1 : -1) * numerator / gcd using BigInteger to calculate value for the numerator
			if(denominator.compareTo(BigInteger.ZERO) > 0){
				this.numerator = (BigInteger.ONE).multiply(numerator.divide(gcd));
			}
			else{
				this.numerator = (BigInteger.ONE).multiply(numerator.divide(gcd)).negate();
			}
			
			// Sets the denominator equal to the absolute value of the denominator divided by the gcd
			this.denominator = (denominator.abs()).divide(gcd);
		}

		/** Find GCD of two numbers */
		private BigInteger gcd(BigInteger n, BigInteger d)
		{
			// Returns the absolute values of the BigInteger
			BigInteger n1 = n.abs();
			BigInteger n2 = d.abs();
			
			// If n2 equals 0 return n1
			if(n2.equals(BigInteger.ZERO)){
				return n1;
			}
			else{
				// else recursively return the gcd of n2 and n1%n2
				return gcd(n2,n1.mod(n2));
			}
		}

		/** Return numerator */
		public BigInteger getNumerator()
		{
			return numerator; // Returns numerator
		}

		/** Return denominator */
		public BigInteger getDenominator()
		{
			return denominator; // Returns denominator 
		}

		/** Add a rational number to this rational */
		public Rational2 add(Rational2 secondRational)
		{
			// Used BigInteger documentation carried out the operations from the Rational class
			BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
			BigInteger d = denominator.multiply(secondRational.getDenominator());
			return new Rational2(n, d);
		}

		/** Subtract a rational number from this rational */
		public Rational2 subtract(Rational2 secondRational)
		{
			// Used BigInteger documentation carried out the operations from the Rational class
			BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
			BigInteger d = denominator.multiply(secondRational.getDenominator());
			return new Rational2(n, d);
		}

		/** Multiply a rational number to this rational */
		public Rational2 multiply(Rational2 secondRational)
		{
			// Used BigInteger documentation carried out the operations from the Rational class
			BigInteger n = numerator.multiply(secondRational.getNumerator());
			BigInteger d = denominator.multiply(secondRational.getDenominator());
			return new Rational2(n, d);
		}

		/** Divide a rational number from this rational */
		public Rational2 divide(Rational2 secondRational)
		{
			// Used BigInteger documentation carried out the operations from the Rational class
			BigInteger n = numerator.multiply(secondRational.getDenominator());
			BigInteger d = denominator.multiply(secondRational.numerator);
			return new Rational2(n, d);
		}

		@Override
		public String toString()
		{
			// If denominator == 0 return the numerator + ""
			if (denominator.equals(BigInteger.ONE))
				return numerator + "";
			else
				return numerator + "/" + denominator; // Else carry out this operation
		}

		/** Override the equals method in the Object class */
		public boolean equals(Object parm1)
		{
			if(this == parm1) return true;
			if((parm1 == null) || (parm1.getClass()!=this.getClass())) return false;
			
			// Changed the == to .equals to account for BigInteger
			if ((this.subtract((Rational2) (parm1))).getNumerator().equals(BigInteger.ZERO))
				return true;
			else
				return false;
		}

		/** Override the abstract intValue method in java.lang.Number */
		public int intValue()
		{
			return (int) doubleValue();
		}

		/** Override the abstract floatValue method in java.lang.Number */
		public float floatValue()
		{
			return (float) doubleValue();
		}

		/** Override the doubleValue method in java.lang.Number */
		public double doubleValue()
		{
			// Converts BigIntegers to doubles and then divides 
			return (numerator.doubleValue()*1)/denominator.doubleValue();
		}

		/** Override the abstract longValue method in java.lang.Number */
		public long longValue()
		{
			return (long) doubleValue();
		}

		@Override
		public int compareTo(Rational2 o)
		{
			// If the statement results in a value greater than 0, return 1
			if (this.subtract((Rational2)o).getNumerator().compareTo(BigInteger.ZERO) > 0)
				return 1;
			// If the statement results in a value less than 0, return -1
			else if (this.subtract((Rational2)o).getNumerator().compareTo(BigInteger.ZERO) < 0)
				return -1;
			else
				return 0; // If none of these happen return 0
		}

}
