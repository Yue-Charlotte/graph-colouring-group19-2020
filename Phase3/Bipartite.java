import java.util.*;

/* 
	A basic rundown of how this works:
	
	We have a variable k, which is the current colouring.
	If a node has colouring 0, it's simply uncoloured.
	The two actual colours are represented by k = 1 and k = 2.
	
	An array of unseen vertices is made which will track which vertices have been checked yet.
	
	BipartiteCheck receives a set of nodes N to colour. At first, this set N will consist of a single starting node. 
	Every node of this set N gets coloured with the current k value.
	Then, the algorithm loops through every node in N and loops through every connected vertex to each node to check if the colouring is valid. As soon as the algorithm sees a connection like 1 - 1 or 2 - 2, it breaks from all loops. 
	As the algorithm loops through every connected vertices, these are removed from the unseen vertices array.
	If it finishes these loops without detecting an invalid colouring, a new set N' is built from the connected nodes of each node in N where the colouring is 0, so uncoloured.
	This new set N' is passed into BipartiteCheck, which checks this new set.
	If the set N' is empty, then every connected node has been checked and the subgraph must be bipartite, since the loops didnt give a false result.
	
	With the unseen vertices array, alternate subgraphs are accounted for. Once BipartiteCheck has finished one subgraph, it moves onto the next, building from the first node in the unseen vertices array.
	
	The way the algorithm propagates through the graph is similar to a breadth-first search.
	
	TODO: Finish commenting
*/

public class Bipartite {
	int[] colours; 					// Colouring of each vertex (0 = uncoloured, 1 & 2 for other colours.)
	int[] unseen_vertices;	// Vertices which we haven't seen yet.
	
	int k = 1;
	
	boolean result = false;
	
	static final boolean debug = true;
	
	public Bipartite(Graph G){
		colours = new int[G.GetTotalVertices()];
		unseen_vertices = new int[G.GetTotalVertices()];
		
		for(int i = 0; i < unseen_vertices.length; i++){
			unseen_vertices[i] = i;
		}
		
		while (unseen_vertices.length > 0) {
			// Build the array which gets passed onto the BipartiteCheck method.
			// This array has 1 element because we begin from a single node.
			int[] first_vertex_to_check = new int[1];
			first_vertex_to_check[0] = unseen_vertices[0];
			
			// Mark the first node as seen
			NewVertexSeen(first_vertex_to_check[0]);
			
			// Run the check
			result = BipartiteCheck(G, first_vertex_to_check);
			
			if (!result) break;
		}
	}
	
	
	
	public boolean BipartiteCheck(Graph G, int[] vertices){
		
		// The new array which will be checked once this iteration is done
		int amount_of_new_vertices = 0;
		
		int[] new_vertices = new int[0];
		
		// Assign current colour (k) to each vertex as long as it is uncoloured
		for(int i = 0; i < vertices.length; i++){
			if (colours[vertices[i]] == 0) {
				colours[vertices[i]] = k;
			}
			
			int[] connected = G.GetEdgesForVertex(vertices[i]);
			
			for(int j = 0; j < connected.length; j++){
				// System.out.print(" v " + connected[j] + " has colour " + colours[connected[j]]);
				
				if (colours[connected[j]] == 0) {
					
					boolean already_in_new_array = false;
					
					for(int h = 0; h < new_vertices.length; h++){
						if (new_vertices[h] == connected[j]) {
							already_in_new_array = true;
							break;
						}
					}
					
					if (!already_in_new_array) new_vertices = AddToArray(new_vertices, connected[j]);
					//System.out.println(Arrays.toString(new_vertices));
				}
			}
		}
		
		int new_vertices_index = 0;
		
		
		// Check if colouring is valid
		boolean invalid_colouring_detected = false;		
		
		for(int ii = 0; ii < vertices.length; ii++){
			int[] connected = G.GetEdgesForVertex(vertices[ii]);
			
			for(int jj = 0; jj < connected.length; jj++){
				// System.out.println(vertices[ii] + ", " + connected[jj] + " : " + colours[vertices[ii]] + ", " + colours[connected[jj]]);
				
				if (colours[vertices[ii]] == colours[connected[jj]]){
					invalid_colouring_detected = true;
					break;
				}
			}
			
			if (invalid_colouring_detected) break;
		}
		
		
		if (k == 1) k = 2;
		else k = 1;
		
		if (invalid_colouring_detected) return false;
		
		if (new_vertices.length > 0) return BipartiteCheck(G, new_vertices);
		
		return true;
		
	}
	
	
	
	// Have we spotted a vertex that hasn't been seen previously? If so, remove it (vertex v) from the array
	public void NewVertexSeen(int v){
		if (unseen_vertices.length > 0){
			// System.out.println("Vertex seen: " + v + " - " + Arrays.toString(unseen_vertices));
			
			int[] unseen_vertices_temp = new int[unseen_vertices.length - 1];
			
			int index = 0; // Index of where to put 
			for(int i = 0; i < unseen_vertices_temp.length; i++){
				
				if (unseen_vertices[i] != v){
					unseen_vertices_temp[index] = unseen_vertices[i];
					index++;
				}
			}
			
			// Replace the original array
			unseen_vertices = unseen_vertices_temp;
		}
	}
	
	
	
	// Add new element to array
	public int[] AddToArray(int[] arr, int i){
		// Make a new array 1 element bigger than input arr
		int[] temp = new int[arr.length + 1];
		
		// Copy all elements from previous array
		System.arraycopy(arr, 0, temp, 0, arr.length);
		
		// Append new element at the end
		temp[temp.length - 1] = i;
		
		return temp;
	}
} 

/*
class BipartiteCheck {
	
}*/