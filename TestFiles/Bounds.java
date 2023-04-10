import java.util.*;

public class Bounds {
    
    public static void main(String[] args)
    {
        //Scanner
        Scanner in = new Scanner(System.in);
        boolean even = false;

        System.out.println("Enter Vertices: ");
        int vertices = in.nextInt();
        System.out.println("Enter Edges: ");
        int edges = in.nextInt();

        int lower = CalculateLower(vertices, edges, even);
        int upper = CalculateUpper(vertices, edges, even); 

        System.out.println("Lower bound: " + lower);
        System.out.println("Upper bound: " + upper);

        //Close scanner
        in.close();

    }

    public static int CalculateLower(int v, int e, boolean even)
    {
        int low = 0;
        if(v % 2 == 0)
            even = true;

        //lower bound
        if(e > 1)
            low = 2; //if an edge exits, lower bound is always at least 2

        if(even)
        {
            if(e > ((v/2) - 1) * v)
                low = v/2;
        }
        else
        {
            if(e > (((v-1)/2) * (v-1)) ) 
                low = v/2;
        }     
        return low;
    }

    public static int CalculateUpper(int v, int e, boolean even)
    {      
        int up = v;
        //upper bound
        for(double i = 0; i < v; i++)
        {
            if(e > ( (i-1) * (i/2) ))
            {
                up = (int)i;
            }
        }
        return up;
    }
}
