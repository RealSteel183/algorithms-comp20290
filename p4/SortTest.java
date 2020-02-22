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
    public static void main(String[] args) {
        System.out.println("Welcome to Practical 4!");
        System.out.println("1. Run timing analysis.");
        System.out.println("2. See sorted arrays (only small sizes).");
        System.out.print("Choose 1 or 2: ");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                runSelectionTests();
                runInsertionTests();
                runBogoTests();
                break;
            case 2:
                seeSelectionSort();
                seeInsertionSort();
                seeBogoSort();
                break;
            default:
                System.out.println("Invalid choice.");
        }

    }

    // selection sort timing analysis
    private static void runSelectionTests() {
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
        System.out.println("\n-Selection Sort-");
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", a1.length, testSelection(a1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", b1.length, testSelection(b1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", c1.length, testSelection(c1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", d1.length, testSelection(d1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", e1.length, testSelection(e1));
    }

    // insertion sort timing analysis
    private static void runInsertionTests() {
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
        System.out.println("\n-Insertion Sort-");
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", a1.length, testInsertion(a1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", b1.length, testInsertion(b1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", c1.length, testInsertion(c1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", d1.length, testInsertion(d1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", e1.length, testInsertion(e1));
    }

    // bogo sort timing analysis
    private static void runBogoTests() {
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
        System.out.println("\n-Bogo Sort-");
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", a1.length, testBogo(a1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", b1.length, testBogo(b1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", c1.length, testBogo(c1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", d1.length, testBogo(d1));
        System.out.printf("Time taken for array of size %d =\t%d nanosec\n", e1.length, testBogo(e1));
    }

    // selection sort before and after
    private static void seeSelectionSort() {
        int[] a1 = new int[SIZE[0]];
        int[] b1 = new int[SIZE[1]];
        System.arraycopy(a, 0, a1, 0, a.length);
        System.arraycopy(b, 0, b1, 0, b.length);

        System.out.printf("\nOrignal array (size %d):\t", SIZE[0]);
        display(a1);
        System.out.print("After selection sort:\t\t");
        Sort.selection(a1);
        display(a1);

        System.out.printf("\nOrignal array (size %d):\t", SIZE[1]);
        display(b1);
        System.out.print("After selection sort:\t\t");
        Sort.selection(b1);
        display(b1);
    }

    // insertion sort before and after
    private static void seeInsertionSort() {
        int[] a1 = new int[SIZE[0]];
        int[] b1 = new int[SIZE[1]];
        System.arraycopy(a, 0, a1, 0, a.length);
        System.arraycopy(b, 0, b1, 0, b.length);
        
        System.out.printf("\nOrignal array (size %d):\t", SIZE[0]);
        display(a1);
        System.out.print("After insertion sort:\t\t");
        Sort.insertion(a1);
        display(a1);

        System.out.printf("\nOrignal array (size %d):\t", SIZE[1]);
        display(b1);
        System.out.print("After insertion sort:\t\t");
        Sort.insertion(b1);
        display(b1);
    }

    // bogo sort before and after
    private static void seeBogoSort() {
        int[] a1 = new int[SIZE[0]];
        int[] b1 = new int[SIZE[1]];
        System.arraycopy(a, 0, a1, 0, a.length);
        System.arraycopy(b, 0, b1, 0, b.length);
        
        System.out.printf("\nOrignal array (size %d):\t", SIZE[0]);
        display(a1);
        System.out.print("After bogo sort:\t\t");
        Sort.bogo(a1);
        display(a1);

        System.out.printf("\nOrignal array (size %d):\t", SIZE[1]);
        display(b1);
        System.out.print("After bogo sort:\t\t");
        Sort.bogo(b1);
        display(b1);
    }

    // test Selection Sort performance for randomly generated array
    private static long testSelection(int[] a) {
        long startTime = System.nanoTime();
        Sort.selection(a);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // test Insertion Sort performance for randomly generated array
    private static long testInsertion(int[] a) {
        long startTime = System.nanoTime();
        Sort.insertion(a);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // test Bogo Sort performance for randomly generated array
    private static long testBogo(int[] a) {
        long startTime = System.nanoTime();
        Sort.bogo(a);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // generate a randomly filled array of given size
    private static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            // fill array with random elements between [0, size)
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }

    // display the array elements
    private static void display(int[] arr) {
        String ans = "{ ";
        for (int i = 0; i < arr.length - 1; i++) {
            ans += arr[i] + ", ";
        }
        ans += arr[arr.length - 1] + " }";
        System.out.println(ans);
    }

    // print a line of 80 dashes
    private static void printLine() {
        System.out.println();
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}