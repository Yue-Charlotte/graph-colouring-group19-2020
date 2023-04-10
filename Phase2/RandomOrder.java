// Code by Christopher

import java.util.*;

public class RandomOrder implements GameMode {
	// Implemented instance fields
	boolean current_vertex_changeable = false;
	
	// Instance fields specific to this game mode
	private int[] order;									// The order in which the player will proceed.
	private int current_vertex_index = 0;	// The index of the order array for the current_vertex.
	
	// Initialize the random order gamemode
	public RandomOrder(int v){
		// Temporary array with every vertex from 0 to graph vertex count.
		int[] temp = new int[v];
		for(int i = 0; i < v; i++){
			temp[i] = i;
		}
		
		// Main order
		order = new int[v];
		int orderindex = 0;
		
		while(orderindex < v){
			int new_value = temp[(int)Math.floor(Math.random()*(temp.length))];
			order[orderindex] = new_value;
			
			int[] temp2 = new int[temp.length-1];
			int tempindex = 0;
			
			for(int i = 0; i < temp.length; i++){
				if (temp[i] != new_value){
					temp2[tempindex] = temp[i];
					tempindex++;
				}
			}
			
			temp = temp2;
			orderindex++;
		}
		
		current_vertex_index = 0;
	
		System.out.println("RANDOM ORDER MODE");
	}
	
	// Move on to the next vertex in this ordering
	public void NextVertex(){
		if (current_vertex_index + 1 < order.length){
			Group19Phase2.gameManager.selectedvertex = order[++current_vertex_index];
		}else{
			Finish.finishCheck(-1);
		}
	}
	
	// Which vertex is at a certain place in the order?
	public int GetOrderIndex(int i){
		return order[i];
	}
	
	// Set the currently-selected vertex
	public void SetCurrentVertex(int v){
		// Do nothing here because the player can't click to select vertices in this mode.
	}
	
	// Set the currently-selected vertex's colour
	public void ColourCurrentVertex(int v, int c){
		Group19Phase2.G.SetVertexColour(Group19Phase2.gameManager.selectedvertex, c);
		GUI.baseFrame.repaint();
		
		Group19Phase2.gameManager.GoToNextVertex();
	}
	
	public String toString(){
		return Arrays.toString(order);
	}
}