package p6;

import util.TestRunner;

/**
 * Compare the performance of merge sort, quick sort, and enhanced quick sort.
 *
 * @author Rajit Banerjee
 */
public class QuickSortTest {
    private static final int[] ARRAY_SIZES = {10, 15, 100, 1000, 10000, 100000};

    // Run tests for various sorting algorithms
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"merge_sort", "quick_sort", "enhanced_quick_sort"};
        TestRunner.run(sortTypes, ARRAY_SIZES);
    }

}