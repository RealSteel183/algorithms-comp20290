package a1;

import util.BinaryIn;
import util.BinaryOut;
import util.MinPQ;
import util.StdOut;

/*
 *  Compilation: javac a1/Huffman.java
 *  Execution: java a1/Huffman compress inputFileName outputFileName
 *  Execution: java a1/Huffman decompress inputFileName outputFileName
 *  Dependencies: util/BinaryIn.java, util/BinaryOut.java, util/MinPQ.java, util/StdOut.java
 *
 *  Compress or decompress a given file using the Huffman algorithm (optimal
 *  prefix-free coding).
 *
 *  Examples:
 * % java a1/Huffman compress a1/medTale.txt a1/medTale_comp.txt
 * % java a1/Huffman decompress a1/medTale_comp.txt a1/medTale_decomp.txt
 *
 */

/**
 * The {@code Huffman} class provides support for the following:
 * <p>
 * 1. compress() - Static method to compress a file using Huffman coding.
 * <p>
 * 2. decompress() - Static method to decompress a compressed file (reversing
 * the Huffman encoding process).
 * <p>
 * 3. buildTrie(freq) - Private method to build the Huffman trie using the
 * information about frequencies of characters in the text to be compressed.
 * <p>
 * 4. buildCode(table, node, s) - Private method to construct the codeword table,
 * comprising Huffman codes for each character in text, by traversing the Huffman trie.
 * <p>
 * 5. writeTrie(node) - Private method to display the encoded bits from the Huffman trie.
 * <p>
 * 6. readTrie() - Private method to read a Huffman trie's encoded bits.
 * <p>
 * 7. printStats(f1, f2) - Private method to display the number of bits in original file f1,
 * number of bits in compressed file f2, and the compression ratio.
 * *
 * 8. countBits(file) - Private method to count the number of bits in the given file.
 * <p>
 * 9. Node - Inner static class to represent a Huffman trie node.
 * <p>
 * 10. The encoding and decoding use the 8-bit extended ASCII alphabet.
 *
 * @author Rajit Banerjee
 */

public class Huffman {

    private static final int ALPHABET_SIZE = 256; // extended ASCII
    private static BinaryIn binaryIn;
    private static BinaryOut binaryOut;

    private Huffman() {
    }

    /**
     * Reads a sequence of 8-bit bytes from a given file; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to a given output file.
     */
    public static void compress() {
        // Read the input
        char[] text = binaryIn.readString().toCharArray();

        // Tabulate frequency counts, build Huffman trie and code table
        int[] freq = new int[ALPHABET_SIZE];
        for (char ch : text) {
            freq[ch]++;
        }
        Node root = buildTrie(freq);
        String[] table = new String[ALPHABET_SIZE];
        buildCode(table, root, "");

        writeTrie(root); // Write the Huffman trie
        binaryOut.write(text.length); // Bytes in original message

        // Text encoding (using Huffman code)
        for (char ch : text) {
            for (char code : table[ch].toCharArray()) {
                binaryOut.write(code == '1');
            }
        }
        binaryOut.close();
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {
        Node root = readTrie(); // Read the Huffman trie
        int numBytes = binaryIn.readInt(); // Bytes in original message
        int i = 0;
        while (i++ < numBytes) {
            Node node = root;
            // Traverse Huffman trie until a leaf node is reached
            while (node.isInternal()) {
                node = binaryIn.readBoolean() ? node.getRight() : node.getLeft();
            }
            binaryOut.write(node.getChar(), 8);
        }
        binaryOut.close();
    }

    // Build the Huffman trie, given the frequencies of every character in text
    private static Node buildTrie(int[] freq) {

        // Initialise priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < ALPHABET_SIZE; i++) {
            if (freq[i] > 0) {
                pq.insert(new Node(i, freq[i], null, null));
            }
        }

        // Special case: only one character with a non-zero frequency exists
        if (pq.size() == 1) {
            char ch = freq['\0'] == 0 ? '\0' : '\1';
            pq.insert(new Node(ch, 0, null, null));
        }

        // Merge two smallest subtrees
        while (pq.size() > 1) {
            Node l = pq.delMin();
            Node r = pq.delMin();
            pq.insert(new Node('\0', l.getFreq() + r.getFreq(), l, r));
        }
        return pq.delMin();
    }

    // Make a lookup table from symbols and their encodings
    private static void buildCode(String[] table, Node node, String s) {
        if (node.isInternal()) {
            buildCode(table, node.getLeft(), s + '0');
            buildCode(table, node.getRight(), s + '1');
        } else {
            table[node.getChar()] = s;
        }
    }

    // Write bit-string encoded trie to standard output
    private static void writeTrie(Node node) {
        binaryOut.write(!node.isInternal());
        if (node.isInternal()) {
            writeTrie(node.getLeft());
            writeTrie(node.getRight());
        } else {
            binaryOut.write(node.getChar(), 8);
        }
    }

    // Read Huffman trie from standard input
    private static Node readTrie() {
        boolean isLeaf = binaryIn.readBoolean();
        if (isLeaf) {
            return new Node(binaryIn.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    // Display compression statistics: original bits, compressed bits, ratio
    private static void printStats(String originalFile, String compressedFile) {
        double originalBits = countBits(originalFile);
        double compressedBits = countBits(compressedFile);
        double ratio = (compressedBits / originalBits) * 100;
        StdOut.printf("\nOriginal bits:\t\t\t%.0f", originalBits);
        StdOut.printf("\nCompressed bits: \t\t%.0f", compressedBits);
        StdOut.printf("\nCompression ratio:\t\t%.0f/%.0f = %.2f%%\n", compressedBits, originalBits, ratio);
    }

    // Count the number of bits in a given file (path)
    private static int countBits(String file) {
        BinaryIn binaryIn = new BinaryIn(file);
        int count = 0;
        while (!binaryIn.isEmpty()) {
            binaryIn.readBoolean();
            count++;
        }
        return count;
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3 && !args[0].equals("compress") && !args[0].equals("decompress")) {
            String message = "\n\nInvalid command! See below for execution details:\n" +
                    "% java a1/Huffman compress inputFileName outputFileName\n" +
                    "% java a1/Huffman decompress inputFileName outputFileName\n" +
                    "\nExamples:\n" +
                    "% java a1/Huffman compress a1/medTale.txt a1/medTale_comp.txt\n" +
                    "% java a1/Huffman decompress a1/medTale_comp.txt a1/medTale_decomp.txt\n";
            throw new IllegalArgumentException(message);
        } else {
            binaryIn = new BinaryIn(args[1]); // Input file
            binaryOut = new BinaryOut(args[2]); // Output file
            long t1 = System.currentTimeMillis();
            if (args[0].equals("compress")) {
                compress();
                StdOut.printf("\nTime taken for compression:\t%d milliseconds",
                        (System.currentTimeMillis() - t1));
                StdOut.printf("\nInput file (original):\t\t%s", args[1]);
                StdOut.printf("\nOutput file (compressed):\t%s", args[2]);
                printStats(args[1], args[2]);
            } else {
                decompress();
                StdOut.printf("\nTime taken for decompression:\t%d milliseconds",
                        (System.currentTimeMillis() - t1));
                StdOut.printf("\nInput file (compressed):\t%s", args[1]);
                StdOut.printf("\nOutput file (decompressed):\t%s", args[2]);
                StdOut.printf("\nFinal bits (decompressed):\t%d\n", countBits(args[2]));
            }
        }
    }

    // Huffman trie node
    private static class Node implements Comparable<Node> {

        private final char ch;
        private final int freq;
        private final Node left;
        private final Node right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        // Accessor methods
        private char getChar() {
            return ch;
        }

        private int getFreq() {
            return freq;
        }

        private Node getLeft() {
            return left;
        }

        private Node getRight() {
            return right;
        }

        // Check if a trie node is an internal node (not a leaf node)
        private boolean isInternal() {
            return (left != null) || (right != null);
        }

        // Comparison based on frequency
        @Override
        public int compareTo(Node node) {
            return freq - node.freq;
        }
    }

}