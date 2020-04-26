package p7;

import util.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compares the performance of brute force and KMP substring search.
 *
 * @author Rajit Banerjee
 */

public class SearchTest {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/p7/input.txt"));
        StdOut.println("~ Compare the performance of brute force and KMP substring search for input.txt ~");
        StdOut.println("Search times in nanoseconds:");
        StdOut.println("Text Size\tBrute Force Search\tKMP Search\tIndex (BF)\tIndex (KMP)");
        while (sc.hasNextLine()) {
            // The text file contains alternating lines of patterns and text, each tes is 2 lines long
            String pattern = sc.nextLine();
            String text = sc.nextLine();

            long startTime = System.nanoTime();
            int i1 = BruteForce.search(pattern, text);
            long bruteForce = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            int i2 = KMPSearch.search(pattern, text);
            long kmp = System.nanoTime() - startTime;

            StdOut.printf("%5d\t%15d\t%15d\t%10d\t%10d\n", text.length(), bruteForce, kmp, i1, i2);
        }
    }

}
