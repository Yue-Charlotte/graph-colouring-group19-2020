import java.util.ArrayList;

//Maximum clique class
public class MaxCliq 
{
    //input will be stored as an adjacency matrix
    private int[][] Matrix;

	//Stores the value of the maxcliq, this is the value that should be used after the algorithm finishes
	public int maxCliq;

	//Constructor - Store the inputmatrix as the matrix variable
    public MaxCliq(int[][] inputmatrix)
    {
        this.Matrix = inputmatrix;
        this.maxCliq = 0;
	}

	//Main method to invoke algorithm
    public void Calculation()
    {
		//Start main algorithm with all vertices
		ArrayList<Integer> Collection = new ArrayList<Integer>();
        for(int v = 0; v < Matrix.length; v++)
        {
            Collection.add(v);
        }
        
        //Update maxCliq variable with the length of the maxCliqVertices set
        MaxCliqIteration(Collection, new ArrayList<Integer>()); 
    }

    /**
     * Recursive method to check whether vertices are part of a complete graph
     * 
     * @param VertexSet Set with all viable vertices
     * @param CompleteGraphVertices Contains the vertices that form a complete subgraph
     */
    private void MaxCliqIteration(ArrayList<Integer> VertexSet, ArrayList<Integer> CompleteGraphVertices)
    {
        try
        {
            while(VertexSet.size() > 0)
            {
                //Quit loop there are not enough vertices left to create a bigger cliq
                if(VertexSet.size() + CompleteGraphVertices.size() < maxCliq)
                    break;  

                //Select vertex from the (remaining) set and add it to the subgraph to check
                int currentVertex = VertexSet.get(VertexSet.size()-1);
                CompleteGraphVertices.add(currentVertex);
                
                //Search for connected vertices > add them to the potential list if they are connected
                ArrayList<Integer> PotentialVertices = new ArrayList<Integer>();
                for(int vertex : VertexSet)
                {
                    if(Matrix[currentVertex][vertex] == 1)
                    {
                           PotentialVertices.add(vertex);
                    }
                }

                //If there are no connections to the current vertex
                //Check if the current subgraph is greater than the current maxcliq value
                if(PotentialVertices.isEmpty() && (CompleteGraphVertices.size() > maxCliq))
                {
                    maxCliq = CompleteGraphVertices.size();               
                }	
                else 
                {
                    //Recursive method to check different vertices
                    MaxCliqIteration(PotentialVertices, CompleteGraphVertices);
                }
                
                //Remove vertex from the set after it has been checked for its subgraph's max clique value     
                VertexSet.remove((Integer)currentVertex); 
                CompleteGraphVertices.remove((Integer)currentVertex); 
            }
        }
        catch(Exception exc)
        {
            System.out.println("Error found: " + exc.getMessage());
        }
       
    }
    
}
