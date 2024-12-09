package cz.braza.leetcode;

/*
862. Shortest Subarray with Sum at Least K
Hard
Topics
Companies
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3


Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
 */
public class L862ShortestSubarrayWithSumKplus {
    /* First try
    Add to the sum until we have at least K, note the length, then try to shorten it from the beginning

     */
    public static int shortestSubarray(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int currentLength = 0;
        for (int index = 0; index < nums.length; index++) {
            currentSum += nums[index];
            currentLength++;
            while (currentSum >= k && currentLength > 0) {
                if (currentLength < minLength)
                    minLength = currentLength;
                System.out.printf("DBG idx: %d, current length: %d, current sum: %d, min: %d %n", index, currentLength, currentSum, minLength);
                currentSum -= nums[index - currentLength + 1];
                currentLength--;
                // this approach does not work correctly because of possible negative numbers,
                // we cannot find optimal solution
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static int shortestSubarray2(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int currentLength = 0;
        for (int index = 0; index < nums.length; index++) {
            currentSum += nums[index];
            currentLength++;
            if (currentSum >= k) {
                if (currentLength < minLength)
                    minLength = currentLength;
                // start from here and find shortest length
                int smallSum = nums[index];
                int smallLength = 1;
                while (smallLength < minLength && smallSum < k)
                    smallSum += nums[index - smallLength++];
                if (smallLength < minLength) {
                    minLength = smallLength;
                    currentSum = smallSum;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        int[] t = {-28,81,-20,28,-29};
        System.out.println(shortestSubarray2(t, 89));
    }

}
