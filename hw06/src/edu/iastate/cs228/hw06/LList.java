package edu.iastate.cs228.hw06;


/**
   A class that implements the ADT list by using a chain of
   linked nodes that has a head reference.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class LList<T> extends CustomClass<T>
{
	private Node firstNode;            // Reference to first node of chain
	private int  numberOfEntries; 

	public LList()
	{
		initializeDataFields();
	} // end default constructor
   
	public void clear()
	{
		initializeDataFields();
	} // end clear
   
	public void add(T newEntry) 	      // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else                              // Add to end of non-empty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if	
		
		numberOfEntries++;
	}  // end add

   public void add(int newPosition, T newEntry)
	{
 		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node(newEntry);
         
			if (newPosition == 1)                  // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else									         // Case 2: list is not empty
			{                                      // and newPosition > 1
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
         
			numberOfEntries++;
		}
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   } // end add

	public T remove(int givenPosition)
	{
      T result = null;                           // Return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();

         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode(); // Remove entry
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();     // Save entry to be removed
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);   // Remove entry
         } // end if

         numberOfEntries--;                      // Update count
         return result;                          // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	public T replace(int givenPosition, T newEntry)
	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

   public T getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
                                             
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
      boolean result;

      if (numberOfEntries == 0) // Or getLength() == 0
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      } // end if

      return result;
   } // end isEmpty
	
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
		firstNode = null;
		numberOfEntries = 0;
   } // end initializeDataFields
	
   // Returns a reference to the node at a given position.
   // Precondition: The chain is not empty;
   //               1 <= givenPosition <= numberOfEntries.	
	private Node getNodeAt(int givenPosition)
	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
      
		return currentNode;
	} // end getNodeAt

	private class Node
	{
      private T    data; // Entry in list
      private Node next; // Link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;	
      } // end constructor

      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;	
      } // end constructor

      private T getData()
      {
         return data;
      } // end getData

      private void setData(T newData)
      {
         data = newData;
      } // end setData

      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node

	@Override
	public void addFirst(T newEntry) {
		
		Node newNode = new Node(newEntry); // Create new Node
		
		// If the list is empty, the head is the newNode
		if(firstNode == null){
			firstNode = newNode;
		}
		
		newNode.next = firstNode; // Set newNode.next as the current head
		firstNode = newNode; // Set the head equal to the newNode
		numberOfEntries++; // Increase the size 
	}

	@Override
	public void addLast(T newEntry) {
		
		Node newNode = firstNode; // Create new Node with reference to firstNode
		
		// Loop to find the last Node of the list
		while(newNode.next != null){
			newNode = newNode.next;
		}
		
		// Adds the element to the back of the list
		newNode.next = new Node(newEntry);
		newNode = newNode.next;
		numberOfEntries++; // Increment the size
	}

	@Override
	public T removeFirst() {
		
		// If empty, throw the exception
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		
		Node firstNode = this.firstNode; // Takes the value of the firstNode
		this.firstNode = firstNode.next; // Updates firstNode
		numberOfEntries--; // Decrements size
		
		return firstNode.getData();
	}

	@Override
	public T removeLast() {
		
		// If empty, throw the exception
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		
		
		Node firstNode = this.firstNode; // Takes the value of the firstNode
		Node previousNode = null; // Node to reference the previousNode
		
		// Loop to find the lastNode of the list
		while(firstNode.next != null){
			previousNode = firstNode;
			firstNode = firstNode.next;
		}
		
		previousNode.next = null; // Sets the next node to null
		numberOfEntries--; // Decrements size
		
		return firstNode.getData();
	}

	@Override
	public T getFirst() {
		
		// If empty, throw the exception
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		// Return the first Node
		return firstNode.data;
	}

	@Override
	public T getLast() {
		
		// If empty, throw the exception
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
			
		}
		
		Node firstNode = this.firstNode; // Takes the value of the firstNode
		
		// Loop to find the lastNode of the list
		while(firstNode.next != null){
			firstNode = firstNode.next;
		}
		
		return firstNode.getData();
	}

	@Override
	public void moveToEnd() {
		
		T firstNode = this.firstNode.getData(); // Stores the data of the firstNode
		remove(this.firstNode.getData()); // Removes the firstNode reference 
		
		add(numberOfEntries + 1, firstNode); // Adds the data of firstNode to the end 
		
	}

	@Override
	public boolean remove(T anEntry) {
		
		Node firstNode = this.firstNode; // Takes value of firstNode
		
		// Loops through to find where firstNode's data is equal to the entry
		for(int i = 1; i < numberOfEntries; i++){
			
			// If they match, remove the entry and return true
			if(firstNode.getData().equals(anEntry)){
				remove(i);
				return true;
			}
			
			firstNode = firstNode.next; // Updates firstNode
		}
		return false; // False if not found
	}

	@Override
	public int getPosition(T anEntry) {
		
		int index = 1; // Position tracker
		Node firstNode = this.firstNode; // Takes the value of firstNode
		
		// Loops through the list to see where in the list anEntry is found
		while(firstNode != null){
			
			if(firstNode.getData().equals(anEntry)){
				
				return index;
			}
			
			index++;
			firstNode = firstNode.next;
		}
		return -1; // If it's not found, return -1
	}
} // end LList



