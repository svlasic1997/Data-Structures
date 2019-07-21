package edu.iastate.cs228.proj5;
/*
 *  @author Scott Vlasic
 *  
 *  
 *  
 */

import java.util.HashMap;
import java.util.Stack;

public class MaxPath
{
  /**
   *
   * If G is null or maxPath is null, then it throws IllegalArgumentException
   * with the message "null arguments". If maxPath is not empty, then it 
   * throws IllegalArgumentException with the message "maxPath is not empty".
   *
   * This method calls depthFirstSearch(...) in the class DFS on G, and saves 
   * the reference to a stack of vertices from depthFirstSearch(...) into the 
   * variable, say topoOrder, of type Stack<V>. If topoOrder is null, then it 
   * throws IllegalArgumentException with the message "The graph has a cycle". 
   * If topoOrder is empty, then it throws IllegalStateException with the 
   * message "topoOrder is empty".
   *
   * Then it creates a dist map and a pred map (see lecture code on Dijkstra's
   * algorithm under week 13 of Lecture Notes). And it sets the value to 0 
   * for each vertex in the dist map, 
   * and sets the value to null for each vertex in the pred map.
   *
   * For each vertex u in the stack topoOrder, removes u from the stack, and 
   * for each edge from u to v, if the cost of the path to v via vertex u is 
   * larger than the current cost of v (given by dist.get(v)), then updates 
   * the current cost of v with the larger cost and sets the value of v to u 
   * in the pred map (see lecture code on Dijkstra's algorithm, i.e., 
   * DiGraph.java).
   *
   * Let variable, say score, of type Integer be the maximum distance of any 
   * path seen so far and let variable, say end, of type V be the ending vertex 
   * of a path with the distance score. Initially, score is set to 0 and end to 
   * null. Whenever the distance of a new path ending at vertex v is larger than
   * score, this method, sets score to the larger distance and sets end to v.
   *
   * At the end of this method, score is the maximum distance of all paths in
   * the graph and a path with this maximum distance ends at the vertex end.
   * Uses the pred map to generate each vertex in this path in reverse order,
   * starting at the vertex end, and places the vertices in the stack maxPath
   * with the stack top being the first vertex in this path. Note that the pred
   * value for the first vertex is null.
   * 
   * 
   */
  public static <V> Integer findMaxPath(DiGraph<V> G, Stack<V> maxPath)
  {
	  
	Integer score = 0;
	V end = null;
	
	if(G == null || maxPath == null){
		throw new IllegalArgumentException("null arguments");
	}
	
	if(!maxPath.isEmpty()){
		throw new IllegalArgumentException("maxPath is not empty");
	}
	
	Stack<V> topoOrder = DFS.depthFirstSearch(G);
	
	if(topoOrder == null){
		throw new IllegalArgumentException("The graph has a cycle");
	}
	
	if(topoOrder.isEmpty()){
		throw new IllegalStateException("topoOrder is empty");
	}
	
	HashMap<V, Integer> dist = new HashMap<V, Integer>();
	HashMap<V, V> pred = new HashMap<V, V>();
	
	// Sets value to 0 for each vertex in dist and null for each vertex in pred
	for(V w : G.vertices()){
		dist.put(w, 0);
		pred.put(w, null);
	}
	
	
	while(!topoOrder.isEmpty()){
		
		V pair = topoOrder.pop();
		for(Edge<V, Integer> e: G.adjacentTo(pair)){
			
			int vdistance = dist.get(e.getVertex());
			int udistance = dist.get(pair);
			
			if((e.getCost() + udistance) > vdistance){
				dist.put(e.getVertex(), udistance + e.getCost());
				pred.put(e.getVertex(), pair);
			}
			
			if(dist.get(e.getVertex()) > score){
				score = dist.get(e.getVertex());
				end = e.getVertex();
			}
		}
	}
	
	V added = end;
	
	while(true){
		maxPath.push(added);
		added = pred.get(added);
		
		if(pred.get(added) == null){
			maxPath.push(added);
			break;
		}
	}
	
	return score;
  }
}
