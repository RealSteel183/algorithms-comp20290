package p4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Sort implements Callable<int[]> {
    private static final int CUTOFF = 10; // for smaller sub-arrays, use insertion sort
    private Method sort;
    private int[] arr;

    public Sort(String sortType, int[] arr)
            throws NoSuchMethodException, SecurityException {
        sort = Sort.class.getMethod(sortType, int[].class);
        this.arr = arr;
    }

    @Override
    public int[] call() throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        sort.invoke(null, arr);
        return arr;
    }

    // check if given array is sorted
    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // selection sort algorithm
    public static void selection(int[] arr) {
        int min_index;
        for (int i = 0; i < arr.length - 1; i++) {
            min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            swap(arr, i, min_index);
        }
    }

    // stupid sort - BogoSort algorithm
    public static void bogo(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    // call insertion sort helper
    public static void insertion(int[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }

    // insertion sort algorithm
    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            /*
             * shift elements [0 to i-1] to the right as long as
             * they are greater than key element
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }

    // merge sort algorithm
    public static void merge(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] leftHalf = half(arr, 0, mid - 1);
        int[] rightHalf = half(arr, mid, arr.length - 1);
        merge(leftHalf);
        merge(rightHalf);
        mergeHalves(arr, leftHalf, rightHalf);
    }

    // enhanced merge sort algorithm
    public static void fast_merge(int[] arr) {
        if (arr.length <= CUTOFF) {
            insertion(arr);
        } else {
            int mid = arr.length / 2;
            int[] leftHalf = half(arr, 0, mid - 1);
            int[] rightHalf = half(arr, mid, arr.length - 1);
            fast_merge(leftHalf);
            fast_merge(rightHalf);
            if (leftHalf[leftHalf.length - 1] > rightHalf[0]) {
                mergeHalves(arr, leftHalf, rightHalf);
            } else {
                joinHalves(arr, leftHalf, rightHalf);
            }
        }
    }

    // method to generate the left/right half of an array
    private static int[] half(int[] a, int start, int end) {
        int[] ans = new int[end - start + 1];
        System.arraycopy(a, start, ans, 0, ans.length);
        return ans;
    }

    // merge two sorted arrays together
    private static void mergeHalves(int[] arr, int[] left, int[] right) {
        int x = 0, y = 0, index = 0;
        while (x < left.length && y < right.length) {
            if (left[x] <= right[y]) {
                arr[index++] = left[x++];
            } else {
                arr[index++] = right[y++];
            }
        }
        while (x < left.length) {
            arr[index++] = left[x++];
        }
        while (y < right.length) {
            arr[index++] = right[y++];
        }
    }

    // join two halves of an array
    private static void joinHalves(int[] arr, int[] left, int[] right) {
        System.arraycopy(left, 0, arr, 0, left.length);
        System.arraycopy(right, 0, arr, left.length, right.length);
    }

    // call helper function to implement quick sort
    public static void quick(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // quick sort algorithm
    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            // find the pivot
            int pivot = partition(arr, lo, hi);

            quickSort(arr, lo, pivot - 1);
            quickSort(arr, pivot + 1, hi);
        }
    }

    // call helper enhanced quick sort
    public static void quick_enhanced(int[] arr) {
        enhancedQuickSort(arr, 0, arr.length - 1);
    }

    // enhanced quick sort algorithm
    private static void enhancedQuickSort(int[] arr, int lo, int hi) {
        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi);
        } else if (lo < hi) {
            int pivot_index = medianOf3(arr, lo, lo + (hi - lo) / 2, hi);

            enhancedQuickSort(arr, lo, pivot_index - 1);
            enhancedQuickSort(arr, pivot_index + 1, hi);
        }
    }

    // median of 3 method - pivot selection
    private static int medianOf3(int[] arr, int lo, int mid, int hi) {
        // sort lo, mid, hi
        if (arr[mid] < arr[lo]) {
            swap(arr, mid, lo);
        }
        if (arr[hi] < arr[lo]) {
            swap(arr, hi, lo);
        }
        if (arr[hi] < arr[mid]) {
            swap(arr, hi, mid);
        }

        // place pivot at hi
        swap(arr, mid, hi);
        // partition array based on pivot
        return partition(arr, lo, hi);
    }

    // partition the array so that pivot is in the right place
    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int i = (lo - 1);
        for (int j = lo; j < hi; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    // swap elements at indices i and j or given array
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // randomly shuffle array elements
    private static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int random_index = (int) (Math.random() * (i + 1));
            swap(a, i, random_index);
        }
    }
}