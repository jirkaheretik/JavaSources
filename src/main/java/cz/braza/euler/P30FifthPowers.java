package cz.braza.euler;

/**
 * sum of all the numbers that are equal to sum of fifth power of their digits
 */
public class P30FifthPowers {
    public static int digitsToPower(int num, int power) {
        int sum = 0;
        while (num > 0) {
            sum += Math.pow(num % 10, power);
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        /*
        // test digitsToPower:
        System.out.println(digitsToPower(1634, 4));
        System.out.println(digitsToPower(9474, 4));
         */

        int sum = 0;
        for (int i = 10; i < 10000000; i++)
            if (i == digitsToPower(i, 5)) {
                sum += i;
                System.out.println(i);
            }
        System.out.println("Total sum: " + sum);
    }
}
