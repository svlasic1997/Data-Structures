
package edu.iastate.cs228.hw08;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 
 * 
 * A class that implements the ADT dictionary by using a chain of nodes.
 * The dictionary is unsorted and has distinct search keys, i.e., can have 
 * duplicate values, however, those are differentiated based on their keys.
 * 
 * @author Scott Vlasic
 * 
 * NOTEs and REQUIREMENTs:
 * 
 * Exactly same as the ones listed for SortedVectorDictionary class.
 * 
 * In addition to above ANSWER the following 6 QUESTIONS, inside these 
 * comments right below each question. Figures needed to answer questions
 * 3, 4, and 5 are shown on Canvas under description of HW08.
 * 
 * =========================================================================
 * Q1. (a) What is the height of the shortest binary tree that contains 22
 *     nodes? (b) Is this tree full? (c) Is it balanced?
 *     
 * A1. (a) 5
 *     (b) No
 *     (c) Yes
 * =========================================================================
 * Q2. Consider a binary tree that has four levels.
 *     (a) What is the maximum number of nodes in this tree?
 *     (b) What is the maximum number of leaves in this tree?
 *     
 * A2. (a) 15
 *     (b) 8   
 * =========================================================================
 * Q3. Consider a traversal of a binary tree, which contains Integer data. 
 *     Suppose that visiting a node means to simply display the data in the 
 *     node. What are the results of each of the following traversals of the 
 *     binary tree shown in Figure 1.
 *     (a) Preorder
 *     (b) Postorder
 *     (c) Inorder
 *     (d) Level order
 *     
 * A3. (a) 6, 4, 2, 1, 3, 5, 8, 7, 9, 10, 11
 *     (b) 1, 3, 2, 5, 4, 9, 7, 11, 10, 8, 6
 *     (c) 1, 2, 3, 4, 5, 6, 9, 7, 8, 10, 11
 *     (d) 6, 4, 8, 2, 5, 7, 10, 1, 3, 9, 11
 *     
 * =========================================================================
 * Q4. Repeat Q3 but for the binary tree shown in Figure 2. 
 * A4. (a) 11, 8, 3, 2, 1, 5, 4, 6, 10, 9, 7
 *     (b) 2, 1, 3, 4, 6, 5, 8, 9, 7, 10, 11
 *     (c) 2, 3, 1, 8, 4, 5, 6, 11, 9, 10, 7
 *     (d) 11, 8, 10, 3, 5, 9, 7, 2, 1, 4, 6
 *  
 * =========================================================================
 * Q5. The two binary trees shown in Figures 1 and 2 contain Integer data.
 *     (a) Is the tree in Figure 1 a binary search tree? Why or why not?
 *     (b) Is the tree in Figure 2 a maxheap? Why or why not?
 *  
 * A5. (a) Yes because the nodes to the left of the root are less than the root and the nodes to the right of the root 
 * 		   are greater than the root
 *     (b) No because 6 is a descendant of 5 but since 6 is greater than 5, the tree is not a maxheap
 *     
 * =========================================================================
 * Q6. Can a binary search tree ever be a maxheap? Explain.
 * A6. A maxheap is a tree in which the descendants of a node are less than or equal to the node.  Since a binary 
 * 	   search tree the right child of a root is greater than the root itself, a binary search tree cannot be a maxheap                          
 *     
 *     
 *     
 *     
 *     
 *     
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
 private Node firstNode;   // Reference to first node of chain
 private int  numberOfEntries; 
	
 public LinkedDictionary()
 {
  firstNode = null;
  numberOfEntries = 0;
 }
	
 public V add(K key, V value)
 {
  if(Objects.isNull(key) || Objects.isNull(value))
	throw new IllegalArgumentException();
  
  V result = null;
  
  Node newNode = new Node(key, value);
  Node currentNode = firstNode;
  Node nodeBefore = null;
  
  while((currentNode != null) && !(key.equals(currentNode.getKey()))){
	  nodeBefore = currentNode;
	  currentNode = currentNode.getNextNode();
  }
  
  if((currentNode != null) && key.equals(currentNode.getKey())){
	  result = currentNode.getValue();
	  currentNode.setValue(value);
  }
  
  else{
	  
	  if(nodeBefore == null){
		  newNode.setNextNode(firstNode);
		  firstNode = newNode;
	  }
	  
	  else{
		  newNode.setNextNode(currentNode);
		  nodeBefore.setNextNode(newNode);
	  }
	  
	  numberOfEntries++;
  }
  
  return result;
 }

 public V remove(K key)
 {
	 
  if(Objects.isNull(key))
	throw new IllegalArgumentException();

  V result = null;
  Node current = firstNode;
  Node previousNode = null;
  
  while(current != null){
	  
	  if(key.equals(current.key) && current == firstNode){
		  result = current.getValue();
		  firstNode = current.next;
		  numberOfEntries--;
		  break;
	  }
	  else if(key.equals(current.key)){
		result = current.getValue();
		previousNode.next = current.next;
	  	numberOfEntries--;
	  	break;
	  }
	  else{
		  previousNode = current;
		  current = current.next;
	  }
  }
  
  return result;  
 }

 public V getValue(K key)
 {
	 
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
	   
    boolean found = false;
	Node currentNode = firstNode;
	
	while (!found && (currentNode != null))
	{
		if (key.equals(currentNode.key))
			found = true;
		else
			currentNode = currentNode.next;
	} 
	return currentNode.getValue();
  
 }

 public boolean contains(K key)
 {
  
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
  
  boolean found = false;
  Node currentNode = firstNode;
  
  while(!found && (currentNode != null)){
	  
	  if(key.equals(currentNode.key)){
		  found = true;
	  }
	  else{
		  currentNode = currentNode.next;
	  }
  }
	   
  return found; 
 }

 public boolean isEmpty()
 {
  return numberOfEntries == 0;
 }
	
 public int getSize()
 {
  return numberOfEntries;
 }

 public void clear()
 {
  numberOfEntries = 0;		
 }

 // Needs to output String representation in exact same
 // format as the one done by SortedVectorDictionary.
 public String toString()
 {
	 
  String s = "[";
  Node currentNode = firstNode;
  
  if(isEmpty()){
	  return "[]";
  }
  
  while(currentNode!= null){
	  
	  s += "(" + currentNode.getKey() + ":" + currentNode.getValue() + ")";
	  
	  if(currentNode.next != null){
		  s += ", ";
	  }
	  
	  else{
		  break;
	  }
	  
	  currentNode = currentNode.next;
  }
  
  return s + "]";
 }

 public Iterator<K> getKeyIterator()
 {
  return new KeyIterator();
 }
	
 public Iterator<V> getValueIterator()
 {
  return new ValueIterator();
 }

 private class KeyIterator implements Iterator<K>
 {
  private Node nextNode;
		
  private KeyIterator()
  {
   nextNode = firstNode;
  }
		
  public boolean hasNext() 
  {
   return nextNode != null;
  }
		
  public K next()
  {
   
   K result;
   
   if(hasNext()){
	   result = nextNode.getKey();
	   nextNode = nextNode.getNextNode();
   }
   else{
	   throw new NoSuchElementException();
   }
   return result;
  }
 } 
	
 private class ValueIterator implements Iterator<V>
 {
  private Node nextNode;
  		
  private ValueIterator()
  {
   nextNode = firstNode;
  }
		
  public boolean hasNext() 
  {
   return nextNode != null;
  }
		
  public V next()
  {
   
   V result;
   if(hasNext()){
	   result = nextNode.getValue();
	   nextNode = nextNode.getNextNode();
   }
   else{
	   throw new NoSuchElementException();
   }
   return result;
  }
 }

 private class Node
 {
  private K key;
  private V value;
  private Node next;

  private Node(K searchKey, V dataValue)
  {
   key = searchKey;
   value = dataValue;
   next = null;	
  }
		
  private Node(K searchKey, V dataValue, Node nextNode)
  {
   key = searchKey;
   value = dataValue;
   next = nextNode;	
  }
		
  private K getKey()
  {
   return key;
  }
		
  private V getValue()
  {
   return value;
  }

  private void setValue(V newValue)
  {
   value = newValue;
  }

  private Node getNextNode()
  {
   return next;
  }
		
  private void setNextNode(Node nextNode)
  {
   next = nextNode;
  }
  
  public String toString()
  {
   return "("+key+":"+value+")";	
  }
 }
}
		
