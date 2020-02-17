package p4;

public class SortTest {
    private static final int SIZE_ONE = 10;
    private static final int SIZE_TWO = 12;
    private static final int SIZE_THREE = 100;
    private static final int SIZE_FOUR = 1000;
    private static final int SIZE_FIVE = 10000;

    // run tests for all 3 sorting algorithms with arrays of various sizes
    public static void main(String[] args) {
        testSortsWithSize(SIZE_ONE);
        testSortsWithSize(SIZE_TWO);
    }

    // test all 3 sorting algorithms with arrays of a given size
    private static void testSortsWithSize(int size) {
        printLine();
        System.out.printf("Testing sorting algorithm with array of SIZE = %d", size);
        int[] a1 = generateArray(size);
        int[] a2 = new int[size];
        int[] a3 = new int[size];
        System.arraycopy(a1, 0, a2, 0, size);
        System.arraycopy(a1, 0, a3, 0, size);

        System.out.printf("Time taken for array of size %d = %d nanosec\n", a1.length, testSelection(a1));
        System.out.printf("Time taken for array of size %d = %d nanosec\n", a2.length, testInsertion(a2));
        System.out.printf("Time taken for array of size %d = %d nanosec\n", a3.length, testBogo(a3));
    }

    // test Selection Sort performance for randomly generated array
    private static long testSelection(int[] a) {
        System.out.println("\n-Selection Sort-");
        System.out.print("Original array:\t");
        display(a);

        long startTime = System.nanoTime();
        Sort.selection(a);
        long endTime = System.nanoTime();

        System.out.print("Sorted array:\t");
        display(a);
        return endTime - startTime;
    }

    // test Insertion Sort performance for randomly generated array
    private static long testInsertion(int[] a) {
        System.out.println("\n-Insertion Sort-");
        System.out.print("Original array:\t");
        display(a);

        long startTime = System.nanoTime();
        Sort.insertion(a);
        long endTime = System.nanoTime();

        System.out.print("Sorted array:\t");
        display(a);
        return endTime - startTime;
    }

    // test Bogo Sort performance for randomly generated array
    private static long testBogo(int[] a) {
        System.out.println("\n-Bogo Sort-");
        System.out.print("Original array:\t");
        display(a);

        long startTime = System.nanoTime();
        Sort.bogo(a);
        long endTime = System.nanoTime();

        System.out.print("Sorted array:\t");
        display(a);
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

    private static void printLine() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}