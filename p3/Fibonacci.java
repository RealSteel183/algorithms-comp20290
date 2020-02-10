package p3;

public class Fibonacci {
    private static long fibonacciIterative(int n) {
        if (n <= 1)
            return 1;

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    private static long fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        // for n > 9, recursive fibonacci starts taking more time than the iterative one
        // the difference becomes more pronounced once n takes vales > 30
        int n = 30;
        final long t1 = System.currentTimeMillis();
        long iterativeFib = fibonacciIterative(n);
        final long t2 = System.currentTimeMillis();

        System.out.println("Iterative answer = " + iterativeFib);
        System.out.println("Time taken for iterative Fibonacci (N = " + n + "): " + (t2 - t1));

        final long t3 = System.currentTimeMillis();
        long recursiveFib = fib(n);
        final long t4 = System.currentTimeMillis();

        System.out.println("Recursive answer = " + recursiveFib);
        System.out.println("Time taken for recursive Fibonacci (N = " + n + "): " + (t4 - t3));
    }
}

