package cz.braza.leetcode;

/*
50. Pow(x, n)
Medium
Topics
Companies
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
Either x is not zero or n > 0.
-104 <= xn <= 104
 */
public class L50PowXN {
    /*
    Simple approach, no cycles and ifs, works for normal range values :-)
     */
    public static double myPow(double x, int n) {
        return n == 0 ? 1 : n > 0 ? x * myPow(x, n - 1) : myPow(1 / x, -n);
    }

    /*
    Rewritten to a cycle instead of recursion, still cannot catch all the edge cases though
     */
    public static double myPowWhile(double x, int n) {
        if (n < 0) return myPowWhile(1 / x, -n);
        if (n == 0) return 1;
        double result = x;
        while (n-- > 1)
            result *= x;
        return result;
    }

    /*
    Just a test function for the encountered behavior - we do -1*n and get the same value back
    (for Integer.MIN_VALUE)
     */
    public static void fckTest(int n) {
        System.out.println("Got N: " + n);
        if (n < 0) fckTest(-n);
        System.out.println("Got out.");
    }

    /*
    From solution/editorial, basically we do more powers at once, thus O(log n) instead of O(n)
     */
    public static double myPowSolution(double x, int n) {
        if(n < 0){
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while(n != 0){
            if((n & 1) != 0){
                pow *= x;
            }

            x *= x;
            n >>>= 1;

        }

        return pow;
    }

    public static void main(String[] args) {
        //System.out.println(myPowWhile(1.1, 2147483647));
        fckTest(Integer.MIN_VALUE);
    }
}
