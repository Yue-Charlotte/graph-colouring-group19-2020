import java.io.*;
import java.util.*;

//Graph class
public class Graph
{
    private int vertexcount;
    private List<Integer>[] adjacent;
		
    //basic method for retrieving vertexcount
    public int GetVertexCount()
    {
        return vertexcount;
    }

    //Constructor
    public Graph(int vCount)
    {
        this.vertexcount = vCount;
        adjacent = (List<Integer>[])new List[vCount];  
        for (int i = 0; i < vCount; i++)
        {
            adjacent[i] = new ArrayList<Integer>();
        }			
    }

    //add edge to graph class
    public void AddEdge(int a, int b)
    {
        adjacent[a].add(b);
        adjacent[b].add(a);
    }

    //boolean method to check if edge exists
    public boolean HasEdge(int i, int j) 
    {
		return adjacent[i].contains(j);
	}

    //return list with edges linked to this vertex
    public List<Integer> Connected(int vertex) 
    {
		return adjacent[vertex];
	}
}