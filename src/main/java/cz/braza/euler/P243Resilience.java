package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P243Resilience {
    private static long nsd(long a, long b) {
        while (b != 0) { long r = a % b; a = b; b = r; }
        return a;
    }

    private static double showResilience(long num) {
        long resilient = 1;
        for (long i = 2; i < num; i++) {
            if (nsd(i, num) == 1)
                resilient++;
        }
        double resilience = (double)resilient / (num - 1);
        System.out.println(resilient + "/" + (num - 1) + " == " + resilience);
        return resilience;
    }

    public static void main(String[] args) {
        double limit = (double)15499 / 94744; // 0.1535881955585578;
        System.out.println("Real limit is " + limit);
        // limit *= 0.99; // seek a couple of iterations more...
        int theClosest = 223092870;
        int[] numbers = {theClosest, 2 * theClosest, 3 * theClosest, 4 * theClosest, 5 * theClosest, 6 * theClosest, 8 * theClosest, 9 * theClosest, 10 * theClosest, 12 * theClosest, 14 * theClosest, 15 * theClosest, 16 * theClosest, 18 * theClosest, 20 * theClosest, 21 * theClosest, 22 * theClosest, 24 * theClosest, 25 * theClosest, 26 * theClosest, 27 * theClosest, 28 * theClosest};
        for (int num : numbers)
            if (showResilience(num) < limit)
                System.out.println("WOW! Found it?!");;

        long primes = 1;
        EratosthenesSieve sito = new EratosthenesSieve(100000);
        double res = 1;
        while ((res = showResilience(primes)) > limit) {
            int prime = sito.nextPrime();
            primes *= prime;
            System.out.println("Last used prime: " + prime + " and new number is " + primes + ":");
        }
    }
}
