package edu.iastate.cs228.hw3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  
 * @author Scott Vlasic
 *
 */

/**
 * This class describes a circular doubly-linked list of states to represent both the OPEN and CLOSED lists
 * used by the A* algorithm.  The states on the list are sorted in the  
 * 
 *     a) order of non-decreasing cost estimate for the state if the list is OPEN, or 
 *     b) lexicographic order of the state if the list is CLOSED.  
 * 
 */
public class OrderedStateList 
{

	/**
	 * Implementation of a circular doubly-linked list with a dummy head node.
	 */
	  private State head;           // dummy node as the head of the sorted linked list 
	  private int size = 0;
	  
	  private boolean isOPEN;       // true if this OrderedStateList object is the list OPEN and false 
	                                // if the list CLOSED.

	  /**
	   *  Default constructor constructs an empty list. Initialize heuristic. Set the fields next and 
	   *  previous of head to the node itself. Initialize instance variables size and heuristic. 
	   * 
	   * @param h 
	   * @param isOpen   
	   */
	  public OrderedStateList(Heuristic h, boolean isOpen)
	  {
		  //TODO
		  State.heu = h;   // initialize heuristic used for evaluating all State objects. 
		  size = 0;
		  this.isOPEN = isOpen;
		  
	  }

	  
	  public int size()
	  {
		  return size; 
	  }
	  
	  
	  /**
	   * A new state is added to the sorted list.  Traverse the list starting at head.  Stop 
	   * right before the first state t such that compare(s, t) <= 0, and add s before t. If 
	   * no such state exists, simply add s to the end of the list. 
	   * 
	   * Precondition: s does not appear on the sorted list. 
	   * 
	   * @param s
	   */
	  public void addState(State s)
	  { 
		  State temp = s;
		  
		  if(head != null){
			  head.previous = temp;
		  }
		  
		  head = temp;
		  size++;
	  }
	  
	  
	  /**
	   * Conduct a sequential search on the list for a state that has the same board configuration 
	   * as the argument state s.  
	   * 
	   * Calls compareStates(). 
	   * 
	   * @param s
	   * @return the state on the list if found
	   *         null if not found 
	   */
	  public State findState(State s)
	  {
		  // TODO 
		  return null; 
	  }
	  
	  
	  /**
	   * Remove the argument state s from the list.  It is used by the A* algorithm in maintaining 
	   * both the OPEN and CLOSED lists. 
	   * 
	   * @param s
	   * @throws IllegalStateException if s is not on the list 
	   */
	  public void removeState(State s) throws IllegalStateException
	  {
		  // TODO 
	  }
	  
	  
	  /**
	   * Remove the first state on the list and return it.  This is used by the A* algorithm in maintaining
	   * the OPEN list. 
	   * 
	   * @return  
	   */
	  public State remove()
	  {
		  // TODO
		  return null; 
	  }
	  
	  
	  /**
	   * Compare two states depending on whether this OrderedStateList object is the list OPEN 
	   * or the list CLOSE used by the A* algorithm.  More specifically,  
	   * 
	   *     a) call the method compareTo() of the State if isOPEN == true, or 
	   *     b) create a StateComparator object to call its compare() method if isOPEN == false. 
	   * 
	   * @param s1
	   * @param s2
	   * @return
	   */
	  private int compareStates(State s1, State s2)
	  {
		  // TODO 
		  
		  return 0; 
	  }
}
