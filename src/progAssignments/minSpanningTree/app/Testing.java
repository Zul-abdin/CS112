package progAssignments.minSpanningTree.app;

import progAssignments.minSpanningTree.structures.*;

import java.io.IOException;

public class Testing {

    public static void main(String[] args) throws IOException {

        //PC Version
        Graph g1 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph1.txt");
        Graph g2 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph2.txt");
        Graph g3 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph3.txt");
        Graph g4 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph4.txt");
        //Graph gempty = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\emptyGraph.txt");


        /* Mac Version
         * Graph g1 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph1.txt");
         * Graph g2 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph2.txt");
         * Graph g3 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph3.txt");
         * Graph g4 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph4.txt");
         * Graph empty = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/emptyGraph.txt");
         */

        PartialTreeList testg1 = PartialTreeList.initialize(g1);
        PartialTreeList testg2 = PartialTreeList.initialize(g2);
        PartialTreeList testg3 = PartialTreeList.initialize(g3);
        PartialTreeList testg4 = PartialTreeList.initialize(g4);
        //PartialTreeList emptyTest = PartialTreeList.initialize(gempty);

        System.out.println("Graph 1: " + PartialTreeList.execute(testg1));
        System.out.println("Graph 2: " + PartialTreeList.execute(testg2));
        System.out.println("Graph 3: " + PartialTreeList.execute(testg3));
        System.out.println("Graph 4: " + PartialTreeList.execute(testg4));
        //System.out.println(PartialTreeList.execute(emptyTest));
    }

}
