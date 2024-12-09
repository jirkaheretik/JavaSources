package cz.braza.leetcode;

import java.util.Arrays;

/*
From Contest (Virtual):
3366. Minimum Array Sum
User Accepted:1990
User Tried:5419
Total Accepted:2131
Total Submissions:15102
Difficulty:Medium
You are given an integer array nums and three integers k, op1, and op2.

You can perform the following operations on nums:

Operation 1: Choose an index i and divide nums[i] by 2, rounding up to the nearest whole number. You can perform this operation at most op1 times, and not more than once per index.
Operation 2: Choose an index i and subtract k from nums[i], but only if nums[i] is greater than or equal to k. You can perform this operation at most op2 times, and not more than once per index.
Note: Both operations can be applied to the same index, but at most once each.

Return the minimum possible sum of all elements in nums after performing any number of operations.
 */
public class L3366MinimumArraySum {
    /* Intuition:
    We should halve the highest numbers (each just once)
    we can subtract op2*k if we have enough numbers of value at least k
    edge cases: for same index, we can apply op1 & op2, iff it is at least 2*k,
    we can apply op2 & op1, but will not save that much
    Idea:
    sort, from highest, do op1 until we reach values of 2*k.
    Then do op2 k times, if enough numbers
    Sort again, do the rest of op1, if any (possible breach of the requirement
    to use the same op just once on a single index)
     */
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        Arrays.sort(nums);
        int index = nums.length - 1;
        while (op1 > 0) {
            nums[index] = (nums[index] % 2) + nums[index] / 2;
            index--;
            op1--;
        }
        int sum = 0;
        for (int num: nums) {
            if (num >= k && op2 > 0) {
                num -= k;
                op2--;
            }
            sum += num;
        }
        return sum;
    }
}
