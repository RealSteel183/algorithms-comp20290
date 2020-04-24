package p6;

import p4.TestRunner;
import util.StdOut;

/**
 * Compare the performance of merge sort, quick sort, and enhanced quick sort.
 *
 * @author Rajit Banerjee
 */

public class QuickSortTest {

    private static final int[] ARRAY_SIZES = {10, 15, 100, 1000, 10000, 100000};

    // Run tests for various sorting algorithms
    public static void main(String[] args) throws Exception {
        StdOut.println("~ Compare the performance of merge, quick, and enhanced quick sorts ~");
        String[] sortTypes = {"merge_sort", "quick_sort", "enhanced_quick_sort"};
        TestRunner.run(sortTypes, ARRAY_SIZES);
    }

}