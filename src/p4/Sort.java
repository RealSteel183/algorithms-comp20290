package p4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Sort implements Callable<int[]> {
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

    // insertion sort algorithm
    public static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
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

    }

    // enhanced merge sort algorithm
    public static void cool_merge(int[] arr) {
        // TODO
    }

    // stupid sort - BogoSort algorithm
    public static void bogo(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    // method to merge two sorted arrays together
    private static void mergeHalves(int[] a, int[] b) {

    }

    // method to swap elements at indices i and j or given array
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // check if given array is sorted
    private static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // randomly shuffle array elements
    private static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int random_index = (int) (Math.random() * a.length);
            swap(a, i, random_index);
        }
    }
}