package progAssignments.minSpanningTree.app;

import progAssignments.minSpanningTree.structures.*;

import java.io.IOException;

public class Driver2 {

    public static void main(String[] args) throws IOException{
        Graph test1 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph1.txt");
        PartialTreeList list1 = PartialTreeList.initialize(test1);

        Graph test2 = new Graph("/Users/zain/IdeaProjects/CS112/src/progAssignments/minSpanningTree/graph2.txt");
        PartialTreeList list2 = PartialTreeList.initialize(test2);


        Vertex v1 = list1.remove().getArcs().getMin().getv2() ;
        list1.removeTreeContaining(v1);
        //while(list1.size() != 0){
        //System.out.println(list1.remove());
        //	}

        Vertex v2 = list1.remove().getArcs().getMin().getv2() ;
        System.out.println(list1.removeTreeContaining(v2)) ;
        while(list1.size() != 0){
            //System.out.println(list1.remove());
            System.out.println(list1.remove().toString() ) ;
        }

        Vertex v3 = list2.remove().getArcs().getMin().getv2();
        list2.removeTreeContaining(v3);

        System.out.println() ;

        System.out.println();
        Vertex v4 = list2.remove().getArcs().getMin().getv2() ;
        System.out.println(list2.removeTreeContaining(v4) ) ;
        while(list2.size() != 0){
            System.out.println(list2.remove().toString());
        }

        System.out.println() ;
        System.out.println();

    }


}