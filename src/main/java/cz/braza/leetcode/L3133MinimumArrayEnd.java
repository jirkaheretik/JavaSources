package cz.braza.leetcode;

import java.util.Arrays;

/*
3133. Minimum Array End
Medium
Topics
Companies
Hint
You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.

Return the minimum possible value of nums[n - 1].



Example 1:

Input: n = 3, x = 4

Output: 6

Explanation:

nums can be [4,5,6] and its last element is 6.

Example 2:

Input: n = 2, x = 7

Output: 15

Explanation:

nums can be [7,15] and its last element is 15.

Constraints:

1 <= n, x <= 10^8
 */
public class L3133MinimumArrayEnd {
    public static final int MAXBIT = 45;

    /*
    Find bits in x and (n - 1) (array of T/F)
    Now we need to "plant"/merge the 0/1s from n-1 into free (0) bits of x, then return it
    Runs 1ms, beats 87.7%
     */
    public static long minEnd(int n, int x) {
        boolean[] bitsX = getBits(x);
        boolean[] bitsN = getBits(n - 1);
        long result = x;
        int nIndex = 0;
        int xIndex = 0;
        while (xIndex < bitsX.length) {
            if (bitsX[xIndex++]) continue;
            // advance n index and update x if 1-bit found:
            result += bitsN[nIndex++] ? (1L << (xIndex - 1)) : 0;
        }
        return result;
    }

    public static boolean[] getBits(int n) {
        // get bit length:
        boolean[] res = new boolean[MAXBIT];
        for (int index = 0; index < MAXBIT; index++) {
            int val = 1 << index;
            if (val > n) break;
            else if ((n & val) > 0)
                res[index] = true;
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(minEnd(3, 4));
        //System.out.println(minEnd(2, 7));
        System.out.println(minEnd(88484395, 7198715));
    }
}
