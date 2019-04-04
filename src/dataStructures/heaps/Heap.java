package dataStructures.heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap <T extends Comparable<T>>{

    /* Mode: min or max heap */
    final static int MAX_HEAP = 1;
    final static int MIN_HEAP = 2;
    private int mode;   // defines what kind of heap this is

    private ArrayList<T> items;  // array to hold the heap

    public Heap (int mode) {
        items = new ArrayList<T>();
        this.mode = mode;
    }
    public void insert (T item) {
        items.add(item);
        siftUp();
    }
    private void siftUp() {
        int k = items.size() - 1; // item was inserted at the last position of the array
        while (k > 0) { // 0 is root's index
            int p = (k-1)/2; // p is the index of the k's parent
            T kid = items.get(k); // kid is the value at k index
            T parent = items.get(p); // parent's value at p index
            if ((mode == MAX_HEAP && kid.compareTo(parent) > 0) ||
                    mode == MIN_HEAP && kid.compareTo(parent) < 0) {

                // swap kid with parent
                items.set(k, parent);
                items.set(p, kid);

                // move one level up
                k = p;
            } else {
                break;
            }
        }
    }
    public T delete () {
        if (items.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        } else if (items.size() == 1) {
            return items.remove(0);
        }
        // more than 1 item
        T root = items.get(0);  // save the root
        T lastItem = items.remove(items.size()-1); // remove the last element
        items.set(0, lastItem);                    // move the last element into root
        siftDown();
        return root;
    }

    private void siftDown() {
        int k = 0; // root's index
        int l = 2*k+1; // left child's index
        while (l < items.size()) {
            // k has a left child (l is within array bounds)
            int r = l+1; // right child's index
            int maxMin = l; // assume left is the greatest/smallest
            T lChild = items.get(l);

            if (r < items.size() &&
                    ((mode == MAX_HEAP && items.get(r).compareTo(lChild) > 0 ) ||
                            (mode == MIN_HEAP && items.get(r).compareTo(lChild) < 0))) {
                maxMin = r; // max or min child is the right one
            }

            T parent = items.get(k);
            T child = items.get(maxMin);

            if ((mode == MAX_HEAP && child.compareTo(parent) > 0) ||
                    (mode == MIN_HEAP && child.compareTo(parent) < 0)) {

                //swap
                items.set(k, child);
                items.set(maxMin, parent);

                // move one level down
                k = maxMin;
                l = 2*k+1;
            } else {
                break; // child is smaller (max heap) OR greater (min heap) than parent
            }
        }
    }

    public boolean isEmpty () {
        return items.isEmpty();
    }

    public static void main (String[] args) {
        Heap<Integer> heap = new Heap<Integer>(Heap.MAX_HEAP);
    }
}
