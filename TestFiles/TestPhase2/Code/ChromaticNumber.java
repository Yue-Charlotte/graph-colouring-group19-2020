import java.io.*;
import java.util.*;

public class ChromaticNumber {
	public static int upper; //upperbound
	public static int lower; //lowerbound
	public static int chrom; //closest to chromatic number
	
	public static int calculateChromaticNumber(Graph G, int n, int m){
		
		// Set a timer to post our results every 10 seconds (Tjardo did all the timer black magic)
		Timer timer = new Timer();
		timer.schedule(new TimerClass(), 2000, 10000);
				
		/* Create the Graph using the graph class, then fill it with the edges we read from the textfile.
		This simplifies some aspects of the algorithm, such as getting the vertex count and checking if two vertices share an edge. */
		
		// Using the bound methods to quickly check the lower and the upper bound.
		lower = CalculateLower(n, m, false);
		upper = CalculateUpper(n, m, false);
		
		// Print the starting values
		System.out.println("Starting boundaries: ");
		System.out.println("Lower bound: " + lower);
		System.out.println("Upper bound: " + upper);
		
		// We start at our upper bound, then going down until we potentially reach our lower bound.
		// Meaning first we attempt CheckForSolution with all colours, then all colours - 1, all colours - 2, ..., until we reach the lower bound determined at the start.
		for(int j = upper; j > lower; j--){
			CheckForSolution(G, j);
		}
		
		// If program has finished, stop the timer and print final results.
		timer.cancel();
		System.out.println("Final values: ");
		System.out.println("Lower bound: " + lower);
		System.out.println("Upper bound: " + upper);
		System.out.println("Chromatic: " + chrom);
		System.out.println();	
		
		return chrom;
	}
	
	/**
	* This method attempts to see if a graph G can be coloured with k colours.
	* Here we also build our vertex colours array, where we store the "colour" of each vertex as a number.
	* The array is initialized to 0. In this case, 0 means that a given vertex has not been assigned a colour yet.
	* With this, we can pass our colours array on to vertexCheck, which does the actual checking.
	* One vertexCheck is done, we set the upper bound and the chromatic number (in case of success, otherwise we set the lower bound and the chromatic number.)
	*
	* @param  G 	The graph we are checking.
	* @param  k 	The maximum amount of colours we will attempt for this graph.
	*/
	public static void CheckForSolution(Graph G, int k){
		int v = G.GetVertexCount();
		int[] v_colours = new int[v];
		
		if (vertexCheck(G, k, v_colours, 0)){ // Starting with 0, i.e. the first vertex in our graph.
			upper = k;
			chrom = k;
		}else{			
			chrom = k + 1; // If no solution is found for this value then previous k = chromatic number
			lower = k + 1; // Update lower bound as well
		}
	}

	/**
	* Our main recursive method.
	* Important to note: Our algorithm is essentially what's commonly called "backtracking".
	* This means we're solving it recursively, building the solution one piece at a time.
	* By doing this, we eliminate the solutions which do not work/meet our expectations.
	* The comments within explain everything.
	*
	* @param  G 	The graph we are checking.
	* @param  k	 	The maximum amount of colours we will attempt for this graph.
	* @param  v_colours[] Our colour list, as described in method 1.
	* @param 	v		The current vertex we're checking.
	*/
	public static boolean vertexCheck(Graph G, int k, int v_colors[], int v) {
		// Have we coloured all vertices? Return true.
		if (v == G.GetVertexCount())
			return true;

		// try different colors for current vertex v, starting from 1 to maximum for this attempt, k
		for (int cr = 1; cr <= k; cr++) {
			// Check if assignment of colour cr to v is valid
			if (colourCheck(G, v, v_colors, cr)) {
				v_colors[v] = cr;
					// Recur to assign colours to rest of the vertices
				if (vertexCheck(G, k, v_colors, v + 1)) {
					return true;
				}

				// If assigning colour cr doesn't lead to a solution then remove
				v_colors[v] = 0;
			}
		}

		// Default case. No colour was assigned to vertex v, so we return false.
		return false;
	}
	
	/**
	* This method checks if a certain colouring is valid. 
	* We loop through each vertex in the graph, and check it in relation to the input vertex v.
	* If v and another vertex in the Graph share an edge, and they are the same colour, we find that the given colouring is invalid.
	* If they don't share the same edge, or if they do and the other vertex doesn't have the colour k which we are attempting to use, then the given colouring is valid.
	*
	* @param  G The graph we are checking.
	* @param  v The current vertex index which we are checking.
	* @param  v_colours[] Our colour list.
	* @param  k The current colour we are checking.
	* @return Boolean, whether the attempted colouring is valid or not.
	*/
	public static boolean colourCheck(Graph G, int v, int[] v_colours, int k){
		for(int i = 0; i < G.GetVertexCount(); i++){
			if (G.HasEdge(v, i) && k == v_colours[i]){
				return false;
			}
		}
		return true;
	}
				
	/**
	* First it checks if the graph has an even or odd number of vertices
	* Then it checks if the graph has at least 1 edge > because this will mean at least 2 colours
	* Then it will do a calculation based on the total amount of edges.
	* Then it will find the optimal way of distributing these edges and checking how many colours is needed to complete this graph in a valid way
	* Then the lower bound will be at least this number
	*
	* @param  v amount of vertices
	* @param  e amount of edges
	* @param  even is the amount of vertices even?
	* @return Upper bound of the chromatic number for this graph
	*/
	public static int CalculateLower(int v, int e, boolean even)
	{
		int low = 0;
		if(v % 2 == 0)
			even = true;

		//lower bound
		if(e > 1)
			low = 2; //if an edge exits, lower bound is always at least 2

		if(even)
		{
			if(e > ((v/2) - 1) * v)
				low = v/2;
		}
		else
		{
			if(e > (((v-1)/2) * (v-1)) ) 
				low = v/2;
		}     
		return low;
	}

	/**
	* Check what the biggest completed graph (k) could be using the amount of edges that is given
	* Then the upper bound will be <= k vertices
	*
	* @param  v amount of vertices
	* @param  e amount of edges
	* @param  even is the amount of vertices even?
	* @return Upper bound of the chromatic number for this graph
	*/
	public static int CalculateUpper(int v, int e, boolean even)
	{      
		int up = v;
		//upper bound
		for(double i = 0; i < v; i++)
		{
			if(e > ( (i-1) * (i/2) ))
			{
				up = (int)i;
			}
		}
		return up;
	}

}//end of class

// Timer class for our results so the terminal does not get spammed.
class TimerClass extends TimerTask
{
    public void run()
    {
			// Print these 3 results every scheduled step in the timer
			System.out.println("Updated values: ");
			System.out.println("Lower bound: " + ChromaticNumber.lower);
			System.out.println("Upper bound: " + ChromaticNumber.upper);
			System.out.println("Chromatic: " + ChromaticNumber.chrom);
			System.out.println();
    }
}