
public class REBT 
{
    private int maxcolours; //maximum amount of colours that is allowed
    private int vertices; //vertex count
    private int[][] graph; //the adjacency matrix
    private int[] vertexcolors; //index is the vertex and the value is the colour
    private int lastvertex; //last vertex that was colored
    private int lastcolor; //last color used on last vertex
    private boolean solution;

    public REBT(int[][] AM, int vcount, int maxcolor)
    {
        graph = AM;
        vertices = vcount;
        maxcolours = maxcolor;
        vertexcolors = new int[vcount];
        solution = false;
        lastvertex = 0;
        lastcolor = 1;

        FindSolution();
    }

    private void FindSolution()
    {
        RecursiveBackTrack(); //start at vertex 0 and color 1

        if(solution)
        {
            System.out.println("Solution found for: " + maxcolours);
        }
        else
        {
            System.out.println("No solution found for: " + maxcolours);
        }
        for(int k = 0; k < vertexcolors.length; k++)
        {
            System.out.print(" " + vertexcolors[k]);
        }
        System.out.println("");
    }

    private boolean ValidColor(int v, int c)
    {
        //Check if a vertex is both connected and has the parameter colour
        for(int i = 0; i < graph.length; i++)
        {
            if(graph[v][i] == 1)
            {
                if(vertexcolors[i] == c)
                    return false;
            }
        }
        return true;
    }
 

    private void RecursiveBackTrack()
    {
        while(!solution && lastcolor <= maxcolours)
        {
            //Check which vertex was last colored after iteration
            //if lastvertex = end then solution found 
            if(lastvertex == vertices)
            {
                solution = true;
                System.out.println("Solution found for: " + maxcolours);
                return;
            }

            //Start coloring from vertex 0, starting from first color
            Coloring(lastvertex, lastcolor);

            if(lastvertex < vertices) //if not at last vertex then go back to last vertex and increase corresponding coloring by 1
            {   
                if(lastcolor >= maxcolours)
                {
                    while(lastcolor >= maxcolours && lastvertex != 0)
                    {                    
                        lastvertex--;
                        lastcolor = vertexcolors[lastvertex];
                        lastcolor++;
                    } 

                    if(lastvertex <= 0)
                    {
                        //solution doesnt exist, recursive call went back to v0
                        solution = false;
                        return;
                    }
                } 
                else
                {
                    lastcolor++;
                }

                //Clear all colorings after the reset to last vertex
                for(int j = (lastvertex); j < vertexcolors.length; j++)
                {
                    vertexcolors[j] = 0;
                }
            }

        }

    }

    private void Coloring(int v, int color)
    {     
        if(v == vertices)
        {
            lastvertex = v;
            solution = true;
            System.out.println("Solution found for: " + maxcolours);
            return;
        }

        //As long as there remain empty vertices continue cycle
        if(v < vertices && color <= maxcolours)
        {
            for(int c = color; c <= maxcolours; c++) 
            {
                //Set curv to current color
                if(ValidColor(v, c))
                {
                    //System.out.println("Current vertex: " + v + " is colored: " + c);
                    vertexcolors[v] = c;

                    //update lastvertex and lastcolor
                    lastvertex = v;
                    lastcolor = c;

                    //Continue with next vertex, from first color
                    Coloring((v+1), 1);
                }
            } 
        }
    }

 
}