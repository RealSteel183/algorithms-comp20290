package p5;

public class MergeSortTest {
    private static int[] array_sizes = {5, 10, 100, 1000, 10000, 100000, 200000};

    // run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"insertion", "merge", "fast_merge"};
        p4.SortTest.runTests(sortTypes, array_sizes);
    }
}