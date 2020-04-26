package p1;

import util.StdIn;
import util.StdOut;

import java.util.InputMismatchException;

/**
 * Multiplication of two long integers using the Russian Peasant's algorithm.
 *
 * @author Rajit Banerjee
 */

public class RussianPeasant {

    // Main method to test the Russian Peasant's algorithm
    public static void main(String[] args) {
        StdOut.println("~ Russian Peasant's multiplication algorithm ~");
        promptUser();
        long choice = readLong();
        switch ((int) choice) {
            case 0:
                break;
            case 1:
                runTiming();
                break;
            case 2:
                StdOut.println("Enter two numbers to multiply:");
                long a = readLong();
                long b = readLong();

                final long startTime = System.nanoTime();
                long ans = RussianMultiply(a, b);
                final long elapsedTime = System.nanoTime() - startTime;

                StdOut.println("Product (" + a + " x " + b + ") = " + ans);
                StdOut.println("Time taken: " + elapsedTime + " nanoseconds");
                break;
            default:
                StdOut.println("Invalid input, please try again!");
        }
    }

    // Implement the Russian Peasant's algorithm
    private static long RussianMultiply(long m, long n) {
        long res = 0;
        while (n != 0) {
            // if n is is odd, add the larger number to result
            if (n % 2 == 1) {
                res += m;
            }
            n /= 2;
            m *= 2;
        }
        return res;
    }

    // Run a timing analysis for inputs of different sizes
    private static void runTiming() {
        StdOut.printf("\nTiming analysis");
        StdOut.printf("\n%8s\t%8s\t%15s\t\t%8s", "m", "n", "m * n", "Time (nanosec)\n");
        int i = 3;
        while (i < (int) (Math.pow(3, 10))) {
            final long startTime = System.nanoTime();
            long ans = RussianMultiply(i, i);
            final long elapsedTime = System.nanoTime() - startTime;
            StdOut.printf("%8d\t%8d\t%15d\t%8d\n", i, i, ans, elapsedTime);
            i *= 3;
        }
    }

    // Prompt the user to choose from a menu
    private static void promptUser() {
        StdOut.println("1. Run timing analysis");
        StdOut.println("2. Multiply two numbers");
        StdOut.print("Choose 1 or 2 (0 to exit): ");
    }

    // Ensures that user enters valid input
    private static long readLong() {
        long input = -1;
        while (input == -1) {
            try {
                input = StdIn.readLong();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid input, please try again!");
            }
        }
        return input;
    }

}