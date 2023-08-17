package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P35CircularPrimes {
    public static void main(String[] args) {
        int STROP = 1000000;
        EratosthenesSieve sito = new EratosthenesSieve(STROP);
        int countCirculars = 0;
        while (sito.hasNext()) {
            int prime = sito.nextPrime();
            // now show if it is circular:
            if (prime < 10) {
                System.out.println(prime);
                countCirculars++;
            } else {
                String primeStr = String.valueOf(prime);
                boolean isCircular = true;
                for (int i = 0; i < primeStr.length() - 1; i++) {
                    if (!sito.isPrime(
                            Integer.parseInt(primeStr.substring(i + 1) + primeStr.substring(0, i + 1))

                    )) {
                        isCircular = false;
                        break;
                    }

                }
                if (isCircular) {
                    System.out.println(primeStr);
                    countCirculars++;
                }
            }
        }
        System.out.println("Number of circular primes up to " + STROP + " is " + countCirculars);
    }
}
