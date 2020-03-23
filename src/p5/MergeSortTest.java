package p5;

/**
 * Compare the performance of insertion sort, merge sort, and enhanced merge sort.
 *
 * @author Rajit Banerjee
 */
public class MergeSortTest {
    private static int[] array_sizes = {5, 10, 100, 1000, 10000, 100000, 200000};

    // Run tests for various sorting algorithms
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"insertion_sort", "merge_sort", "enhanced_merge_sort"};
        p4.SortTest.runTests(sortTypes, array_sizes);
    }

}