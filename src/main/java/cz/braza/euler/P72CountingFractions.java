package cz.braza.euler;

import cz.braza.EratosthenesSieve;

public class P72CountingFractions {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int dummy = b;
            b = a % b;
            a = dummy;
        }
        return a;
    }
    public static boolean isReduced(int a, int b) { return gcd(a, b) == 1; }
    public static void main(String[] args) {
        EratosthenesSieve sito = new EratosthenesSieve(1000001);
        final int LIMIT = 1_000_000;
        final int DODOT = 10000;
        final int BIGDOT = 100_000;
        long count = 0;
        for (int n = 2; n <= LIMIT; n++) {
            if (sito.isPrime(n))
                count += n - 1;
            else
                for (int i = 1; i < n; i++)
                    if (isReduced(i, n))
                        count++;
            if (n % BIGDOT == 0)
                System.out.println("X\n" + java.time.Instant.now());
            else if (n % DODOT == 0)
                System.out.print(".");
        }
        System.out.println("\nThere is a total of " + count + " reduced proper fractions with D <= " + LIMIT);
    }
    // Answer 303966327601 (run for 16h) incorrect
    // Answer 303963552391 (17,5h)
}
