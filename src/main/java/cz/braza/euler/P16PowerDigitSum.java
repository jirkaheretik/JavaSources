package cz.braza.euler;

import java.math.BigInteger;

public class P16PowerDigitSum {
    /**
     * Python: sum(map(int, str(2**1000)))
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
    public static void main(String[] args) {
        BigInteger velkeCislo = BigInteger.TWO.pow(1000);
        String velkyRetezec = velkeCislo.toString();
        System.out.println("Počet míst: " + velkyRetezec.length());
        System.out.println(velkeCislo);
        System.out.println(findSum(velkyRetezec));
    }
}
