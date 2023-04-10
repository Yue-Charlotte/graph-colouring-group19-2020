
public class TrivialBounds 
{
    private int vertices;
    private int edges;
    private int lower;
    private int upper;
    private int[] sorted;

    public TrivialBounds(Graph g) //graph as input. then able to retireve edges and vertices or in matrix form
    {
        vertices = g.GetTotalVertices();
        edges = g.GetTotalEdges();
        sorted = g.GetSortedEdgeCountList();
        CalculateBounds();
    }

    public void CalculateBounds()
    {
        //Lower bound
        if(vertices >= 2 || edges >= 1)
        {          
            lower = 2;
        }

        //Trivial Upper bound
        for(int ve = sorted.length-1; ve > 0; ve--)
        {
            int cur = sorted[ve];
            int count = 0;
            for(int cal = 0; cal < sorted.length; cal++)
            {
                if(cur <= sorted[cal])
                    count++;
            }
            
            if(count >= cur)
            {
                upper = (cur+1);
                return;
            }
            
        }
    }

    public int GetUpper()
    {
        return upper;
    }

    public int GetLower()
    {
        return lower;
    }
    
}
