//make an array for the "colours" where Colour[this part] represents which vertex it is, and the number assigned represents the Colour.
int[] Colour = new Int[n];
//set every Colour to 1
for (int i = 0; i < n; i++){
int[i] = 1;
}
//for every edge, check which one it is, then check if the colours of the vertices are the same, if not, give the colour of the second vertex +1 
for (int edge = 0; edge < m; edge++)
for (int l = 0; l < n; l++){
for (int r = 0; r < n; r++){
if (e[edge].u = l && e[edge].v = r){
if (Colour[l] = Colour[r]){
	Colour[r]++;
}
}
}
}
// get the maximum of all colour, which is also the amount of colours necessary
System.out.println(max(Colour[] + " is a valid answer")
	
}
}

public static int max(A[]) {
int largest = 0;
for(int i = 0; i < A.length; i++)
if (A[i] > largest){
largest = A[i];
    }
return largest;
}
