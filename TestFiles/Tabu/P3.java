package p3;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Subclass to store the edges
class Edge
{
	int v1;
	int v2;
}

public class P3 
{	
	public static void main(String args[]) throws Exception
	{
		//Programs launchtime
		long launchTime = System.nanoTime();

		//     if(args.length < 1)
		//     {
		//         System.out.println("Error! No filename specified.");
		//         System.exit(0);
		//     }

		//Filename
		String inputfile = "graphs/phase3_2020_graph15.txt";		
		//String inputfile = "graphs/g5.txt";		
		//Vertices
		int V = -1;			
		//Edges
		int E = -1;			
		//Edge class that stores each edge with its corresponding vertices
		Edge inputedges[] = null;
		//Adjacency matrix
		int[][] Connected = null;

		int[][] C2 = null;  //same function as Connected


		try 	
		{ 
			FileReader fr = new FileReader(inputfile);
			BufferedReader br = new BufferedReader(fr);
			String currentline = new String();

			//Total vertices + edges
			currentline = br.readLine();
			if( currentline.startsWith("VERTICES = ") )
			{
				V = Integer.parseInt(currentline.substring(11));
			}        	
			currentline = br.readLine();
			if( currentline.startsWith("EDGES = ") )
			{
				E = Integer.parseInt(currentline.substring(8));	
			}

			//variables
			inputedges = new Edge[E];           
			Connected = new int[V][V];

			C2 = new int[V+1][V+1];

			for(int i = 0; i < E; i++)
			{
				currentline = br.readLine();
				String data[] = currentline.split(" ");
				if(data.length != 2)
				{
					System.out.println("Error! Invalid edge found: " + currentline);
					System.exit(0);
				}

				inputedges[i] = new Edge();
				int curv1 = Integer.parseInt(data[0]);
				int curv2 = Integer.parseInt(data[1]);

				//Always stores the lowest vertex in v1
				if(curv1 > curv2)
				{
					inputedges[i].v1 = curv2;
					inputedges[i].v2 = curv1;
				}
				else
				{
					inputedges[i].v1 = curv1;
					inputedges[i].v2 = curv2;
				}

				//v1 is connected to v2, and v2 is connected to v1
				Connected[curv1-1][curv2-1] = 1; //vertex 1 is stored at position 0
				Connected[curv2-1][curv1-1] = 1;

				
				C2[curv1][curv2]=1;
				C2[curv2][curv1]=1;

			}


			String surplus = br.readLine();
			if(surplus != null)
			{
				System.out.println("Warning: There is data in your file after the last edge: '" + surplus + "'");						
			}

			//Close readers
			br.close();
			fr.close();
		}
		catch (IOException ex)
		{ 
			//Catch possible IO errors
			System.out.println("Error! Problem reading file " + inputfile);
			System.exit(0);
		}
		finally
		{
			System.out.println("Graph loading complete. ");
		}

		//Store everything in Graph class
		Graph G = new Graph(V, E, inputedges, Connected);

		G.setC2(C2);

		//Time it took to store the graph      
		long endLoadTime = System.nanoTime();
		long LoadTime = endLoadTime - launchTime;

		//Display all data on screen      
		System.out.println("----- Graph Data -----");   
		System.out.println("Vertices: " + G.GetTotalVertices());
		System.out.println("Edges: " + G.GetTotalEdges());
		System.out.println("Graph Density: " + new DecimalFormat("#.##").format(G.GetGraphDensity()) + "%");
		System.out.println("Time used to load graph: " + (LoadTime/1000000) + "ms");
		System.out.println("-----------------------");
		System.out.println("");
		System.out.println("Press any key to run the algorithms. ");

		//Scanner to register input
		Scanner in = new Scanner(System.in);
		if(in.hasNext())
		{
			//run algorithms
			RunGraphAlgorithm(G);
		}

	}//end of main

	public static void RunGraphAlgorithm(Graph g) throws Exception
	{      
		TrivialBounds bounds = new TrivialBounds(g);
		int upper = bounds.GetUpper();
		int lower = bounds.GetLower();
		System.out.println("Trivial bounds: ");      
		System.out.println("Lower: " + lower);
		System.out.println("Upper: " + upper);

		MaxCliq MC = new MaxCliq(g.GetAdjacencyMatrix());
		MC.Calculation();
		System.out.println("Max cliq: " + MC.maxCliq);

		WP welsh = new WP(g.GetEPVList(), upper);
		System.out.println("WP: " + welsh.GetResult());


		System.out.println("--------Basic Graph Analysis------");
		CycleDetection CD = new CycleDetection(g);
		System.out.println("Is a Cycle Graph : " + CD.isCycle());
		System.out.println("Has a Cycle : " + CD.hasCycle());

		Trees t = new Trees(g);
		System.out.println("Is a Tree Graph : " + t.isTree());

		Wheel w = new Wheel(g);
		System.out.println("Is a Wheel Graph : " + w.isWheel());

		System.out.println("--------Test------");
		long startTime = System.currentTimeMillis();
		Tabu tb=new Tabu(g);
		tb.process();
		System.out.println("Chromatic number:"+(tb.k+1));
		System.out.println("Iterations:"+ tb.iterations);
		long stopTime = System.currentTimeMillis();
		long reactionTime = stopTime - startTime;
		System.out.print("Run Time: " + (reactionTime) + " ms");
		
	}

}//end of P3 class
