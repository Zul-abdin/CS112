package dataStructures.linkedLists.intLL;

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

    public static boolean addAfter (Node f, int target, int newItem){
        for(Node pointer = f; pointer != null; pointer=pointer.next){
            if(pointer.data == target){
                //found the target, insert newItem after target
                Node n = new Node(newItem, pointer.next); //creates new node which points to pointer.next
                pointer.next = n; //pointer.next is updated to point to the new node
                return true;
            }
        }
        return false;
    }

    public static Node addBefore (Node front, int target, int newItem){
        if(front.data == target){
            front = new Node(newItem, front);
            return front;
        }
        for(Node ptr = front; ptr.next != null; ptr = ptr.next){
            if(ptr.next.data == target){
                Node k = new Node(newItem, ptr.next);
                ptr.next = k;
                return front;
            }
        }
        return front;
    }
    public static Node deleteEveryOther (Node front) {
        if (front == null){
            return  null;
        }
        for(Node ptr = front; ptr != null && ptr.next != null; ptr = ptr.next){
                ptr.next = ptr.next.next;
        }
        return front;
    }


    public static Node addInAscending (Node f, int newItem) {
        if(f.data >= newItem){
            Node n = new Node(newItem, f);
            return n;
        }
        Node prev = f;
        Node ptr = f.next;
        while (ptr != null){
            if(prev.data <= newItem && ptr.data >= newItem){

                prev.next = new Node(newItem, ptr);
                return f;
            }
            prev = ptr;
            ptr = ptr.next;
        }
        prev.next = new Node(newItem, null);
        return f;
    }

    public static Node delete (Node f, int target){

        Node pointer = f;
        Node prev = null;

        while(pointer != null && pointer.data != target){
            prev = pointer; //make previous point to where current is pointing to
            pointer = pointer.next; // move current ahead, one hop
        }

        if(pointer == null){
            return f; //Target was not found
        } else if(pointer == f){
            return f.next; //Target is the front
        } else {
            //deleting from the middle or end
            prev.next = pointer.next;
            return f;
        }

    }

    public static Node replicate(Node front){
        if(front == null){
            return null;
        }
        Node f = new Node(front.data, null);
        f.next = replicate(front.next);
        return f;
    }

    public static void main(String[] args) {

        Node Front = null;
        Front = addToFront(6, Front);
        Front = addToFront(5, Front);
        Front = addToFront(4, Front);
        Front = addToFront(3, Front);
        Front = addToFront(7, Front);
        traverse(Front);
        Front = deleteFront(Front);
        traverse(Front);
        System.out.println(search(8, Front));
        System.out.println(search(5, Front));
        System.out.println(addAfter(Front, 6, 10));
        traverse(Front);
        Front = deleteEveryOther(Front);
        traverse(Front);
    }
}
