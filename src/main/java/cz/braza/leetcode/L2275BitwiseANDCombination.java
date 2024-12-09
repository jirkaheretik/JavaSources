package cz.braza.leetcode;

import java.util.Arrays;

/*
2275. Largest Combination With Bitwise AND Greater Than Zero
Medium
Topics
Companies
Hint
The bitwise AND of an array nums is the bitwise AND of all integers in nums.

For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
Also, for nums = [7], the bitwise AND is 7.
You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers of candidates. Each number in candidates may only be used once in each combination.

Return the size of the largest combination of candidates with a bitwise AND greater than 0.



Example 1:

Input: candidates = [16,17,71,62,12,24,14]
Output: 4
Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62 & 24 = 16 > 0.
The size of the combination is 4.
It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
Note that more than one combination may have the largest size.
For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24 & 14 = 8 > 0.
Example 2:

Input: candidates = [8,8]
Output: 2
Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
The size of the combination is 2, so we return 2.


Constraints:

1 <= candidates.length <= 105
1 <= candidates[i] <= 107
 */
public class L2275BitwiseANDCombination {
    public static final int[] BITS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
            2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144,
            524288, 1048576, 2097152, 4194304, 8388608};
    public static final int SIZE = BITS.length;

    /*
    First attempt - go through the numbers,
    find all the individual bits set, and increase their bucket.
    Then we just go through the bucket array to find maximum.
    Runs 35ms, beats 15.9% (most solutions do 30-34ms)
     */
    public static int largestCombination(int[] candidates) {
        int[] counts = new int[SIZE];
        for (int num: candidates)
            for (int bit = 0; bit < SIZE; bit++)
                if ((num & BITS[bit]) > 0)
                    counts[bit]++;
        /*
        int result = 0;
        for (int i = 0; i < SIZE; i++)
            if (counts[i] > result)
                result = counts[i];
        return result;
         */
        return Arrays.stream(counts).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] t1 = {16,17,71,62,12,24,14};
        System.out.println(largestCombination(t1));
    }
}
