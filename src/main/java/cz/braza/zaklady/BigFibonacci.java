package cz.braza.zaklady;

import java.math.BigInteger;

public class BigFibonacci {
    public static void main(String[] args) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            BigInteger c = b.add(a);
            a = b;
            b = c;
            System.out.println(i + ". " + c);
        }
        long end = System.currentTimeMillis();
        System.out.println("Program run " + (end - start) + "ms.");
    }
}
