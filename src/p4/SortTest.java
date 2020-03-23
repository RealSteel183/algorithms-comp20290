package p4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Compare the performance of selection sort, insertion sort, and bogo sort.
 *
 * @author Rajit Banerjee
 */
public class SortTest {
    static Scanner sc = new Scanner(System.in);
    private static int[] array_sizes = {10, 12, 100, 1000, 10000};
    private static ArrayList<Integer[]> random_arrays = new ArrayList<>();

    // Run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"selection_sort", "insertion_sort", "bogo_sort"};
        runTests(sortTypes, array_sizes);
    }

    /**
     * Display user options and perform tasks according to choice
     *
     * @param sortTypes   types of sorts to be tested
     * @param array_sizes different array sizes to perform tests on
     * @throws Exception if any unusual event takes place while running tests
     */
    public static void runTests(String[] sortTypes, int[] array_sizes) throws Exception {
        generateRandomArrays(array_sizes);
        promptUser();
        int op = 0;
        try {
            op = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Invalid option!");
        }
        switch (op) {
            case 1:
                for (String type : sortTypes) {
                    timingAnalysis(type);
                }
                break;
            case 2:
                for (String type : sortTypes) {
                    visualiseSort(type);
                }
                break;
            default:
                System.exit(0);
        }
    }

    // Generate random arrays of specified sizes
    private static void generateRandomArrays(int[] array_sizes) {
        random_arrays.clear();
        for (int size : array_sizes) {
            random_arrays.add(generateArray(size));
        }
    }

    // Generate a randomly filled array of given size
    private static Integer[] generateArray(int size) {
        Integer[] arr = new Integer[size];
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            // Fill array with random integers
            arr[i] = rd.nextInt(size);
        }
        return arr;
    }

    // Prompt the user to choose from a menu
    private static void promptUser() {
        System.out.println("-Sorting Analysis-");
        System.out.println("1. Run timing analysis.");
        System.out.println("2. See sorted arrays (only small sizes).");
        System.out.print("Choose 1 or 2: ");
    }

    // Timing analysis
    private static void timingAnalysis(String sortType) throws Exception {
        printLine();
        System.out.printf("-%s-\n", sortType.toUpperCase());
        for (Integer[] a : random_arrays) {
            Integer[] array = new Integer[a.length];
            System.arraycopy(a, 0, array, 0, a.length);
            Sort<Integer> sort = new Sort<>(sortType, array);
            System.out.printf("Time taken for array of size %d = %d nanoseconds\n", array.length,
                    Timing.nanoTimePerformance(sort));
            if (Sort.isNotSorted(array)) throw
                    new AssertionError(String.format("%s didn't work!", sortType));
        }
    }

    // Show the arrays before and after sorting (only for small size arrays)
    private static void visualiseSort(String sortType) throws Exception {
        /*
        Copy only the first 2 small arrays of size 10 and 12.
        Larger order array sizes are impractical to display in terminal.
        */
        Integer[][] copy_arrays = {random_arrays.get(0), random_arrays.get(1)};
        for (Integer[] a : copy_arrays) {
            Integer[] array = new Integer[a.length];
            System.arraycopy(a, 0, array, 0, a.length);
            Sort<Integer> sort = new Sort<>(sortType, array);

            System.out.println("\nArray size: " + array.length);
            System.out.printf("BEFORE %s:\t%s\n", sortType.toUpperCase(), Sort.display(array));
            sort.call();
            System.out.printf("AFTER %s:\t%s\n", sortType.toUpperCase(), Sort.display(array));
            if (Sort.isNotSorted(array)) throw
                    new AssertionError(String.format("%s didn't work!", sortType));
        }
    }

    // Print a line of dashes
    private static void printLine() {
        for (int i = 0; i < 57; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
