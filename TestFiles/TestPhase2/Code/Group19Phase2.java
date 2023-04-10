import java.io.*;
import java.util.*;

public class Group19Phase2 {
	public final static boolean DEBUG = true;		
	
	public static void main( String args[] ) {
		// test values for random graph generation
		int n = 100;
		int m = 30;
		ColEdge[] e = GenerateGraph.generateRandomGraph(n, m);

		Graph G = new Graph(n);
		for(int i = 0; i < e.length; i++){
			/* Why -1 for each u and v?
			If we keep u and v, we get an array bounds exception.
			Since we're basing our checking code on assigning each vertex to a numerical index, we need to make the data appropriate for array use.
			How can we be sure this doesn't change anything about the result?
			The actual content of the vertex doesn't really matter for our indexing approach.
			All edges are preserved, so we can use these vertex values for our calculation. */
			G.AddEdge(e[i].u-1, e[i].v-1);
		}

		ChromaticNumber.calculateChromaticNumber(G, n, m);
	}

	//-----------------------------------------------------------------------------------------------------------------
	// PHASE 1 CODE
	//
}
