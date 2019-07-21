package edu.iastate.cs228.hw01;


/**
 * 
* @author Y. Daniel Liang
*/

// GeometricObject.java: The abstract GeometricObject class
public abstract class GeometricObject
{
	private String color;
	private boolean filled;

	/** Default constructor */
	protected GeometricObject()
	{
		color = "white";
		filled = false;
	}

	/** Construct a geometric object */
	protected GeometricObject(String color, boolean filled)
	{
		this.color = color;
		this.filled = filled;
	}

	/** Getter method for color */
	public String getColor()
	{
		return color;
	}

	/** Setter method for color */
	public void setColor(String color)
	{
		this.color = color;
	}

	/**
	 * Getter method for filled. Since filled is boolean, so, the get method name
	 * is isFilled
	 */
	public boolean isFilled()
	{
		return filled;
	}

	/** Setter method for filled */
	public void setFilled(boolean filled)
	{
		this.filled = filled;
	}

	/** Abstract method findArea */
	public abstract double getArea();

	/** Abstract method getPerimeter */
	public abstract double getPerimeter();
}
