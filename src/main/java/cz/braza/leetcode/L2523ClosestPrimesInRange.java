package cz.braza.leetcode;

/*
Daily 7.3.2025 - https://leetcode.com/problems/closest-prime-numbers-in-range/

2523. Closest Prime Numbers in Range
Medium
Topics
Companies
Hint
Given two positive integers left and right, find the two integers num1 and num2 such that:

left <= num1 < num2 <= right .
Both num1 and num2 are prime numbers.
num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
Return the positive integer array ans = [num1, num2]. If there are multiple pairs satisfying these conditions, return the one with the smallest num1 value. If no such numbers exist, return [-1, -1].



Example 1:

Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.
Example 2:

Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.


Constraints:

1 <= left <= right <= 10^6
 */
public class L2523ClosestPrimesInRange {
    public int[] closestPrimesOptimized(int left, int right) {
        boolean[] ifPrimes = new boolean[right - left + 1];
        boolean[] smallPrimes = new boolean[(int)Math.ceil(Math.sqrt(right))];
        // small sieve is to get primes to check (up to square root of right), then check them in ifPrimes,
        // so we don't need to store million items array
        int N = ifPrimes.length;
        int n = smallPrimes.length;
        for (int num = 2; num < n; num++) {
            if (!smallPrimes[num]) {
                // check out its multiples in smallPrimes:
                int idx = 2 * num;
                while (idx < n) {
                    smallPrimes[idx] = true;
                    idx += num;
                }
                // check out its multiples in ifPrimes:

            }
        }

        return new int[]{-1, -1}; // TODO!!! (if needed, the other version kinda works)
    }

    /*
    Full Eratosthenes Sieve up to right (included).
    Then traverse the bool array to find closest two primes,
    returning early if we find two that are 1 or 2 apart (only 2, 3 can be two apart), then no closer tuple than 2.

    Runs 60ms, beats 85.2%, 66 testcases, 13% runs in 7ms.
     */
    public int[] closestPrimes(int left, int right) {
        boolean[] ifPrimes = new boolean[right + 1];
        int stop = (int)Math.floor(Math.sqrt(right));
        for (int prime = 2; prime <= stop; prime++)
            if (!ifPrimes[prime]) {
                int idx = 2 * prime;
                while (idx <= right) {
                    ifPrimes[idx] = true;
                    idx += prime;
                }
            }
        // now find the closest distance:
        int smaller = -1;
        int larger = -1;
        int distance = right;
        int checkLeft = left >= 2 ? left : 2;
        // find first prime:
        while (checkLeft <= right && ifPrimes[checkLeft]) checkLeft++;
        if (checkLeft > right) return new int[]{-1, -1};
        while (checkLeft <= right) {
            // find another prime (checkRight)
            int checkRight = checkLeft + 1;
            while (checkRight <= right && ifPrimes[checkRight]) checkRight++;
            // did we find it?
            if (checkRight <= right)
                if (checkRight - checkLeft <= 2)
                    return new int[]{checkLeft, checkRight};
                else if (checkRight - checkLeft < distance) {
                    smaller = checkLeft;
                    larger = checkRight;
                    distance = checkRight - checkLeft;
                }
            checkLeft = checkRight;
        }
        return new int[]{smaller, larger};
    }
}
