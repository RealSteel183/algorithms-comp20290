package p3;

import util.StdIn;
import util.StdOut;

import java.util.InputMismatchException;

/**
 * Two approaches, iterative and recursive, to find the Nth
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

    // Ensures that user enters an integer choice
    private static int readInt() {
        int input = -1;
        while (input == -1) {
            try {
                input = StdIn.readInt();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid choice, please try again!");
            }
        }
        return input;
    }

    // Main method to compare the recursive and iterative Fibonacci functions
    public static void main(String[] args) {
        StdOut.println("~ Compute the Nth Fibonacci number ~");
        StdOut.println("Enter N: ");
        int n = readInt();
        final long t1 = System.nanoTime();
        long iterativeFib = fibonacciIterative(n);
        final long t2 = System.nanoTime();

        StdOut.println("Iterative answer = " + iterativeFib);
        StdOut.println("Time taken for iterative Fibonacci (N = " + n + "): " + (t2 - t1) + " nanoseconds");

        final long t3 = System.nanoTime();
        long recursiveFib = fib(n);
        final long t4 = System.nanoTime();

        StdOut.println("Recursive answer = " + recursiveFib);
        StdOut.println("Time taken for recursive Fibonacci (N = " + n + "): " + (t4 - t3) + " nanoseconds");
    }

}