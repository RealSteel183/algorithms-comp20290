package p4;

public class SortTest {
    public static void main(String[] args) {
        int[] a = { 1, 7, 7, 3, 2, 10 };
        testSelection(a);
        int[] b = { 19, 23, 643, 24, 34 };
        testInsertion(b);
        int[] c = { 19, 23, 643, 24, 34 };
        testBogo(c);
    }

    private static void testSelection(int[] a) {
        System.out.println("\n-Selection Sort-");
        System.out.print("Original array:\t");
        display(a);
        Sort.selection(a);
        System.out.print("Sorted array:\t");
        display(a);
    }

    private static void testInsertion(int[] a) {
        System.out.println("\n-Insertion Sort-");
        System.out.print("Original array:\t");
        display(a);
        Sort.insertion(a);
        System.out.print("Sorted array:\t");
        display(a);
    }

    private static void testBogo(int[] a) {
        System.out.println("\n-Bogo Sort-");
        System.out.print("Original array:\t");
        display(a);
        Sort.bogo(a);
        System.out.print("Sorted array:\t");
        display(a);
    }

    private static void display(int[] arr) {
        String ans = "{ ";
        for (int i = 0; i < arr.length - 1; i++) {
            ans += arr[i] + ", ";
        }
        ans += arr[arr.length - 1] + " }";
        System.out.println(ans);
    }
}