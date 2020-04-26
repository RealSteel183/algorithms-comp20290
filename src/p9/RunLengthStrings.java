package p9;

import util.StdOut;

/*
 *  Compilation:  javac p9/RunLengthStrings.java
 *  Execution:    java p9/RunLengthStrings someString
 *  Dependencies: util/StdOut.java
 *
 *  Example:
 *  % java p9/RunLengthStrings aaaabbbbbcdefff
 *  Original string:
 *  aaaabbbbbcdefff
 *  Compressed string:
 *  a4b5cdef3
 *
 */

/**
 * The {@code RunLengthStrings} class provides a static method for
 * compressing a String provided as command line argument.
 *
 * @author Rajit Banerjee
 */

public class RunLengthStrings {

    /**
     * Compresses the input String and writes the
     * results to standard output.
     *
     * @param input the String to compress
     */
    public static void compress(String input) {
        int counter = 1;
        input += " ";
        if (input.length() > 1) {
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == input.charAt(i - 1)) {
                    counter++;
                } else {
                    StdOut.print(input.charAt(i - 1));
                    if (counter > 1) {
                        StdOut.print(counter);
                    }
                    counter = 1;
                }
            }
        } else {
            StdOut.print(input);
        }
    }

    /**
     * Sample client that calls {@code compress()} on the command line
     * argument String input.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            StdOut.println("Original string:\n" + args[0]);
            StdOut.println("Compressed string:");
            compress(args[0]);
        }
    }

}