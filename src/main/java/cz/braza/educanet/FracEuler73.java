package cz.braza.educanet;

import cz.braza.Timing;

import java.util.SortedSet;
import java.util.TreeSet;

public class FracEuler73 {
    public static void countFractions(Frac lower, Frac upper, int maxDenominator) {
        SortedSet<Frac> zlomky = new TreeSet<>();
        for (int d = 2; d <= maxDenominator; d++)
            for (int n = 1; n < d; n++) {
                Frac f = Frac.of(n, d);
                if (lower.compareTo(f) < 0 && upper.compareTo(f) > 0)
                    zlomky.add(f);
            }
        System.out.println("There are " + zlomky.size() + " fractions between " + lower + " and " +  upper + " with highest denominator " + maxDenominator + ".");
    }

    public static int findFractionsCount(Frac lower, Frac upper, int maxDenominator) {
        SortedSet<Frac> zlomky = new TreeSet<>();
        for (int d = 2; d <= maxDenominator; d++)
            for (int n = 1; n < d; n++) {
                Frac f = Frac.of(n, d);
                if (lower.compareTo(f) < 0 && upper.compareTo(f) > 0)
                    zlomky.add(f);
            }
        return zlomky.size();
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int dummy = b;
            b = a % b;
            a = dummy;
        }
        return a;
    }
    public static boolean isReduced(int a, int b) { return gcd(a, b) == 1; }

    public static int findFractionsCount(double lower, double upper, int maxDenominator) {
        int count = 0;
        for (int d = 2; d <= maxDenominator; d++)
            for (int n = 1; n < d; n++) {
                if (isReduced(n, d)) {
                    double val = (double) n / d;
                    if (lower < val && upper > val)
                        count++;
                }
            }
        return count;
    }

    public static void main(String[] args) {
        final Frac lower = Frac.of(1, 31);
        final Frac upper = Frac.of(12, 13);
        final double dLower = lower.toReal();
        final double dUpper = upper.toReal();
        final int D = 5031;
        Timing.time(() -> countFractions(lower, upper, D));
        int pocet = Timing.time(() -> findFractionsCount(lower, upper, D));
        System.out.println(pocet + " fractions betwwen " + lower + " and " + upper + " with denominator up to " + D);
        pocet = Timing.time(() -> findFractionsCount(dLower, dUpper, D));
        System.out.println(pocet + " fractions betwwen " + lower + " and " + upper + " with denominator up to " + D);
    }
}
