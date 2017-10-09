package edu.cpp.cs331.graphs.dshin1;
import edu.cpp.cs331.graphs.dshin1.Graph;

public class PrimsMST implements MinimumSpanningTree
{
	Graph temp = new Graph();
	Edge e;
	Vertex one;
	Vertex two;
	Graph primMST(Graph G)
    {
		//conversion to matrix from G
		int [][]matrix = new int[G.vList.size()][G.vList.size()];
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

		//Now construct a Symmetrical Matrix about the main diagonal
		for(int i = 0; i < G.vList.size(); i++)
		{
			for( int k = 0; k < G.vList.size(); k++)
			{
				if(i == k)
				{
					continue;
				}
				else
				{
					matrix[k][i] = matrix[i][k];
				}
			}
		}
		
		
        // Array to store constructed MST
        int parent[] = new int[G.vList.size()];
 
        // Key values used to pick minimum weight edge in cut
        int key[] = new int [G.vList.size()];
 
        // To represent set of vertices not yet included in MST
        boolean mstSet[] = new boolean[G.vList.size()];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < G.vList.size(); i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
                        // picked as first vertex
        parent[0] = -1; // First node is always root of MST
 
        // The MST will have V vertices
        for (int count = 0; count < G.vList.size()-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet,G);
 
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
 
            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < G.vList.size(); v++)
 
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (matrix[u][v]!=0 && mstSet[v] == false &&
                    matrix[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = matrix[u][v];
                }
        }

        for(int i = 0; i < G.vList.size(); i++)
        {
        	temp.vList.add(G.vList.get(i));	
        }
        
        for(int i = 1; i < G.vList.size(); i++)
        {
        	one = new Vertex(parent[i]);
        	//System.out.println("this is parent[i]: " + parent[i]);
        	two = new Vertex(i);
        	e = new UndirectedEdge(one, two, matrix[i][parent[i]]);
        	temp.eList.add(e);
        	
        }
        return temp;
    }
	
    int minKey(int key[], boolean mstSet[], Graph F)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;
 
        for (int v = 0; v < F.vList.size(); v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }
    
	
	@Override
	public Graph genMST(Graph g) 
	{
		temp = primMST(g);
		return temp;
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
	
	
}
