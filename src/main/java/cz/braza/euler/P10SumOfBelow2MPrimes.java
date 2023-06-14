package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P10SumOfBelow2MPrimes {
    public static void main(String[] args) {
        int MAX = 2000000;
        EratosthenesSieve sito = new EratosthenesSieve(MAX + 1000);
        long sum = 0;
        int cislo;
        while ((cislo = sito.nextPrime()) < MAX) {
            sum += cislo;
        }
        System.out.println("Sum of primes under " + MAX + " is " + sum);
    }
}
