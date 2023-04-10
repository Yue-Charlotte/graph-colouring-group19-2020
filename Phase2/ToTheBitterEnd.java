// Code by Christopher, relies on chromatic number algorithms by Tjardo

import java.util.*;

public class ToTheBitterEnd implements GameMode {
	// Implemented instance fields
	boolean current_vertex_changeable = true;
	
	// Instance fields specific to this game mode
	private int current_vertex_index = 0;	// The index of the order array for the current_vertex.
	
	// Initialize the random order gamemode
	public ToTheBitterEnd(){
		System.out.println("TO THE BITTER END MODE");
	}
	
	// Move on to the next vertex in this ordering
	public void NextVertex(){
		if (Group19Phase2.gameManager.selectedvertex + 1 < Group19Phase2.G.GetVertexCount()){
			Group19Phase2.gameManager.selectedvertex++;
		}else{
			Group19Phase2.gameManager.selectedvertex = 0;
		}
	}
	
	public void SetCurrentVertex(int v){
		Game.selectedvertex = v;
	}
	
	public void ColourCurrentVertex(int v, int c){
		Group19Phase2.G.SetVertexColour(Group19Phase2.gameManager.selectedvertex, c);
		GUI.baseFrame.repaint();
		if (!Group19Phase2.G.HasUncolouredVertices() && Group19Phase2.G.AmountOfBadEdges() == 0 && Group19Phase2.G.AmountOfColoursUsed() == Game.chromaticnumber){
			Finish.finishCheck(-1);
		}
	}
	
	public int GetOrderIndex(int i){
		return -1;
	}
}