package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P47DistinctPrimeFactors {
    public static int getPrimeFactorsCount(int num, EratosthenesSieve sito) {
        int count = 0;
        for (int i = 2; i < num; i++)
            if (sito.isPrime(i) && (num % i == 0))
                count++;
        return count;
    }

    public static void main(String[] args) {
        int STROP = 1000000;
        EratosthenesSieve sieve = new EratosthenesSieve(STROP);
        // testy:

        int[] vstupy = { 14, 15, 644, 645, 646, 998, 134043, 134044, 134045, 134046};
        for (int cislo : vstupy)
            System.out.println(cislo + " má " + getPrimeFactorsCount(cislo, sieve) + " prvociselnych delitelu.");


        int consecutive = 0;
        for (int i = 210; i < STROP; i++) {
            if (sieve.isPrime(i)) {
                consecutive = 0;
                continue;
            }
            if (getPrimeFactorsCount(i, sieve) == 4) {
                consecutive++;
                if (consecutive == 4) {
                    System.out.println("Last is " + i + ", thus result should be " + (i - 3));
                    break;
                }
            } else {
                consecutive = 0;
            }
        }
    }
}
