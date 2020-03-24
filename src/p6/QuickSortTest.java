package p6;

import util.TestRunner;

/**
 * Compare the performance of merge sort, quick sort, and enhanced quick sort.
 *
 * @author Rajit Banerjee
 */
public class QuickSortTest {
    private static int[] array_sizes = {5, 10, 100, 1000, 10000, 100000, 200000};

    // Run tests for various sorting algorithms
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"merge_sort", "quick_sort", "enhanced_quick_sort"};
        TestRunner.run(sortTypes, array_sizes);
    }

}
