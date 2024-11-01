package cz.braza.leetcode;

/*
1671. Minimum Number of Removals to Make Mountain Array
Hard
Topics
Companies
Hint
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.



Example 1:

Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.
Example 2:

Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].


Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
 */
public class L1671RemovalsForMountainArray {
    /*
    First plan: use L300 (longest increasing subsequence), add longest decreasing subsequence to it. DONE.
    iterate through array, whenever we find a local maximum, we compute LIS(front)+LDS(back) and store if higher
    In the end, arr.length-ourMax is the minimal number of removals
     */
    public int minimumMountainRemovals(int[] nums) {
        int maxSum = 0;

        for (int index = 1; index < nums.length - 1; index++)
            // do we have a local (non-strict) maximum?
            if (nums[index - 1] <= nums[index] && nums[index] >= nums[index + 1]) {
                // count left and right, and use only if both are at least 2 (otherwise not a mountain)
                int left = L300LongestIncreasingSubsequence.lengthOfLISPart(nums, 0, index);
                int right = L300LongestIncreasingSubsequence.lengthOfLDSPart(nums, index, nums.length - 1);
                int sum = left > 1 && right > 1 ? left + right : 0;
                if (sum > maxSum) maxSum = sum;
            }
        return nums.length - maxSum + 1;
    }

}
