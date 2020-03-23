package p4;

import java.util.concurrent.Callable;
public class Timing {
    public static <E> long nanoTimePerformance(Callable<E[]> callable)
            throws Exception {
        long startTime = System.nanoTime();
        callable.call();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}