package dataStructures.binaryTree;


//extends forces the data type T to implement the interface Comparable
public class BST<T extends Comparable<T>> {

    BSTNode<T> root;
    int size;

    public BST (){
        root = null;
        size = 0;
    }

    public BSTNode<T> search(T target){
        BSTNode<T> ptr = root;
        while(ptr != null){
            int c = target.compareTo(ptr.key);
            if(c == 0) { //found target
                return ptr;
            } else if(c < 0){ //target is smaller than the ptr
                ptr = ptr.left;
            } else { //target is greater than the ptr
                ptr = ptr.right;
            }
        }
        return null;
    }

    public void insert(T newkey){
         //1. Search for newKey until it fails

        BSTNode<T> ptr = root;
        BSTNode<T> prev = null;

        int c = 0;
        while(ptr != null) {
            c = newkey.compareTo(ptr.key);

            if(c == 0){
                throw new IllegalArgumentException("Duplicates not allowed");
            }
            prev = ptr;
            if(c < 0){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

         //2. insert at failure point
        BSTNode<T> node = new BSTNode<T>(newkey, null, null);
        if(c < 0){
            prev.left = node;
        } else {
            prev.right = node;
        }
        size++;
    }

}
