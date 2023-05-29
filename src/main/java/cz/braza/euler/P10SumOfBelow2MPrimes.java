package cz.braza.euler;

import cz.braza.Eratosthenes2;

public class P10SumOfBelow2MPrimes {
    public static void main(String[] args) {
        int MAX = 2000000;
        Eratosthenes2 sito = new Eratosthenes2(MAX + 1000);
        long sum = 0;
        int cislo;
        while ((cislo = sito.nextPrime()) < MAX) {
            sum += cislo;
        }
        System.out.println("Sum of sub2M primes is " + sum);
    }
}
