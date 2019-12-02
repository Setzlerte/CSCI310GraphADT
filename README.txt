GraphADT for CSCI 310 Advanced Algorithims
Thomas Setzler

Class weightedGrapgh:

Initilaized with number of nodes and a boolean value.  The boolean value is true if the graph is directed, false otherwise.

The class has methods to get the number of edges, number of nodes, value of a node, weight of an edge from one node to another,
if an edge exists between to nodes, and all neighbors of a node. 

The class has methods to set the value of a node, add edges between nodes, delete edges between two nodes, and print the
adjacency matrix representation of the graph to a designated file.

When refrencing a node, the refrence must be done via the index within the adjacency matrix, not the node's value.


Class Main:

The main method has a number of premade demo graphs in.  My computer cannot support the 10,000 or 100,000 sized graphs so they
are commented out. The time in nanoseconds to run StronglyConnected is calculated via taking the time before, and after then
subtrating the before from the after.

populator is a method i created to randomly populate a graph with edges between the nodes.  The weight is randomly chosen between 1 and 20.

StronglyConnected determines if a graph is strongly connected by assuming the first node and its neighbors are connected,
then evaluating if all subsequent nodes are connected to the graph.  If they are, then the the method returns true, otherise it reutrns false.

