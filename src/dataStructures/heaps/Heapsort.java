package dataStructures.heaps;


public class Heapsort {

    public static void main(String[] args) {

        Heap<Integer> heap = new Heap(Heap.MIN_HEAP);

        int[] n = {7, 2, 1, 6};
        // 1. build phase
        for ( int item : n ) {
            heap.insert(item);
        }

        // 2. sort phase
        int i = 0;
        while ( !heap.isEmpty() ) {
            n[i] = heap.delete();
            i += 1;
        }

        for ( int item : n ) {
            System.out.print(item + " ");
        }
    }
}