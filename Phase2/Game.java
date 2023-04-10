// Code by Christopher

import java.util.Arrays;

public class Game {
	public static GameMode mode;						// Stores the game mode logic object
	public static ColorSet colorset;				// Stores the game's colour set
	public static int selectedvertex = 0;		// Currently-selected vertex (used across the whole program)
	public static int chromaticnumber = 0;	// Graph's chromatic number (used across the whole program)
	
	// Initialize everything related to the game
	// Initialize all game modes and their specific components
	public Game(int m){
		switch(m){
			// To the Bitter End
			case 1:
				mode = new ToTheBitterEnd();
			
				break;
			
			// Best Upper Bound
			case 2:
				mode = new BestUpperBound();
				
				// Initialize the timer components
				// Play time: 5 seconds per vertex
				GUI.VT = new VisualTimer(5000 * Group19Phase2.G.GetVertexCount());
				
				break;
				
			// Random order
			case 3:
				mode = new RandomOrder(Group19Phase2.G.GetVertexCount());
				selectedvertex = mode.GetOrderIndex(0);
				
				System.out.println(selectedvertex);
				break;
		}
		
		// Create the game's colour set
		colorset = new ColorSet();
	}
	
	/* Set the graph's bounds after algorithmic computation.
	 This is needed here because we can't determine  what the bounds are straight after generating the graph, it takes a *little* bit of computing time. 
	 So, in each bound algorithm's class, we send the computed value here once it is done with its computation. 
	 If we have both values, we can determine if the actual chromatic number has to be calculated through backtracking.
	 
	 For clarification, the bounds are calculated by the Graph class as soon as the graph is generated in GUI.java.
	 */
	public static void GameChromaticNumber(int bound, int col){
		if (bound == 1) Group19Phase2.G.maxcliq_bound = col;
		if (bound == 2) Group19Phase2.G.wp_bound = col;
		
		// Check if we have to calculate the chromatic number through backtracking
		if (Group19Phase2.G.maxcliq_bound > 0 && Group19Phase2.G.wp_bound > 0) {
		System.out.println("Max clique bound: " + Group19Phase2.G.maxcliq_bound + ", WP bound: " + Group19Phase2.G.wp_bound);
		
			if (Group19Phase2.G.maxcliq_bound == Group19Phase2.G.wp_bound) {
				chromaticnumber = Group19Phase2.G.maxcliq_bound;	
				System.out.println("Game's chromatic number: " + chromaticnumber);
			}else{
				System.out.println("Some backtracking required.");
				
				int[][] adj = Graph.adjacency_matrix;
				int[][] vpv = Graph.vpv;
				int cs = Math.max(Group19Phase2.G.maxcliq_bound, Group19Phase2.G.wp_bound);
				
				Backtrack backtracking = new Backtrack(adj, vpv, cs);
			}
		}
	}
	
	// Which vertex should be picked next? Used by random order mode.
	public void GoToNextVertex(){
		mode.NextVertex();
	}
}