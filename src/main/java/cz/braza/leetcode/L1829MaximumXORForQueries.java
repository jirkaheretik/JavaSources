package cz.braza.leetcode;

/*
1829. Maximum XOR for Each Query
Medium
Topics
Companies
Hint
You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform the following query n times:

Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k is maximized. k is the answer to the ith query.
Remove the last element from the current array nums.
Return an array answer, where answer[i] is the answer to the ith query.



Example 1:

Input: nums = [0,1,1,3], maximumBit = 2
Output: [0,3,2,3]
Explanation: The queries are answered as follows:
1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
Example 2:

Input: nums = [2,3,4,7], maximumBit = 3
Output: [5,2,6,5]
Explanation: The queries are answered as follows:
1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
4th query: nums = [2], k = 5 since 2 XOR 5 = 7.
Example 3:

Input: nums = [0,1,2,2,5,7], maximumBit = 3
Output: [4,3,6,4,6,7]


Constraints:

nums.length == n
1 <= n <= 105
1 <= maximumBit <= 20
0 <= nums[i] < 2maximumBit
nums is sorted in ascending order.
 */
public class L1829MaximumXORForQueries {
    public static final int[] BITS = {1, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023,
            2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143,
            524287, 1048575};
    /*
    First run: create one array for xor values, another for answers
    We start with filling the partial xors, then just go again and fill the answers
    Runs 3ms, beats 79%
     */
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] xors = new int[nums.length];
        int[] answers = new int[nums.length];
        // first count all the partial xors
        int maxValue = BITS[maximumBit];
        int tempValue = nums[0];
        xors[0] = nums[0];
        for (int index = 1; index < nums.length; index++) {
            tempValue ^= nums[index];
            xors[index] = tempValue;
        }
        // now fill in answers:
        for (int index = 0; index < nums.length; index++) {
            answers[index] = xors[xors.length - 1 - index] ^ maxValue;
        }
        return answers;
    }

    /*
    Second run: actually we do not need the xor values at all, we
    can as well fill in the answers, but from the end to back.
    One array, one go only.
    Though it is twice as fast, runs the same 3ms
     */
    public int[] getMaximumXorOptimized(int[] nums, int maximumBit) {
        int num = nums.length;
        int[] answers = new int[num];
        // first count all the partial xors
        int maxValue = BITS[maximumBit];
        int tempValue = nums[0];
        answers[num - 1] = tempValue ^ maxValue;
        for (int index = 1; index < num; index++) {
            tempValue ^= nums[index];
            answers[num - 1 - index] = tempValue ^ maxValue;
        }
        return answers;
    }

    /*
    Same as second, just ditch precomputed BITS and do a bit shifting calculation
    Runs 2ms, beats 100%
     */
    public int[] getMaximumXorNoArr(int[] nums, int maximumBit) {
        int num = nums.length;
        int[] answers = new int[num];
        int maxValue = (1 << maximumBit) - 1;
        int tempValue = 0;
        for (int index = 0; index < num; index++)
            answers[num - 1 - index] = (tempValue ^= nums[index]) ^ maxValue;
        return answers;
    }
}
