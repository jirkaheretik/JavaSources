package cz.braza.euler;

/**
 * 145 = 1! + 4! + 5! = 1 + 24 + 120
 * Find sum of all numbers such that SUM of factorials of their digits is the number
 * Must be a sum (2+ digit numbers), so 1!==1 and 2!==2 not included
 */

public class P34DigitFactorials {

    public static long[] FACTS = new long[10];
    public static int fact(int n) {
        if (n < 2) return 1;
        return n * fact(n - 1);
    }

    public static void main(String[] args) {
        // fill in FACTS:
        FACTS[0] = 1;
        FACTS[1] = 1;
        for (int i = 2; i < 10; i++)
            FACTS[i] = i * FACTS[i - 1];
        System.out.println(FACTS[9]);
        // now the calculation:
        int sum = 0;
        for (int i = 13; i < 99999999; i++) {
            int localSum = 0;
            int value = i;
            while (value > 0) {
                localSum += FACTS[value % 10];
                value /= 10;
            }
            if (i == localSum) {
                sum += i;
                System.out.println("Found a curious number " + i);
            }
        }
        System.out.println("Sum of all curious numbers up to the given limit is " + sum);
    }
}
