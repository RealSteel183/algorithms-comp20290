package p3;

import util.StdIn;
import util.StdOut;

/**
 * Two approaches, iterative and recursive, to find the nth
 * Fibonacci number.
 *
 * @author Rajit Banerjee
 */
public class Fibonacci {
    // Iterative calculation of the nth Fibonacci number
    private static long fibonacciIterative(int n) {
        if (n <= 1)
            return 1;

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    // Recursive calculation of the nth Fibonacci number
    private static long fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        /*
        From n > 9, recursive fibonacci starts taking more time than the iterative one,
        the difference becomes more pronounced once n takes values > 30
        */
        StdOut.println("~ Compute the Nth Fibonacci number ~");
        StdOut.println("Enter N: ");
        int n = StdIn.readInt();
        final long t1 = System.currentTimeMillis();
        long iterativeFib = fibonacciIterative(n);
        final long t2 = System.currentTimeMillis();

        StdOut.println("Iterative answer = " + iterativeFib);
        StdOut.println("Time taken for iterative Fibonacci (N = " + n + "): " + (t2 - t1) + " milliseconds");

        final long t3 = System.currentTimeMillis();
        long recursiveFib = fib(n);
        final long t4 = System.currentTimeMillis();

        StdOut.println("Recursive answer = " + recursiveFib);
        StdOut.println("Time taken for recursive Fibonacci (N = " + n + "): " + (t4 - t3) + " milliseconds");
    }

}