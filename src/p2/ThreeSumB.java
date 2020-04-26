package p2;

import util.In;

import java.util.Arrays;

/**
 * Compilation:  javac p2/ThreeSumB.java
 * Execution:    java p2/ThreeSumB inputFile
 *
 * Example:
 * % java p2/ThreeSumB p2/8ints.txt
 * % java p2/ThreeSumB p2/1Kints.txt
 *
 * <p>
 * Reads n integers and counts the number of triples that sum to exactly 0.
 * <p>
 * Limitations
 * -----------
 * - we ignore integer overflow
 * - doesn't handle case when input has duplicates
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class ThreeSumB {

    // Do not instantiate.
    private ThreeSumB() {
    }

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i - 1])
                return true;
        return false;
    }

    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k} such that
     * {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param a the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k} such that
     * {@code a[i] + a[j] + a[k] == 0}
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a))
            throw new IllegalArgumentException("array contains duplicate integers");
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j)
                    count++;
            }
        }
        return count;
    }

    /**
     * Reads in a sequence of distinct integers from a file, specified as a
     * command-line argument; counts the number of triples sum to exactly zero;
     * prints out the time to perform the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        final long t1 = System.currentTimeMillis();
        int count = count(a);
        final long t2 = System.currentTimeMillis();
        long elapsed = (t2 - t1) / 1000;

        System.out.println("count = " + count);
        System.out.println("Time taken = " + elapsed + " secs");
    }

}