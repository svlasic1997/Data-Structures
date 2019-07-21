package edu.iastate.cs228.hw04;/** *  * @author Scott Vlasic *  * NOTE:  * 0. Put your Firstname and Lastname after above author tag. * 	  Make sure that in both cases the first letter is uppercase *    and all others are lowercase (and a space). * 1. You are allowed to create and use your own private helper methods.  * 2. No additional data fields can be introduced. * 3. No custom classes of your own can be introduced or used. * 4. Import statements are not allowed. * 5. Fully qualified class names usage is not allowed. * 6. You are allowed to reuse any part of the source codes provided *    or shown in lecture notes of week 5 (or before). * 7. You are not allowed to create arrays of objects and manipulate *    queue objects using arrays.  * * * DESCRIPTION: * A class that implements the ADT deque by using a "circular doubly linked chain" of  * nodes. In doubly linked chain, the first and last nodes each contain one null  * reference, since the first node has no previous node and the last node has no node  * after it. (For details of doubly linked chain implementation of deque, check slide  * number 3 of "queueDequePriorityQueueImplementations_part3.pdf" file under lecture  * notes of Friday of Week 5 on Canvas.) In a "circular doubly linked chain", the first * node references the last node, and the last node references the first. Only one * external reference is necessary - a reference to the first node - since you can * quickly get to the last node from the first node.  *  */public class CircularDoublyLinkedDeque<T> implements DequeInterface<T> {	private DLNode firstNode; // References node for front of deque	public CircularDoublyLinkedDeque() 	{		firstNode = null;	} // end default constructor	public void addToBack(T newEntry) 	{		DLNode n = new DLNode(firstNode, newEntry, null);				if(isEmpty()){						firstNode = n;					}				else{						firstNode.previous.setNextNode(n);					}				firstNode.previous = n;	}	public void addToFront(T newEntry) 	{		DLNode n = new DLNode(null, newEntry, firstNode);				if(isEmpty()){						firstNode = n;			n.setPreviousNode(firstNode);					}				else{						if(firstNode.getNextNode() == null){				firstNode.setPreviousNode(n);				n.setPreviousNode(firstNode);			}						else{								n.setPreviousNode(firstNode.getNextNode());				firstNode.setPreviousNode(n);			}					}				firstNode = n;			}	public T getFront() 	{				T firstNode = null;				if(isEmpty()){						throw new EmptyQueueException();		}				else{						firstNode = this.firstNode.getData();		}				return firstNode;			}	public T getBack() 	{		T lastNode = null;				if(isEmpty()){						throw new EmptyQueueException();					}				else if (firstNode.getNextNode() == null){						lastNode = firstNode.getData();					}				else{						lastNode = firstNode.getPreviousNode().getData();					}				return lastNode;	}	public T removeFront() 	{				if(isEmpty()){						throw new EmptyQueueException();		}				T front = getFront();				assert firstNode != null;		if(firstNode.getNextNode() == null){						firstNode.setNextNode(firstNode);					}				firstNode = firstNode.getNextNode();				if(firstNode.getNextNode() == firstNode){						firstNode = null;		}				else{						firstNode.setPreviousNode(null);					}						return front;	}	public T removeBack() 	{				if(isEmpty()){						throw new EmptyQueueException();		}				T back = getBack();				firstNode.setPreviousNode(firstNode.previous.previous);				return back;	}	public void clear() 	{		firstNode = null;	}	public boolean isEmpty() 	{		if(firstNode == null){						return true;		}				return false;	}		private class DLNode	{		private T      data;  	 // Deque entry		private DLNode next;  	 // Link to next node		private DLNode previous; // Link to previous node		private DLNode()		{			this(null, null, null);		} // end default constructor		private DLNode(T dataPortion)		{			this(null, dataPortion, null);		} // end constructor		private DLNode(DLNode previousNode, T dataPortion, DLNode nextNode)		{			data = dataPortion;			next = nextNode;				previous = previousNode;		} // end constructor		private T getData()		{			return data;		} // end getData		private void setData(T newData)		{			data = newData;		} // end setData		private DLNode getNextNode()		{			return next;		} // end getNextNode		private void setNextNode(DLNode nextNode)		{			next = nextNode;		} // end setNextNode		private DLNode getPreviousNode()		{			return previous;		} // end getPreviousNode		private void setPreviousNode(DLNode previousNode)		{			previous = previousNode;		} // end setPreviousNode	} // end DLNode} // end CircularDoublyLinkedDeque