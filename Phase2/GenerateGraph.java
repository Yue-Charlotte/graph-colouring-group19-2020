// graphFromTextFile method provided from phase 1, generateRandomGraph method by Christopher

import java.io.*;
import java.util.*;

public class GenerateGraph {
	private final static String COMMENT = "//";	
	
	public static ColEdge[] graphFromTextFile(String inputfile){
		boolean seen[] = null;

		//! n is the number of vertices in the graph
		int n = -1;
		
		//! m is the number of edges in the graph
		int m = -1;
		
		//! e will contain the edges of the graph
		ColEdge e[] = null;

		try { 
			FileReader fr = new FileReader(inputfile);
			BufferedReader br = new BufferedReader(fr);

			String record = new String();
				
			//! The first few lines of the file are allowed to be comments, staring with a // symbol.
			//! These comments are only allowed at the top of the file.
				
			//! -----------------------------------------
			while ((record = br.readLine()) != null) {
				if( record.startsWith("//") ) continue;
				break; // Saw a line that did not start with a comment -- time to start reading the data in!
			}

			if( record.startsWith("VERTICES = ") ){
				n = Integer.parseInt( record.substring(11) );					
				if(Group19Phase2.DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
			}

			seen = new boolean[n+1];	
				
			record = br.readLine();
				
			if( record.startsWith("EDGES = ") ) {
				m = Integer.parseInt( record.substring(8) );					
				if(Group19Phase2.DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
			}

			e = new ColEdge[m];	
											
			for(int d = 0; d < m; d++){
				if (Group19Phase2.DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
					record = br.readLine();
					String data[] = record.split(" ");
					if (data.length != 2) {
						System.out.println("Error! Malformed edge line: " + record);
						System.exit(0);
					}
					e[d] = new ColEdge();
					
					e[d].u = Integer.parseInt(data[0]);
					e[d].v = Integer.parseInt(data[1]);

					seen[ e[d].u ] = true;
					seen[ e[d].v ] = true;
					
					if(Group19Phase2.DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
			
					}
								
				String surplus = br.readLine();
				if( surplus != null ) {
					if( surplus.length() >= 2 ) if(Group19Phase2.DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
				}
				
				Group19Phase2.graphVertexCount = n;
				
				return e;
			}
		catch (IOException ex) { 
			// catch possible io errors from readLine()
			System.out.println("Error! Problem reading file "+inputfile);
		}

		for( int x=1; x<=n; x++ ){
			if( seen[x] == false ){
				if(Group19Phase2.DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
			}
		}

		return null;
	}
	
	/**
	 * Generate a random graph based on the amount of vertices and edges.
	 * @param n amount of vertices
	 * @param m amount of edges
	 * @return ColEdge[] e, which contains every edge's respective vertices.
	 */
	public static ColEdge[] generateRandomGraph(int n, int m){
		boolean DEBUGprintCurrentEdge;

		ColEdge e[] = new ColEdge[m];
		
		Group19Phase2.graphVertexCount = n;
		
		for(int i = 0; i < m; i++){
			DEBUGprintCurrentEdge = true;

			e[i] = new ColEdge();
			
			e[i].u = (int)(Math.random()*n) + 1;
			e[i].v = e[i].u;

			// Ensure that an edge strictly connects two vertices that aren't the same (otherwise it will be impossible to get a chromatic number for this graph...)
			while (e[i].v == e[i].u) {
				e[i].v = (int)(Math.random()*n) + 1;
			}

			// Ensure we don't have this edge already. If we already have this edge, try another random connection.
			for(int j = 0; j < i; j++) {
				if ((e[j].u == e[i].u && e[j].v == e[i].v) || (e[j].u == e[i].v && e[j].v == e[i].u)) {
					i--;
					DEBUGprintCurrentEdge = false;
					if (Group19Phase2.DEBUG) System.out.println("Redoing edge...");
					break;
				}
			}
			
			if (DEBUGprintCurrentEdge && Group19Phase2.DEBUG){
				System.out.println(e[i].u + " " + e[i].v);
			}
		}
		return e;
	}
}