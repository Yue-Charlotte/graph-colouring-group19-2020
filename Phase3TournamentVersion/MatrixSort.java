import java.util.*;

public class MatrixSort {
	private static final boolean DEBUG = false;
	private int[][] converted_adj_matrix;
	
	// Constructor class
	public MatrixSort(int[][] adj_matrix){
		// Scan the matrix and get an array with the degrees, where deg[i][0] == vertex number & deg[i][1] == degree (amount of edges)
		int[][] degrees = ScanMatrix(adj_matrix);
		
		if (DEBUG) System.out.println("Before sort: " + Arrays.deepToString(degrees));
		
		// Sorting step
		Sort(degrees);
		
		if (DEBUG) System.out.println("After sort: " + Arrays.deepToString(degrees));
		
		// Conversion step
		converted_adj_matrix = ConvertAdjMatrix(adj_matrix, degrees);
		
		if (DEBUG) System.out.println("After conversion: " + Arrays.deepToString(converted_adj_matrix));
	}
	
	
	// SORTING STUFF
	
	// Scan the matrix, return an array which stores the vertex number and the degree
	public int[][] ScanMatrix(int[][] adj_matrix){
		int[][] deg_array = new int[adj_matrix.length][2];
		
		for(int i = 0; i < adj_matrix.length; i++){
			int degree = 0;	// How many 1s can we find in a specific row?
			
			for(int j = 0; j < adj_matrix[i].length; j++){
				if (adj_matrix[i][j] == 1) {
					degree++;
				}
			}
			
			deg_array[i][0] = i;			// Store the vertex number we just checked (useful for conversion later on)
			deg_array[i][1] = degree;	// Store the vertex's degree
		}
		
		return deg_array;
	}
	
	// Perform the sort (based on second element)
	public void Sort(int[][] arr){
		int i = 0;
		while (i < arr.length){
			// System.out.println(i + " : " + Arrays.deepToString(degrees));
			if (CheckSwap(arr, i)) {
				i = -1;
			}
			
			if (i != arr.length){
				i++;
			}
		}
	}
	
	// Check if two items in the array have to be swapped
	public boolean CheckSwap(int[][] arr, int i){
		if (i < arr.length - 1) {
			// Compare the degrees of these vertices and swap them
			if (arr[i][1] < arr[i+1][1]) {
				swap(arr, i, i+1);
				return true;
			}
		}
		
		return false;
	}
	
	// Swap two elements of an array
	public void swap(int[][] arr, int i, int j){
		int[] temp = arr[i];
		
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	// CONVERSION
	
	// let's go and convert this
	public int[][] ConvertAdjMatrix(int[][] adj_matrix, int[][] degrees){
		// Generate new empty adjacency matrix; this will be the end result
		int[][] new_adj_matrix = new int[adj_matrix.length][adj_matrix[0].length];
		
		// Loop through each vertex individually
		for(int i = 0; i < degrees.length; i++) {
			int[] connections = new int[degrees[i][1]];
	
			// Use the original adjacency matrix to retreive original connections
			int connections_found = 0;
			for(int ii = 0; ii < adj_matrix[degrees[i][0]].length; ii++){
				if (adj_matrix[degrees[i][0]][ii] == 1) {
					connections[connections_found] = ii;
					connections_found++;
				}
				
				// We don't need to continue looping once we have found all connections.
				if (connections_found >= connections.length) break;
			}
			
			if (DEBUG) System.out.println(Arrays.toString(connections));
			
			
			// Loop through its connections to assign them in the new adj matrix
			for(int j = 0; j < connections.length; j++) {
				// Scan its index in the degrees list, this becomes its new vertex number
				for(int k = 0; k < degrees.length; k++) {
					if (degrees[k][0] == connections[j]) {
						new_adj_matrix[i][k] = 1;
					}
				}
			}
		}
		
		return new_adj_matrix;
	}
	
	public int[][] GetConvertedMatrix(){
		return converted_adj_matrix;
	}
	
	
	/*
	public static void main(String[] args){
		// Test array. First number represents vertex number, second number represents degree (amount of connections it has)
		int[][] test = {
				{0, 0, 1, 1},
				{0, 0, 1, 0},
				{1, 1, 0, 1},
				{1, 0, 1, 0}
			};
			
		new MatrixSort(test);
		
		int[][] test2 = {
				{0, 1, 1, 1, 0},
				{1, 0, 1, 0, 1},
				{1, 1, 0, 1, 1},
				{1, 0, 1, 0, 1},
				{0, 1, 1, 1, 0}
			};
			
		MatrixSort matrix_sort = new MatrixSort(test2);
		test2 = matrix_sort.GetConvertedMatrix();
		
		System.out.println(Arrays.deepToString(adj_matrix));
	}
	*/
}