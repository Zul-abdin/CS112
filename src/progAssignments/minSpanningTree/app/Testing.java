package progAssignments.minSpanningTree.app;

import progAssignments.minSpanningTree.structures.Graph;
import progAssignments.minSpanningTree.structures.PartialTree;

import java.io.IOException;

public class Testing {

    public static void main(String[] args) throws IOException {

        //PC Version
        //Graph x = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph1.txt"); //PC version
        //Graph y = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph2.txt"); //PC version

        //Mac Version
        //Graph x = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph1.txt");
        Graph y = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph2.txt");
        //Graph a = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph3.txt");
        //Graph b = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph4.txt");
        //Graph empty = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/emptyGraph.txt");

        //PartialTreeList testx = PartialTreeList.initialize(x);
        PartialTreeList testy = PartialTreeList.initialize(y);
        //PartialTreeList testa = PartialTreeList.initialize(a);
        //PartialTreeList testb = PartialTreeList.initialize(b);
        //PartialTreeList emptyTest = PartialTreeList.initialize(empty);

        //System.out.println(PartialTreeList.execute(testx));
        System.out.println(PartialTreeList.execute(testy));
        //System.out.println(PartialTreeList.execute(testa));
        //System.out.println(PartialTreeList.execute(testb));
        //System.out.println(PartialTreeList.execute(emptyTest));
    }

}
