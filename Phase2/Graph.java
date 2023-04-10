// Code by Tjardo (used in phase 1), new methods by Christopher

import java.io.*;
import java.util.*;

//Graph class
public class Graph
{
    private int vertexcount;
    private List<Integer>[] adjacent;
		private int[] colours;
		
		public static int maxcliq_bound;
		public static int wp_bound;
			
		// Static variables to enable backtracking search more easily
		public static int[][] adjacency_matrix;
		public static int[][] vpv;
		
		//Constructor
    public Graph(int vCount, ColEdge[] e){
			this.vertexcount = vCount;
			
			adjacent = (List<Integer>[])new List[vCount];  
			for (int i = 0; i < vCount; i++)
			{
					adjacent[i] = new ArrayList<Integer>();
			}			
				
			for(int i = 0; i < e.length; i++){
				/* Why -1 for each u and v?
				If we keep u and v, we get an array bounds exception.
				Since we're basing our checking code on assigning each vertex to a numerical index, we need to make the data appropriate for array use.
				How can we be sure this doesn't change anything about the result?
				The actual content of the vertex doesn't really matter for our indexing approach.
				All edges are preserved, so we can use these vertex values for our calculation. */
				this.AddEdge(e[i].u-1, e[i].v-1);
			}
			
			this.colours = new int[vertexcount];
			for(int i = 0; i < vertexcount; i++){
				colours[i] = -1;
			}
			
			adjacency_matrix = GetAdjacencyMatrix();
			vpv = GetEdgesArray();
			
			// Begin chromatic number bound calculations.
			MaxCliq mc = new MaxCliq(adjacency_matrix);
			WP wp = new WP(adjacency_matrix);
    }
		
		//add edge to graph class
    public void AddEdge(int a, int b) {
			adjacent[a].add(b);
			adjacent[b].add(a);
    }
		
		//basic method for retrieving vertexcount
    public int GetVertexCount(){
      return vertexcount;
    }
		
		// Set the colour of a specific vertex
		public void SetVertexColour(int v, int c){
			if (v >= 0 && v < vertexcount){
				colours[v] = c;
				
				System.out.println("Amount of colours used so far: " + AmountOfColoursUsed() + ", Amount of bad edges: " + AmountOfBadEdges());
			}
		}
		
		// Get the colour of a specific vertex
		public int GetVertexColour(int v){
			if (v >= 0 && v < vertexcount){
				return colours[v];
			}
			
			return 0;
		}
		
		// Get total amount of bad edges
		public int AmountOfBadEdges(){
			int badedges = 0;
			
			for(int i = 0; i < vertexcount; i++){
				
				List<Integer> A = Connected(i);
				for(int j = 0; j < A.size(); j++){
					
					int vv = A.get(j);
					if (vv < i){
						break;
					}
					
					if (CheckEdgeColouring(i, vv)){
						badedges++;
					}
				}
			}
			
			return badedges;
		}
		
		// Get the total amount of colours used in the graph
		public int AmountOfColoursUsed(){
			int[] colours_used = new int[0];
			
			for(int i = 0; i < vertexcount; i++){
				if (colours[i] == -1){
					continue;
				}
				
				boolean col_seen = false;
				for(int j = 0; j < colours_used.length; j++){
					if (colours[i] == colours_used[j]){
						col_seen = true;
						break;
					}
				}
				
				if (!col_seen) {
					int[] colours_used_new = new int[colours_used.length + 1];
					
					for(int j = 0; j < colours_used.length; j++){
						colours_used_new[j] = colours_used[j];
					}
					
					colours_used_new[colours_used.length] = colours[i];
					
					colours_used = colours_used_new;
				}
			}
			
			return colours_used.length;
		}
		
		// Check if a certain colour has been used already
		public boolean HasThisColourBeenUsed(int c){
			for(int i = 0; i < vertexcount; i++){
				if (colours[i] == c){
					return true;
				}
			}
			return false;
		}
		
		// Checks both vertices of an edge to ensure they don't share the same colour
		public boolean CheckEdgeColouring(int v1, int v2){
			//if (v1 != v2 && (v1 >= 0 && v1 < vertexcount) && (v2 >= 0 && v2 < vertexcount)){
			if (v1 != v2){
				if (colours[v1] == -1 || colours[v2] == -1 || (colours[v1] == colours[v2])){
					return true;
				}
			}
			
			return false;
		}
		
		public boolean HasUncolouredVertices(){
			for(int i = 0; i < vertexcount; i++){
				if (colours[i] == -1){
					return true;
				}
			}
			
			return false;
		}

    //boolean method to check if edge exists
		public boolean HasEdge(int i, int j){
			return adjacent[i].contains(j);
		}

		//return list with edges linked to this vertex
		public List<Integer> Connected(int vertex){
			return adjacent[vertex];
		}
		
		//return an int[][] adjacency matrix of the graph
		public int[][] GetAdjacencyMatrix() {
			int[][] adjmatrix = new int[vertexcount][vertexcount];
			
			for(int i = 0; i < vertexcount; i++) {
				for(int j = 0; j < adjacent[i].size(); j++){
					adjmatrix[i][adjacent[i].get(j)] = 1;
				}
			}
			
			return adjmatrix;
		}
		
		public int[][] GetEdgesArray() {
			int[][] arr = new int[vertexcount][];
			
			for(int i = 0; i < vertexcount; i++){
				arr[i] = new int[adjacent[i].size()];
				for(int j = 0; j < adjacent[i].size(); j++){
					arr[i][j] = adjacent[i].get(j);
				}
			}
			
			return arr;
		}
		
		public String toString(){
			String s = "";
			for(int i = 0; i < vertexcount; i++){
				s += "vertex " + (i) + ", colour: " + colours[i] + ", adjacents vertices: " + adjacent[i] + "\n";
			}
			return s;
		}
}