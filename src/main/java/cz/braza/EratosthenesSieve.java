package cz.braza;

/**
 * Object oriented Eratosthenes net - gets a number as a parameter in a constructor,
 * runs the algorithm, then can return results
 */
public class EratosthenesSieve {
    private int STROP = 2000000;
    private boolean[] SITO;

    private int lastIndex = 0;

    public EratosthenesSieve(int strop) {
        STROP = strop;
        SITO = new boolean[STROP + 1];
        prosevej();
    }

    private void prosevej(int prvocislo) {
        int nasobek = 2;
        while (prvocislo * nasobek <= STROP)
            SITO[prvocislo * nasobek++] = true;
    }

    private void prosevej() {
        SITO[0] = true;
        SITO[1] = true;
        // dvojka:
        prosevej(2);
        for (int i = 3; i <= Math.sqrt(STROP); i += 2) {
            if (!SITO[i])
                prosevej(i);
        }
    }

    /**
     * number of primes up to the limit (STROP)
     */
    public int countPrimes() {
        int count = 0;
        for (int i = 0; i <= STROP; i++)
            if (!SITO[i]) count++;
        return count;
    }

    public void setLowerBound(int bottom) {
        if (bottom >= 0 && bottom <= STROP) {
            lastIndex = bottom;
            while (SITO[lastIndex])
                lastIndex++;
        }
    }

    public boolean isPrime(int value) {
        return !SITO[value];
    }

    public int nextPrime() {
        while (SITO[lastIndex])
            lastIndex++;
        return lastIndex++;
    }

    public boolean hasNext() {
        for (int i = lastIndex; i <= STROP; i++) {
            if (!SITO[i])
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int limit = 20000000;
        System.out.println("Spoustim Eratosthenovo sito...");
        EratosthenesSieve sito = new EratosthenesSieve(limit);
        System.out.println("Sito dokonceno, muzeme testovat.");
        System.out.println("Existuje " + sito.countPrimes() + " prvocisel do " + limit);
        System.out.println("First five primes:");
        for (int i = 0; i < 5; i++)
            System.out.println(sito.nextPrime());
        System.out.println("Setting lower bound to 8888...");
        sito.setLowerBound(8888);
        System.out.println("First ten primes from here:");
        for (int i = 0; i < 10; i++)
            System.out.println(sito.nextPrime());

    }
}
