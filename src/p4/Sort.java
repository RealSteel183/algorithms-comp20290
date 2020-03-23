package p4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Sort<E extends Comparable<E>> implements Callable<E[]> {
    private static final int CUTOFF = 10; // for smaller sub-arrays, use insertion sort
    private Method sort;
    private E[] arr;

    public Sort(String sortType, E[] arr)
            throws NoSuchMethodException, SecurityException {
        sort = Sort.class.getMethod(sortType, Comparable[].class);
        this.arr = arr;
    }

    @Override
    public E[] call() throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        sort.invoke(null, (Object) arr);
        return arr;
    }

    // selection sort algorithm
    public static <E extends Comparable<E>> void selection(E[] arr) {
        int min_index;
        for (int i = 0; i < arr.length - 1; i++) {
            min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min_index]) < 0) {
                    min_index = j;
                }
            }
            swap(arr, i, min_index);
        }
    }

    // stupid sort - BogoSort algorithm
    public static <E extends Comparable<E>> void bogo(E[] arr) {
        while (isNotSorted(arr)) {
            shuffle(arr);
        }
    }

    // Check if given array is not sorted
    public static <E extends Comparable<E>> boolean isNotSorted(E[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == null || a[i].compareTo(a[i + 1]) > 0) {
                return true;
            }
        }
        return false;
    }

    // randomly shuffle array elements
    private static <E extends Comparable<E>> void shuffle(E[] a) {
        for (int i = 0; i < a.length; i++) {
            int random_index = (int) (Math.random() * (i + 1));
            swap(a, i, random_index);
        }
    }

    // call insertion sort helper
    public static <E extends Comparable<E>> void insertion(E[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }

    // insertion sort algorithm
    private static <E extends Comparable<E>> void insertionSort(E[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            E key = arr[i];
            int j = i - 1;
            /*
             * shift elements [0 to i-1] to the right as long as
             * they are greater than key element
             */
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }

    // merge sort algorithm
    public static <E extends Comparable<E>> void merge(E[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        E[] leftHalf = half(arr, 0, mid - 1);
        E[] rightHalf = half(arr, mid, arr.length - 1);
        merge(leftHalf);
        merge(rightHalf);
        mergeHalves(arr, leftHalf, rightHalf);
    }

    // enhanced merge sort algorithm
    public static <E extends Comparable<E>> void fast_merge(E[] arr) {
        if (arr.length <= CUTOFF) {
            insertion(arr);
        } else {
            int mid = arr.length / 2;
            E[] leftHalf = half(arr, 0, mid - 1);
            E[] rightHalf = half(arr, mid, arr.length - 1);
            fast_merge(leftHalf);
            fast_merge(rightHalf);
            if (leftHalf[leftHalf.length - 1].compareTo(rightHalf[0]) > 0) {
                mergeHalves(arr, leftHalf, rightHalf);
            } else {
                joinHalves(arr, leftHalf, rightHalf);
            }
        }
    }

    // method to generate the left/right half of an array
    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> E[] half(E[] a, int start, int end) {
        E[] ans = (E[]) new Comparable[end - start + 1];
        System.arraycopy(a, start, ans, 0, ans.length);
        return ans;
    }

    // merge two sorted arrays together
    private static <E extends Comparable<E>> void mergeHalves(E[] arr, E[] left, E[] right) {
        int x = 0, y = 0, index = 0;
        while (x < left.length && y < right.length) {
            if (left[x].compareTo(right[y]) <= 0) {
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
    private static <E extends Comparable<E>> void joinHalves(E[] arr, E[] left, E[] right) {
        System.arraycopy(left, 0, arr, 0, left.length);
        System.arraycopy(right, 0, arr, left.length, right.length);
    }

    // call helper function to implement quick sort
    public static <E extends Comparable<E>> void quick(E[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // quick sort algorithm
    private static <E extends Comparable<E>> void quickSort(E[] arr, int lo, int hi) {
        if (lo < hi) {
            // find the pivot
            int pivot = partition(arr, lo, hi);

            quickSort(arr, lo, pivot - 1);
            quickSort(arr, pivot + 1, hi);
        }
    }

    // call helper enhanced quick sort
    public static <E extends Comparable<E>> void quick_enhanced(E[] arr) {
        shuffle(arr); // improves performance?
        enhancedQuickSort(arr, 0, arr.length - 1);
    }

    // enhanced quick sort algorithm
    private static <E extends Comparable<E>> void enhancedQuickSort(E[] arr, int lo, int hi) {
        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi);
        } else if (lo < hi) {
            int pivot_index = medianOf3(arr, lo, lo + (hi - lo) / 2, hi);

            enhancedQuickSort(arr, lo, pivot_index - 1);
            enhancedQuickSort(arr, pivot_index + 1, hi);
        }
    }

    // median of 3 method - pivot selection
    private static <E extends Comparable<E>> int medianOf3(E[] arr, int lo, int mid, int hi) {
        // sort lo, mid, hi
        if (arr[mid].compareTo(arr[lo]) < 0) {
            swap(arr, mid, lo);
        }
        if (arr[hi].compareTo(arr[lo]) < 0) {
            swap(arr, hi, lo);
        }
        if (arr[hi].compareTo(arr[mid]) < 0) {
            swap(arr, hi, mid);
        }

        // place pivot at hi
        swap(arr, mid, hi);
        // partition array based on pivot
        return partition(arr, lo, hi);
    }

    // partition the array so that pivot is in the right place
    private static <E extends Comparable<E>> int partition(E[] arr, int lo, int hi) {
        E pivot = arr[hi];
        int i = (lo - 1);
        for (int j = lo; j < hi; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    // swap elements at indices i and j or given array
    private static <E extends Comparable<E>> void swap(E[] a, int i, int j) {
        E temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // display the array elements
    public static <E extends Comparable<E>> void display(E[] arr) {
        StringBuilder ans = new StringBuilder("{ ");
        for (int i = 0; i < arr.length - 1; i++) {
            ans.append(arr[i]).append(", ");
        }
        ans.append(arr[arr.length - 1]).append(" }");
        System.out.println(ans);
    }
}