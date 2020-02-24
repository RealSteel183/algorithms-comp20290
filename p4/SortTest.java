package p4;

import java.util.Scanner;

public class SortTest {
    static Scanner sc = new Scanner(System.in);
    private static final int[] SIZE = { 10, 12, 100, 1000, 10000 };
    private static int[] a = generateArray(SIZE[0]);
    private static int[] b = generateArray(SIZE[1]);
    private static int[] c = generateArray(SIZE[2]);
    private static int[] d = generateArray(SIZE[3]);
    private static int[] e = generateArray(SIZE[4]);

    // run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Practical 4!");
        System.out.println("1. Run timing analysis.");
        System.out.println("2. See sorted arrays (only small sizes).");
        System.out.print("Choose 1 or 2: ");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                runTests("selection");
                runTests("insertion");
                runTests("bogo");
                break;
            case 2:
                visualiseSort("selection");
                visualiseSort("insertion");
                visualiseSort("bogo");
                break;
            default:
                System.out.println("Invalid choice.");
        }

    }

    // timing analysis
    public static void runTests(String sortType)
            throws Exception {
        int[] a1 = new int[SIZE[0]];
        int[] b1 = new int[SIZE[1]];
        int[] c1 = new int[SIZE[2]];
        int[] d1 = new int[SIZE[3]];
        int[] e1 = new int[SIZE[4]];
        System.arraycopy(a, 0, a1, 0, a.length);
        System.arraycopy(b, 0, b1, 0, b.length);
        System.arraycopy(c, 0, c1, 0, c.length);
        System.arraycopy(d, 0, d1, 0, d.length);
        System.arraycopy(e, 0, e1, 0, e.length);

        printLine();
        System.out.printf("\n\n-%s SORT-\n", sortType.toUpperCase());
        for (int[] array : new int[][] { a1, b1, c1, d1, e1 }) {
            Sort sort = new Sort(sortType, array);
            System.out.printf("Time taken for array of size %d =\t%d nanosec\n", array.length,
                    Timing.nanoTimePerformance(sort));
        }
    }

    // show the arrays before and after sorting
    public static void visualiseSort(String sortType) throws Exception{
        int[] a1 = new int[SIZE[0]];
        int[] b1 = new int[SIZE[1]];
        System.arraycopy(a, 0, a1, 0, a.length);
        System.arraycopy(b, 0, b1, 0, b.length);

        for (int[] array : new int[][] { a1, b1 }) {
            Sort sort = new Sort(sortType, array);
            System.out.printf("\nORIGINAL ARRAY (size %d):\t", array.length);
            display(array);
            System.out.printf("AFTER %s SORT:\t\t", sortType.toUpperCase());
            sort.call();
            display(array);
        }
    }

    // generate a randomly filled array of given size
    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            // fill array with random elements between [0, size)
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    // display the array elements
    public static void display(int[] arr) {
        String ans = "{ ";
        for (int i = 0; i < arr.length - 1; i++) {
            ans += arr[i] + ", ";
        }
        ans += arr[arr.length - 1] + " }";
        System.out.println(ans);
    }

    // print a line of 80 dashes
    public static void printLine() {
        System.out.println();
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}