package p5;

import util.TestRunner;

/**
 * Compare the performance of insertion sort, merge sort, and enhanced merge sort.
 *
 * @author Rajit Banerjee
 */
public class MergeSortTest {
    private static final int[] ARRAY_SIZES = {10, 15, 100, 1000, 10000, 100000};

    // Run tests for various sorting algorithms
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"enhanced_merge_sort", "merge_sort", "insertion_sort"};
        TestRunner.run(sortTypes, ARRAY_SIZES);
    }

}