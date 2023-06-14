package cz.braza.euler;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Proper divisors of N: all integers <N that do divide N
 * d(N) - sum of proper divisors of N
 *
 * Perfect number: N = d(N)
 * Deficient number: N > d(n)
 * Abundant number: N < d(N)
 *
 * Task: sum of all positive integers that CANNOT be written as the sum of two abundant numbers
 */

/* //*** (already in class P21AmicableNumbers)
class Cislo {
    private ArrayList<Integer> delitele = new ArrayList<>();
    private int cislo;

    private int soucet = 0;

    public Cislo(int num) {
        cislo = num;
        delitele.add(1);
    }

    public int sectiDelitele() {
        int sum = 0;
        for (int delitel: delitele)
            sum += delitel;
        return sum;
    }

    public int dejSoucet() {
        if (soucet == 0)
            soucet = sectiDelitele();
        return soucet;
    }

    public void pridej(int i) {
        delitele.add(i);
    }
}

 */
public class P23NonAbundantSums {


    public static void main(String[] args) {
        final int MAX = 30000;
        Cislo[] cisla = new Cislo[MAX];
        // inicializace:
        for (int i = 1; i < MAX; i++) {
            cisla[i] = new Cislo(i);
        }
        // naplneni delitelu:
        for (int d = 2; d < MAX; d++) { // delitel
            for (int i = 2; i * d < MAX; i++) // jeho nasobky
                cisla[i * d].pridej(d);
        }
        ArrayList<Integer> abundant = new ArrayList<>();
        for (int i = 10; i < MAX; i++) {
            if (i < cisla[i].sectiDelitele()) {
                // System.out.println(i);
                abundant.add(i);
            }
        }
        Integer[] abundArr = new Integer[0];
        abundArr = abundant.toArray(abundArr);
        boolean[] lzeTo = new boolean[MAX];
        // cycle through all the abundant number combination and circle those that we CAN get by summing two abundants.
        for (int prvni = 0; prvni < abundArr.length; prvni++)
            for (int druhe = prvni; druhe < abundArr.length; druhe++)
                if (abundArr[prvni] + abundArr[druhe] < MAX)
                    lzeTo[abundArr[prvni] + abundArr[druhe]] = true;
        int sum = 0;
        System.out.println("Čísla, která nejde zapsat:");
        for (int i = 1; i < MAX; i++)
            if (!lzeTo[i]) {
                sum += i;
                System.out.println(i);

            }
        System.out.println("Celková suma: " + sum);
    }
}
