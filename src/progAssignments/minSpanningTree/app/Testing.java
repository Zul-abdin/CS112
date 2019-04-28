package progAssignments.minSpanningTree.app;

import progAssignments.minSpanningTree.structures.Graph;
import progAssignments.minSpanningTree.structures.PartialTree;

import java.io.IOException;

public class Testing {

    public static void main(String[] args) throws IOException {

        Graph x = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph1.txt");
        Graph y = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph2.txt");

        PartialTreeList testx = PartialTreeList.initialize(x);
        PartialTreeList testy = PartialTreeList.initialize(y);

        System.out.println(PartialTreeList.execute(testx));
        System.out.println(PartialTreeList.execute(testy));
    }

}
