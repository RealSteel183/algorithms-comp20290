package p5;

import java.util.Scanner;

public class EnhancedSortTest {
    static Scanner sc = new Scanner(System.in);
    private static int[] sizes = { 10, 12, 100, 1000, 10000, 100000, 1000000 };

    // run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        p4.SortTest.generateRandomArrays(sizes);
        System.out.println("Welcome to Practical 5!");
        System.out.println("1. Run timing analysis.");
        System.out.println("2. See sorted arrays (only small sizes).");
        System.out.print("Choose 1 or 2: ");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                p4.SortTest.runTests("insertion");
                p4.SortTest.runTests("merge");
                p4.SortTest.runTests("cool_merge");
                break;
            case 2:
                p4.SortTest.visualiseSort("insertion");
                p4.SortTest.visualiseSort("merge");
                p4.SortTest.visualiseSort("cool_merge");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}