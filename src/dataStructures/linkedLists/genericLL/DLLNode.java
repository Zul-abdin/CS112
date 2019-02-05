package dataStructures.linkedLists.genericLL;

public class DLLNode<T> {

    DLLNode<T> previous; //previous node
    T data;
    DLLNode<T> next; //next node

    public DLLNode(T data, DLLNode<T> previous, DLLNode<T> next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

}
