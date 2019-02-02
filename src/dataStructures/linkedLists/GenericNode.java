package dataStructures.linkedLists;

public class GenericNode<T> {

    T data; //This data can be of any data type
    GenericNode<T> next; //Link to the next Node in the LL

    public GenericNode(T data, GenericNode<T> next){
        this.data = data;
        this.next = next;
    }

}
