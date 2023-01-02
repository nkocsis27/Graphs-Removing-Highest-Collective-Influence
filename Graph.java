/*
 * Meg Hanson 
 * Natalie Kocsis
 * Project 3 
 * 4/22
 */

import java.util.*;
public class Graph {
    public static ArrayList<Node> listofnodes;

    public Graph() {
    	listofnodes = new ArrayList<Node>();
    }
    
    public static Boolean isNodeinList(int n)
    {
    	for (int i = 0; i < listofnodes.size(); i++)
    	{
    		if (listofnodes.get(i).getNum() == n)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    public static Node searchForNode(int data)
    {
    	for (int i = 0; i < listofnodes.size(); i++)
    	{
    		if (listofnodes.get(i).getNum() == data)
    		{
    			return listofnodes.get(i);
    		}
    	}
    	//didnt find it but it shouldn't get to this point 
    	return null;
    }
    
    public void printlistofnodes()
    {
    	for (int i = 0; i < listofnodes.size(); i++)
    	{
    		System.out.println("Current node: " + listofnodes.get(i).getNum());
    		System.out.println("Degree of node: " + listofnodes.get(i).getDegree());
    		listofnodes.get(i).printadjlist();
    	}
    }
    
    public Node addNodetoList(int data)
    {
    	if (!(isNodeinList(data)))
    	{
    		//makes a new node and adds it to the array list 
    		Node newnode = new Node(data);
    		listofnodes.add(newnode);
    		return newnode;
    	}
    	return searchForNode(data);
    }

    public Node highestDegreeNode()
    {
    	int maxdegree = 0;
    	Node hdegreenode = listofnodes.get(0);
    	for (int i = 1; i < listofnodes.size(); i++) {
			if (!listofnodes.get(i).isRemoved()) {
				if (listofnodes.get(i).getDegree() == maxdegree) // if both nodes have same high degree
				{
					//check to see which vertices number is smaller and make that the max degree Node
					if (listofnodes.get(i).getNum() < hdegreenode.getNum() ) {
						//make the smaller vertices number the new highest degree
						hdegreenode = listofnodes.get(i);
						maxdegree = listofnodes.get(i).getDegree();
					}
				} else if (listofnodes.get(i).getDegree() > maxdegree) {
					hdegreenode = listofnodes.get(i);
					maxdegree = listofnodes.get(i).getDegree();
				}
			}
		}
    	//System.out.println("highest degree node: " + hdegreenode.getNum() + "\ndegree: " + hdegreenode.getDegree() );
			return hdegreenode;
    }

    public Node highestCI() {
		int maxCI = 0;
		Node maxCInode = listofnodes.get(0);
		for (int i = 1; i < listofnodes.size(); i++) {
			if (!listofnodes.get(i).isRemoved()) {
				if (listofnodes.get(i).getCollectiveInfluence() == maxCI) // if both nodes have same high degree
				{
					//check to see which vertices number is smaller and make that the max degree Node
					if (listofnodes.get(i).getNum() < maxCInode.getNum()) {
						//make the smaller vertices number the new highest degree
						maxCInode = listofnodes.get(i);
						maxCI = listofnodes.get(i).getCollectiveInfluence();
					}
				} 
				else if (listofnodes.get(i).getCollectiveInfluence() > maxCI) {
					maxCInode = listofnodes.get(i);
					maxCI = listofnodes.get(i).getCollectiveInfluence();
				}
			}
		}
		return maxCInode;
	}

    public int calculateCollectiveInfluenceforanode(Node source, int r)
    {
    	int ci =0;
    	int degreeofsource = source.getDegree() - 1;
			ArrayList<Node> arr = SP(source, r);

			for (int j = 0; j < arr.size(); j++)
			{
				ci+= arr.get(j).getDegree() - 1;
			}
			//multiply the degree - 1 of source times the sum of all degrees - 1 of nodes r away
			//from the source 
			int finalci = ci * degreeofsource;
			return finalci;
		}

    public void clearDistancesandParents() {
		for (int i = 0; i < listofnodes.size(); i++) {
			listofnodes.get(i).setDistance(-1);
			listofnodes.get(i).setParent(null);
		}
	}

    public void initNodesSP(Node s) {
    	s.setDistance(0);
    	for (int i = 0; i < listofnodes.size(); i++) {
    		listofnodes.get(i).setParent(null);
    		listofnodes.get(i).setVisted(false);
			if (listofnodes.get(i).getNum() != s.getNum()) {
				listofnodes.get(i).setDistance(Integer.MAX_VALUE);
			}
		}
	}

	public ArrayList<Node> SP(Node s, int r) {
    	int greatestDis = 1;
    	initNodesSP(s);
		Queue<Node> q = new LinkedList<Node>();
			q.add(s);
		while (!q.isEmpty() && greatestDis < r) {
			Node temp = q.poll();
			if(temp == null){
				continue;
			}
			int size = temp.getAdjlist().size();
			for (int i = 0; i < size; i++) {
				if (!temp.getAdjlist().get(i).isVisted()) {
					temp.getAdjlist().get(i).setParent(temp);
					if (temp.getAdjlist().get(i).getDistance() > temp.getDistance() + 1) {
						int count = temp.getDistance() + 1;
						temp.getAdjlist().get(i).setDistance(temp.getDistance() + 1);
						if (count > r) {
							greatestDis = count;
						}
						q.add(temp.getAdjlist().get(i));
					}
				}
			}
				temp.setVisted(true);
		}
		ArrayList<Node> nodesofradiusr = new ArrayList<>();
		for (int i = 0; i < listofnodes.size(); i++)
		{
			if (listofnodes.get(i).getDistance() == r)
			{
				nodesofradiusr.add(listofnodes.get(i));
			}
		}
		return nodesofradiusr;
	}
}