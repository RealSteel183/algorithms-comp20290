package p4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SortTest {
    static Scanner sc = new Scanner(System.in);
    private static int[] array_sizes = {10, 12, 100, 1000, 10000};
    private static ArrayList<Integer[]> random_arrays = new ArrayList<>();

    // run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        String[] sortTypes = {"selection", "insertion", "bogo"};
        runTests(sortTypes, array_sizes);
    }

    // display user options and perform tasks according to choice
    public static void runTests(String[] sortTypes, int[] array_sizes) throws Exception {
        generateRandomArrays(array_sizes);
        System.out.println("-Sorting Analysis-");
        System.out.println("1. Run timing analysis.");
        System.out.println("2. See sorted arrays (only small sizes).");
        System.out.print("Choose 1 or 2: ");
        int op = sc.nextInt();
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
                System.out.println("Invalid choice.");
        }
    }

    // generate random arrays of specified sizes
    public static void generateRandomArrays(int[] array_sizes) {
        for (int size : array_sizes) {
            random_arrays.add(generateArray(size));
        }
    }

    // generate a randomly filled array of given size
    public static Integer[] generateArray(int size) {
        Integer[] arr = new Integer[size];
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            // fill array with random integers
            arr[i] = rd.nextInt();
        }
        return arr;
    }

    // timing analysis
    public static void timingAnalysis(String sortType) throws Exception {
        printLine();
        System.out.printf("-%s SORT-\n", sortType.toUpperCase());
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

    // show the arrays before and after sorting (only for small size arrays)
    public static void visualiseSort(String sortType) throws Exception {
        // copy only the first 2 small arrays of size 10 and 12
        // larger order array sizes are impractical to display in terminal
        Integer[][] copy_arrays = {random_arrays.get(0), random_arrays.get(1)};
        for (Integer[] a : copy_arrays) {
            Integer[] array = new Integer[a.length];
            System.arraycopy(a, 0, array, 0, a.length);
            Sort<Integer> sort = new Sort<>(sortType, array);

            System.out.printf("\nORIGINAL ARRAY (size %d):\t", array.length);
            Sort.display(array);
            System.out.printf("AFTER %s SORT:\t\t", sortType.toUpperCase());
            sort.call();
            Sort.display(array);
            if (Sort.isNotSorted(array)) throw
                    new AssertionError(String.format("%s didn't work!", sortType));
        }
    }


    // print a line of dashes
    public static void printLine() {
        for (int i = 0; i < 57; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}