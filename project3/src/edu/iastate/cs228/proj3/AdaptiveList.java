package edu.iastate.cs228.proj3;

import java.util.Arrays;

/*
 *  @author Scott Vlasic
 *
 *
 *  An implementation of List<E> based on a doubly-linked list 
 *  with an array for indexed reads/writes
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E>
{
  public class ListNode 
  {                     
    public E data;        
    public ListNode next; 
    public ListNode prev; 
    
    public ListNode(E item)
    {
      data = item;
      next = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c)
  {
	clear();
	// Implemented using example from SimpleLinkedList lecture slide
	for(E e: c){
		add(e);
	}
	
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node 
  // from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.next = toRemove.next;
    toRemove.next.prev = toRemove.prev;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to chain after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.next = current.next;
    toAdd.next.prev = toAdd;
    toAdd.prev = current;
    current.next = toAdd;
  }

  @SuppressWarnings("unchecked")
  private void updateArray() // makes theArray up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");
    
    theArray = (E[])(toArray(toArray())); // Casted properly as seen from piazza
    
    arrayUTD = true; // Marks arrayUpToDate as true
  }

  private void updateLinked() // makes the linked list up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");

	ListNode current = head.next;
    	
    for(int i = 0; i < numItems; i++){
    	
    	current.data = theArray[i];
    	current = current.next;
    }
    
    linkedUTD = true;
  }

  @Override
  public int size()
  {
    return numItems; // Size of the list
  }

  @Override
  public boolean isEmpty()
  {
    // If number of items is 0, list is empty
	if(numItems == 0){
		return true;
	}
    return false; 
  }

  @Override
  public boolean add(E obj)
  {	  
	if(linkedUTD == false){
		updateLinked();
	}
	
	ListNode newNode= new ListNode(obj); // New Node
	
	link(tail.prev, newNode); // Adds the node to the linked list
	numItems++; // Increments size
	arrayUTD = false; // Changes arrayUpToDate to false
    return true; 
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
	
	if(linkedUTD == false){
		updateLinked();
	}
	
	if(c == null){
		throw new NullPointerException();
	}
	
	if(c.isEmpty()){
		return false;
	}
	
	for(E e: c){
		add(e);
	}
	
	
    return true; 
  }

  @Override
  public boolean remove(Object obj)
  {
	  
    // Implemented off of lecture slides with modifications
	ListNode previous;
	ListNode current;
	
	for(previous = null, current = head; current != null; previous = current, current = current.next){
		
		if(obj == current.data || obj != null && obj.equals(current.data)){
			break;
		}
	}
	
	if(current == null){
		return false;
	}
	
	if(previous != null){
		previous.next = current.next;
	}
	
	else{
		head = head.next;
	}
	
	numItems--;
    return true;
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.next;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj)
  {

	checkIndex2(pos);
	
	if(pos == 0){
		ListNode toAdd = new ListNode(obj);
		toAdd.data = obj; 
		toAdd.next = head;
		head = toAdd;
		numItems++;	
		return;
	}
	
	ListNode toAdd = new ListNode(obj);
	toAdd.data = obj;
	ListNode cur = findNode(pos);
	toAdd.next = cur.next;
	cur.next = toAdd;
	numItems++;
	
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
	  
	if(linkedUTD == false){
		updateLinked();
	}
	
	if(pos < 0 || pos > numItems){
		throw new IndexOutOfBoundsException();
	}
	
	// Implemented using code from lecture slides
	checkIndex2(pos);
	
	if(isEmpty()){
		return false;
	}
	
	int i = pos;
	for(E e: c){
		add(i++, e);
	}
	
    return true; 
  }

  @Override
  public E remove(int pos)
  {
	checkIndex(pos);
	
	if(pos==0){
		
		if(head.next == null){
			throw new NoSuchElementException();
		}
		
		E returnValue = head.data;
		head = head.next;
		numItems--;
		return returnValue;
	}
	
	ListNode cur = findNode(pos);
	ListNode temp = cur.next;
	checkNode(temp);
	cur.next = temp.next;
	numItems--;
	return temp.data;
	
  }

  @Override
  public E get(int pos)
  {
	  
	updateArray();
    return theArray[pos];
    
  }

  @Override
  public E set(int pos, E obj)
  {
	  
	updateArray();
	E temp = theArray[pos];
	theArray[pos] = obj;
	linkedUTD = false;
	return temp;
	
  } 

  /**
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  reverses the order of the elements in the 
   *  array without using any additional array, 
   *  and returns true. Note that if the array 
   *  is modified, then linkedUTD needs to be set 
   *  to false.
   */
  public boolean reverse()
  {

	// If number elements is at most 1, return false
	if(numItems <= 1){
		return false;
	}
	
	if(arrayUTD == false){
		updateArray();
	}
	
	for(int i = 0; i < numItems/2; i++){
		
		E temp = theArray[i];
		theArray[i] = theArray[numItems - i - 1];
		theArray[numItems - i - 1] = temp;
	}
	
	linkedUTD = false;
    return true;
  }

  
  /** 
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  swaps the items positioned at even index 
   *  with the subsequent one in odd index without 
   *  using any additional array, and returns true.
   *  Note that if the array is modified, then 
   *  linkedUTD needs to be set to false. 
   */
  public boolean reorderOddEven()
  {
	  
	// If number elements is at most 1, return false
	if(numItems <= 1){
		return false;
	}

	// Updates Array
	if(arrayUTD == false){
		updateArray();
	}
	
	// Loops through the array
	for(int i = 0; i < numItems; i++){
		
		// Finds the odd index
		if(i%2 != 0){
			
			// Swaps odd index with even
			E temp = theArray[i];
			theArray[i] = theArray[i-1];
			theArray[i-1] = temp;
		}
	}
	
	
	linkedUTD = false;
    return true;
  }
  
  @Override
  public boolean contains(Object obj)
  {
   if(linkedUTD == false){
	   updateLinked();
   }
   
   if(numItems == 0){
	   return false;
   }
   
   if(obj == null)
   {
	   return false;
   }
   
   // Implemented using updated code from lecture slides
   ListNode current;
   for(current = head; current != null; current = current.next){
	   
	   if(obj == current.data || obj != null && obj.equals(current.data)){
		   return true;
	   }
   }
   
   return false;
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
	  
   if(c.isEmpty()){
	   return false;
   }
   for(Object o: c){
	   if(!contains(o)){
		   return false;
	   }
   }
   return true; 
  }


  @Override
  public int indexOf(Object obj)
  {
	if(linkedUTD == false){
		updateLinked();
	}
	
	// Modified implementation of lecture slide
	ListNode current;
	int position = 0;
	for(current = head.next; current != null; current = current.next, position++){
		
		if(obj == current.data || obj != null && obj.equals(current.data)){
			
			return position;
			
		}
	}
    return -1; 
  }

  @Override
  public int lastIndexOf(Object obj)
  {
	if(linkedUTD == false){
		updateLinked();
	}
    // Modified implementation of lecture slides
	ListIterator<E> iter = listIterator(numItems);
	
	while(iter.hasPrevious()){
		
		E data = iter.previous();
		if(obj == data || obj != null && obj.equals(data)){
			return iter.nextIndex();
		}
	}
	
    return -1; 
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    
	if(linkedUTD == false){
		updateLinked();
	}
	
	boolean changed = false;
	
	ListNode temp = head.next;
	
	for(Object o : c){
		
		for(int i = 0; i < numItems; i++){
			
			if(temp.data == o){
				unlink(temp);
				numItems--;
				changed = true;
			}
			
			temp = temp.next;
		}
		temp = head.next;
		
	}
	
	return changed;
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
	
	if(linkedUTD == false){
		updateLinked();
	}
	
	boolean changed = false;
	AdaptiveListIterator iter = new AdaptiveListIterator();
	
	while(iter.hasNext()){
		
		E data = iter.next();
		if(!c.contains(data)){
			iter.remove();
			changed = true;
		}
	}
    return changed; 
  }

  @Override
  public Object[] toArray()
  {
	  
	// Updated the linkedUTD if it's false
	if(linkedUTD == false){
		
		updateLinked();
	}
	
	Object[] result = new Object[numItems];
	ListNode temp = head.next;
	
	for(int i = 0; i < numItems; i++){
		result[i] = temp.data;
		temp = temp.next;
	}

    return result; 
  }
  
  
  /**
   * In here you are allowed to use only 
   * java.util.Arrays.copyOf method.
   */
  @Override
  public <T> T[] toArray(T[] arr)
  {
	  
	if(linkedUTD == false){
		
		updateLinked();
	}
	// Implemented from SimpleLinkedList lecture slide
	if(arr.length < numItems){
		arr = Arrays.copyOf(arr, numItems);
	}
	System.arraycopy(toArray(), 0, arr, 0, numItems);
	if(arr.length > numItems){
		arr[numItems] = null;
	}
    return arr; 
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    public AdaptiveListIterator()
    {
      if ( ! linkedUTD ) updateLinked();
      index = 0; // Iterator starts at 0
      cur = head; // Current node equal to head
      last = null; // Last node equal to null
    }
    public AdaptiveListIterator(int pos)
    {
      if ( ! linkedUTD ) updateLinked();
      checkIndex2(pos);
      index = pos; // Iterator starts at the specified position
      last = null; // Last node equal to null
      cur = pos == 0 ? null : findNode(pos);
    }

    @Override
    public boolean hasNext()
    {
      if(index >= numItems){
    	  return false;
      }
      else{
    	  return true;
      }
    }

    @Override
    public E next()
    {
    
      // Implemented from lecture slide code
      if(hasNext() == false){
    	  throw new NoSuchElementException();
      }
      
      if(index >= numItems){
    	  throw new RuntimeException("index 1");
      }
      
      index++;
      
      last = cur = cur == null ? head : cur.next;
      return cur.data;
    } 

    @Override
    public boolean hasPrevious()
    {
    	
      return cur.prev != null;
      
    }

    @Override
    public E previous()
    {
      
      if(hasPrevious() == false){
    	  throw new NoSuchElementException();
      }
      
      if(index <= 0){
    	  throw new RuntimeException("index 2");
      }
      
      index--;
      last = cur;
      cur = lookPrev(cur);
      return last.data;
    }
    
    @Override
    public int nextIndex()
    {
      
    	return index;
    }

    @Override
    public int previousIndex()
    {
      
    	return index - 1;
    }

    @Override
    public void remove()
    {
    	
      if(last == null){
    	  throw new IllegalStateException();
      }
      
      if(cur == last){
    	  if(index <= 0){
    		  throw new RuntimeException("index 3");
    	  }
      }
      
      if(last == head){
    	  if(head == null){
    		  throw new NoSuchElementException();
    	  }
    	  
    	  head = head.next;
    	  numItems--;
    	  cur = null;
      }
      
      else{
    	  numItems--;
    	  if(cur == last){
    		  cur = lookPrev(cur);
    	  }
    	  
    	  cur.next = last.next;
      }
      
      last = null;
    }

    /**
     * Helper method implemented from lecture slides to look at the previous Node 
     * @param vet Node lookPrev is to be performed on
     * @return temp Node
     */
    private ListNode lookPrev(ListNode vet){
    	
    	ListNode temp;
    	
    	if(vet == head){
    		return null;
    	}
    	
    	for(temp = head; temp.next != vet; temp = temp.next){
    		
    		if(temp.next == null){
    			throw new RuntimeException();
    		}
    	}
    	
    	return temp;
    }
    
    @Override
    public void add(E obj)
    {

      if(cur == null){
    	  ListNode toAdd = new ListNode(obj);
    	  toAdd.data = obj;
    	  toAdd.next = head;
    	  head = toAdd;
    	  numItems++;
    	  last = null; 
    	  cur = head;
    	  index = 1;
    	  return;
      }
      
      ListNode added = new ListNode(obj);
      added.data = obj;
      added.next = cur.next;
      cur.next = added;
      cur = added;
      last = null;
      
      index++;
      numItems++;
    } 

    @Override
    public void set(E obj)
    {
    	
    	// Implemented from lecture slides
    	if(last == null){
    		throw new IllegalStateException();
    	}
    	
    	last.data = obj;
    }
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.next; tmp != tail; tmp = tmp.next )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   // Other options System.lineSeparator or
   // String.format with %n token...
   // Not making data field.
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.next; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.next;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
