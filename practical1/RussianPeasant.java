import java.util.Scanner;

public class RussianPeasant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two numbers to multiply: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        final long startTime = System.currentTimeMillis();
        int ans = RussianMultiply(a, b);
        final long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("Product (" + a + " x " + b + ") = " + ans);
        System.out.println("Time taken: " + elapsedTime + " milliseconds");

    }

    private static int RussianMultiply(int m, int n) {
        int res = 0;
        // finding the smaller number that will be halved
        int a = Math.min(m, n);
        // finding the larger number that will be doubled
        int b = Math.max(m, n);
        while (a != 0) {
            // if the smaller number is is odd, add the larger number to result
            if (a % 2 == 1) {
                res += b;
            }
            // halve the smaller number
            a /= 2;
            // double the larger number
            b *= 2;
        }
        return res;
    }
}
