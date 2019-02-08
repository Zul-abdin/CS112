package dataStructures.linkedLists.genericLL;

public class GenericNode<T> {

    public T data; //This data can be of any data type
    public GenericNode<T> next; //Link to the next Node in the LL

    public GenericNode(T data, GenericNode<T> next){
        this.data = data;
        this.next = next;
    }

}
