package cz.braza.euler;

import cz.braza.EratosthenesSieve;

/**
 * Project Euler 3 - find the largest prime dividing 600851475143
 *
 * used https://www.hackmath.net/cz/kalkulacka/prvociselny-rozklad-cisla-faktorizace?n=600851475143&submit=Vypo%C4%8D%C3%ADtej
 * result: 600851475143 = 71 × 839 × 1471 × 6857
 */
public class P3LargestPrime {

    public static int[] primes = new int[1000];

    public static void main(String[] args) {
        long vstup = 600851475143L;
        int max = 0;
        EratosthenesSieve sito = new EratosthenesSieve(1000000);
        while (vstup > max) {
            int prime = sito.nextPrime();
            if (vstup % prime == 0) {
                System.out.println("Found " + prime);
                max = prime;
                while (vstup % prime == 0)
                    vstup /= prime;
            }
        }
    }
}
