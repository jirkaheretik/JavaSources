package cz.braza.euler;

import cz.braza.Timing;
import cz.braza.educanet.Frac;

/**
 * Euler 71:
 * Find out the highest fraction N/D, such:
 * - it is lower than 3/7
 * - D <= 1_000_000
 */
public class P71OrderedFractions {
    public static void doWithFractions() {
        Frac target = Frac.of(3, 7);
        Frac lower = Frac.of(2, 7);
        for (int D = 1_000_000; D > 900_000; D--)
            for (int N = (int) Math.ceil(D * 3.0 / 7); N >= (int) Math.floor(D * 3.0 / 7); N--) {
                Frac f = Frac.of(N, D);
                if (f.compareTo(lower) > 0 && f.compareTo(target) < 0)
                    lower = f;
            }
        System.out.println("Result: " + lower);
    }

    public static void doWithDoubles() {
        // PART2 - Doubles
        double target = 3.0/7;
        int numerator = 0;
        double lower = 2.0/7;
        for (int D = 1_000_000; D > 900_000; D--)
            for (int N = (int) Math.ceil(D * 3.0 / 7); N >= (int) Math.floor(D * 3.0 / 7); N--) {
                double  df = (double) N / D;
                if (df > lower && df < target) {
                    lower = df;
                    numerator = N;
                }
            }
        System.out.println("Result: " + numerator);
    }

    public static void doWithDoublesOneCycle() {
        // PART3 - Doubles one cycle
        double target = 3.0/7;
        int numerator = 0;
        double lower = 2.0/7;
        for (int D = 1_000_000; D > 900_000; D--) {
            int N = (int) Math.floor(D * 3.0 / 7);
            double df = (double) N / D;
            if (df > lower && df < target) {
                lower = df;
                numerator = N;
            }
        }
        System.out.println("Result: " + numerator);
    }

    public static void main(String[] args) {
        Timing.time(() -> doWithFractions(), 1);
        Timing.time(() -> doWithDoubles(), 1);
        Timing.time(() -> doWithDoublesOneCycle(), 1);
    }
}
