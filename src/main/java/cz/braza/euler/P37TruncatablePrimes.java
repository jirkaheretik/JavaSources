package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P37TruncatablePrimes {
    public static void main(String[] args) {
        int STROP = 1000000;
        EratosthenesSieve sito = new EratosthenesSieve(STROP);
        int sumOfTruncatables = 0;
        int countOfTruncatables = 0;
        sito.setLowerBound(10);
        maincycle:
        while (sito.hasNext()) {
            int prime = sito.nextPrime();
            // now show if it is truncatable:
            String primeStr = String.valueOf(prime);
            // truncate from the left:
            for (int i = 1; i < primeStr.length(); i++) {
                if (!sito.isPrime(Integer.parseInt(primeStr.substring(i))))
                    continue maincycle;
                if (!sito.isPrime(Integer.parseInt(primeStr.substring(0, i))))
                    continue maincycle;
            }
            // found a gem?!
            System.out.println(prime);
            countOfTruncatables += 1;
            sumOfTruncatables += prime;

        }
        System.out.println("Sum of truncatable primes up to " + STROP + " is " + sumOfTruncatables + " and there are " + countOfTruncatables + " of them.");

    }
}
