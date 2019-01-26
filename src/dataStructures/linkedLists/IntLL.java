package dataStructures.linkedLists;

public class IntLL {

    public static Node addToFront(int item, Node f){

        Node n = new Node(item, f);
        return n;
    }

    public static Node deleteFront(Node f){
        return f.next;
    }

    public static void traverse(Node f){

        Node pointer = f;
        while(pointer != null){
            System.out.print(pointer.data + " --> ");
            pointer = pointer.next;
        }
        System.out.println("\\");

    }

    public static boolean search(int target, Node f){

        Node pointer = f;
        while(pointer !=null){
            if(pointer.data == target) return true;
            pointer = pointer.next;
        }
        return false;

    }

    public static void main(String[] args) {

        Node Front = null;
        Front = addToFront(6, Front);
        Front = addToFront(5, Front);
        Front = addToFront(4, Front);
        Front = addToFront(3, Front);
        traverse(Front);
        Front = deleteFront(Front);
        traverse(Front);
        System.out.println(search(8, Front));
        System.out.println(search(5, Front));


    }
}
