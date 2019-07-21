package edu.iastate.cs228.hw01;


/**
 * @author Scott Vlasic
 * Class for a triangle object which extends the superclass GeometricObject
 *         
 */
 
 
public class Triangle extends GeometricObject
{
	
	/**
	 * Variables for the 3 sides of the triangle
	 */
	private double side1,side2,side3;
	
	/**
	 * No argument constructor used to create default triangle with sides'
	 * values of 1.0
	 */
	public Triangle() {
		side1 = 1.0;
		side2 = 1.0;
		side3 = 1.0;
	}
	
	/**
	 * Constructor that creates triangle with specified side1, side2, & side3
	 * @param side1
	 * @param side2
	 * @param side3
	 */
	public Triangle(double side1, double side2, double side3){
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	
	@Override
	public String toString()
	{
		return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}

	/**
	 * Access method for side1
	 * @return value of side1
	 */
	public double getSide1(){
		return side1;
	}
	
	/**
	 * Access method for side2
	 * @return value of side2
	 */
	public double getSide2(){
		return side2;
	}
	
	/**
	 * Access method for side3
	 * @return value of side3
	 */
	public double getSide3(){
		return side3;
	}
	
	/**
	 * Method used to calculate the area of the triangle using Heron's formula 
	 */
	@Override
	public double getArea() {
		
		double s = (side1+side2+side3)/2.0; // Used to calculate half of the triangle's perimeter
		
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3)); // Use Heron's formula to return area
	}

	/**
	 * Method used to calculate perimeter of the triangle
	 */
	@Override
	public double getPerimeter() {
		
		return side1 + side2 + side3; // Formula for perimeter of a triangle is P = side1 + side2 + side3
		
	}
}

