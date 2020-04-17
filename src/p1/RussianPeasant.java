package p1;

import util.StdIn;
import util.StdOut;

/**
 * Multiplication of two long integers using the Russian Peasant's algorithm.
 *
 * @author Rajit Banerjee
 */
public class RussianPeasant {
    // Main method to test the Russian Peasant's algorithm
    public static void main(String[] args) {
        StdOut.println("~ Russian Peasant's multiplication algorithm ~");
        StdOut.println("Enter two numbers to multiply:");
        long a = StdIn.readLong();
        long b = StdIn.readLong();

        final long startTime = System.nanoTime();
        long ans = RussianMultiply(a, b);
        final long elapsedTime = System.nanoTime() - startTime;

        StdOut.println("Product (" + a + " x " + b + ") = " + ans);
        StdOut.println("Time taken: " + elapsedTime + " nanoseconds");
    }

    // Implement the Russian Peasant's algorithm
    private static long RussianMultiply(long m, long n) {
        long res = 0;
        long smaller = Math.min(m, n);
        long larger = Math.max(m, n);
        while (smaller != 0) {
            // if the smaller number is is odd, add the larger number to result
            if (smaller % 2 == 1) {
                res += larger;
            }
            smaller /= 2;
            larger *= 2;
        }
        return res;
    }

}