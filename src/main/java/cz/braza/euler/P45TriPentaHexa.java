package cz.braza.euler;

import java.util.ArrayList;

/**
 * Triangular numbers: n*(n+1)/2 ... 1, 3, 6, 10, 15, ...; T285 = 40755
 * Pentagonal numbers: n*(3n-1)/2 ... 1, 5, 12, 22, 35, ...; P165 = 40755
 * Hexagonal numbers: n*(2n-1) .....  1, 6, 15, 28, 45, ...; H143 = 40755
 * Find next number (after 40755) that is triangular, hexagonal and pentagonal
 */
public class P45TriPentaHexa {
    public static void main(String[] args) {
        int N = 100000;
        int needle = 405939771; // when working with ints into longs, this is (wrongly) counted as P, T and H as well due to some overflows.
        ArrayList<Long> hexa = new ArrayList<>();
        for (long i = 1; i < N; i++) {
            long res = ((2 * i - 1) * i);
            hexa.add(res);
            if (res == needle) System.out.println("H" + i + " = " + res);
        }
        System.out.println("Done computing " + N + " hexagonal numbers.");
        ArrayList<Long> pentaHexa = new ArrayList<>();
        for (long i = 1; i < 2 * N; i++) {
            long cislo = i * (3 * i - 1) / 2;
            if (hexa.contains(cislo)) {
                pentaHexa.add(cislo);
                if (cislo == needle) System.out.println("P" + i + " = " + cislo);
            }
        }
        System.out.println("Done computing " + (2 * N) + " pentagonal numbers.");
        // and now triangular:
        for (long i = 1; i < 3 * N; i++) {
            long cislo = i * (i + 1) / 2;
            if (pentaHexa.contains(cislo))
                System.out.println(i + ". traingular number is " + cislo);
        }
    }
}
