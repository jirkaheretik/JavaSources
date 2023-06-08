package cz.braza.euler;

import cz.braza.Eratosthenes2;

/**
 * Find A and B (abs(a) <= 1000, abs(b) <= 1000) so that
 * N^2 + A*N + B produces the most CONSECUTIVE primes starting from N = 0;
 */
public class P27QuadraticPrimes {
    public static void main(String[] args) {
        Eratosthenes2 sito = new Eratosthenes2(1234567);
        int maxN = 0;
        int product = 0;
        for (int a = -999; a < 1000; a++)
            for (int b = -1000; b <= 1000; b++) {
                int n = 0;
                int cislo;
                do {
                    cislo = n * n + a * n + b;
                    if (cislo < 0) break;
                    n++;
                } while (sito.isPrime(cislo));
                if (n >= maxN) {
                    System.out.println("New max " + n + " for coefficients A = " + a + " and B = " + b);
                    maxN = n;
                    product = a * b;
                }
            }
        System.out.println("Product of A*B for max consecutive numbers: " + product);
    }
}
