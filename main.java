//Thomas Setzler
//CSCI 310 Advanced Aglorithims
//GraphADT

import graphADT.weightedGraph;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args){

        //size 10 graph
        weightedGraph size10 = new weightedGraph();
        size10.init(10,false);
        size10 = populator(size10);
        size10.graphPrint("size10.txt");
        System.out.println("size 10 done");
        //size 100
        weightedGraph size100 = new weightedGraph();
        size100.init(100,false);
        size100 = populator(size100);
        size100.graphPrint("size100.txt");
        System.out.println("size 100 done");
        long start = System.nanoTime();
        System.out.println("Size 100 is Strongly Connected? " + stronglyConnected(size100));
        long end = System.nanoTime();
        long ellapsedTime = (end - start);
        System.out.print("For size 100, the time(in nanoseconds) to compute strongly connected is: ");
        System.out.println(ellapsedTime);

        /*

        // had to comment out size 10,000 and size 100,000 as my computer cannot handle that size of a data set

        //size 10,000
        weightedGraph size10000 = new weightedGraph();
        size10000.init(10000,false);
        size10000 = populator(size10000);
        size10000.graphPrint("size10,000.txt");
        System.out.println("size 10,000 done");
        //size 100,000
        weightedGraph size100000 = new weightedGraph();
        size100000.init(100000,false);
        size100000 = populator(size100000);
        size100000.graphPrint("size100,000.txt");
        System.out.println("size 100,000 done");
         */
        //BIPARTITE GRAPH
        int i;
        weightedGraph bipartite = new weightedGraph();
        bipartite.init(5,true);
        for (i =0; i < 5; i++){
            bipartite.setValue(i,i+1);//sets the value of each node to a value greater than one
        }
        bipartite.addEdge(0,1,5);
        bipartite.addEdge(0,2,5);
        bipartite.addEdge(1,3,4);
        bipartite.addEdge(4,1,3);
        bipartite.graphPrint("bipartiteGraph.txt");





        System.out.println("DONE");
    }  //end main
    public static weightedGraph populator(weightedGraph G){
        //fills the graph with edges based on randomizations

        //variables and random pools
        Random rand = new Random();
        Random randWeight = new Random();
        int newWeight;
        int size = G.getNodeCT();
        int temp = 0;
        int i = 0;
        int j = 0;

        for(i = 0; i < size; i++){
            // loops through each node and assigns it edges to other nodes randomly
            G.setValue(i,i);
            for (j = 0; (j < (size/3 + (size /5))); j++) {
                // gives each node a number of edges contigent on the size of the graph
                temp = rand.nextInt(size);
                while (temp == i) {
                    //prevents node from having an edge to itself
                    temp = rand.nextInt(size);
                }//end while
                newWeight = randWeight.nextInt(20); // generates a random weight
                G.addEdge(i,temp,newWeight);
            }//end J Loop

        } // end I Loop
        return G;
    }// end populator

    public static boolean stronglyConnected(weightedGraph G){
        //returns true or false depending on if G is strongly connected
        int size = G.getNodeCT();
        boolean[] connected = new boolean[size];

        int i = 0;
        int[] tempNeighbors;
        int j = 0;
        int connectCT = 0;
        boolean backtrack = false;


        for(i =0; i< G.getNodeCT(); i++){
            //clears array each itteration and resets values
            tempNeighbors = new int[size];
            tempNeighbors = G.getNeighbors(i);
            backtrack = false;

            if( i == 0){
                //assumes that if node 0 is not connected, it cannot be strongly connected.
                connected[i] = true;
                for(j=0; j<tempNeighbors.length;j++){
                    connected[tempNeighbors[i]] = true;
                }
            }
            else{
                for(j =0; j < tempNeighbors.length; j++){
                    // iterates through the neighbors of each node and adds them to a list of booleans of connected nodes if one is connected
                    if(connected[tempNeighbors[j]] == true){
                        if(backtrack == false){
                            j = 0;
                            backtrack = true;
                            connected[i] = true;} // backtracks so entire list of neighbors can be added if it is determined they are connected
                    }
                    else if(backtrack == true) {
                        //once backtracking begins, adds each connected node to list
                        if ( connected[tempNeighbors[j]] != true){
                            connected[tempNeighbors[j]] = true;
                        }
                    }

                }//end j loop

            }//end i loop
            }

        //if connectedNodes = size of graph, then it is connected
        for( i =0; i< connected.length;i++){
            //checks the amount of trues in the connected array
            if( connected[i] == true){
                connectCT++;
            }

        }
        if(connectCT == size){
            return true;
        }
        else{
            return false;
        }



    }//end StronglyConnected

}
