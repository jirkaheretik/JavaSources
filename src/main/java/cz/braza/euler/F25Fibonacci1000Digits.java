package cz.braza.euler;

import java.math.BigInteger;

public class F25Fibonacci1000Digits {
    public static void main(String[] args) {
        int index = 12;
        BigInteger f1 = BigInteger.valueOf(89);
        BigInteger f2 = BigInteger.valueOf(144);
        while (true) {
            // next number:
            index++;
            if (index < 0) System.out.println("Overflow!!!");
            BigInteger tmp = f1.add(f2);
            f1 = f2;
            f2 = tmp;
            if (f2.toString().length() >= 1000) {
                System.out.println("Found a Fibonacci number with 1000+ digits:\n" + f2);
                System.out.println("Its index is " + index);
                break;
            }
        }

    }
}
