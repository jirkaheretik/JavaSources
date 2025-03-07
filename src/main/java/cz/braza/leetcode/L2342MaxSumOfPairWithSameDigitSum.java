package cz.braza.leetcode;

/*
Daily 12.2.2025

2342. Max Sum of a Pair With Equal Sum of Digits
Medium
Topics
Companies
Hint
You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].

Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.



Example 1:

Input: nums = [18,43,36,13,7]
Output: 54
Explanation: The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
Example 2:

Input: nums = [10,12,19,14]
Output: -1
Explanation: There are no two numbers that satisfy the conditions, so we return -1.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */
public class L2342MaxSumOfPairWithSameDigitSum {
    /*
    We create a 2D array for storing best two values for any digit sum
    (since nums are up to 10^9, no digit sum can be higher than 81)
    Process nums, count sum of digits, update pairs and maxSum.
    Return -1 if we don't find a pair at all, otherwise best pair sum.

    Runs 20ms, beats 92.4%, 83 testcases
     */
    public int maximumSum(int[] nums) {
        int maxSum = -1;
        int[][] bestPairs = new int[82][2]; // first index - sum of digits, then first and second value
        for (int num: nums) {
            // count sum of digits:
            int dummy = num;
            int sumOfDigits = 0;
            while (dummy > 0) {
                sumOfDigits += dummy % 10;
                dummy /= 10;
            }
            // update bestPairs, if possible:
            if (num > bestPairs[sumOfDigits][0]) {
                bestPairs[sumOfDigits][1] = bestPairs[sumOfDigits][0];
                bestPairs[sumOfDigits][0] = num;
            } else if (num > bestPairs[sumOfDigits][1])
                bestPairs[sumOfDigits][1] = num;
            // update best value, if we have a pair:
            if (bestPairs[sumOfDigits][1] > 0 && bestPairs[sumOfDigits][1] + bestPairs[sumOfDigits][0] > maxSum)
                maxSum = bestPairs[sumOfDigits][1] + bestPairs[sumOfDigits][0];
        }
        return maxSum;
    }
}
