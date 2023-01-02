# Graphs-Removing-Highest-Collective-Influence

For this project, we were asked to implement two main algorithms, one involving removing the edges coming to/from n nodes in a graph with the highest
degree and another algorithm which removes the edges coming to/from n nodes in a graph with the highest collective influence. The collective influence is
the degree of the node - 1 times the sum of all the nodes of radius 2 minus from the source node. The main reason we are using these two approaches to
remove the connections/edges from a vertice to other vertices is to show how both approaches will work, however removing the collective influence method
can oftentimes be more efficient in decreasing the connections among a group of nodes or in this case, people which can be advantageous when trying to
stop a disease. 

The first StopContagion.java is the main driver class, which processes the input from the user, reads the text file to create the edges and vertices of the
graph and then finds the veritice to remove all the edges from the highest degree node or the highest collective influence node n number of times. The
number of times this process occurs (n times) is specified by the user. 

In the second java file Graph.java, the main helper nodes such as creating a list of nodes in the graph, finding the highest degree and/or collective
influence value in the graph, calculating the collective influence for a given source node of radius r using the shortest path algorithm (the shortest path
algorithm is another method we implemented). 

In the third java file Node.java, we create the Node class to create a Node object (or veritice) which holds a number of instance variables. The instance
variables held in a node include its number, its adjacency list, its degree, collective influence, a isVisited and isRemoved boolean, a distance, and a
parent Node (the distance, isVisited and parent instance variables are used in shortest path dijkstra's algorithm). In the Node class, we also include
setters and getters for all the instance variables which are useful in the other classes. 

****
How to Run:

Compiler: javac Graph.java Node.java StopContagion.java

Run: java StopContagion.java [-d] [-r RADIUS] num_nodes input_file
****
Arguments:
-d: optional argument; if given, the program will use the degree of each node and not its collective influence. Otherwise, it will operate using the
collective influence.

 -r RADIUS: optional argument; the program will use the value RADIUS for r, or will default to r=2 otherwise.
 
 num_nodes: the number of nodes to remove; mandatory argument.

example: java StopContagion.java -r 2 4 test.txt
