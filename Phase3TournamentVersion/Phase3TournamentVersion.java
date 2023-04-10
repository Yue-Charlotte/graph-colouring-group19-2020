import java.io.*;

//Subclass to store the edges
class Edge
{
    int v1;
    int v2;
}

public class Phase3TournamentVersion 
{	
    private static int LOWER = 0;
    private static int UPPER = 0;
    private static int CHROMATIC = 0;

    public static void main(String args[])
    {
        if(args.length < 1)
        {
            System.out.println("Error! No filename specified.");
            System.exit(0);
        }

        //Filename
        String inputfile = args[0];		
        //Vertices
        int V = -1;			
        //Edges
        int E = -1;			
        //Edge class that stores each edge with its corresponding vertices
        Edge inputedges[] = null;
        //Adjacency matrix
        int[][] Connected = null;

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

            //Variables
            inputedges = new Edge[E];           
            Connected = new int[V][V];

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

                //v1 is connected to v2, and v2 is connected to v1, vertex 1 is stored at position 0
                Connected[curv1-1][curv2-1] = 1; 
                Connected[curv2-1][curv1-1] = 1;
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

        //Store everything in Graph class
        Graph G = new Graph(V, E, inputedges, Connected);

        //Run Trivial bounds Algorithm
        TrivialBounds bounds = new TrivialBounds(G);       
        LOWER = bounds.GetLower();
        UPPER = bounds.GetUpper();
        System.out.println("NEW BEST UPPER BOUND = " + UPPER);
        System.out.println("NEW BEST LOWER BOUND = " + LOWER);

        //Compare bounds
        if(ChromaticCheck(LOWER, UPPER))
        {
            CHROMATIC = LOWER;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }

        //Check for Biparite graph, Chromatic = 2 if true
        Bipartite bipartite = new Bipartite(G);
        if(bipartite.result)
        {
            CHROMATIC = 2;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }
        else
        {
            LOWER = 3;
            System.out.println("NEW BEST LOWER BOUND = " + LOWER);
        }

        //Compare bounds
        if(ChromaticCheck(LOWER, UPPER))
        {
            CHROMATIC = LOWER;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }
				
        //Check for graph structures
        //Cycle
        CycleDetection CD = new CycleDetection(G);
        if(CD.isCycle())
        {
            //Check for uneven vertices
            if(G.GetTotalVertices() % 2 != 0)
            {
                CHROMATIC = 3;
                System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            }
                
        }
        //Wheel
        Wheel w = new Wheel(G);
        if(w.isWheel())
        {
            LOWER = 3;
            System.out.println("NEW BEST LOWER BOUND = " + LOWER);
            UPPER = 4;
            System.out.println("NEW BEST UPPER BOUND = " + UPPER);
        }

        //Run Max Clique Algorithm
        MaxCliq MC = new MaxCliq(G.GetAdjacencyMatrix());
        MC.Calculation();
        if(MC.maxCliq > LOWER)
        {
            LOWER = MC.maxCliq;
            System.out.println("NEW BEST LOWER BOUND = " + LOWER);
        }

        //Compare bounds
        if(ChromaticCheck(LOWER, UPPER))
        {
            CHROMATIC = LOWER;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }

        //Run Welsh Powell Algorithm
        WP welsh = new WP(G.GetAdjacencyMatrix(), UPPER);
        if(welsh.GetResult() < UPPER)
        {
            UPPER = welsh.GetResult();
            System.out.println("NEW BEST UPPER BOUND = " + UPPER);
        }

        //Compare bounds
        if(ChromaticCheck(LOWER, UPPER))
        {
            CHROMATIC = LOWER;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }

        //Run A-RLF
        ARLF Arlf = new ARLF(G.GetAdjacencyMatrix());
        if(Arlf.GetARLFupper() < UPPER)
        {
            UPPER = Arlf.GetARLFupper();
            System.out.println("NEW BEST UPPER BOUND = " + UPPER);
        }
	    
	    //Run A-RLF - Optimized Version 2
        ARLFOpt2 RLFO = new ARLFOpt2(G.GetAdjacencyMatrix());
        if(RLFO.GetARLFupper() < UPPER)
        {
            UPPER = RLFO.GetARLFupper();
            System.out.println("NEW BEST UPPER BOUND = " + UPPER);
        }
         
        //Compare bounds
        if(ChromaticCheck(LOWER, UPPER))
        {
            CHROMATIC = LOWER;
            System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
            System.exit(0);
        }

        //Prune graph 
        Pruning prG = new Pruning(G, 2); //custom set to 2, can be optimized later
        int[][] newAM = prG.PruneGraph();

        //Sort this new AM for faster backtracking
        MatrixSort MS = new MatrixSort(newAM);
        int[][] SortedAM = MS.GetConvertedMatrix();

        //Run Backtracking until timeout or until solution
        while(LOWER != UPPER)
        {   
            //Run Algorithm from lower bound       
            REBT rebt = new REBT(SortedAM, SortedAM.length, LOWER);

            if(rebt.GetResult())
            {
                UPPER = LOWER;               
                System.out.println("NEW BEST UPPER BOUND = " + UPPER);
                CHROMATIC = LOWER;
                System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
                System.exit(0);
            }
            else
            {
                LOWER += 1;
                System.out.println("NEW BEST LOWER BOUND = " + LOWER);
            }
        }

        //If all done post chromatic and end.
        CHROMATIC = LOWER;
        System.out.println("CHROMATIC NUMBER = " + CHROMATIC);
        System.exit(0);

    } 

    public static boolean ChromaticCheck(int low, int up)
    {
        if(low == up)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
