package p7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src\\p7\\input.txt"));
        System.out.println("TEXT SIZE\tBRUTE FORCE SEARCH\tKMP SEARCH");
        while (sc.hasNext()) {
            String pattern = sc.next();
            String text = sc.next();

            long startTime = System.nanoTime();
            BruteForce.search(pattern, text);
            long bruteForce = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            KMPSearch.search(pattern, text);
            long kmp = System.nanoTime() - startTime;

            System.out.printf("%d\t%d\t%d\n", text.length(), bruteForce, kmp);
        }
    }

}
