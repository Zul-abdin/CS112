package progAssignments.minSpanningTree.app;

import progAssignments.minSpanningTree.structures.*;

import java.io.IOException;

public class Driver2 {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException{
        Graph test1 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph1.txt"); //PC
        PartialTreeList list1 = PartialTreeList.initialize(test1);

        Graph test2 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph2.txt"); //PC
        PartialTreeList list2 = PartialTreeList.initialize(test2);

        Graph test3 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph3.txt"); //PC
        PartialTreeList list3 = PartialTreeList.initialize(test3) ;

        Graph test4 = new Graph("C:\\Users\\Zain\\IdeaProjects\\CS112\\src\\progAssignments\\minSpanningTree\\graph4.txt"); //PC
        PartialTreeList list4 = PartialTreeList.initialize(test4 ) ;


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

        Vertex v5 = list3.remove().getArcs().getMin().getv2();
        System.out.println( list3.removeTreeContaining(v5) ) ;
        while( list3.size() != 0 ) {
            System.out.println(list3.remove().toString() ) ;
        }

        System.out.println() ;
        System.out.println();

        Vertex v6 = list4.remove().getArcs().getMin().getv2();
        System.out.println( list4.removeTreeContaining(v6 ) ) ;
        while( list4.size() != 0 ) {
            System.out.println( list4.remove().toString() ) ;
        }
    }


}