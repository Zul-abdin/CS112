package dataStructures.linkedLists.genericLL;

public class DLL<T> {

    DLLNode<T> front;
    int size;

    DLL(){
        front = null;
        size = 0;
    }

    public void addToFront(T newData){
        DLLNode<T> newNode = new DLLNode<T>(newData, null, front);

        if(front != null) {
            front.previous = newNode;
        }
        front = newNode;
        size++;
    }

    public void traverse(){
        DLLNode<T> ptr = front;
        while(ptr != null){
            System.out.println(ptr.data + " --> ");
            ptr = ptr.next;
        }
        System.out.println("\\");
    }

    public void addAfter(T target, T newData){
        DLLNode<T> ptr = front;
        while(ptr != null){
            if(ptr.data.equals(target)){
                DLLNode<T> newNode = new DLLNode<>(newData, ptr, ptr.next);
                ptr.next = newNode;
                if(newNode.next != null) {
                    newNode.next.previous = newNode;
                }
                size++;
            }
        }
    }
    public DLLNode moveToFront(DLLNode<T> front, DLLNode<T> target) {
        if(front == null || front == target || target == null){
            return front;
        }
        target.next = front;
        target.previous = null;
        front.previous = null;
        return target;
    }

}
