package edu.iastate.cs228.proj4;

import java.util.Arrays;


/**
 * 
 * @author Scott Vlasic
 *
 *
 * An entry tree class.
 *
 *
 */
public class EntryTree<K, V> 
{
 // Dummy root node. 
 // Made public for grading.
 public Node root;

 /**
  * 
  * You are allowed to add at most TWO more data fields to 
  * EntryTree class of int type ONLY if you need to.
  *  
  */
 
 /**
  * Number of items in the queue
  */
 public int num = 0;
 
 // All made public for grading.
 public class Node implements EntryNode<K, V> 
 {
  public Node child; // reference to the first child node
  public Node parent; // reference to the parent node
  public Node prev; // reference to the previous sibling
  public Node next; // reference to the next sibling
  public K key; // the key for this node
  public V value; // the value at this node

  public Node(K aKey, V aValue) 
  {
   key = aKey;
   value = aValue;
   child = null;
   parent = null;
   prev = null;
   next = null;
  }

  @Override
  public EntryNode<K, V> parent() 
  {
   // Returns the parent node
   return parent;
  }

  @Override
  public EntryNode<K, V> child() 
  {
   // Returns child of this node
   return child; 
  }

  @Override
  public EntryNode<K, V> next() 
  {
	// Returns the next sibling of this node
    return next;
  }

  @Override
  public EntryNode<K, V> prev() 
  {
   // Returns the previous sibling of this node
   return prev;
  }

  @Override
  public K key() 
  {
   // Returns non-null key for the node
   return key;
  }

  @Override
  public V value() 
  {
   // Returns the value for this node
   return value;
  }
 }

 public EntryTree() 
 {
  root = new Node(null, null);
 }

 /**
  * Returns the value of the entry with a specified key sequence, 
  * or {@code null} if this tree contains no entry with this key 
  * sequence.
  * 
  * This method returns {@code null} if {@code keyarr} is null or 
  * if its length is {@code 0}. If any element of {@code keyarr} 
  * is {@code null}, then the method throws a 
  * {@code NullPointerException}. The method returns the value of 
  * the entry with the key sequence in {@code keyarr} or {@code null} 
  * if this tree contains no entry with this key sequence. An example 
  * is given in provided sample input and output files to illustrate 
  * this method.  
  *
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public V search(K[] keyarr) 
 {

  // If keyarr is null or if its length is 0, return null
  if(keyarr == null || keyarr.length == 0){
	  return null;
  }
  
  Node currentNode = root;

  for(int i = 0; i < keyarr.length; i++){
	  
	  if(keyarr[i] == null){
		  throw new NullPointerException();
	  }
  }
  
  if(currentNode.child == null){
	  return null;
  }
  
  for(int i = 0; i < keyarr.length; i++){
	  
	  currentNode = findNode(currentNode, keyarr[i]);
	  
	  if(currentNode == null){
		  return null;
	  }
  }
  
  return currentNode.value;
  
  
  
 }

 /**
  * 
  * This method returns an array of type {@code K[]} with the longest 
  * prefix of the key sequence specified in {@code keyarr} such that 
  * the keys in the prefix label the nodes on the path from the root 
  * to a node. The length of the returned array is the length of the 
  * longest prefix.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null}, 
  * or if its length is {@code 0}. If any element of {@code keyarr} is
  * {@code null}, then the method throws a {@code NullPointerException}. 
  * A prefix of the array {@code keyarr} is a key sequence in the subarray 
  * of {@code keyarr} from index {@code 0} to any index {@code m>=0}, 
  * i.e., greater than or equal to; the corresponding suffix is a key
  * sequence in the subarray of {@code keyarr} from index {@code m+1} to
  * index {@code keyarr.length-1}. The method returns an array of type
  * {@code K[]} with the longest prefix of the key sequence specified in
  * {@code keyarr} such that the keys in the prefix are, respectively,
  * with the nodes on the path from the root to a node. The length of the
  * returned array is the length of the longest prefix. Note that if the
  * length of the longest prefix is {@code 0}, then the method returns 
  * {@code null}. This method can be used to select a shorted key sequence
  * for an {@code add} command to create a shorter path of nodes in the
  * tree. An example is given in the attachment to illustrate how this
  * method is used with the {@code #add(K[] keyarr, V aValue)} method.  
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public K[] prefix(K[] keyarr) 
 {
  // Null if keyarr is null or length is 0
  if(keyarr == null  || keyarr.length == 0){
	  return null;
  }
  
  for(int i = 0; i < keyarr.length; i++){
	  if(keyarr[i] == null){
		  return null;
	  }
  }
  
  int i;
  Node currentNode = root;
  boolean matched = false;
  int length = 0;
  
  for(i = 0; i < keyarr.length; i++){
		if(currentNode.child == null)
			break;
		currentNode = currentNode.child;
		while(currentNode != null)
		{
			if(currentNode.key.equals(keyarr[i]))
			{
				length++;
				matched = true;
				break;
			}
			currentNode = currentNode.next;
		}
		if(!matched)
			break;
		matched = false;
	}
	
  K[] returnarr = Arrays.copyOfRange(keyarr, 0, length);
  if(returnarr.length == 0) 
	  returnarr = null;
  return returnarr;
 }

 /**
  * 
  * This method returns {@code false} if {@code keyarr} is {@code null}, 
  * its length is {@code 0}, or {@code aValue} is {@code null}. If any 
  * element of {@code keyarr} is {@code null}, then the method throws a
  * {@code NullPointerException}.
  * 
  * This method locates the node {@code P} corresponding to the longest 
  * prefix of the key sequence specified in {@code keyarr} such that the 
  * keys in the prefix label the nodes on the path from the root to the node. 
  * If the length of the prefix is equal to the length of {@code keyarr}, 
  * then the method places {@code aValue} at the node {@code P} (in place of 
  * its old value) and returns {@code true}. Otherwise, the method creates a 
  * new path of nodes (starting at a node {@code S}) labelled by the 
  * corresponding suffix for the prefix, connects the prefix path and suffix 
  * path together by making the node {@code S} a child of the node {@code P}, 
  * and returns {@code true}. An example input and output files illustrate 
  * this method's operation.
  *
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @param Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public boolean add(K[] keyarr, V aValue) 
 {

  // If keyarr is null or length is 0 or if aValue is null return false
  if(keyarr == null || keyarr.length == 0 || aValue == null){
	  return false;
  }
  
  
  for(int i = 0; i < keyarr.length; i++){
	  
	  if(keyarr[i] == null){
		  throw new NullPointerException();
	  }   
  }
  
  Node currentNode = root;
  for(int i = 0; i < keyarr.length; i++){
	  
	  Node cNode = findNode(currentNode, keyarr[i]);
	  
	  if(cNode == null){
		  
		  cNode = new Node(keyarr[i], null);
		  cNode.parent = currentNode;
		  
		  if(currentNode.child == null){
			  cNode.parent.child = cNode;
		  }
		  
		  else{
			  Node sNode = currentNode.child;
			  while(sNode.next != null){
				  sNode = sNode.next;
			  }
			  sNode.next = cNode;
			  cNode.prev = sNode;
		  }
	  }
	  
	  currentNode = cNode;
	  
  }
  
  currentNode.value = aValue;
  return true;
 }

 /**
  * Removes the entry for a key sequence from this tree and returns its value
  * if it is present. Otherwise, it makes no change to the tree and returns
  * {@code null}.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null} or its
  * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
  * the method throws a {@code NullPointerException}. The method returns 
  * {@code null} if the tree contains no entry with the key sequence specified
  * in {@code keyarr}. Otherwise, the method finds the path with the key sequence,
  * saves the value field of the node at the end of the path, sets the value field
  * to {@code null}.
  * 
  * The following rules are used to decide whether the current node and higher
  * nodes on the path need to be removed. The root cannot be removed. Any node 
  * whose value is not {@code null} cannot be removed. Consider a non-root node 
  * whose value is {@code null}. If the node is a leaf node (has no children),
  * then the node is removed. Otherwise, if the node is the parent of a single
  * child and the child node is removed, then the node is removed. Finally, the
  * method returns the saved old value.
  * 
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  *   
  */
 public V remove(K[] keyarr) 
 {

  if(keyarr == null || keyarr.length == 0){
	  return null;
  }
  
  Node currentNode = root;
  
  V returnedValue;
  
  for(int i = 0; i < keyarr.length; i++){
	  if(keyarr[i] == null){
		  throw new NullPointerException();
	  }
  }
  
  for(int i = 0; i < keyarr.length; i++){
	  
	  currentNode = findNode(currentNode, keyarr[i]);
	  
  }
  
  returnedValue = currentNode.value;
  currentNode.value = null;
  removeHelper(currentNode);
  
  return returnedValue;
 }
 

 /**
  * 
  * This method prints the tree on the console in the output format 
  * shown in provided sample output file. If the tree has no entry, 
  * then the method just prints out the line for the dummy root node.
  * 
  */
 public void showTree() 
 {
  printTree(0, root);
 }
 
 
 
 /**
  * 
  * Returns all values in this entry tree together with their keys.
  * The order of outputs would be similar to level order traversal,
  * i.e., first you would get all values together with their keys in
  * first level from left to right, then second level, and so on.
  * If tree has no values then it would return {@code null}.
  *
  * For the example image given in description, the 
  * returned String[][] would look as follows:
  * 
  *  {{"IA","Grow"}, {"ISU","CS228"}}   
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.LinkedList}.
  * 
  *  
  */	
 public String[][] getAll()
 {
	 
  int i = 0;
  String str1 = "";
  java.util.LinkedList<Node> queue = new java.util.LinkedList<Node>();
  
  String [][] ret = new String[num][2];
  
  Node currentNode = root;
  queue.add(currentNode);

  while(!queue.isEmpty()){
	  
	  Node tempNode = queue.remove();
	  
	  if(tempNode.value != null){
		  ret[i][1] = tempNode.value.toString();
		  
		  Node n = tempNode;
		  
		  while(n != currentNode){
			  str1 += n.key.toString() + " ";
			  n = n.parent;
		  }
		  
		  ret[i][0] = str1;
		  i++;
		  str1 = "";
	  }
	  
	  for(Node newNode = tempNode.child; newNode != null; newNode = newNode.next){
		  queue.add(newNode);
	  }
  }
  
  if(num == 0){
	  return null;
  }
  
  return ret;
 }

 
 /**
  * Given a key, finds the node that corresponds to the specific key
  * @param key
  * Key to be found
  * @return
  * Node with the specified key
  */
 private Node findNode(Node currentNode, K key){
	 
	 if(currentNode.child == null){
		 return null;
	 }
	 
	 currentNode = currentNode.child;
	 
	 while((currentNode != null) && !currentNode.key.equals(key)){
		 currentNode = currentNode.next;
	 }
	 
	 return currentNode;
 }
 
 
 /**
  * Removes all links to a node if it can be removed. Implemented with help of TA's 
  * @param currentNode
  * Node to be checked
  */
 private void removeHelper(Node currentNode){
	 
	 if(currentNode == null){
		 return;
	 }
	 
	 if(currentNode.child == null && currentNode.value == null){
		 
		 if(currentNode.parent != null && currentNode.parent.child.equals(currentNode)){
			 
			 currentNode.parent.child = currentNode.next;
			 removeHelper(currentNode.parent);
			 
		 }
		 
		 if(currentNode.next != null){
			 currentNode.next.prev = currentNode.prev;
		 }
		 
		 if(currentNode.prev != null){
			 currentNode.prev.next = currentNode.next;
		 }
	 }
 }
 
 
 /**
  * Recursive method to help print out the tree 
  * @param levels
  * Number of levels the tree has
  * @param currentNode
  * Current Node within the tree
  */
 private void printTree(int levels, Node currentNode){
	 
	 if(currentNode == null){
		 return;
	 }
	 
	 String numLevels = "";
	 while(currentNode != null){
		 
		 for(int i = 0; i < levels; i++){
			 numLevels += "..";
		 }
		 
		 if(levels == 0){
			 System.out.println(currentNode.key + "::" + currentNode.value);
		 }
		 
		 else{
			 System.out.println("...." + numLevels + currentNode.key + "::" + currentNode.value);
		 }
		 
		 printTree(levels + 1, currentNode.child);
		 
		 currentNode = currentNode.next;
		 numLevels = "";
	 }
 }
 
 
}
