package cz.braza.euler;

import java.util.ArrayList;

/**
 * Proper divisors of N: all integers <N that do divide N
 * d(N) - sum of proper divisors of N
 *
 * Amicable numbers: a, b, such that d(a) = b, and d(b) = a, and a != b
 *
 * Task: sum of all amicable numbers under 10000
 */

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
public class P21AmicableNumbers {


    public static void main(String[] args) {
        final int MAX = 10000;
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
        // sanity check:
        System.out.println("D(220)=" + cisla[220].dejSoucet() + ", D(284)=" + cisla[284].dejSoucet());
        int totalSum = 0;
        for (int i = 1; i < MAX; i++) {
            int d = cisla[i].dejSoucet();
            if (d > i && d < MAX && cisla[d].dejSoucet() == i)
                totalSum += d + i;
        }
        System.out.println("Total sum of amicable numbers: " + totalSum);
    }
}
