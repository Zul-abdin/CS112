package dataStructures.linkedLists;

import java.io.NotActiveException;
import java.util.NoSuchElementException;

public class GenericLL<T> {

    GenericNode<T> front;
    int size;

    public  GenericLL(){
        front = null;
        size = 0;
    }

    public void addToFront(T newItem){
        front = new GenericNode<T>(newItem, front);
        size++;
    }

    public void traverse(){
        for(GenericNode<T> pointer = front; pointer != null; pointer = pointer.next){
            System.out.print(pointer.data + " --> ");
        }
        System.out.println("\\");
    }

    public GenericNode<T> deleteFront(){
        if(front == null){
            throw new NoSuchElementException("Deleting from empty list");
        } else {
            GenericNode<T> tmp = front; //save the reference to the first node
            front = front.next;
            size--;
            return tmp;
        }
    }

    public int getSize() {
        return size;
    }

    public void delete(T target){

        GenericNode<T> pointer = front;
        GenericNode<T> behindPointer = null;

        while(pointer.next != null && pointer.data != target){

            behindPointer = pointer;
            pointer = pointer.next;

        }

        if(pointer == null){
            throw new NoSuchElementException("Target is not in list");
        } else if (pointer == front){
            front = front.next;
        } else {
            behindPointer.next = pointer.next;
        }
    }

}
