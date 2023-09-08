package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P46GoldbachOther {
    public static void main(String[] args) {
        int STROP = 10000000;
        EratosthenesSieve sito = new EratosthenesSieve(STROP);
        for (int i = 3; i < STROP; i += 2) {
            if (sito.isPrime(i)) continue; // only for composite numbers
            boolean found = false;
            for (int j = 1; 2 * j * j < i; j++)
                if (sito.isPrime(i - 2 * j * j)) {
                    found = true;
                    break;
                }
            if (!found)
                System.out.println("We found a result: " + i);
        }
    }
}
