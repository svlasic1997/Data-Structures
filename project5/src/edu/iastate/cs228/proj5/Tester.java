package edu.iastate.cs228.proj5;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/**
 * @author Cory Smith
 *  
 *  I'm not really sure what to test for visitDFS since it's needed for depthFirstSearch, so I don't have any tests specifically for it
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tester {
	
	static DiGraph<String> graph = new DiGraph<>();
	static DiGraph<String> cyclicGraph = new DiGraph<>();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@BeforeClass
	public static void setup() {
		graph.addEdge("S", "A", 6);
		graph.addEdge("S", "B", 10);
		graph.addEdge("S", "G", -5);
		graph.addEdge("A", "D", 8);
		graph.addEdge("A", "E", 5);
		graph.addEdge("B", "A", -6);
		graph.addEdge("B", "E", 10);
		graph.addEdge("C", "S", 11);
		graph.addEdge("D", "G", 7);
		graph.addEdge("E", "D", 6);
		graph.addEdge("E", "G", 5);
		graph.addEdge("F", "B", -8);
		graph.addEdge("F", "C", 2);
		
		cyclicGraph.addEdge("A", "B", 1);
		cyclicGraph.addEdge("B", "C", 2);
		cyclicGraph.addEdge("C", "A", 3);
	}
	
	@Test
	public void test01_DepthFirstSearch() {
		Stack<String> order = DFS.depthFirstSearch(graph);
		assertEquals("F", order.pop());
		assertEquals("C", order.pop());
		assertEquals("S", order.pop());
		assertEquals("B", order.pop());
		assertEquals("A", order.pop());
		assertEquals("E", order.pop());
		assertEquals("D", order.pop());
		assertEquals("G", order.pop());
		assertTrue(order.empty());
		
		assertNull(DFS.depthFirstSearch(cyclicGraph));
	}
	
	@Test
	public void test02_DepthFirstSearchEx() {
		ex.expect(IllegalArgumentException.class);
		DFS.depthFirstSearch(null);
	}
	
	@Test
	public void test03_FindMaxPath() {
		Stack<String> maxPath = new Stack<>();
		assertEquals(new Integer(46), MaxPath.findMaxPath(graph, maxPath));
		assertEquals("F", maxPath.pop());
		assertEquals("C", maxPath.pop());
		assertEquals("S", maxPath.pop());
		assertEquals("B", maxPath.pop());
		assertEquals("E", maxPath.pop());
		assertEquals("D", maxPath.pop());
		assertEquals("G", maxPath.pop());
		assertTrue(maxPath.empty());
	}
	
	@Test
	public void test04_FindMaxPathEx1() {
		ex.expect(IllegalArgumentException.class);
		ex.expectMessage("null arguments");
		MaxPath.findMaxPath(graph, null);
	}
	
	@Test
	public void test05_FindMaxPathEx2() {
		ex.expect(IllegalArgumentException.class);
		ex.expectMessage("null arguments");
		MaxPath.findMaxPath(null, new Stack<String>());
	}
	
	@Test
	public void test06_FindMaxPathEx3() {
		ex.expect(IllegalArgumentException.class);
		ex.expectMessage("The graph has a cycle");
		MaxPath.findMaxPath(cyclicGraph, new Stack<String>());
	}
	
	@Test
	public void test07_FindMaxPathEx4() {
		ex.expect(IllegalStateException.class);
		ex.expectMessage("topoOrder is empty");
		MaxPath.findMaxPath(new DiGraph<String>(), new Stack<String>());
	}
	
	@Test
	public void test08_FindMaxPathEx5() {
		ex.expect(IllegalArgumentException.class);
		ex.expectMessage("maxPath is not empty");
		Stack<String> s = new Stack<>();
		s.add("q");
		MaxPath.findMaxPath(graph, s);
	}
	@Test 
	public void cyclicTest1() {
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("W", "A", 5);
		G8.addEdge("A", "L", 3);
		assertNotNull(DFS.depthFirstSearch(G8));
		G8.addEdge("L", "W", 999);
		assertNull(DFS.depthFirstSearch(G8));
	}
	
	@Test 
	public void cyclicTest2() {
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("W", "A", 1);
		G8.addEdge("A", "L", 1);
		assertNotNull(DFS.depthFirstSearch(G8));
		G8.addEdge("L", "W", 1);
		assertNull(DFS.depthFirstSearch(G8));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void cyclicTest3() {
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("W", "A", 1);
		G8.addEdge("A", "L", 1);
		G8.addEdge("L", "W", 1);
		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
	}
	
	@Test (expected=IllegalStateException.class)
	public void noEntriesTest() {
		DiGraph<String> G8 = new DiGraph<>();
		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
	}

}
