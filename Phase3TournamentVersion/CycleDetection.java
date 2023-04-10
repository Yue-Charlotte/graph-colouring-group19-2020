// Code by Yuewei

//for checking Cycle in the graph
public class CycleDetection {

	private Graph Graph;
	private boolean[] VisitedVertices;
	private boolean hasCycle = false;
	private boolean isCycle = true;

	/**
	 * Idea : for every visited vertex'v', if there is an adjacent'u'
	 * such that u is already visited and u is not parent of v,then there is a cycle in the graph
	 * notice that the first vertex is marked as position 0
	 */

	public CycleDetection(Graph g) {
		Graph = g;

		// set all the vertices as not visited (java false as default)
		VisitedVertices = new boolean[g.GetTotalVertices()];

		//if the graph has vertices less than 2 then it is not a cycle and no cycle
		if(g.GetTotalVertices() <= 2) {
			isCycle = false;
			return;
		}

		//check if this is a cycle graph
		for (int v = 0; v < Graph.GetTotalVertices(); v++) {
			if(isCycle(v) == false)
				break;
		}

		// call out the recursive function to detect cycle in DFS
		for (int v = 0; v < Graph.GetTotalVertices(); v++) {
			//if it is a cycle graph then stop
			if(isCycle(v) == true) {
				hasCycle = true;
				break;
			}
			// recursion for unvisited v
			else if (!VisitedVertices[v]) {
				if (depthFirstSearch(v, v)) {
					hasCycle = true;
					break;
				}
			}
		}
	}

	// recursion with VisitedVertices[] and parent(where this vertex coming from) to detect cycle in the subgraph of vertex v. 
	public boolean depthFirstSearch(int v, int parent) {

		// set the current node as visited 
		VisitedVertices[v] = true;

		// recursion for all the vertices adjacent to this vertex 
		for (int i : Graph.Connected(v)) {
			// if an adjacent is not visited, then go recursion for that adjacent 
			if (!VisitedVertices[i]) {
				if (depthFirstSearch(i, v))
					return true;
				//if an adjacent is visited, and not parent of current vertex, then there is a cycle. 
			} else {
				if (i != parent) {
					return true;
				}
			}
		}
		return false;
	}

	// return true if the graph has a cycle, else false. 

	//check the numbers of vertex's neighbors 
	public boolean isCycle(int v) {

		//once the neighbors are not 2 then this is not a cycle graph and stop checking
		if(Graph.Connected(v).size() != 2) 
			isCycle = false;

		return isCycle;
	}

	public boolean hasCycle() {
		return hasCycle;
	}

	public boolean isCycle() {
		return isCycle;
	}

}