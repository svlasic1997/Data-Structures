package edu.iastate.cs228.proj3;

import edu.iastate.cs228.proj3.AdaptiveList;

/**
 * https://canvas.iastate.edu/courses/53780/assignments/692428
 * 
 * @author Steven Marshall Sheets - SMSheets@IAState.edu
 */
public class CanvasTests
{

	public static void main(String[] args)
	{
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("B");
		seq.add("A");
		seq.add("C");
		System.out.println("After the three seq.add() operations: ");
		System.out.println("linkedUTD: " + seq.getlinkedUTD() + ", expecting true");
		System.out.println("arrayUTD: " + seq.getarrayUTD() + ", expecting false");
		System.out.println(seq.toString());
		System.out.println("Expected:");
		System.out.println("A sequence of items from the most recent array:\n[]");
		System.out.println("A sequence of items from the most recent linked list:\n(B, A, C)\n");
		
		
		System.out.println(seq.get(1));
		System.out.println("After the seq.get(1) operation: ");
		System.out.println("linkedUTD: " + seq.getlinkedUTD() + ", expecting true");
		System.out.println("arrayUTD: " + seq.getarrayUTD() + ", expecting true");
		System.out.println(seq.toString());
		System.out.println("Expected:");
		System.out.println("A sequence of items from the most recent array:\n[B, A, C]");
		System.out.println("A sequence of items from the most recent linked list:\n(B, A, C)\n");
		
		
		System.out.println(seq.set(1, "D"));
		System.out.println("After the seq.set(1, 'D') operation: ");
		System.out.println("linkedUTD: " + seq.getlinkedUTD() + ", expecting false");
		System.out.println("arrayUTD: " + seq.getarrayUTD() + ", expecting true");
		System.out.println(seq.toString());
		System.out.println("Expected:");
		System.out.println("A sequence of items from the most recent array:\n[B, D, C]");
		System.out.println("A sequence of items from the most recent linked list:\n(B, A, C)\n");
		
		
		seq.add("E");
		System.out.println("After the seq.add('E') operation: ");
		System.out.println("linkedUTD: " + seq.getlinkedUTD() + ", expecting true");
		System.out.println("arrayUTD: " + seq.getarrayUTD() + ", expecting false");
		System.out.println(seq.toString());
		System.out.println("Expected:");
		System.out.println("A sequence of items from the most recent array:\n[B, D, C]");
		System.out.println("A sequence of items from the most recent linked list:\n(B, D, C, E)\n");
		
	}
}