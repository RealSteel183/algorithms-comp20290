package p8;

import util.StdOut;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Builds a Trie data structure to help store and search for String keys.
 *
 * @author Rajit Banerjee
 */

public class Trie {

    // Number of symbols: 26 for the English alphabet
    private static final int ALPHABET_SIZE = 26;
    private static final TrieNode ROOT = new TrieNode();

    /**
     * Inserts a key into the Trie.
     *
     * @param key to be inserted into Trie.
     */
    public static void insert(String key) {
        TrieNode curr = ROOT;
        for (char letter : key.toCharArray()) {
            // If letter not in Trie, create child node with letter
            if (curr.getChild(letter) == null) {
                curr.createChild(letter);
            }
            curr = curr.getChild(letter);
        }
        curr.setEndOfWord(true);
    }

    /**
     * Search for a key in the Trie.
     *
     * @param key to be searched
     * @return {@code true}, if key is present in Trie
     */
    public static boolean search(String key) {
        TrieNode curr = ROOT;
        for (char letter : key.toCharArray()) {
            // Return false at any point a child letter is not found
            if (curr.getChild(letter) == null) {
                return false;
            }
            curr = curr.getChild(letter);
        }
        // Check that final child letter is found and end of word is reached
        return curr != null && curr.getIsEndOfWord();
    }

    // Simple tests for key insertion and search using a Trie
    public static void main(String[] args) {
        StdOut.println("~ Demonstrate construction of a Trie, and search for certain words ~");
        // Input keys (use only lowercase 'a' - 'z')
        String[] keys = {"bank", "book", "bar", "bring", "film",
                "filter", "simple", "silt", "silver"};
        String[] output = {"not present in trie", "present in trie"};

        // Construct trie
        for (String key : keys) {
            insert(key.toLowerCase());
        }
        // Search for a word
        StdOut.println("Words inserted in trie: " + Arrays.toString(keys));
        StdOut.println("Enter a word to search for:");
        String input = new Scanner(System.in).nextLine();
        StdOut.printf("\t%s:\t%s\n", input, output[search(input) ? 1 : 0]);
    }

    // Inner class to represent the constituent node of a Trie
    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        private boolean isEndOfWord; // Checks if node is leaf node (end of a word)

        // Creates a new node in the Trie
        TrieNode() {
            setEndOfWord(false);
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }

        // Get the node for a given letter
        TrieNode getChild(char letter) {
            return children[letter - 'a'];
        }

        // Make a new node in the Trie for a given letter
        void createChild(char letter) {
            children[letter - 'a'] = new TrieNode();
        }

        // Get the flag which checks if a node marks the end of a key
        boolean getIsEndOfWord() {
            return isEndOfWord;
        }

        // Sets the end of word flag for the calling node
        void setEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }
    }

}