import util.StdIn;
import util.StdOut;

import java.util.InputMismatchException;

/**
 * Main class to showcase the features of the repository.
 *
 * @author Rajit Banerjee
 */
public class Main {
    public static void main(String[] args) throws Exception {
        StdOut.println("~ COMP20290: Algorithms, portfolio by Rajit Banerjee ~");
        showMenu();
        execute(readInt());
    }

    // Display menu options to the user
    private static void showMenu() {
        String message = "\nOptions available:\n1. Russian Peasant's multiplication algorithm\n" +
                "2. Complexity analysis, to count triples summing to 0 in input files\n" +
                "3. Compute the Nth Fibonacci number\n" +
                "4. Solve the Towers of Hanoi problem\n" +
                "5. Compare the performance of selection, insertion and bogo sorts\n" +
                "6. Compare the performance of insertion, merge, and enhanced merge sorts\n" +
                "7. Compare the performance of merge, quick, and enhanced quick sorts\n" +
                "8. Search for a pattern in text using brute force\n" +
                "9. Search for a pattern in text using Knuth-Morris-Pratt algorithm\n" +
                "10. Compare the performance of brute force and KMP substring search for input.txt file\n" +
                "11. Demonstrate construction of a Trie, and search for certain words\n" +
                "12. Demonstrate some run length compression experiments\n" +
                "\nEnter your choice (1-12) or 0 to exit:";
        StdOut.println(message);
    }

    // Showcases features based on user's choice
    private static void execute(int choice) throws Exception {
        switch (choice) {
            case 0:
                StdOut.println("\nSee you later!");
                break;
            case 1:
                p1.RussianPeasant.main(null);
                showMenu();
                execute(readInt());
                break;
            case 2:
                String message = "~ Complexity analysis ~\n" +
                        "This demonstration requires the use of the command line.\n" +
                        "Open your terminal to the src/ directory and follow the instructions below:\n" +
                        "To count the triples summing to 0, you can use ThreeSumA (slow) or ThreeSumB (fast).\n" +
                        "The classes ThreeSumA and ThreeSumB have already been compiled.\n" +
                        "In your terminal, use any of these text files in place of \"FILE\" below:\n8ints.txt, " +
                        "1Kints.txt, 2Kints.txt, 4Kints.txt, 8Kints.txt, 16Kints.txt, 32Kints.txt\n\n" +
                        "$ java p2/ThreeSumA p2/FILE\nor,\n" +
                        "$ java p2/ThreeSumB p2/FILE\n\n" +
                        "E.g. $ java p2/ThreeSumB p2/1Kints.txt\n\n" +
                        "Enter 'x' when you are ready to proceed.";
                StdOut.println(message);
                while (Character.toLowerCase(StdIn.readChar()) != 'x') ;
                showMenu();
                execute(readInt());
                break;
            case 3:
                p3.Fibonacci.main(null);
                showMenu();
                execute(readInt());
                break;
            case 4:
                p3.Hanoi.main(null);
                showMenu();
                execute(readInt());
                break;
            case 5:
                p4.ElementarySortTest.main(null);
                showMenu();
                execute(readInt());
                break;
            case 6:
                p5.MergeSortTest.main(null);
                showMenu();
                execute(readInt());
                break;
            case 7:
                p6.QuickSortTest.main(null);
                showMenu();
                execute(readInt());
                break;
            case 8:
                p7.BruteForce.main(null);
                showMenu();
                execute(readInt());
                break;
            case 9:
                p7.KMPSearch.main(null);
                showMenu();
                execute(readInt());
                break;
            case 10:
                p7.SearchTest.main(null);
                showMenu();
                execute(readInt());
                break;
            case 11:
                p8.Trie.main(null);
                showMenu();
                execute(readInt());
                break;
            case 12:
                message = "~ Run length compression experiments ~\n" +
                        "This demonstration requires the use of the command line.\n" +
                        "Open your terminal to the /src directory and follow the documentation PDF below:\n" +
                        "https://github.com/CompAlgorithms/algorithm-portfolio-20290-rajitbanerjee/blob/master/src/p9" +
                        "/Practical%209%20-%20Run%20Length%20Compression.pdf\n" +
                        "Enter 'x' when you are ready to proceed.";
                StdOut.println(message);
                while (Character.toLowerCase(StdIn.readChar()) != 'x') ;
                showMenu();
                execute(readInt());
                break;
            default:
                StdOut.println("Invalid choice, please try again!");
                showMenu();
                execute(readInt());
        }
    }

    // Ensures that user enters an integer choice
    private static int readInt() {
        int input = -1;
        while (input == -1) {
            try {
                input = StdIn.readInt();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid choice, please try again!");
                showMenu();
            }
        }
        return input;
    }

}