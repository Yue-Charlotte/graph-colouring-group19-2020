import java.util.ArrayList;
import java.util.Collections;

public class ARLFOpt2 
{
    private int[][] am;
    private int totalCv;
    private int totalVertices;
    private static int ColorClass;
    private ArrayList<Ver> Vertices; //unordered vertex list
    private ArrayList<Ver> Vdegrees; //sorted arraylist with vertices with highest degrees first

    public ARLFOpt2(int[][] adjacencymatrix)
    {
        am = adjacencymatrix;
        totalVertices = am.length;
        totalCv = 0;

        //Setup
        Vertices = new ArrayList<Ver>();
        Vdegrees = CalcDegrees(adjacencymatrix);

        //Run main program
        RecursiveIteration(Vdegrees, new ArrayList<Ver>(), Vdegrees.get(0));

    }

    public int GetARLFupper()
    {
        return ColorClass;
    }

    // Set U = uncolored vertices, entire graph at the start
    // Set C = Color class, this number will be the upper bound
    // vertex v = picked by highest degree first, then based on A- method
    // Set W = all vertices directly connected to the current v
    private void RecursiveIteration(ArrayList<Ver> U, ArrayList<Ver> W, Ver StartVertex)
    {
        try
        {
            //If uncolored vertices exist
            while(totalCv < totalVertices)
            {
                //pick a new vertex from U to create a new C
                Ver currentV = StartVertex;
                int curVID = StartVertex.GetVID();
    
                //Add current vertex to C and remove from U
                U.remove(currentV);
                totalCv++;
                ColorClass++;
                //System.out.println("Currently at: " + ColorClass + " color classes.");
                //System.out.println("Total Vertices colored: " + totalCv);

                //Remove adj ones
                RemoveAdjVertices(U, W, curVID);

                //If U is not empty, check for remaining vertices in U to add to the color class
                while(!U.isEmpty())
                {
                    //select new curv from U
                    Ver temp = VertexSelection(U, W);
                    U.remove(temp);
                    RemoveAdjVertices(U, W, temp.GetVID());
                    totalCv++;
                }

                //Add all Vertices from W back to U and empty W
                for (Ver ver1 : W) 
                {
                    U.add(ver1);
                }
                W.clear();

                //recursion if we need to create a new color class
                if(!U.isEmpty())
                {
                    //Select new Vertex to create new class with and return it as a parameter in the recursion
                    Collections.sort(U);
                    Ver newC = U.get(0); //This has optimization options
                    RecursiveIteration(U, W, newC);
                }

            } //end while

        }
        catch(Exception exc)
        {
            System.out.println("Error found. A-RLF result invalid. ");
        }        
    }
    
    private Ver VertexSelection(ArrayList<Ver> U, ArrayList<Ver> W)
    {        
        //Priority is based on the following:
        //  Most neighbours in W
        //  !! -- If ties, then least neighbours in U (if ties then just lowest index) -- !! not implemented yet
        Ver priorityVer = U.get(0); //set lowest index as dummy value, if sets are empty or only ties occur
        int curHiNeW = 0; //current highest neighbours in W

        for (Ver v : U) 
        {
            int count = 0; //counter for amount of neighbours
            for (Ver wVer : W) 
            {
                if(am[v.GetVID()][wVer.GetVID()] == 1)
                {
                    count++;
                }            
            }

            //Mixed results - some are faster some are slower with this part added
            if(count == curHiNeW) //When tied, lowest amount of neighbours in U
            {
                int oldVadjCount = 0;
                int curVadjCount = 0;
                for(Ver Uver : U)
                {
                    if(am[priorityVer.GetVID()][Uver.GetVID()] == 1)
                    {
                        oldVadjCount++;
                    }
                    if(am[v.GetVID()][Uver.GetVID()] == 1)
                    {
                        curVadjCount++;
                    }
                }
                //Compare values, select lowest
                if(curVadjCount < oldVadjCount)
                {
                    priorityVer = v;
                    curHiNeW = count;
                }
            }
            //--- End optimization step ---

            if(count > curHiNeW)
            {
                priorityVer = v;
                curHiNeW = count;
            }
        }

        return priorityVer;
    }

    private void RemoveAdjVertices(ArrayList<Ver> U, ArrayList<Ver> W, int curvid)
    {
         //add all adjacent ones to W
         for (Ver ver : U) 
         {
             if(am[curvid][ver.GetVID()] == 1)
             {
                 W.add(ver);
             }
         }

         //Remove all Vertices in W from U
         for (Ver ver2 : W) 
         {
             U.remove(ver2);
         }
    }

    //create array based on the degrees of each vertex
    private ArrayList<Ver> CalcDegrees(int[][] amatrix)
    {
        ArrayList<Ver> degrees = new ArrayList<Ver>();

        for(int i = 0; i < am.length; i++)
        {
            int curvd = 0;
            for(int j = 0; j < am[i].length; j++)
            {
                if(am[i][j] == 1)
                    curvd++;
            }
            degrees.add(new Ver(i, curvd));
            Vertices.add(new Ver(i, curvd));
            curvd = 0;
        }

        Collections.sort(degrees);
        return degrees;
    } 


}

//Custom class to store vertex degrees
class Ver implements Comparable<Ver>
{
    private int vid;
    private int degree;

    public Ver(int vertex, int connectededges)
    {
        vid = vertex;
        degree = connectededges;
    }

    public Ver()
    {

    }

    public int GetVID()
    {
        return vid;
    }

    public int GetDegree()
    {
        return degree;
    }

    @Override
    public int compareTo(Ver v2) 
    {
        return Integer.compare(this.GetDegree(), v2.GetDegree());
    }
}