import java.util.ArrayList;

public class WP
{
    private int upperbound;

    public WP(int[][] AM, int maxcolours)
    {
        int[][] EpV = EdgesPerVertex(AM);
        upperbound = GetUpperBound(CalcDegrees(EpV), maxcolours);
    }

    /**
     * Main Method for calculation
     * @param graph Arraylist<Vertex> that is the return value of the CalcDegrees method, has all vertices ordered based on its degree
     * @param colours an integer array with all available colours
     * @return return the final int upper bound value
     */
    public int GetUpperBound(ArrayList<Vertex> graph, int maxcolours)
    {
        int[] colours = new int[maxcolours];
        for(int j = 0; j < maxcolours; j++)
        {
            colours[j] = (j+1);
        }
        ArrayList<Integer> usedcolours = new ArrayList<Integer>();
        FillColours(graph, colours);

        for (Vertex v : graph) 
        {
            int col = v.GetColour();
            if(!usedcolours.contains((Integer)col))
                usedcolours.add(col);
        }

        return usedcolours.size();
    }

    //Sub method that fills the vertices with available colours based on Welsh Powell method
    private  void FillColours(ArrayList<Vertex> graph, int[] colours)
    {
        for (Vertex v : graph) 
        {
            if(!v.GetColoured())
            {
                for (int col : colours) 
                {
                    if(CheckValid(graph, v, col))
                    {
                        v.SetColour(col);
                        break;
                    }
                }               
            }
        }
    }

    //Method to check whether a vertex can be coloured with a cetain coloured 
    private  boolean CheckValid(ArrayList<Vertex> all, Vertex ver, int colour)
    {
        for (int vc : ver.GetConnectedVertices()) 
        {               
            Vertex cur = new Vertex(); //Need to find a way to optimize finding a certain vertex based on its identifiying number (int vertex)
            for (Vertex vertex : all) 
            {
                if(vertex.GetVertex() == vc)
                {
                    cur = vertex;
                    break;
                }                   
            }

            if(cur.GetColour() == colour)
                return false; 
        }
        return true;
    }

    //create vertex array based on the degrees of each vertex
    public ArrayList<Vertex> CalcDegrees(int[][] ec)
    {
        ArrayList<Vertex> degrees = new ArrayList<Vertex>();

        for(int i = 0; i < ec.length; i++)
        {
            degrees.add(new Vertex(i, ec[i].length, ec[i]));
        }

        //sort arraylist
        ArrayList<Vertex> SortedVertices = new ArrayList<Vertex>();
        for (int s = 0; s < ec.length; s++)
        {
            Vertex highest = degrees.get(0);
            for (Vertex vertex : degrees) 
            {
                if(highest.GetEdgeCount() < vertex.GetEdgeCount())
                    highest = vertex;
            }

            SortedVertices.add(highest);
            degrees.remove(highest);
        }

        return SortedVertices;
    } 

    public int[][] EdgesPerVertex(int[][] adjm)
    {
        int[][] epv = new int[adjm.length][adjm.length];
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
            epv[i] = new int[occur];
            occur = 0;
        }

        for(int i = 0; i < adjm.length; i++)
        {
            for(int j = 0; j < adjm[0].length; j++)
            {
                if(adjm[i][j] == 1)
                {
                    epv[i][count] = j;
                    count++;
                }      
            } 
            count = 0;
        }

        return epv;
    }

    public int GetResult()
    {
        return upperbound;
    }
}
 
/**
 * Vertex Class
 * Creates an object out of each vertex and stores it with the information is needed to perform the welsh powell algorithm
 */
class Vertex
{
    private int vertex;
    private int edgecount;
    private int[] connectedvertices;
    private int colour; //-1 is null
    private boolean coloured;

    public Vertex(int v, int ec, int[] connected)
    {
        this.vertex = v;
        this.edgecount = ec;
        this.connectedvertices = connected;
        colour = -1; //vertex is not coloured
        coloured = false;
    }

    public Vertex()
    {

    }

    public int GetVertex()
    {
        return vertex;
    }

    public int GetEdgeCount()
    {
        return edgecount;
    }

    public int[] GetConnectedVertices()
    {
        return connectedvertices;
    }

    public int GetColour()
    {
        return colour;
    }

    public void SetColour(int c)
    {
        colour = c;
        coloured = true;
    }

    public boolean GetColoured()
    {
        return coloured;
    }
}

