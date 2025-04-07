package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Daily 6.4.2025 - https://leetcode.com/problems/largest-divisible-subset/
368. Largest Divisible Subset
Medium
Topics
Companies
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 10^9
All the integers in nums are unique.
 */
public class L368LargestDivisibleSubset {
    /*
    From Solutions (Editorial locked), Sorting + O(n^2) + creating result in O(n)
    Dp array, but not really dp
    Runs 14ms, beats 66.5%, 49 testcases (33% runs in 13ms)
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        int maxi = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            if (dp[i] > dp[maxi]) maxi = i;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = maxi; i >= 0; i = prev[i]) {
            res.add(nums[i]);
            if (prev[i] == -1) break;
        }
        return res;
    }

    /*
    Update: previously we run O(n^2), for each index i we ALWAYS check ALL indices 0-i to find
    best dp[j] value. But instead we can run downwards from i-1 to 0, and we can stop
    if we know there cannot be any better solution.
    Runs 13ms, beats 99.6%
     */
    public List<Integer> largestDivisibleSubsetUpdate(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        int[] maxToI = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        int maxi = 0;
        for (int i = 1; i < nums.length; i++) {
            // TODO: binarysearch for highest j such that nums[j] <= nums[i]/2
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
                if (dp[i] > maxToI[j]) break; // we cannot find any better solution here
            }
            if (dp[i] > dp[maxi]) maxi = i;
            maxToI[i] = dp[maxi];
        }
        List<Integer> res = new ArrayList<>();
        for (int i = maxi; i >= 0; i = prev[i]) {
            res.add(nums[i]);
            if (prev[i] == -1) break;
        }
        return res;
    }
}
