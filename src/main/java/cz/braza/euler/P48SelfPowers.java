package cz.braza.euler;

import java.math.BigInteger;

public class P48SelfPowers {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.ONE;
        for (int i = 2; i <= 1000; i++) {
            sum = sum.add(BigInteger.valueOf(i).pow(i));
        }
        String bigNum = sum.toString();
        System.out.println("Done. Length of the number: " + bigNum.length());
        System.out.println(bigNum);
        System.out.println("Last 10 digits: " + bigNum.substring(bigNum.length()-10));
    }
}
