package cz.braza.euler;

/**
 * Triangle number: 1, 3, 6, 10, 15, 21, 28, ...
 * A(n) = n + A(n-1)
 *
 * Find the first triangle number with more than 500 divisors, that is 499+ in <2;n)
 */
public class P12TriangleDIvisors {
    public static int noOfDivisors(long num) {
        int count = 2; // 1, num
        for (long i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0)
                count += 2;
        return count;
    }

    public static void main(String[] args) {
        long an = 28;
        long n = 7;
        long max = 6;
        while (max < 501) {
            an += ++n;
            int divisors = noOfDivisors(an);
            if (divisors > max) {
                max = divisors;
                if (max > 400)
                    System.out.println(n + ". number An=" + an + " has " + divisors + " divisors.");
            }
        }
    }
}
