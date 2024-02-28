package cz.braza;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Timing {
    public static <R> R time(Supplier<R> operation, int count) {
        if (count < 1) count = 1; // sanity check
        long sum = 0;
        R result = null;
        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            result = operation.get();
            long end = System.nanoTime();
            long diff = TimeUnit.NANOSECONDS.toMillis(end - start);
            sum += diff;
            // System.out.printf("Execution took %dms\n", diff);
        }
        if (count > 1)
            System.out.println("Run " + count + " times with avg execution time " + (sum / count) + " ms");
        else
            System.out.println("Execution took " + sum + " ms.");
        return result;
    }
    public static <R> R time(Supplier<R> operation) {
        return time(operation, 1);
    }

    public static void time(Runnable operation, int count) {
        time(() -> { operation.run(); return null; }, count);
    }

    public static void time(Runnable operation) {
        time(() -> { operation.run(); return null; }, 1);
    }
}
