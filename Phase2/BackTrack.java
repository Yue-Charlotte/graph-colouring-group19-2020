// Code by Tjardo

import java.util.Arrays;

public class Backtrack
{
    private int[][] connected;
    private int[] currentcoloring;
    private int maxcolorsused;	
    private int ver;

    public Backtrack(int[][] am, int[][] vpv, int allowedC)
    {       
        connected = vpv;
        ver = am.length;
        maxcolorsused = allowedC;

        //Create the currentcoloring array and set all vertices to -1
        currentcoloring = new int[ver];    
        currentcoloring[0] = 1;
        
        for(int i = 1; i < ver; i++)
            currentcoloring[i] = -1;

        //Run main algorithm
        //We skip vertex 0, because it will never change, and start from color 1
        RecursiveBackTrack(1, 1, currentcoloring);
				
				Game.chromaticnumber = maxcolorsused;
				System.out.println("Chromatic number through backtracking: " + Game.chromaticnumber);
    }


    private void RecursiveBackTrack(int curv, int cu, int[] assignedC)
    {
        //If the current amount of colors used is less than allowed we continue
        if (cu < maxcolorsused)
        {
            //If all vertices are colored, we print the result
            if (curv < ver)
            {
                //Iterate over all available colors
                for (int c = 1; c <= cu; c++) 
                {
                    //If the vertex is allowed to be colored with the current color, we set its value
                    if (CheckValid(curv, c, assignedC)) 
                    {
                        assignedC[curv] = c;
                        RecursiveBackTrack((curv+1), cu, assignedC); //recursive step with next vertex
                    }
                }

                //If we cant color the vertex anymore we increase the total amount of colors used 
                cu++;
                assignedC[curv] = cu;
                RecursiveBackTrack((curv+1), cu, assignedC);
            }
            else if (!Arrays.stream(currentcoloring).anyMatch(x -> x == -1)) //If all vertices are colored, post solution
            {           
                System.out.println("Solution found for: " + cu);
                maxcolorsused = cu;

                for (int i = 0; i < ver; i++)
                currentcoloring[i] = assignedC[i];
            }
        }

    }

    private boolean CheckValid(int vertex, int color, int[] assignedC)
    {
        for (int v : connected[vertex])
        {
            if (currentcoloring[v] == color)
                return false;
        }
        return true;
    }
}
