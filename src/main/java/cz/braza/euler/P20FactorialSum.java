package cz.braza.euler;

import java.math.BigInteger;

public class P20FactorialSum {
    /**
     * Python: sum(map(int, str(fact(100))))
     */

    static int findSum(String str)
    {
        int sum = 0;
        char[] znaky = str.toCharArray();
        for (char c: znaky) {
            int ascii = (int) c;
            if ((ascii >= 48) && (ascii <= 57))
                sum += ascii - 48;
        }
        return sum;
    }

    static BigInteger fact(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static void main(String[] args) {
        BigInteger f100 = fact(100);
        String velkyRetezec = f100.toString();
        System.out.println(f100);
        System.out.println("Počet míst: " + velkyRetezec.length());
        System.out.println(findSum(velkyRetezec));
    }
}
