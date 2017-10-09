package edu.cpp.cs331.graphs.dshin1;
import edu.cpp.cs331.graphs.dshin1.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstrasSP implements ShortestPath
{
	public DijkstrasSP()
	{
	}//end default constructor
	
	public List<Edge> dijkstra(Graph G, Vertex source, Vertex goal)
	{
		int n = G.vList.size();
		boolean value = false;
		myVertex current;
		long min;
		int q = 0, index = 0, target = 0;
		List<myVertex> unvisited = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		List<myVertex> myVerticies = new ArrayList<>();
		
		
		//check to see if goal is in G.
		for(int i = 0; i < G.vList.size(); i++)
		{
			if(G.vList.get(i).getId() == goal.getId())
			{
				value = true;
			}
		}
		//if the goal vertex is not within the set of our verticies then return vertex -1 with a weight of 0
		if(!value)
		{
			//Vertex x = new Vertex(-1);
			//Edge e = new Edge(x,x,0);
			//edges.add(e);
			System.out.println("The specified goal vertex V" + goal.getId() + " is not within the Graph. Unable to perform Dijkstra's Algorithm");
			return edges;
		}
		//convert Vertex class to myVertex class..
		
		for(int i = 0; i < n; i++)
		{
			myVertex temps = new myVertex(G.vList.get(i).getId(), Integer.MAX_VALUE, false);
			//temp.setId(G.vList.get(i).getId());
			myVerticies.add(temps);
		}
		for(int i = 0; i < G.vList.size(); i++)
		{
			//setting the source node vertex to zero
			if(myVerticies.get(i).getId() == source.getId())
			{
				myVerticies.get(i).setNodeWeight(0);
				myVerticies.get(i).setVisitedStatus(true);
			}
			//otherwise set the vertex equal to infinity.
			else
			{
				myVerticies.get(i).setNodeWeight(Integer.MAX_VALUE);
				myVerticies.get(i).setVisitedStatus(false);
			}
		}
		
		//now we set the initial node as current. For our purposes we will exchange
		//the ordering of our myVertex array so that our "source" vertex passed into dijkstra
		//will be the first element of our array.
		if(source.getId() != myVerticies.get(0).getId())
		{
			Collections.swap(myVerticies,0,source.getId());
		}
		
		//finding the index of our goal vertex, relative to our vertex array, "myVerticies"
		for(int i = 0; i < myVerticies.size(); i++)
		{
			if(myVerticies.get(i).getId() == goal.getId())
			{
				target = i;	
			}
		}

		//step one has been completed..
		//for(int i = 0; i < myVerticies.size(); i++)
		//{
		//	System.out.println("Vertex " + myVerticies.get(i).getId() + " has a weight of " + myVerticies.get(i).getNodeWeight() );
		//}
		//current will be assigned the initial vertex in myVerticies
		// and its visited status will change to visited.
		// Now, we keep a list of all the other unvisited Verticies.
		current = myVerticies.get(0);
		current.setVisitedStatus(true);
		for(int i = 1; i < n; i++)
		{
			unvisited.add(myVerticies.get(i));
		}
		//this completes step 2.
		//for(int i = 0; i < unvisited.size(); i++)
		//{
		//	System.out.println("Vertex " + unvisited.get(i).getId() + " is in the unvisited set ");
		//}
		
		//System.out.println("what is myVerticies.get(target)?>>" + myVerticies.get(target).getId() + myVerticies.get(target).getVistitedStatus());
		while(unvisited.size() > 0 && myVerticies.get(target).getVistitedStatus() != true )
		{
			for(int i = 0; i < G.eList.size(); i++)
			{
				if(G.eList.get(i).one.getId() == current.getId())
				{

					for(int k = 0; k < unvisited.size(); k++)
					{
						
						if(G.eList.get(i).two.getId() == unvisited.get(k).getId())
						{
							//System.out.println(G.eList.get(i).one.getId() + " connects to " + G.eList.get(i).two.getId());
							if(unvisited.get(k).getNodeWeight() > (current.getNodeWeight() + G.eList.get(i).getWeight())) 
							{
								unvisited.get(k).setNodeWeight(current.getNodeWeight() + G.eList.get(i).getWeight());		
								//System.out.println("this is unvisited nodeWeight of: " + unvisited.get(k).getId() + " : " + unvisited.get(k).getNodeWeight());
							}
						}
					}
				}
			}
			//finding minimum weight of unvisited..
			min = Integer.MAX_VALUE;
			for(int j = 0; j < unvisited.size(); j++)
			{
				if(min > unvisited.get(j).getNodeWeight())
				{
					min = unvisited.get(j).getNodeWeight();
					q = j;
				}
				//System.out.println("printing unvisited: " + unvisited.get(j).getNodeWeight());
			}
			//System.out.println("min node weight is equal to " + min);
			//System.out.println("q: the index of unvisited where minimum is found is: " + q);
		
			//adding minimum weight edges.
			
			//System.out.println("What is the current value of current?? >> " + current.getId());
			for(int i = 0; i < G.eList.size(); i++)
			{
				if(G.eList.get(i).one.getId() == current.getId())
				{
					for(int j = 0; j < unvisited.size(); j++)
					{
						if(G.eList.get(i).two.getId() == unvisited.get(q).getId())
						{
							edges.add(G.eList.get(i));
							break;
						}
					}
				}
				
			}
			
			index = unvisited.get(q).getId();
			for(int i = 0; i < myVerticies.size(); i++)
			{
				if(index == myVerticies.get(i).getId())
				{
					current = myVerticies.get(i);
					current.setVisitedStatus(true);
				}
			}
			//System.out.println("The next value of current is updated to be: " + current.getId());
			unvisited.get(q).setVisitedStatus(true);
			unvisited.remove(q);
			
			//for(int i = 0; i< unvisited.size(); i++)
			//{
			//	System.out.println("These are the remaining verticies in unvisited: " + unvisited.get(i).getId());
			//}
		}//end while loop
		
		//System.out.println("Here i will start to add the edges..");
		//System.out.println("2nd added Edge should be: " + edges.get(1).toString());
	//	System.out.println("3rd added Edge should be: " + edges.get(2).toString());
	
		return edges;
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
	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal) 
	{
		return dijkstra(G,source,goal);
	}

}
