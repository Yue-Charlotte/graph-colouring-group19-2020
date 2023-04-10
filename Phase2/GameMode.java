// Code by Christopher

public interface GameMode {
	boolean current_vertex_changeable = true;	// Can the player change the current vertex on their own?
	
	public void SetCurrentVertex(int v);	// Set the currently selected vertex to another vertex
	public void ColourCurrentVertex(int v, int c);
	public void NextVertex();
	public int GetOrderIndex(int i);
}