package cz.braza.educanet;

import cz.braza.Timing;
import java.util.Random;

public class ProfilingPythagoreanTriangles {

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
        /*
        int REPEAT = 3;
        Timing.time(() -> findP1000v1(), REPEAT);
        System.out.println("------------------------------------------------");
        Timing.time(() -> findP1000vRnd(), REPEAT);
        System.out.println("------------------------------------------------");
        Timing.time(() -> findP1000v2(), REPEAT);
        System.out.println("------------------------------------------------");
        Timing.time(() -> findP1000v9(), REPEAT);

         */

        // counting iterations:
        /*
        Summary (for value 1000):
        Three cycles - 1 billion (10^9) iterations
        Two cycles - 1 million iterations (1000x faster)
        Two cycles with small fix - 83k iterations (12x faster)
        Two cycles with triangle check - 21k iterations (4x faster, 50000x in total)
        Summary for value 5000:
        var 1 - 125 billions
        var 2 - 25M (5000x)
        var 3 - 2.1M (12x)
        var 4 - 520k (4x, 240000x in total)
         */
        int[] values = {100, 500, 1000, 5000};
        for (int val: values) {
            System.out.println("Finding Pythagorean triplets for sum of sides " + val);
            System.out.println("==============================");
            System.out.println("Stupid: " + countIterationsStupid(val));
            System.out.println("Compute C: " + countIterationsBetter(val));
            System.out.println("Strip edge cases: " + countIterationsBetter2(val));
            System.out.println("Make it triangle: " + countIterationsEvenBetter(val));
            System.out.println();
        }
    }

    /// different view: count iterations:
    public static long countIterationsStupid(int sideSum) {
        long iterCount = 0;
        for (int a = 0; a < sideSum; a++)
            for (int b = 0; b < sideSum; b++)
                for (int c = 0; c < sideSum; c++) {
                    if (a < b && b < c && a + b + c == sideSum && a*a + b*b == c*c)
                        System.out.printf("Found solution %d, %d, %d%n", a, b, c);
                    iterCount++;
                }
        return iterCount;
    }

    public static long countIterationsBetter(int sideSum) {
        long iterCount = 0;
        for (int a = 0; a < sideSum; a++)
            for (int b = 0; b < sideSum; b++) {
                int c = sideSum - a - b;
                if (a < b && b < c && a * a + b * b == c * c)
                    System.out.printf("Found solution %d, %d, %d%n", a, b, c);
                iterCount++;
            }
        return iterCount;
    }

    public static long countIterationsBetter2(int sideSum) {
        long iterCount = 0;
        for (int a = 1; a < sideSum / 3; a++)
            for (int b = a + 1; b < (sideSum - a) / 2; b++) {
                int c = sideSum - a - b;
                if (a * a + b * b == c * c)
                    System.out.printf("Found solution %d, %d, %d%n", a, b, c);
                iterCount++;
            }
        return iterCount;
    }

    public static long countIterationsEvenBetter(int sideSum) {
        long iterCount = 0;
        for (int a = 1; a < sideSum / 3; a++)
            for (int b = Math.max(a + 1, sideSum / 2 - a); b < (sideSum - a) / 2; b++) {
                int c = sideSum - a - b;
                if (a * a + b * b == c * c)
                    System.out.printf("Found solution %d, %d, %d%n", a, b, c);
                iterCount++;
            }
        return iterCount;
    }
}
