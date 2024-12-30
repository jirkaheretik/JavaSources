package cz.braza.leetcode;

/*
2466. Count Ways To Build Good Strings
Medium
Topics
Companies
Hint
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.



Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation:
One possible valid good string is "011".
It can be constructed as follows: "" -> "0" -> "01" -> "011".
All binary strings from "000" to "111" are good strings in this example.
Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".


Constraints:

1 <= low <= high <= 10^5
1 <= zero, one <= low
 */
public class L2466CountWays2BuildString {
    public int countGoodStringsNaive(int low, int high, int zero, int one) {
        return (int) (countWays(0, 0, low, high, zero, one) % 1_000_000_007);
    }

    public static long countWays(long total, int currLength, int low, int high, int zero, int one) {
        if (currLength > high) return 0;
        long res = currLength >= low ? total : 0;
        res += countWays(total, currLength + zero, low, high, zero, one)
                + countWays(total, currLength + one, low, high, zero, one);
        return res;
    }

    /*
    Use dp - array stating how many possibilities for a given length (0-high)
    use modulo to keep values in a good range.
    Runs 7ms, beats 93.6%
     */
    public int countGoodStringsDp(int low, int high, int zero, int one) {
        long totalCount = 0;
        long[] dp = new long[1 + high];
        dp[0] = 1;
        for (int length = 1; length <= high; length++) {
            if (length - zero >= 0) dp[length] += dp[length - zero];
            if (length - one >= 0) dp[length] += dp[length - one];
            dp[length] %= 1_000_000_007;
            if (length >= low) totalCount += dp[length];
        }
        return (int) (totalCount % 1_000_000_007);
    }
}
