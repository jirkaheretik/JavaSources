package cz.braza.leetcode;

/*
300. Longest Increasing Subsequence
Medium
Topics
Companies
Given an integer array nums, return the length of the longest strictly increasing
subsequence
.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
// NOTE: Done for Daily (L1671)
public class L300LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        return lengthOfLISPart(nums, 0, nums.length - 1);
    }

    public static int lengthOfLISPart(int[] nums, int minIdx, int maxIdx) {
        int arrLen = maxIdx - minIdx + 1;
        int[] dp = new int[arrLen];
        int len;
        int max = 0;
        for(int i = 0; i < arrLen; i++) {
            len = 0;
            for(int j = 0; j < i; j++)
                if(nums[i + minIdx] > nums[j + minIdx])
                    if(dp[j] > len)
                        len = dp[j];
            dp[i] = len + 1;
            if(dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    // similarily, Lenght of Decreasing Sequence
    public static int lengthOfLDS(int[] nums) {
        return lengthOfLDSPart(nums, 0, nums.length - 1);
    }

    public static int lengthOfLDSPart(int[] nums, int minIdx, int maxIdx) {
        int arrLen = maxIdx - minIdx + 1;
        int[] dp = new int[arrLen];
        int len;
        int max = 0;
        for(int i = 0; i < arrLen; i++) {
            len = 0;
            for(int j = 0; j < i; j++)
                if(nums[i + minIdx] < nums[j + minIdx])
                    if(dp[j] > len)
                        len = dp[j];
            dp[i] = len + 1;
            if(dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18}; // expected 4
        int[] cisl = {0,1,0,3,2,3}; // expected 4
        int[] sevens = {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}; // expected 1
        System.out.println(lengthOfLIS(nums) + " should be 4");
        System.out.println(lengthOfLIS(cisl) + " should be 4");
        System.out.println(lengthOfLIS(sevens) + " should be 1");
        System.out.println(lengthOfLISPart(nums, 1, 4) + " should be 2");
        System.out.println(lengthOfLISPart(cisl, 0, 3) + " should be 3");
        System.out.println(lengthOfLISPart(sevens, 3, 11) + " should be 1");
        System.out.println(lengthOfLDS(nums) + " should be 4");
        System.out.println(lengthOfLDS(cisl) + " should be 2");
        System.out.println(lengthOfLDS(sevens) + " should be 1");
        System.out.println(lengthOfLDSPart(nums, 1, 4) + " should be 3");
    }
}
