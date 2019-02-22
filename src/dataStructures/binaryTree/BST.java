package dataStructures.binaryTree;


import java.util.NoSuchElementException;

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

        if( prev == null ){
            //tree is empty
            root = node;
        }
        else if(c < 0){
            prev.left = node;
        } else {
            prev.right = node;
        }
        size++;
    }

    public static <T extends Comparable<T>> void inOrder(BSTNode<T> node){

        if (node == null){
            return;
        }

        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);
    }

    public void delete(T target){
        //1. Find node to delete, call it x
        BSTNode<T> x = root;
        BSTNode<T> p = null; //previous or parent

        int c = 0;
        while(x != null){
            c = target.compareTo(x.key);
            if(c == 0){
                break; //found target
            }
            p = x;
            x = (c < 0) ? x.left : x.right;
        }

        if(x ==null){
            throw new NoSuchElementException("Target not found");
        }
        BSTNode<T> y = null;
        if(x.left != null && x.right != null){
            y = x.left;
            p = x;
            while(y != null){
                p = y;
                y = y.right;
            }
            //copy y's key over x's key
            x.key = y.key;

            //Prepare to delete
            x = y;
        }
        //Handle Case 1 and 2
        //(1) leaf
        //(2) One Child
        //(3) Two Children

        BSTNode<T> tmp = (x.right != null) ? x.right : x.left;

        if(x == p.left){
            p.left = tmp;
        } else if(x == p.right) {
            p.right = tmp;
        }
    }

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        bst.insert(15);
        bst.insert(13);
        bst.insert(71);
        bst.insert(9);
        bst.insert(14);
        bst.insert(10);
        bst.insert(7);
        bst.insert(30);
        inOrder(bst.root);


    }

}
