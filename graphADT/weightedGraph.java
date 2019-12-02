package graphADT;
import java.io.*;

public class weightedGraph {
    // creates a graph using a matrix to allow for easier directed and undirected graphs
    //ALL NODE REFRENCES ARE TO THEIR INDEX WITHIN adjMatrix[][], NOT THEIR VALUES
    //Values can be obtained from the corresponding spot in nodeValues[]

    private int[][] adjMatrix;
    private Object[] nodeValues;
    private int edgeCT;
    private boolean directed; // true if a directed graph


    // construct basic graph
    public weightedGraph() { }

    public void init(int numVerticies, boolean directedStatus) {
        // initialize graph of size numVerticies
        adjMatrix = new int[numVerticies][numVerticies];
        nodeValues = new Object[numVerticies];
        edgeCT = 0;
        directed = directedStatus;
    }
    // ************** GETTERS****************
    public int getEdgeCT() {
        //get number of edges
        return edgeCT;
    }

    public int getNodeCT() {
        //get number of nodes
        return nodeValues.length;
    }

    public Object getValue(int nodeIndex) {
        // get node value at nodeIndex.
        // nodeIndex refers to the position within the matrix, not the value of the node
        return nodeValues[nodeIndex];
    }
    public int getWeight(int startNode, int endNode) {
        //get weight of edge from startnode to endNode
        return adjMatrix[startNode][endNode];
    }
    public boolean getEdge(int startNode, int endNode) {
        //get if an edge exists from start node to end node
        return adjMatrix[startNode][endNode] != 0;
    }
    public int[] getNeighbors(int targetNode) {
        // Returns an array of the indicies of all neighbors for the target node
        int i;
        int count = 0;
        int[] temp;

        for (i=0; i<nodeValues.length; i++)
            if (adjMatrix[targetNode][i] != 0) count++;
        temp = new int[count];
        for (i=0, count=0; i<nodeValues.length; i++)
            if (adjMatrix[targetNode][i] != 0) temp[count++] = i;
        return temp;
    }

    // ******************* SETTERS *********************

    public void setValue(int index, Object value) {
        //set node at index with value
        nodeValues[index] = value;
    }

    // *********************** MODIFIERS *************************************
    public void addEdge(int startNode, int endNode, int edgeWeight) {
        // creates an edge from startNode to endNode of edgeWeight
        if (edgeWeight == 0) return; // weight cannot be 0
        if (directed == true) {
            adjMatrix[startNode][endNode] = edgeWeight;
            edgeCT++;
        }
        else {
            adjMatrix[startNode][endNode] = edgeWeight;
            adjMatrix[endNode][startNode] = edgeWeight;
            edgeCT++;
        }
    }


    public void deleteEdge(int startNode, int endNode) {
        //deletes edge from startNode to endNode

        if (directed == true) {
            adjMatrix[startNode][endNode] = 0;
            edgeCT--;
        }
        else {
            adjMatrix[startNode][endNode] = 0;
            adjMatrix[endNode][startNode] = 0;
            edgeCT--;
        }
    }
    public void graphPrint(String fileName)  {
        //writes the adjacency matrix for the graph ADT to a designated file (fileName)
        try {
            PrintWriter graphWrite = new PrintWriter(fileName);
            int i = 0;
            int j = 0;
            String outputLine = "\t";
            for (i = 0; i < getNodeCT(); i++) {
                //prints top line of  labels to file
                graphWrite.write(Integer.toString(i) + "\t");
            }
            graphWrite.write(" \n");

            for (i = 0; i < getNodeCT(); i++) {
               graphWrite.write(Integer.toString(i) + " |");
                for (j = 0; j < getNodeCT(); j++) {
                   graphWrite.write(Integer.toString(adjMatrix[i][j]) + "\t");
                }
                graphWrite.write("\n");
                //System.out.println(outputLine);
            }
            graphWrite.close();

        }//end try
        catch (IOException e){
            e.printStackTrace();
        }



    }
}







