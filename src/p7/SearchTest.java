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
        StdOut.println("TEXT SIZE\tBRUTE FORCE SEARCH\tKMP SEARCH");
        while (sc.hasNext()) {
            String pattern = sc.next();
            String text = sc.next();

            long startTime = System.nanoTime();
            BruteForce.search(pattern, text);
            long bruteForce = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            KMPSearch.search(pattern, text);
            long kmp = System.nanoTime() - startTime;

            StdOut.printf("%d\t%d\t%d\n", text.length(), bruteForce, kmp);
        }
    }

}
