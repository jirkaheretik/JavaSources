package cz.braza.leetcode;

/*
3264. Final Array State After K Multiplication Operations I
Easy
Topics
Companies
Hint
You are given an integer array nums, an integer k, and an integer multiplier.

You need to perform k operations on nums. In each operation:

Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
Replace the selected minimum value x with x * multiplier.
Return an integer array denoting the final state of nums after performing all k operations.
 */
public class L3264ArrayAfterMultiplications {
    /*
    Easy brute force approach to not use any surplus dynamic structures or sorting.
    Just do k steps, in each we find the first index with lowest value, and after running through whole array,
    we multiply value at the index with multiplier.
    Return the array, voila.
    Runs 1ms, beats 100%
     */
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int LENGTH = nums.length;
        for (int step = 0; step < k; step++) {
            // find minimum index
            int lowest = 0;
            for (int idx = 1; idx < LENGTH; idx++)
                if (nums[idx] < nums[lowest])
                    lowest = idx;
            // multiply the value
            nums[lowest] *= multiplier;
        }
        return nums;
    }
}
