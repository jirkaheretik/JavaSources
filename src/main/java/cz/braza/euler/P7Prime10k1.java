package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P7Prime10k1 {
    public static void main(String[] args) {
        EratosthenesSieve sito = new EratosthenesSieve(1000000);
        if (sito.countPrimes() <10002)
            System.out.println("Málo :-(");
        int cislo = 0;
        for (int i = 0; i < 10001; i++) {
            cislo = sito.nextPrime();
        }
        System.out.println("Prime no. 10001 is " + cislo);
    }
}
