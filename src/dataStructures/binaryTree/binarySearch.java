package dataStructures.binaryTree;

public class binarySearch {

    public static int iterativeBinarySearch(int[] A, int target){

        int l = 0;
        int r = A.length - 1;
        while (l <= r){
            int m = (l + r) / 2;
            if(A[m] == target){
                //fount it
                return m;
            } else if(A[m] < target) {
                //target in upper half
                l = m + 1;
            } else {
                //target in lower half
                r = m - 1;
            }
        }
        return -1; //target not found
    }

    public static int recursiveBinarySearch(int[] A, int target, int l, int r){
        if(l > r){
            return -1;
        }
        int m = (l + r) / 2;

        if(A[m] == target){
            return m; //target found
        } else if(A[m] < target){
            //target in the upper half
            return recursiveBinarySearch(A, target, m + 1, r);
        } else {
            //target in the lower half
            return recursiveBinarySearch(A, target, l, m - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {22, 25, 36, 42, 55, 66, 98};
        System.out.println(iterativeBinarySearch(arr, 120));
        System.out.println(iterativeBinarySearch(arr, 36));

        System.out.println(recursiveBinarySearch(arr, 120, 0, arr.length - 1));
        System.out.println(recursiveBinarySearch(arr, 36, 0, arr.length - 1));
    }

}
