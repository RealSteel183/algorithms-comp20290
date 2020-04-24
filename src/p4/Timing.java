package p4;

import java.util.concurrent.Callable;

/**
 * Timing analysis on a Callable class object.
 *
 * @author Rajit Banerjee
 */

public class Timing {

    /**
     * @param callable a class that is Callable
     * @param <E>      generic type of return value of the call() method in callable
     * @return the time in nanoseconds taken to execute the call() method in callable
     * @throws Exception in case of any unusual event
     */
    public static <E> long nanoTimePerformance(Callable<E[]> callable)
            throws Exception {
        long startTime = System.nanoTime();
        callable.call();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}