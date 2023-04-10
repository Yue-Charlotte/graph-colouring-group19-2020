import java.util.ArrayList;
import java.util.Arrays;

public class Pruning 
{
    private int[][] am;
    private int minP;
    private int[][] ecpv;

    public Pruning(Graph g, int minE) //minE = minimum amount of edges required in order for vertex not te be removed
    {
        minP = minE;
        am = g.GetAdjacencyMatrix();
        ecpv = g.GetEPVList();
    }
    
    public int[][] PruneGraph()
    {
        //Determine vertices to prune
        ArrayList<Integer> PrunedV = new ArrayList<Integer>();
        for(int i = 0; i < ecpv.length; i++)
        {
            if(ecpv[i].length < minP)
            {
                PrunedV.add(i);
            }
        }

        //Create int array with vertex indices
        int count = 0;
        int totalpv = PrunedV.size();
        int[] VP = new int[totalpv];
        for (int v : PrunedV) 
        {
            VP[count] = v;
            count++;
        }

        String vps = "";
        for (int i : VP) 
        {
            vps += " " + i;
        }       

        //Create new array with new dimensions
        int[][] newG = new int[am.length-totalpv][am.length-totalpv];
        int rowc = 0;
        int colc = 0;

        //set values in new array if row or column is not in VP
        for(int r = 0; r < am.length; r++)
        {
            if(Contains(VP, r))
            {
                //skip row
            }
            else //continue in this row and check each column
            {
                for(int c = 0; c < am.length; c++)
                {
                    if(Contains(VP, c))
                    {
                        //skip column
                    }
                    else
                    {
                        newG[rowc][colc] = am[r][c];
                        colc++;
                    }
                }
                //Add 1 to succesfull added rows and set columns back to 0
                rowc++;
                colc = 0;
            }
        }
        return newG;
    }

    //simple boolean function to check if number is in the array
    private boolean Contains(int[] segm, int digit)
    {       
        return Arrays.stream(segm).anyMatch(x -> x == digit);		
    }

}
