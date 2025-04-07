package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 7.4.2025 - https://leetcode.com/problems/partition-equal-subset-sum/
416. Partition Equal Subset Sum
Medium
Topics
Companies
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
public class L416PartitionEqualSubsetSum {
    /*
    Classic brute force, leads to TLE of course
     */
    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] prefix = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++)
            prefix[i] = sum += nums[i];
        if (prefix[n - 1] % 2 == 1) return false; // cannot partition odd sum arrays
        return canPartitionPart(nums, prefix, prefix[n - 1] / 2, n - 1);
    }

    private static boolean canPartitionPart(int[] nums, int[] prefix, int lookingFor, int maxIndex) {
        if (maxIndex < 0 || prefix[maxIndex] < lookingFor) return false;
        if (nums[maxIndex] == lookingFor) return true;
        if (nums[maxIndex] > lookingFor) return canPartitionPart(nums, prefix, lookingFor, maxIndex - 1);
        return canPartitionPart(nums, prefix, lookingFor, maxIndex - 1) || canPartitionPart(nums, prefix, lookingFor - nums[maxIndex], maxIndex - 1);
    }

    public static void main(String[] args) {
        int[] t1 = {1,5,11,5};
        int[] t2 = {1,2,3,5};
        System.out.println(canPartition(t1));
        System.out.println(canPartition(t2));
    }

    /*
    DP - boolean array up to targetSum, process each number and set reachable sums to true
    Runs 17ms, beats 95%, 144 testcases

    NOTE that using OPTION2 is slower: 21ms, beats 91%
    */
    public boolean canPartitionDP(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if (totalSum % 2 != 0) return false;
        int targetSum = totalSum / 2;
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        for (int num : nums)
            for (int currSum = targetSum; currSum >= num; currSum--) {
                // OPTION1:
                if (!dp[currSum] && dp[currSum - num]) dp[currSum] = true;
                // OPTION2:
                //dp[currSum] = dp[currSum] || dp[currSum - num];
                if (dp[targetSum]) return true;
            }
        return dp[targetSum];
    }
}
