
/*
 * Meg Hanson 
 * Natalie Kocsis
 * Project 3 
 * 4/22
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;

public class StopContagion {
    private static Graph g;
    
    public static void createPanel()
    {
    	JFrame jf = new JFrame();
    	jf.setTitle("Project 3");
    	jf.setSize(600,400);
    	jf.setVisible(true);
    	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void readFile(String s, String methodtype, int radius, int numofremovals) {
//    	createPanel();
        g = new Graph();
        File file = new File(s);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                int data1 = myReader.nextInt();
                Node node1 = g.addNodetoList(data1);
                int data2 = myReader.nextInt();
                Node node2 = g.addNodetoList(data2);
                node1.getAdjlist().add(node2);
                node2.getAdjlist().add(node1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println("printing out the list of nodes (should be all of them");
        //g.printlistofnodes();
        
        //either calling the collective influence or highest degree removal method
        if (methodtype.equals("c"))
        {
        	runCollectiveInfluenceMethod(numofremovals, radius);
        	
        }
        else if (methodtype.equals("d"))
        {
        	runHighestDegreeMethod(numofremovals);
        }
        
    }
    
    public static void runHighestDegreeMethod(int numofremovals)
    {
    	for (int i = 0; i < numofremovals; i++)
    	{
            Node removed = g.highestDegreeNode();
            removingANode(removed, "d");
    	}
        //System.out.println("\n**PRINTING NEW LIST**\n");
    	//g.printlistofnodes();
    }
    
    public static void removingANode(Node removed, String methodtype)
    {
//       g.listofnodes.remove(removed);
    	removed.setRemoved(true);
        int tempDegree = removed.getDegree();
        int tempCI = removed.getCollectiveInfluence();
        for (int j = 0; j < g.listofnodes.size(); j++) {
            if (g.listofnodes.get(j).getNum() == removed.getNum()) {
                g.listofnodes.get(j).getAdjlist().clear();
            } else {
                g.listofnodes.get(j).removeNodeFromList(removed);
            }
//            System.out.println("Node: " + g.listofnodes.get(j).getNum());
//            g.listofnodes.get(j).printadjlist();
        }

        if (methodtype.equals("d"))
        {
        	System.out.println(removed.getNum() + " " + tempDegree);
        }
        else if (methodtype.equals("c"))
        {
        	System.out.println(removed.getNum() + " " + tempCI);
        }
    }
    
    public static void runCollectiveInfluenceMethod(int numofremovals, int r) {
        for (int j = 0; j < numofremovals; j++) {
            Node source = null;
            for (int i = 0; i < g.listofnodes.size(); i++) {
//                System.out.println("running the collective influence for node " + g.listofnodes.get(i).getNum());
                source = g.listofnodes.get(i);
                if (source.isRemoved()) {
                    source.setCollectiveInfluence(0);
                } else {
                    int finalci = g.calculateCollectiveInfluenceforanode(source, r);
                    g.listofnodes.get(i).setCollectiveInfluence(finalci);
                }
//                System.out.println("Printing ci for node " + g.listofnodes.get(i).getNum() + ": " + g.listofnodes.get(i).getCollectiveInfluence());
                g.clearDistancesandParents();
            }
                Node removed = g.highestCI();
                //now remove that node from every adjacency list
                removingANode(removed, "c");
                for (int k = 0; k < g.listofnodes.size(); k++) {
                    g.listofnodes.get(k).setCollectiveInfluence(0);
                }
        }
    }

    public static void main(String args[]) {
    		int i = 0;
            String input = args[i];
            switch (input) {
                case "-d":
                    readFile(args[args.length-1], "d", -1, Integer.parseInt(args[i+1]));
                    break;
                case "-r":
                    readFile(args[args.length - 1], "c", Integer.parseInt(args[1]), Integer.parseInt(args[i+2]));
                    break;
                default:
                   //running with the default radius of 2 
                	readFile(args[args.length - 1], "c", 2, Integer.parseInt(args[0]));
                    break;
            }
    }
}