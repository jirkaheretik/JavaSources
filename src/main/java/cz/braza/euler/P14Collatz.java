package cz.braza.euler;

/**
 * Collatz Sequence:
 * n -> n/2 for even n,
 * n -> 3n+1 for odd n
 * example: 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1.
 *
 * Problem: Which starting number < 1 000 000 produces the longest chain?
 */
public class P14Collatz {
    public static long next(long num) {
        return (num % 2 == 0) ? (num / 2) : (3 * num + 1);
    }
    public static void main(String[] args) {
        final int MAX = 1000000;
        int[] chainLength = new int[MAX];
        chainLength[0] = 0;
        chainLength[1] = 1;
        chainLength[2] = 2;
        // chainLength[3] = 8;
        int maximum = 0;
        for (int cislo = 3; cislo < MAX; cislo++) {
            int kroku = 1;
            long dalsi = cislo;
            while ((dalsi = next(dalsi)) >= cislo)
                kroku++;
            chainLength[cislo] = chainLength[(int)dalsi] + kroku;
            if (chainLength[cislo] > chainLength[maximum])
                maximum = cislo;

        }
        System.out.println("" + 3 + ", delka: " + chainLength[3]);
        System.out.println("" + 13 + ", delka: " + chainLength[13]);
        System.out.println("Nejdelsi retez: " + chainLength[maximum] + " for number " + maximum);
    }
}
