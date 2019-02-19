package dataStructures.binaryTree;

public class BSTNode<T> {

    BSTNode<T> left; //Root of the left subtree
    BSTNode<T> right; //Root of the right subtree
    T key; //Key used to search

    BSTNode (T key, BSTNode<T> left, BSTNode<T> right){
        this.key = key;
        this.left = left;
        this.right = right;
    }


}
