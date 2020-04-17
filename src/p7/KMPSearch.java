package p7;

import util.StdOut;

import java.util.Arrays;

/**
 * Knuth-Morris-Pratt algorithm implementation for substring search.
 *
 * @author Rajit Banerjee
 */
public class KMPSearch {
    public static int search(String pattern, String text) {
        int patLen = pattern.length();
        int textLen = text.length();
        if (patLen == 0) {
            return 0;
        }
        int[] lps = computeLPSArray(pattern, patLen); // Pattern pre-processing
        int j = 0; // Pattern index
        for (int i = 0; i < textLen; i++) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == patLen - 1) {
                    return i - j;
                }
                j++;
            } else if (j != 0) {
                j = lps[j - 1];
                i--;
            }
        }
        return -1;
    }

    // Stores the length of longest pattern prefix that is a substring [1..index] suffix
    private static int[] computeLPSArray(String pattern, int patLen) {
        int[] lps = new int[patLen];
        int len = 0; // Length of the previous longest prefix suffix
        for (int i = 1; i < patLen; i++) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
                i--;
            }
        }
        System.out.println(Arrays.toString(lps));
        return lps;
    }

    // Driver program to test above function
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        int index = search(pattern, text);
        StdOut.printf("\n%s found in %s at index %d.\n", pattern, text, index);
    }

}