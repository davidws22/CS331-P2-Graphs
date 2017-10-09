package edu.cpp.cs331.graphs.dshin1;
import edu.cpp.cs331.graphs.dshin1.Edge;
import java.util.concurrent.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Main {

	public static void main(String[] args) 
	{
		Random rand = new Random();
		int numVerts = 6;
		float density = 0.5f;
		//variable declarations
		Graph temp = new Graph(); //used to make a directed Graph
		//Graph temp2 = new Graph(); //used to make an Undirected Graph
		Graph subGraph; //used to retrieve the minimum spanning tree, returned by Prims and Kruskals
		DijkstrasSP trial1 = new DijkstrasSP(); //trial 1 and trial 2 are for Shortest paths
		FloydsSP trial2 = new FloydsSP();
		//PrimsMST trial3 = new PrimsMST(); //trial 3 and trial 4 are for Minimum Spanning Trees
		//KruskalsMST trial4 = new KruskalsMST();
		List<Edge> target = new ArrayList<>(); //target is used to hold the list of edges that are returned by the Shortest Path Algorithms
		
		long startTime = 0;
		long endTime = 0;
		long elapsedTime;
		
		
		try
		{
			temp = Graph.genRandomDirectedGraph(numVerts, density);
			//temp2 = Graph.genRandomUndirectedGraph(numVerts, density);
			
			//temp = Graph.genGraphTheorygraph();
		}
		catch(Graph.VertexAlreadyExistsException e)
		{
			System.out.println(e);
		}
		catch(Graph.DuplicateEdgeException e)
		{
			System.out.println(e);
		}
		int counter = 0;
		trial1.display(temp);
		
		while(counter < 20)
		{
		int  x = rand.nextInt(numVerts);
		int  y = rand.nextInt(numVerts);
		
		if(x == y)
		{
			y = rand.nextInt(numVerts);
		}
		//trial1.display(temp2);
	
		//trial1.display(temp);
		startTime = System.nanoTime();
		target = trial1.genShortestPath(temp, temp.vList.get(x), temp.vList.get(y)); //(Subject to change when given appropriate interface)
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		
		System.out.print("Dijkstra's " + (counter + 1) + " Time: " + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS));
		
		startTime = System.nanoTime();
		target = trial2.genShortestPath(temp, temp.vList.get(x), temp.vList.get(y)); //faulty!!
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		
		System.out.println("\tFloyd's " + (counter + 1) + " Time: " + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS));
		
		//trial2.DispShortest(target, temp.vList.get(0), temp.vList.get(4));
		
		//trial1.DispShortest(target, temp.vList.get(3), temp.vList.get(2));
		/*
		startTime = System.nanoTime();
		subGraph = trial3.genMST(temp2);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		
		System.out.println("Prim's Time: " + elapsedTime);
		//trial3.display(subGraph);
		startTime = System.nanoTime();
		subGraph = trial4.genMST(temp2);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		
		System.out.println("Kruskal's Time: " + elapsedTime);
		*/
		counter++;
		}
		
	}

}
