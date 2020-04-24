package a1;

import util.BinaryStdIn;
import util.BinaryStdOut;
import util.MinPQ;

/*
 *  Compilation: javac a1/Huffman.java
 *  Execution: java a1/Huffman compress < input > output
 *  Execution: java a1/Huffman decompress < input > output
 *  Dependencies: util/BinaryStdIn.java, util/BinaryStdOut.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  Examples:
 * % java a1/Huffman compress < a1/medTale.txt > a1/medTale_comp.txt
 * % java a1/Huffman decompress < a1/medTale_comp.txt > a1/medTale_decomp.txt
 *
 */

/**
 * The {@code Huffman} class provides support for the following:
 * <p>
 * 1. compress() - Static method to compress binary input using Huffman coding.
 * <p>
 * 2. decompress() - Static method to decompress binary input (reversing the
 * Huffman encoding process).
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
 * 7. Node - Inner static class to represent a Huffman trie node.
 * <p>
 * 8. The encoding and decoding use the 8-bit extended ASCII alphabet.
 *
 * @author Rajit Banerjee
 */

public class Huffman {

    private static final int ALPHABET_SIZE = 256; // extended ASCII

    private Huffman() {
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {
        // Read the input
        char[] text = BinaryStdIn.readString().toCharArray();

        // Tabulate frequency counts, build Huffman trie and code table
        int[] freq = new int[ALPHABET_SIZE];
        for (char ch : text) {
            freq[ch]++;
        }
        Node root = buildTrie(freq);
        String[] table = new String[ALPHABET_SIZE];
        buildCode(table, root, "");

        writeTrie(root); // Display trie
        BinaryStdOut.write(text.length); // Bytes in original message

        // Text encoding (using Huffman code)
        for (char ch : text) {
            for (char code : table[ch].toCharArray()) {
                BinaryStdOut.write(code == '1');
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {
        Node root = readTrie(); // Read Huffman trie
        int numBytes = BinaryStdIn.readInt();
        int i = 0;
        while (i++ < numBytes) {
            Node node = root;
            // Traverse Huffman trie until a leaf node is reached
            while (node.isInternal()) {
                node = BinaryStdIn.readBoolean() ? node.getRight() : node.getLeft();
            }
            BinaryStdOut.write(node.getChar(), 8);
        }
        BinaryStdOut.close();
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
        BinaryStdOut.write(!node.isInternal());
        if (node.isInternal()) {
            writeTrie(node.getLeft());
            writeTrie(node.getRight());
        } else {
            BinaryStdOut.write(node.getChar(), 8);
        }
    }

    // Read Huffman trie from standard input
    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("compress")) {
            compress();
        } else if (args[0].equals("decompress")) {
            decompress();
        } else {
            String message = "Invalid command! Please refer to Huffman class description.";
            throw new IllegalArgumentException(message);
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