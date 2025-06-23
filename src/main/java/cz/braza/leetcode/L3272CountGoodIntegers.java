package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Daily 12.4. - https://leetcode.com/problems/find-the-count-of-good-integers/
3272. Find the Count of Good Integers
Hard
Topics
Companies
Hint
You are given two positive integers n and k.

An integer x is called k-palindromic if:

x is a palindrome.
x is divisible by k.
An integer is called good if its digits can be rearranged to form a k-palindromic integer. For example, for k = 2, 2020 can be rearranged to form the k-palindromic integer 2002, whereas 1010 cannot be rearranged to form a k-palindromic integer.

Return the count of good integers containing n digits.

Note that any integer must not have leading zeros, neither before nor after rearrangement. For example, 1010 cannot be rearranged to form 101.



Example 1:

Input: n = 3, k = 5

Output: 27

Explanation:

Some of the good integers are:

551 because it can be rearranged to form 515.
525 because it is already k-palindromic.
Example 2:

Input: n = 1, k = 4

Output: 2

Explanation:

The two good integers are 4 and 8.

Example 3:

Input: n = 5, k = 6

Output: 2468



Constraints:

1 <= n <= 10
1 <= k <= 9
 */
public class L3272CountGoodIntegers {
    public static final long[][] RESULT = {{9, 4, 3, 2, 1, 1, 1, 1, 1},
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {}
    };
    public long countGoodIntegers(int n, int k) {
        return RESULT[n - 1][k - 1];
    }

    public static void main(String[] args) {
        // how to find out good integers:
        for (int n = 2; n <= 10; n++) {
            Set<Long>[] cisla = new HashSet[9];
            int half = n / 2;
            boolean isOdd = n % 2 == 1;
            int base = (int) Math.pow(10, half - 1);
            for (int left = base; left < base * 10; left++) {
                // get a reverse:
                int myLeft = left;
                int right = 0;
                while (myLeft > 0) {
                    right *= 10;
                    right += myLeft % 10;
                    myLeft /= 10;
                }
                long total = (long)left * (isOdd ? 10 : 1) * base + right;
                System.out.printf("Left %d, right %d, half %d, total: %d%n", left, right, half, total);
                int times = isOdd ? 10 : 1;
                while (times > 0) {
                    for (int k = 1; k < 10; k++) {
                        if (total % k == 0) cisla[k - 1].add(minimize(total));
                    }
                    times--;
                    total += (long)10 * base;
                }
            }
            for (int k = 1; k <= 9; k++) {

            }

        }
    }

    public static long minimize(long num) {
        return 0; //TODO!
    }

    private static boolean isPalindrome(long num) {
        return false;
    }

    /*
    From Editorial, working with strings.
    Runs 380ms, beats 64%, 90 testcases
     */
    public long countGoodIntegersEditorial(int n, int k) {
        Set<String> dict = new HashSet<>();
        int base = (int) Math.pow(10, (n - 1) / 2);
        int skip = n & 1;
        /* Enumerate the number of palindrome numbers of n digits */
        for (int i = base; i < base * 10; i++) {
            String s = Integer.toString(i);
            s += new StringBuilder(s).reverse().substring(skip);
            long palindromicInteger = Long.parseLong(s);
            /* If the current palindrome number is a k-palindromic integer */
            if (palindromicInteger % k == 0) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                dict.add(new String(chars));
            }
        }

        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++)
            factorial[i] = factorial[i - 1] * i;
        long ans = 0;
        for (String s: dict) {
            int[] cnt = new int[10];
            for (char c : s.toCharArray()) cnt[c - '0']++;
            /* Calculate permutations and combinations */
            long tot = (n - cnt[0]) * factorial[n - 1];
            for (int x : cnt) tot /= factorial[x];
            ans += tot;
        }

        return ans;
    }
}
