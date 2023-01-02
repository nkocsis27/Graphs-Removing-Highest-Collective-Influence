/*
 * Meg Hanson 
 * Natalie Kocsis
 * Project 3 
 * 4/22
 */

import java.util.ArrayList;

public class Node {
    private int num;
    private int degree;
    private int collectiveInfluence;
    private ArrayList<Node> adjlist;
    private boolean isVisted;
    private boolean isRemoved;
    private int distance;
    private Node parent;
    
    public Node(int n)
    {
        this.isVisted = false;
        num = n;
        this.degree = 0;
        this.collectiveInfluence = 0;
        this.adjlist = new ArrayList<Node>();
        this.isRemoved = false;
    }
    
    public ArrayList<Node> getAdjlist() {
		return adjlist;
	}

    public int getDistance() {
        return distance;
    }

    public Node getParent() {
        return parent;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setAdjlist(ArrayList<Node> adjlist) {
		this.adjlist = adjlist;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

    public int getNum() {
        return num;
    }

    public int getCollectiveInfluence() {
        return collectiveInfluence;
    }
    
    public boolean isVisted() {
        return isVisted;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void printadjlist()
    {
        System.out.println("printing adj list: ");
    	for (int i = 0; i < adjlist.size(); i++)
    	{
    		System.out.print(adjlist.get(i).getNum() + "     ");
    	}
    	System.out.println();
    }
    
    public int getDegree()
    {
        this.degree = adjlist.size();
    	return degree;
    }

    public void setCollectiveInfluence(int collectiveInfluence) {
        this.collectiveInfluence = collectiveInfluence;
    }

    public void setVisted(boolean visted) {
        isVisted = visted;
    }

    public void removeNodeFromList(Node n) {
        int numberofnode = n.getNum();
        for (int i = 0; i < this.getAdjlist().size(); i++) {
            if (this.getAdjlist().get(i).getNum() == numberofnode) {
                this.getAdjlist().remove(i);
            }
        }
    }

}