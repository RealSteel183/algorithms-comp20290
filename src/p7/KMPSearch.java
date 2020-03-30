package p7;

import java.util.Arrays;

public class KMPSearch {
    public static int search(String pattern, String text) {
        int patLen = pattern.length();
        int textLen = text.length();
        if (patLen == 0) {
            return 0;
        }
        int[] lps = new int[patLen]; // Longest prefix suffix values
        computeLPSArray(pattern, patLen, lps); // Pattern pre-processing

        int i = 0, j = 0;
        while (i < textLen) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == patLen - 1) {
                    return i - patLen + 1;
                }
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    private static void computeLPSArray(String pattern, int patLen, int[] lps) {
        // Length of the previous longest prefix suffix
        int len = 0, i = 1;
        // Calculate lps[i] for i = 1 to M-1
        while (i < patLen) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = len;
                i++;
            }
        }
        System.out.println(Arrays.toString(lps));
    }

    // Driver program to test above function
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        int index = search(pattern, text);
        System.out.printf("\n%s found in %s at index %d.\n", pattern, text, index);
    }

}