package p1;

import java.util.Scanner;

/**
 * Multiplication of two numbers using the Russian Peasant's algorithm.
 *
 * @author Rajit Banerjee
 */
public class RussianPeasant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two numbers to multiply: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        final long startTime = System.currentTimeMillis();
        int ans = RussianMultiply(a, b);
        final long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("Product (" + a + " x " + b + ") = " + ans);
        System.out.println("Time taken: " + elapsedTime + " milliseconds");

    }

    // Implement the Russian Peasant's algorithm
    private static int RussianMultiply(int m, int n) {
        int res = 0;
        int smaller = Math.min(m, n);
        int larger = Math.max(m, n);
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