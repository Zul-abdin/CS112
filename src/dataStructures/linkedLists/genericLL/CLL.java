package dataStructures.linkedLists.genericLL;

public class CLL<T> {

    GenericNode<T> tail; //reference to last element of CLL
    int size;

    CLL(){
        tail = null;
        size = 0;
    }

    public void addToFront(T newItem) {

        //Create new node
        GenericNode<T> newNode = new GenericNode<>(newItem, null);

        //insert at the front of the CLL
        if(tail == null){
            //List is empty
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next; //new Node will point to the current front node
            tail.next = newNode; //last node will point to the new node
        }
        size++;
    }

    public void deleteFront(){

        if(tail == tail.next || tail == null){
            tail = null;
            size = 0;
        } else {
            tail.next = tail.next.next;
            size--;
        }

    }

    public void traverse(){

        if(tail == null){
            System.out.println("CLL is empty");
            return;
        }

        GenericNode<T> ptr = tail.next;

        do{
            System.out.print(ptr.data + " --> ");
            ptr = ptr.next;
        } while (ptr != tail.next);

    }
}
