import java.io.*;
import java.util.*;

public class Phase1GraphColouring
    {
		public static boolean found = false;
		public final static boolean DEBUG = true;		
		public final static String COMMENT = "//";		
		public static void main( String args[] )
        {
			if( args.length < 1 )
				{
				System.out.println("Error! No filename specified.");
				System.exit(0);
				}

				
			String inputfile = args[0];			
			boolean seen[] = null;
			
			//! n is the number of vertices in the graph
			int n = -1;
			
			//! m is the number of edges in the graph
			int m = -1;
			
			//! e will contain the edges of the graph
			ColEdge e[] = null;
			
			try 	{ 
			    	FileReader fr = new FileReader(inputfile);
			        BufferedReader br = new BufferedReader(fr);

			        String record = new String();
					
					//! THe first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
			        while ((record = br.readLine()) != null)
						{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
						}
	
					if( record.startsWith("VERTICES = ") )
						{
						n = Integer.parseInt( record.substring(11) );					
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
						}

					seen = new boolean[n+1];	
						
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
						{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
						}

					e = new ColEdge[m];	
												
					for( int d=0; d<m; d++)
						{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )
								{
								System.out.println("Error! Malformed edge line: "+record);
								System.exit(0);
								}
						e[d] = new ColEdge();
						
						e[d].u = Integer.parseInt(data[0]);
						e[d].v = Integer.parseInt(data[1]);

						seen[ e[d].u ] = true;
						seen[ e[d].v ] = true;
						
						if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
				
						}
									
					String surplus = br.readLine();
					if( surplus != null )
						{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
						}
					
					}
			catch (IOException ex)
				{ 
		        // catch possible io errors from readLine()
			    System.out.println("Error! Problem reading file "+inputfile);
				System.exit(0);
                }

			for( int x=1; x<=n; x++ )
				{
				if( seen[x] == false )
					{
					if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
					}
				}

        //-----------------------------------------------------------------------------------------------------------------
        
		Graph g1 = new Graph(n+1);
		//fill graph with edges
		for(int ed = 0; ed < e.length; ed++)
		{
			g1.AddEdge(e[ed].u, e[ed].v);
		}

		long startTime = System.nanoTime();// start time
        for(int i = n; i > 0; i--)
        {

            if(found)
            {
				System.out.println("Solution found for C: " + (i+1));
				//add time it takes to complete searching for this value:
				
                //break; // i-1 because result was found during the last iteration
            }
            BackTracking(g1, i); 
			long endTime = System.nanoTime();
			long totalTime = (endTime - startTime)/1000000;
			System.out.println("MiliSeconds needed for search with " + i + " colours: " + (totalTime)); 
        }


        }//end of main method

        //Method 1:
        //Main Method
            //void method with 2 parameters: graph and int (amount of colours we plan on using)
            //create int and fill with vertexcount from graph 
            //create array of vertices with colours
            //set array values to 0 (standard in java)
            //use method 2 to check for solution > solution found is based on return value
		//---<<code here>>----
		public static void BackTracking(Graph g, int m) {
            int V = g.GetVertexCount();

            // color array
            int colors[] = new int[V];
            Arrays.fill(colors, 0); 

            // call ColouringCheck for vertex 0
            if (!ColouringCheck(g, m, colors, 0)) {
                System.out.println("Solution does not exist for: " + m);
                found = false;
            }
            else found = true;

        }

        //Method 2:
        //-recursion method that checks all vertices
            //boolean method with 4 parameters: graph, int (amount of colours allowed), array of colours, vertices
            //if vertices are all coloured then return true
            //for loop that checks different color per vertex
            //use method 3 to check if colour is valid, if yes then fill this vertex with colour
            //use recursion to repeat process with vertex + 1 > return true after all vertices are done
            //return false if unable to assign colours to all vertices
        //---<<code here>>----
		public static boolean ColouringCheck(Graph g, int m, int colors[], int v) {
            // all vertices have a colour then just true
            if (v == g.GetVertexCount())
                return true;

            // try different colors for v
            for (int cr = 1; cr <= m; cr++) {
                // Check if assignment of colour cr to v is fine
                if (Valid(v, g, colors, cr)) {
                    colors[v] = cr;
                    // recur to assign colours to rest of the vertices
                    if (ColouringCheck(g, m, colors, v + 1))
                        return true;

                    // If assigning colour cr doesn't lead
                    // to a solution then remove
                    colors[v] = 0;
                }
            }

            // if no color can be assigned then return false
            return false;
		}
		
        //Method 3:
        //Method to check if connected vertices don't have the same colour
            //boolean method with 4 parameters: graph, vertices, array of colours, int colour
            //for loop that loops until graphs vertixcount
            //if graph has an edge between the vertex and the loop index and colour parameter == vertex with that colour > return false
            //else return true
        //---<<code here>>----
		public static boolean Valid(int v, Graph g, int colors[], int cr) {
            for (int i = 0; i < g.GetVertexCount(); i++) {
                if (g.HasEdge(v, i) && cr == colors[i]) {
                    return false;
                }
            }
            return true;

		}
		
    }//end of class

		class ColEdge
			{
			int u;
			int v;
			}
		

//Graph class
class Graph
{
    private int vertexcount;
    private List<Integer>[] adjacent;

    //Constructor
    public Graph(int vCount)
    {
        vertexcount = vCount;
        adjacent = (List<Integer>[])new List[vCount];  
        for (int i = 0; i < vCount; ++i)
        {
            adjacent[i] = new ArrayList<Integer>();
        }			
    }

	//basic method for retrieving vertexcount
	public int GetVertexCount()
	{
		return vertexcount;
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

    //----Code to print graph----- ignore for now
	// public void printGraph() {
	// 	for (int i = 0; i < vertexcount; i++) {
	// 		List<Integer> edges = Connected(i);
	// 		System.out.print(i + ": ");
	// 		for (int j = 0; j < edges.size(); j++) {
	// 			System.out.print(edges.get(j) + " ");
	// 		}
	// 		System.out.println();
	// 	}
	// }


}
