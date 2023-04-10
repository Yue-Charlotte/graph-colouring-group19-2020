import java.util.*;

public class Bipartite {
	int[] colours; 					// Colouring of each vertex (0 = uncoloured, 1 & 2 for other colours.)
	int[] unseen_vertices;	// Vertices which we haven't seen yet.
	
	int k = 1;
	
	boolean result = false;
	
	public Bipartite(Graph G){
		colours = new int[G.GetTotalVertices()];
		unseen_vertices = new int[G.GetTotalVertices()];
		
		for(int i = 0; i < unseen_vertices.length; i++){
			unseen_vertices[i] = i;
			//System.out.print(" " + unseen_vertices[i]);
		}
		
		while (unseen_vertices.length > 0) {
			int[] first_vertex_to_check = new int[1];
			first_vertex_to_check[0] = unseen_vertices[0];
			NewVertexSeen(first_vertex_to_check[0]);
		
			result = BipartiteCheck(G, first_vertex_to_check);
			
			if (!result) break;
		}
		
		if (!result) System.out.println("Graph is not bipartite");
		else System.out.println("Graph is bipartite");
	}
	
	public boolean BipartiteCheck(Graph G, int[] vertices){
		System.out.println(k + " - " + Arrays.toString(vertices));
		
		// The new array which will be checked once this iteration is done
		int amount_of_new_vertices = 0;
		
		// Assign current colour (k) to each vertex as long as it is uncoloured
		for(int i = 0; i < vertices.length; i++){
			if (colours[vertices[i]] == 0) {
				colours[vertices[i]] = k;
			}
			
			int[] connected = G.GetEdgesForVertex(vertices[i]);
			
			for(int j = 0; j < connected.length; j++){
				if (colours[connected[j]] == 0) {
					amount_of_new_vertices++;
				}
			}
		}
		
		int[] new_vertices = new int[amount_of_new_vertices];
		int new_vertices_index = 0;
		
		
		// Check if colouring is valid
		boolean invalid_colouring_detected = false;		
		
		for(int ii = 0; ii < vertices.length; ii++){
			int[] connected = G.GetEdgesForVertex(vertices[ii]);
			
			for(int jj = 0; jj < connected.length; jj++){
				System.out.println(vertices[ii] + ", " + connected[jj] + " : " + colours[vertices[ii]] + ", " + colours[connected[jj]]);
				
				if (colours[vertices[ii]] == colours[connected[jj]]){
					invalid_colouring_detected = true;
					break;
				}
				
				if (colours[connected[jj]] == 0){
					NewVertexSeen(connected[jj]);
					
					new_vertices[new_vertices_index] = connected[jj];
					new_vertices_index++;
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
			System.out.println("Vertex seen: " + v + " - " + Arrays.toString(unseen_vertices));
			
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
} 

/*
class BipartiteCheck {
	
}*/