package cz.braza.educanet;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ProfilingPythagoreanTriangles {
    public static <R> R timing(Supplier<R> operation, int count) {
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
        System.out.println("Run " + count + " times with avg execution time " + (sum / count) + "ms");
        return result;
    }

    public static void timing(Runnable operation, int count) {
        timing(() -> { operation.run(); return null; }, count);
    }

    /**
     * Tři vnořené cykly, jedna velká podmínka.
     * Miliarda průchodů.
     */
    public static void findP1000v1() {
        for (int a = 0; a < 1000; a++)
            for (int b = 0; b < 1000; b++)
                for (int c = 0; c < 1000; c++)
                    if (a < b && b < c && a + b + c == 1000 && a*a + b*b == c*c) {
                        System.out.println("A = " + a + ", B = " + b + ", C = " + c);
                        //return;
                    }
    }

    /**
     * Dva vnořené cykly, dopočítání třetí hodnoty, jedna velká podmínka.
     * Milion průchodů.
     */
    public static void findP1000v2() {
        for (int a = 0; a < 1000; a++)
            for (int b = 0; b < 1000; b++) {
                int c = 1000 - a - b;
                if (a < b && b < c && a + b + c == 1000 && a * a + b * b == c * c) {
                    System.out.println("A = " + a + ", B = " + b + ", C = " + c);
                    //return;
                }
            }
    }

    /**
     * Dvakrát vygenerovat náhodné číslo, dopočítat třetí, jedna velká podmínka
     * Negarantuje nalezení výsledku v konečném čase.
     */
    public static void findP1000vRnd() {
        long iterations = 0;
        Random rnd = new Random();
        while(true) {
            int a = rnd.nextInt(1, 1000);
            int b = rnd.nextInt(1, 1000);
            int c = 1000 - (a + b);
            iterations++;
            if (a < b && b < c && a + b + c == 1000 && a*a + b*b == c*c) {
                System.out.println("A = " + a + ", B = " + b + ", C = " + c);
                break;
            }
        }
        System.out.println("Took " + iterations + " guesses.");
    }

    /**
     * Dva vnořené cykly s ořezáváním.
     */
    public static void findP1000v9() {
        for (int a = 1; a < 333; a++)
            for (int b = a + 1; b < (1000 - a / 2); b++) {
                int c = 1000 - a - b;
                if (a*a + b*b == c*c) {
                    System.out.println("A = " + a + ", B = " + b + ", C = " + c);
                    //return;
                }

            }
    }

    public static void main(String[] args) {
        int REPEAT = 3;
        timing(() -> findP1000v1(), REPEAT);
        System.out.println("------------------------------------------------");
        timing(() -> findP1000vRnd(), REPEAT);
        System.out.println("------------------------------------------------");
        timing(() -> findP1000v2(), REPEAT);
        System.out.println("------------------------------------------------");
        timing(() -> findP1000v9(), REPEAT);
    }
}
