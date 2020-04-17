package p7;

import util.StdIn;
import util.StdOut;

/**
 * Brute force implementation of a substring search.
 *
 * @author Rajit Banerjee
 */
public class BruteForce {
    /**
     * Brute force search for pattern in text.
     *
     * @param text    String to search pattern in
     * @param pattern String to be search for in text
     * @return starting of pattern in text if found, else -1
     */
    public static int search(String pattern, String text) {
        int patLen = pattern.length();
        int textLen = text.length();
        for (int i = 0; i <= textLen - patLen; i++) {
            int j = -1;
            while (++j < patLen) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == patLen) {
                return i;
            }
        }
        return -1;
    }

    // Alternative brute force algorithm using java substring() and startsWith()
    private static int simpleSearch(String text, String pattern) {
        int textLen = text.length();
        int patLen = pattern.length();
        for (int i = 0; i <= textLen - patLen; i++) {
            if (text.substring(i).startsWith(pattern)) {
                return i;
            }
        }
        return -1;
    }

    // Driver program to test above search function above
    public static void main(String[] args) {
        StdOut.println("~ Search for a pattern in text using brute force ~");
        StdOut.println("Enter text:");
        String text = StdIn.readString();
        StdOut.println("Enter pattern:");
        String pattern = StdIn.readString();
        int index = search(pattern, text);
        if (index == -1) {
            StdOut.printf("\n%s NOT found in %s\n", pattern, text);
        } else {
            StdOut.printf("\n%s found in %s at index %d\n", pattern, text, index);
        }
    }

}