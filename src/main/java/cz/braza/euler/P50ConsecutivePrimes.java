package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P50ConsecutivePrimes {
    public static final int MAX = 1000000;
    public static void main(String[] args) {
        EratosthenesSieve sito = new EratosthenesSieve(MAX);
        // naplnit pole prvocisel:
        int pocetCisel = sito.countPrimes();
        int[] prvocisla = new int[pocetCisel];
        for (int index = 0; index < pocetCisel; index++)
            prvocisla[index] = sito.nextPrime();
        System.out.println("Inicializace provedena.");
        // prochazet:
        int totalCount = 0;
        for (int i = 0; i < prvocisla.length; i++) {
            int index = i;
            int sum = prvocisla[index];
            int count = 1;
            while (index < prvocisla.length - 1) {
                sum += prvocisla[++index];
                count++;
                if (sum > MAX)
                    break;
                if (sito.isPrime(sum)) {
                    if (count > totalCount) {
                        System.out.println("Found a string of " + count + " primes starting from " + i + ", sum " + sum);
                        totalCount = count;
                    }
                }
            }
        }
    }
}
