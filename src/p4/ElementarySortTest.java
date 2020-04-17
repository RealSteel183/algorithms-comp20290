package p4;

/**
 * Compare the performance of selection sort, insertion sort, and bogo sort.
 *
 * @author Rajit Banerjee
 */
public class ElementarySortTest {
    private static final int[] ARRAY_SIZES = {10, 12, 100, 1000, 10000};

    // Run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"selection_sort", "insertion_sort", "bogo_sort"};
        TestRunner.run(sortTypes, ARRAY_SIZES);
    }

}