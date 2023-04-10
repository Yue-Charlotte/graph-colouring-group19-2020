// Project 1-1, Phase 2: Graph Colouring Game
// Group 19: Endri Dibra, Payodh Dwivedi, Tjardo Neis, JD Noblesse, Christopher Schiffmann, Tianyu Wei, Yuewei Wu
// Phase 2 project leader: Christopher Schiffmann
// Every java file includes the name of the people who contributed to the file at the top.

import java.io.*;
import java.util.*;

public class Group19Phase2 {
	public final static boolean DEBUG = true;		
	public static int State = 0;
	public static ColEdge[] e;
	public static Graph G;					// The graph, generated from input data, which also stores each vertex's colour and can check proper edge colouring
	public static Game gameManager;	// Main game logic handler
	public static int graphVertexCount;	// How many vertices does the graph have? Important step for generating the graph in GUI.java when we convert the ColEdge array into the graph itself, once the graph itself is generated we don't use this anymore, but the graph's own vertex count instead. This is just here to store the value between GenerateGraph.java and GUI.java.
	public static GUI screen;	// The main screen of the game
	
	public static void main( String args[] ) {
		screen = new GUI();
	}
	
	public static void RestartGame(){
		try{
			GUI.hint.HintPopup.dispose();
		}catch(Exception e){
			
		}
		GUI.baseFrame.dispose();
		screen = null;
    screen = new GUI();
	}
}
