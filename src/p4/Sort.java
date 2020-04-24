package p4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Callable class to implement different types of sorts on an array
 * containing Comparable elements.
 *
 * @param <E> generic type of items to be sorted
 * @author Rajit Banerjee
 */

public class Sort<E extends Comparable<E>> implements Callable<E[]> {

    private static final int CUTOFF = 15; // for smaller sub-arrays, use insertion sort
    private final Method sort;
    private final E[] arr;

    /**
     * Initialise new sort, use reflection to get the declared method
     * for the required sort type.
     *
     * @param sortType name of sort method to be called
     * @param arr      array to be sorted
     * @throws NoSuchMethodException if method with required name doesn't exist
     */
    public Sort(String sortType, E[] arr) throws NoSuchMethodException {
        sort = Sort.class.getMethod(sortType, Comparable[].class);
        this.arr = arr;
    }

    /**
     * Apply SelectionSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void selection_sort(E[] arr) {
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

    /**
     * Apply InsertionSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void insertion_sort(E[] arr) {
        insertion_sort(arr, 0, arr.length - 1);
    }

    // Insertion sort algorithm
    private static <E extends Comparable<E>> void insertion_sort(E[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            E key = arr[i];
            int j = i - 1;
            /*
             Shift elements to the right as long as
             they are greater than key element
             */
            while (j >= lo && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Apply BogoSort (silly sort) on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void bogo_sort(E[] arr) {
        while (isNotSorted(arr)) {
            shuffle(arr);
        }
    }

    /**
     * Check if given array is not sorted yet.
     *
     * @param a   array to be checked
     * @param <E> generic type of array items
     * @return {@code true} if array is not sorted
     */
    public static <E extends Comparable<E>> boolean isNotSorted(E[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == null || a[i].compareTo(a[i + 1]) > 0) {
                return true;
            }
        }
        return false;
    }

    // Randomly shuffle array elements
    private static <E extends Comparable<E>> void shuffle(E[] a) {
        for (int i = 0; i < a.length; i++) {
            int random_index = (int) (Math.random() * (i + 1));
            swap(a, random_index, i);
        }
    }

    /**
     * Apply MergeSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void merge_sort(E[] arr) {
        merge_sort(arr, 0, arr.length - 1);
    }

    // Merge sort algorithm
    private static <E extends Comparable<E>> void merge_sort(E[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            merge_sort(arr, lo, mid);
            merge_sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
    }

    // Merge two sorted arrays together
    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> void merge(E[] arr, int lo, int mid, int hi) {
        E[] left = (E[]) new Comparable[mid - lo + 1];
        E[] right = (E[]) new Comparable[hi - mid];
        System.arraycopy(arr, lo, left, 0, mid - lo + 1);
        System.arraycopy(arr, mid + 1, right, 0, hi - mid);

        int x = 0, y = 0, index = lo;
        while (x < left.length && y < right.length) {
            // Add smaller element in left/right to merged array
            if (left[x].compareTo(right[y]) <= 0) {
                arr[index++] = left[x++];
            } else {
                arr[index++] = right[y++];
            }
        }
        // Add remaining elements of left half to merged array
        while (x < left.length) {
            arr[index++] = left[x++];
        }
        // Add remaining elements of right half to merged array
        while (y < right.length) {
            arr[index++] = right[y++];
        }
    }

    /**
     * Apply Enhanced MergeSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void enhanced_merge_sort(E[] arr) {
        enhanced_merge_sort(arr, 0, arr.length - 1);
    }

    // Enhanced merge sort algorithm
    private static <E extends Comparable<E>> void enhanced_merge_sort(E[] arr, int lo, int hi) {
        if (lo + CUTOFF > hi) {
            insertion_sort(arr, lo, hi);
        } else if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            enhanced_merge_sort(arr, lo, mid);
            enhanced_merge_sort(arr, mid + 1, hi);
            if (arr[mid].compareTo(arr[mid + 1]) > 0) {
                merge(arr, lo, mid, hi);
            }
        }
    }

    /**
     * Apply QuickSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> void quick_sort(E[] arr) {
        quick_sort(arr, 0, arr.length - 1);
    }

    // Quick sort algorithm
    private static <E extends Comparable<E>> void quick_sort(E[] arr, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(arr, lo, hi);
            quick_sort(arr, lo, pivot - 1);
            quick_sort(arr, pivot + 1, hi);
        }
    }

    // Partition the array so that pivot is in the right place
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

    /**
     * Apply Enhanced QuickSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array elements.
     */
    public static <E extends Comparable<E>> void enhanced_quick_sort(E[] arr) {
        shuffle(arr); // Improves performance
        enhanced_quick_sort(arr, 0, arr.length - 1);
    }

    // Enhanced quick sort algorithm
    private static <E extends Comparable<E>> void enhanced_quick_sort(E[] arr, int lo, int hi) {
        if (lo + CUTOFF > hi) {
            insertion_sort(arr, lo, hi);
        } else if (lo < hi) {
            int pivot_index = medianOf3(arr, lo, lo + (hi - lo) / 2, hi);
            enhanced_quick_sort(arr, lo, pivot_index - 1);
            enhanced_quick_sort(arr, pivot_index + 1, hi);
        }
    }

    // Median of 3 method - pivot selection
    private static <E extends Comparable<E>> int medianOf3(E[] arr, int lo, int mid, int hi) {
        // Sort lo, mid, hi
        if (arr[mid].compareTo(arr[lo]) < 0) {
            swap(arr, mid, lo);
        }
        if (arr[hi].compareTo(arr[lo]) < 0) {
            swap(arr, hi, lo);
        }
        if (arr[hi].compareTo(arr[mid]) < 0) {
            swap(arr, hi, mid);
        }
        // Place pivot at hi - 1 index
        swap(arr, mid, hi - 1);
        // Partition array based on pivot
        return partition(arr, lo, hi - 1);
    }

    // Swap elements at indices i and j or given array
    private static <E extends Comparable<E>> void swap(E[] a, int i, int j) {
        E temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Display a given array on the command line.
     *
     * @param arr array to be displayed
     * @param <E> generic type of array items
     */
    public static <E extends Comparable<E>> String display(E[] arr) {
        return Arrays.toString(arr);
    }

    /**
     * Invokes a particular type of sort in the class.
     *
     * @return the sorted array
     * @throws InvocationTargetException if method to be invoked throws underlying exception
     * @throws IllegalAccessException    if method to be invoked is not defined
     */
    @Override
    public E[] call() throws InvocationTargetException, IllegalAccessException {
        sort.invoke(null, (Object) arr);
        return arr;
    }

}