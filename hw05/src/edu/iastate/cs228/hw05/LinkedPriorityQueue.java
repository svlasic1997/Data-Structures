package edu.iastate.cs228.hw05;

/**
 * A class of priority queues represented by a chain of linked nodes.
 * 
 * @author Scott Vlasic
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods. 
 * 2. No additional data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the source codes of provided 
 *    source codes or shown under lecture notes.
 * 7. You are not allowed to create arrays of objects and manipulate
 *    queue objects using arrays.
 *  
 *
 * DESCRIPTION:
 * A class of priority queues represented by a linked chain of nodes.
 * 
 * For details of priority queue implementation using linked chain of nodes, 
 * check slide number 14 of "queueDequePriorityQueueImplementations_part3.pdf" 
 * file under lecture notes of Friday of Week 5 on Canvas.
 */


public final class LinkedPriorityQueue<T extends Comparable<? super T>>
                                         implements PriorityQueueInterface<T>
{
   private Node firstNode; // Reference to first node of chain and the front
                           // of the priority queue, which has the highest priority
   private int  length;    // Number of entries in chain

   public LinkedPriorityQueue()
   {
      firstNode = null;
      length = 0;
   }

   public void add(T newEntry)
   {
   	
	// New node created with value to add
	Node newNode = new Node(newEntry);	
	
	// If queue is empty, add to the queue 
	if(isEmpty()){
		newNode.next = firstNode;
		firstNode = newNode;
	}
	
	else{
		
		// Variable to reference firstNode
		Node currentNode = firstNode;
		// Variable to reference the next node in the queue
		Node nextNode = currentNode.getNextNode();
		
		// Uses compareTo to see if the value being added needs to be moved up in the priority
		while(nextNode != null && currentNode.getData().compareTo(newNode.getData()) < 0){
			currentNode = nextNode;
			nextNode = currentNode.getNextNode();
			
		}
		
		// Updates the newNode and currentNode variables
		newNode.next = nextNode;
		currentNode.next = newNode;
	}
	
	// Increases length of the queue
	length++;
	
   }

   public T remove()
   {
	   T result = null;
	   Node n = firstNode;
	   
	   // If empty or firstNode is null, return null
	   if(isEmpty() || n == null){
	   	 return null;
	   }
	   	
	   // If only one value, remove the one value
	   if(n.next == null){
	     result = firstNode.data;
	     firstNode = null;
	     return result;
	   }
	   
	   // Goes through the queue sets n equal to n.next
	   while(n.next.next != null && n!= null){
		   
		   n = n.next;
	   }
	   
	   // Return result as last element in queue
	   result = n.next.data;
	   n.next = null;
	    
	    
	   return result;
   }

   public T peek()
   {
    
	   if(isEmpty()){
		   return null;
	   }
	   
	   else{
		   
		   if(firstNode.next == null){
			   
			   return firstNode.data;
			   
		   }
		   
		   while(firstNode.next != null){
			   firstNode = firstNode.next;
			   length++;
		   }
		   
		   return firstNode.data;
		   
	   }
   }

   /**
    * If queue is empty returns [].
    * Else, returns as [1, 2, 3]
    * Important: note a comma and single space before every
    * item except the last, and after last there is no space.
    * In both cases before and after square brackets there
    * is no space.
    *    
    */
   @Override
   public String toString()
   {
   	
	String s = "[";
	
	Node firstNode = this.firstNode;
	
	if(isEmpty()){
		
		return "[]";
		
	}
	
	if(length == 1){
		
		s += firstNode.data;
		firstNode = firstNode.next; // Don't know if I need this
	}
	
	
	while(firstNode != null && length > 1){
		
		s += firstNode.data;
		firstNode = firstNode.next;
		
		if(firstNode == null){
			
			break;
			
		}
		
		else{
			
			s += ", ";
			
		}
	}
	
   	return s + "]";
   }
   
   
   public boolean isEmpty()
   {
      boolean result;
            
      if (length == 0)
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      }
         
      return result;
   }

   public int getSize()
   {
      return length;
   }

   public void clear()
   {
      firstNode = null;
      length = 0;
   }
   
   
   private class Node
   {
      private T    data; // Entry in priority queue
      private Node next; // Link to next node

      private Node(T dataPortion) 
      {
         data = dataPortion;
         next = null;	
      }
      
      private Node(T dataPortion, Node nextNode) 
      {
         data = dataPortion;
         next = nextNode;	
      }
      
      private T getData()
      {
         return data;
      }
      
      private void setData(T newData)
      {
         data = newData;
      }
      
      private Node getNextNode()
      {
         return next;
      }
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      }
   }
} // end LinkedPriorityQueue