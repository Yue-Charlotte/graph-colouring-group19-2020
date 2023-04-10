package p3;

import java.io.*;  
import java.util.*;  

/**
 * A wheel graph is obtained from a cycle graph C(n-1) by adding a new vertex. 
 * 
 * for n vertices, it has 2(n-1)edges
 * No. of edges in W(n) = No. of edges from hub to all other vertices + No. of edges from all other nodes in cycle graph without a hub.
 * = (n–1) + (n–1) = 2(n–1)
 * 
 * A simple algorithm to check if it is a wheel graph
 * 
 */

public class Wheel {

	private Graph graph;
	private int v; 
	private int e;
	private boolean isWheel;

	Wheel(Graph g){

		graph = g;
		v = graph.GetTotalVertices();
		e = graph.GetTotalEdges();

	}

	public boolean isWheel() {
		CycleDetection CD = new CycleDetection(graph);

		if(CD.hasCycle()) {
			if(e == 2*(v-1))
				isWheel = true;
		}
		
		return isWheel;
	}

}
