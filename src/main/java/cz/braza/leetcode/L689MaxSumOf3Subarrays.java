package cz.braza.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/*
689. Maximum Sum of 3 Non-Overlapping Subarrays
Hard
Topics
Companies
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.



Example 1:

Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]


Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i] < 2^16
1 <= k <= floor(nums.length / 3)
 */
public class L689MaxSumOf3Subarrays {
    /*
    dp+dfs from editorial, sliding sum to precompute subsum array
    Runs 10ms, beats 21.8%
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // precompute the sub-sum array (k-sum starting at a given index)
        int n = nums.length - k + 1;
        int[] subsums = new int[n];
        int subsum = 0;
        for (int i = 0; i < k; i++)
            subsum += nums[i];
        subsums[0] = subsum;
        for (int i = k; i < nums.length; i++) {
            subsum += nums[i] - nums[i - k];
            subsums[i - k + 1] = subsum;
        }
        // now dfs, for every index we can either use it (and gets its sum from subsums and move to index +k) or leave it.
        // once we have 3, we return value
        // memo[i][j]: max sum possible starting from index i with j subarrays remaining
        int[][] memo = new int[n][4];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // First find optimal sum using DP
        dp(subsums, k, 0, 3, memo);

        // Then reconstruct the path to find indices
        int[] result = new int[3];
        /* seems not to work well, use the other dfs()
        return dfs(subsums, k, 0, 3, memo, result);
         */
        List<Integer> indices = new ArrayList<>();
        // Then reconstruct the path to find indices
        dfs(subsums, k, 0, 3, memo, indices);
        for (int i = 0; i < 3; i++) {
            result[i] = indices.get(i);
        }
        return result;
    }

    // DP function to find maximum possible sum
    private int dp(int[] sums, int k, int idx, int rem, int[][] memo) {
        if (rem == 0) return 0;
        if (idx >= sums.length) return rem > 0 ? Integer.MIN_VALUE : 0;
        if (memo[idx][rem] != -1) return memo[idx][rem];
        // Try taking current subarray vs skipping it
        memo[idx][rem] = Math.max(sums[idx] + dp(sums, k, idx + k, rem - 1, memo), dp(sums, k, idx + 1, rem, memo));
        return memo[idx][rem];
    }

    // DFS to reconstruct the solution path
    private int[] dfs(int[] sums, int k, int idx, int rem, int[][] memo, int[] indices) {
        if (rem == 0) return indices;
        if (idx >= sums.length) return indices;

        int withCurrent = sums[idx] + dp(sums, k, idx + k, rem - 1, memo);
        int skipCurrent = dp(sums, k, idx + 1, rem, memo);

        // Choose path that gave optimal result in DP
        if (withCurrent >= skipCurrent) { // Take current subarray
            indices[3 - rem] = idx;
            return dfs(sums, k, idx + k, rem - 1, memo, indices);
        } else // Skip current subarray
            return dfs(sums, k, idx + 1, rem, memo, indices);
    }

    // DFS to reconstruct the solution path
    private void dfs(
            int[] sums,
            int k,
            int idx,
            int rem,
            int[][] memo,
            List<Integer> indices
    ) {
        if (rem == 0) return;
        if (idx >= sums.length) return;

        int withCurrent = sums[idx] + dp(sums, k, idx + k, rem - 1, memo);
        int skipCurrent = dp(sums, k, idx + 1, rem, memo);

        // Choose path that gave optimal result in DP
        if (withCurrent >= skipCurrent) { // Take current subarray
            indices.add(idx);
            dfs(sums, k, idx + k, rem - 1, memo, indices);
        } else { // Skip current subarray
            dfs(sums, k, idx + 1, rem, memo, indices);
        }
    }
}
