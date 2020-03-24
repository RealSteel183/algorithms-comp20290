package p4;

import util.TestRunner;

/**
 * Compare the performance of selection sort, insertion sort, and bogo sort.
 *
 * @author Rajit Banerjee
 */
public class ElementarySortTest {
    private static int[] array_sizes = {10, 12, 100, 1000, 10000};

    // Run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"selection_sort", "insertion_sort", "bogo_sort"};
        TestRunner.run(sortTypes, array_sizes);
    }
}
