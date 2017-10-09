package edu.cpp.cs331.graphs.dshin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalsMST implements MinimumSpanningTree
{
	Graph temp = new Graph();
	List<Edge> edge = new ArrayList<>();
	

	Graph Kruskal(Graph G)
	{
	       	int e = 0;  
	        int i = 0;  
	        for(i = 0; i < G.vList.size(); i++)
	        {
	        	temp.vList.add(G.vList.get(i));
	        }
	      
	        //copying all edges from G to 'edge' arrayList
	        for(i = 0; i < G.eList.size(); i++)
	        {
	        	edge.add(G.eList.get(i));
	        }
	        //sorting all edges in edge in non-decreasing order by weight
	        Collections.sort(edge, new Comparator<Edge>() {
	            public int compare(Edge p1, Edge p2) 
	            {
	                return p1.getWeight()- p2.getWeight();
	            }
	        });
	       
	 
	 
	        subset subsets[] = new subset[G.vList.size()];
	        for(i=0; i<G.vList.size(); ++i)
	            subsets[i]=new subset();
	 
	        
	        for (int v = 0; v < G.vList.size(); ++v)
	        {
	            subsets[v].upper = v;
	            subsets[v].echelon = 0;
	        }
	 
	        i = 0;  
	 
	        // Number of edges to take is vList-1
	        while (e < G.vList.size() - 1)
	        {
	           
	            Edge next_edge;
	            next_edge = edge.get(i);
	            i++;
	 
	            int x = find(subsets, next_edge.one.getId());
	            int y = find(subsets, next_edge.two.getId());

	            if (x != y)
	            {    
	            	temp.eList.add(next_edge);
	            	e++;
	                
	                Union(subsets, x, y);
	            }
	           
	        }
	 	     
	        //display(temp);
		return temp;
	}
	
	 // subset for union find
    public class subset
    {
        int upper, echelon;
    }
	
    
    // A utility function to find set of an element i
    int find(subset subsets[], int i)
    {
        if (subsets[i].upper != i)
            subsets[i].upper = find(subsets, subsets[i].upper);
 
        return subsets[i].upper;
    }
    
    void Union(subset subsets[], int x, int y)
    {
        int X = find(subsets, x);
        int Y = find(subsets, y);
 
        if (subsets[X].echelon < subsets[Y].echelon)
            subsets[X].upper = Y;
        else if (subsets[X].echelon > subsets[Y].echelon)
            subsets[Y].upper = X;
 
        else
        {
            subsets[Y].upper = X;
            subsets[X].echelon++;
        }
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
	
	@Override
	public Graph genMST(Graph G) 
	{
		//print original G.
		//display(G);
		
		return Kruskal(G);
		
	}

}
