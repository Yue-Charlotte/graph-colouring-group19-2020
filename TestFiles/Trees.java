package p3;

import java.io.*;  
import java.util.*;  

/**
 * An undirected graph is a tree when the following two conditions are true:
 * The graph is a connected graph.
 * The graph does not have a cycle.  
 */


public class Trees  {  

	private Graph graph;
	private int v; 
	private boolean isTree;
	private boolean[] VisitedVertices;

	Trees(Graph g) { 
		graph = g;
		v = graph.GetTotalVertices();
	}  

	//the same dfs from CycleDetection Class
	public boolean depthFirstSearch(int v, int parent) {

		// set the current node as visited 
		VisitedVertices[v] = true;

		// recursion for all the vertices adjacent to this vertex 
		for (int i : graph.Connected(v)) {
			// if an adjacent is not visited, then go recursion for that adjacent 
			if (!VisitedVertices[i]) {
				if (depthFirstSearch(i, v))
					return true;
				//if an adjacent is visited, and not parent of current vertex, then there is a cycle. 
			} else {
				if (i != parent) {
					return true;
				}
			}
		}
		return false;
	}

	// Check and returns true if the given graph is a tree, else false.  
	public boolean isTree() {
		isTree = true;

		//set all the vertices as not visited (java false as default)  
		VisitedVertices = new boolean[v]; 

		//if it has a cycle then it is not a tree graph
		if (depthFirstSearch(0,-1)) {
			isTree = false;
		}
		
		// check if we visited all the vertices
		// it is not a tree graph if it is not connected 
		for (int u = 0; u < v; u++)  {
			if (!VisitedVertices[u])  {
				isTree = false;  
			}
		}
		return isTree;  
	}  
}  

