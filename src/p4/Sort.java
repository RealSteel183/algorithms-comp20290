package p4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Callable class to implement different types of sorts on an array
 * containing Comparable elements.
 *
 * @param <E> generic type of items to be sorted
 * @author Rajit Banerjee
 */
public class Sort<E extends Comparable<E>> implements Callable<E[]> {
    private static final int CUTOFF = 10; // for smaller sub-arrays, use insertion sort
    private Method sort;
    private E[] arr;

    public Sort(String sortType, E[] arr)
            throws NoSuchMethodException, SecurityException {
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

    // stupid sort - BogoSort algorithm
    public static <E extends Comparable<E>> void bogo_sort(E[] arr) {
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
    public static <E extends Comparable<E>> void insertion_sort(E[] arr) {
        insertion_sort(arr, 0, arr.length - 1);
    }

    // insertion sort algorithm
    private static <E extends Comparable<E>> void insertion_sort(E[] arr, int lo, int hi) {
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

    // Merge sort algorithm
    public static <E extends Comparable<E>> void merge_sort(E[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        E[] leftHalf = half(arr, 0, mid - 1);
        E[] rightHalf = half(arr, mid, arr.length - 1);
        merge_sort(leftHalf);
        merge_sort(rightHalf);
        merge(arr, leftHalf, rightHalf);
    }

    // Merge two sorted arrays together
    private static <E extends Comparable<E>> void merge(E[] arr, E[] left, E[] right) {
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

    // Generate a portion (usually half) of an array
    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> E[] half(E[] a, int start, int end) {
        E[] ans = (E[]) new Comparable[end - start + 1];
        System.arraycopy(a, start, ans, 0, ans.length);
        return ans;
    }

    // Enhanced merge sort algorithm
    public static <E extends Comparable<E>> void enhanced_merge_sort(E[] arr) {
        if (arr.length <= CUTOFF) {
            insertion_sort(arr);
        } else {
            int mid = arr.length / 2;
            E[] leftHalf = half(arr, 0, mid - 1);
            E[] rightHalf = half(arr, mid, arr.length - 1);
            enhanced_merge_sort(leftHalf);
            enhanced_merge_sort(rightHalf);
            if (leftHalf[leftHalf.length - 1].compareTo(rightHalf[0]) > 0) {
                merge(arr, leftHalf, rightHalf);
            } else {
                joinHalves(arr, leftHalf, rightHalf);
            }
        }
    }

    // Join two halves of an array
    private static <E extends Comparable<E>> void joinHalves(E[] arr, E[] left, E[] right) {
        System.arraycopy(left, 0, arr, 0, left.length);
        System.arraycopy(right, 0, arr, left.length, right.length);
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
            // Find the pivot
            int pivot = partition(arr, lo, hi);

            quick_sort(arr, lo, pivot - 1);
            quick_sort(arr, pivot + 1, hi);
        }
    }

    /**
     * Apply Enhanced QuickSort on the given array.
     *
     * @param arr array to be sorted
     * @param <E> generic type of array elements.
     */
    public static <E extends Comparable<E>> void enhanced_quick_sort(E[] arr) {
        //shuffle(arr); // improves performance?
        enhanced_quick_sort(arr, 0, arr.length - 1);
    }

    // Enhanced quick sort algorithm
    private static <E extends Comparable<E>> void enhanced_quick_sort(E[] arr, int lo, int hi) {
        if (hi - lo <= CUTOFF) {
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

        // Place pivot at hi index
        swap(arr, mid, hi);
        // Partition array based on pivot
        return partition(arr, lo, hi);
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
        StringBuilder ans = new StringBuilder("[");
        for (int i = 0; i < arr.length - 1; i++) {
            ans.append(arr[i]).append(", ");
        }
        ans.append(arr[arr.length - 1]).append("]");
        return ans.toString();
    }

    /**
     * Invokes a particular type of sort in the class.
     *
     * @return the sorted array
     * @throws IllegalAccessException    may be caused due to issue in reflection
     * @throws IllegalArgumentException  may be caused due to issue in reflection
     * @throws InvocationTargetException may be caused due to issue in reflection
     */
    @Override
    public E[] call() throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        sort.invoke(null, (Object) arr);
        return arr;
    }

}
