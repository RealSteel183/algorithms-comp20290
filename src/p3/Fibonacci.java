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
            return n;

        long fib = 1;
        long prevFib = 1;

        for (int i = 2; i < n; i++) {
            long temp = fib;
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

    // Prompt the user to choose from a menu
    private static void promptUser() {
        StdOut.println("1. Run timing analysis");
        StdOut.println("2. Compute the Nth Fibonacci number");
        StdOut.print("Choose 1 or 2 (0 to exit): ");
    }

    // Ensures that user enters an integer choice
    private static int readInt() {
        int input = -1;
        while (input == -1) {
            try {
                input = StdIn.readInt();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid input, please try again!");
            }
        }
        return input;
    }

    // Run a timing analysis for inputs of different sizes
    private static void runTiming() {
        StdOut.printf("\nTiming (in nanoseconds) for Nth Fibonacci computation");
        StdOut.printf("\n%8s\t%8s\t%8s", "N", "Iterative", "Recursive\n");
        int n = 0;
        while (n < 15) {
            final long t1 = System.nanoTime();
            fibonacciIterative(n);
            final long t2 = System.nanoTime();
            fib(n);
            final long t3 = System.nanoTime();
            StdOut.printf("%8d\t%8d\t%8d\n", n, t2 - t1, t3 - t2);
            n++;
        }
    }

    // Main method to compare the recursive and iterative Fibonacci functions
    public static void main(String[] args) {
        StdOut.println("~ Iterative vs Recursive Nth Fibonacci number ~");
        promptUser();
        long choice = readInt();
        switch ((int) choice) {
            case 0:
                break;
            case 1:
                runTiming();
                break;
            case 2:
                StdOut.println("Enter N: ");
                int N = readInt();
                final long t1 = System.nanoTime();
                long iterativeFib = fibonacciIterative(N);
                final long t2 = System.nanoTime();

                StdOut.println("Iterative answer = " + iterativeFib);
                StdOut.println("Time taken for iterative Fibonacci (N = " + N + "): " +
                        (t2 - t1) + " nanoseconds");

                final long t3 = System.nanoTime();
                long recursiveFib = fib(N);
                final long t4 = System.nanoTime();

                StdOut.println("Recursive answer = " + recursiveFib);
                StdOut.println("Time taken for recursive Fibonacci (N = " + N + "): " +
                        (t4 - t3) + " nanoseconds");
                break;
            default:
                StdOut.println("Invalid input, please try again!");
        }
    }

}