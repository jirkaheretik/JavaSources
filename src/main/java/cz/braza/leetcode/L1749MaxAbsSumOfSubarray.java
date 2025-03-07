package cz.braza.leetcode;

/*
Daily 26.2.2025

1749. Maximum Absolute Sum of Any Subarray
Medium
Topics
Companies
Hint
You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).

Return the maximum absolute sum of any (possibly empty) subarray of nums.

Note that abs(x) is defined as follows:

If x is a negative integer, then abs(x) = -x.
If x is a non-negative integer, then abs(x) = x.


Example 1:

Input: nums = [1,-3,2,3,-4]
Output: 5
Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
Example 2:

Input: nums = [2,-5,1,-4,3,-2]
Output: 8
Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.


Constraints:

1 <= nums.length <= 10^5
-104 <= nums[i] <= 10^4
 */
public class L1749MaxAbsSumOfSubarray {
    /*
    We use Kadane's algorithm to find max and min subarray sums,
    then we return max(maxSum, -minSum) as the result.
    Runs 3ms, beats 97.9%, 66 testcases
     */
    public int maxAbsoluteSum(int[] nums) {
        if (nums.length == 0) return 0;
        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE, currentMaxSum = 0, currentMinSum = 0;
        for (int num : nums) {
            currentMaxSum = currentMaxSum + num > num ? currentMaxSum + num : num;
            if (maxSum < currentMaxSum) maxSum = currentMaxSum;
            currentMinSum = currentMinSum + num < num ? currentMinSum + num : num;
            if (minSum > currentMinSum) minSum = currentMinSum;
        }
        return maxSum > -minSum ? maxSum : -minSum;
    }
}
