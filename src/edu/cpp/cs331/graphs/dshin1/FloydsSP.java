package edu.cpp.cs331.graphs.dshin1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.cpp.cs331.graphs.dshin1.Graph;

public class FloydsSP implements ShortestPath
{
	List<Edge> edges = new ArrayList<>();
	List<Integer> verts = new ArrayList<>();
	long [][] path;
	long [][]distance;
	long [][]matrix;
	int val;
	public FloydsSP()
	{
	}

	public List<Edge> Floyd(Graph G,Vertex source, Vertex dest)
	{
		boolean target1 = false;
		boolean target2 = false;
		//preliminary check to see if both source and dest are indeed in G.
		for(int i = 0; i < G.vList.size(); i++)
		{
			if(source.getId() == G.vList.get(i).getId())
			{
				target1 = true;
			}
			if(dest.getId() == G.vList.get(i).getId())
			{
				target2 = true;
			}
		}
		
		if(!target1 || !target2)
		{
			System.out.println("Source or destination Vertex was not found in the given Graph G.");
			return edges;
		}
		//dynamically allocates an NxN matrix, where N is the number of vertices
		matrix = new long[G.vList.size()][G.vList.size()]; 
		//distance = new long[G.vList.size()][G.vList.size()];
		path = new long[G.vList.size()][G.vList.size()];

		
		//conversion from Graph to Adjacency MATRIX
		//this for-loop block just places 0's along the main diagonal and MAX_VALUE everywhere else.
		for(int i = 0; i < G.vList.size(); i++)
		{
			for(int j = 0; j < G.vList.size(); j++)
			{
				if(i == j)
				{
					matrix[i][j] = 0;
				}
				else
				{
					matrix[i][j] = Integer.MAX_VALUE; //integer.MAX_VALUE equals 2147483647
				}
			}
		}
		//this next for loop assigns the appropriate values for the adjacency matrix given from G.
		for(int i = 0; i < G.eList.size(); i++)
		{
			matrix[G.eList.get(i).one.getId()][G.eList.get(i).two.getId()] = G.eList.get(i).getWeight();
		}
		//we have successfully converted a directed graph to an Adjacency matrix at this point.

		//printing the dynamically allocated matrix with corresponding weights.
		/*
		for(int i = 0; i < G.vList.size(); i++)
		{
			for(int j = 0; j < G.vList.size(); j++)
			{
				System.out.printf("%15d",matrix[i][j]);
			}
			System.out.println("");
		}
		*/
		//modified
		for(int i = 0; i < G.vList.size(); i++)
		{
			for(int k = 0; k < G.vList.size(); k++)
			{
				if(matrix[i][k] == Integer.MAX_VALUE)
				{
					path[i][k] = -1;
				}
				else
					path[i][k] = i;
			}
		}
		for(int i = 0; i < G.vList.size(); i++)
		{
			path[i][i] = i;
		}
		
		distance = shortestpath(matrix, path);
		
		/*
		System.out.println("=========================================================");
		display(G);		
		System.out.println("=========================================================");
		
		
		System.out.println("Outputting the distance matrix: ");
		for (int i = 0; i < G.vList.size(); i++)
		{
			for(int k = 0; k < G.vList.size(); k++)
			{
				System.out.printf("%15d", distance[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("This is printing out the path matrix");
		for(int i = 0; i < G.vList.size(); i++)
		{
			for(int k = 0; k < G.vList.size(); k++)
			{
				System.out.printf("%15d", path[i][k]);
			}
			System.out.println();
		}
		*/
		
		//System.out.println("The shortest path from " + source.getId() + " and " + dest.getId() + " is: ");
    	int start = source.getId();
    	int end = dest.getId();
    	Scanner lineScanner;
    	  
    
    	// The path will always end at end.
    	String myPath = end + "";
    
    	// Loop through each previous vertex until you get back to start.
    	while (path[start][end] != start) {
    		myPath = path[start][end] + " -> " + myPath;
    		end = (int)path[start][end];
    	}
    	myPath = start + " -> " + myPath;
    	//System.out.println("Here's the path "+myPath);
    	lineScanner = new Scanner(myPath);
    	while (lineScanner.hasNext())
    	{ //Check if there is anything in the line  
            if (lineScanner.hasNextInt())
            { 
                //System.out.println(lineScanner.nextInt());
                verts.add(lineScanner.nextInt());
                
            }  
            else 
            {  
                lineScanner.next();  
            }  
        }     
    	for(int i = 0; i < verts.size()-1; i++)
    	{
    		for(int k = 0; k < G.eList.size(); k++)
    		{
    			if(G.eList.get(k).one.getId() == verts.get(i) && G.eList.get(k).two.getId() == verts.get(i+1))
    			{
    				edges.add(G.eList.get(k));
    			}
    		}
    	}
    	//DispShortest(edges, source, dest);
		return edges;
	}

	public static long[][] shortestpath(long[][] adj, long[][] path)
	{

    	int n = adj.length;
    	long[][] ans = new long[n][n];
    
    	// Implement algorithm on a copy matrix so that the adjacency isn't
    	// destroyed.
    	copy(ans, adj);

    	// Compute successively better paths through vertex k.
    	for (int k=0; k<n;k++) {

      		// Do so between each possible pair of points.
      		for (int i=0; i<n; i++) {
        		for (int j=0; j<n;j++) {
        		
        
          			if (ans[i][k]+ans[k][j] < ans[i][j]) {
          				ans[i][j] = ans[i][k]+ans[k][j];
          				path[i][j] = path[k][j];
          			}
          		}
      		}
    	}
    
    	// Return the shortest path matrix.
    	return ans;
  	}

	// Copies the contents of array b into array a. Assumes that both a and
  	// b are 2D arrays of identical dimensions.
  	public static void copy(long[][] a, long[][] b) {

    	for (int i=0;i<a.length;i++)
      		for (int j=0;j<a[0].length;j++)
        		a[i][j] = b[i][j];
  	}
  	
	
	
	public void display(Graph temp)
	{
		System.out.println("These are the list of Verticies:");
		for(int i = 0; i < temp.vList.size(); i++)
		{
			System.out.println(" Vertex " + i+ ": " + temp.vList.get(i));
		}
		System.out.println("These are the list of Edges with their associated verticies and Weight: ");
		{
			for(int i = 0; i < temp.eList.size(); i++)
			{
				System.out.println("Edge " + i + " joins " + temp.eList.get(i).one.getId() + " to " + temp.eList.get(i).two.getId() + " at a weight of " + temp.eList.get(i).weight);
			}
		}
	}
	

	public void DispShortest(List<Edge> x, Vertex source, Vertex dest)
	{
		int counter = 0;
		
		System.out.println("The shortest path from " + source.getId() + " and " + dest.getId() + " is: ");
		for(int i = 0; i < x.size(); i++)
		{
			System.out.println("Edge " + i + " joins " + x.get(i).one.getId() + " to " + x.get(i).two.getId() + " at a weight of " + x.get(i).weight);
			counter += x.get(i).weight;
		}
		System.out.println("Total weight is: " + counter);
	}
	
	
	@Override
	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal) {
		// TODO Auto-generated method stub
		return Floyd(G, source, goal);
	}
		
}
