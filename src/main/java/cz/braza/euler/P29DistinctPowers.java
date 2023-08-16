package cz.braza.euler;

import java.math.BigInteger;
import java.util.TreeSet;

public class P29DistinctPowers {
    public static void main(String[] args) {
        TreeSet<BigInteger> hodnoty = new TreeSet<>();
        for (int a = 2; a <= 100; a++) {
            BigInteger bigA = BigInteger.valueOf(a);
            for (int b = 2; b <= 100; b++)
                hodnoty.add(bigA.pow(b));
        }
        System.out.println("There are " + hodnoty.size() + " distinct values.");
    }
}
