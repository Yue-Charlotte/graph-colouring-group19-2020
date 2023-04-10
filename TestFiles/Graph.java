package p3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph 
{
	private int vertices;
	private int edges;
	private int[][] matrix;
	private Edge[] edgedata;
	private int[][] epvlist;
	private int[] ecpv;
	private List<Integer>[] adjacent;

	public Graph(int vertices, int edges, Edge[] ed, int[][] am)
	{
		this.vertices = vertices;
		this.edges = edges;
		this.matrix = am;
		this.edgedata = ed;
		this.epvlist = EdgesPerVertex(am);
		EdgeCountPerVertex();
		ListRelated(vertices);
	}

	private int[][] EdgesPerVertex(int[][] adjm)
	{
		epvlist = new int[adjm.length][adjm.length];
		int occur = 0;
		int count = 0;

		for(int i = 0; i < adjm.length; i++)
		{
			for(int j = 0; j < adjm[0].length; j++)
			{
				if(adjm[i][j] == 1)
				{
					occur++;
				}      
			}               
			epvlist[i] = new int[occur];
			occur = 0;
		}

		for(int i = 0; i < adjm.length; i++)
		{
			for(int j = 0; j < adjm[0].length; j++)
			{
				if(adjm[i][j] == 1)
				{
					epvlist[i][count] = j;
					count++;
				}      
			} 
			count = 0;
		}

		return epvlist;
	}
	

    public void ListRelated(int vCount) {
    	this.vertices = vCount;
		
		adjacent = (List<Integer>[])new List[vCount];  
		
		for (int i = 0; i < vCount; i++)
		{
				adjacent[i] = new ArrayList<Integer>();
		}			
			
		for(int i = 0; i < edgedata.length; i++){

			this.AddEdge(edgedata[i].v1-1, edgedata[i].v2-1);
		}
    }


	public void AddEdge(int a, int b) {
		adjacent[a].add(b);
		adjacent[b].add(a);
		
	}
	
	private void EdgeCountPerVertex()
	{   
		this.ecpv = new int[vertices+1];
		for(int i = 0; i < edges; i++)
		{
			ecpv[edgedata[i].v1]++;
			ecpv[edgedata[i].v2]++;
		}
	}
	
	public List<Integer> Connected(int vertex){
		//System.out.println(adjacent[vertex]);
		return adjacent[vertex];
	}


	public int[] GetSortedEdgeCountList()
	{
		Arrays.sort(ecpv);
		return ecpv;
	}

	public int[][] GetEPVList()
	{
		return epvlist;
	}

	public int[][] GetAdjacencyMatrix()
	{
		return matrix;
	}

	public Edge[] GetEdgeData()
	{
		return edgedata;
	}

	public int GetTotalVertices()
	{
		return vertices;
	}

	public int GetTotalEdges()
	{
		return edges;
	}

	public double GetGraphDensity()
	{
		double density = 0;
		double maxd = 0;     
		for(int j = 1; j < vertices; j++)
		{
			maxd += j;
		}

		density = ((this.edges/maxd) * 100);
		return density;
	}
}
