package edu.cpp.cs331.graphs.dshin1;

//This class was specifically implemented for DijkstrasSP.java;
//We will implement myVertex so that it has an accumulated path weight and its vertex number.

public class myVertex 
{
	protected int id;
	protected long nodeWeight;
	boolean visited; 
	
	public myVertex()
	{
		
	}
	public myVertex(int id, long nodeWeight, boolean visited)
	{
		this.id = id;
		this.nodeWeight = nodeWeight;
		this.visited = visited;
	}
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public long getNodeWeight()
	{
		return nodeWeight;
	}
	
	public void setNodeWeight(long nodeWeight)
	{
		this.nodeWeight = nodeWeight;
	}
	
	public boolean getVistitedStatus()
	{
		return visited;
	}
	
	public void setVisitedStatus(boolean value)
	{
		visited = value;
	}
	
	
}
