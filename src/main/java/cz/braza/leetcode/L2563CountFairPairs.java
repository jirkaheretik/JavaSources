package cz.braza.leetcode;

import java.util.Arrays;

/*
2563. Count the Number of Fair Pairs
Medium
Topics
Companies
Hint
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper


Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).


Constraints:

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */
public class L2563CountFairPairs {
    // naive approach, count all the possibilities, O(n^2)
    public static long countFairPairsNaive(int[] nums, int lower, int upper) {
        long result = 0;
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] >= lower && nums[i] + nums[j] <= upper) {
                    result++;
                    System.out.printf("Idx %d and idx %d give a total %d%n", i, j, nums[i] + nums[j]);
                }
        return result;
    }

    /*
    Now we sort the array beforehand, then use binarySearch to find lower and upper limit for each
    Runs 47ms, beats 57.3%
     */
    public static long countFairPairsSorted(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            int lowerBound = Arrays.binarySearch(nums, lower - nums[i]);
            if (lowerBound < 0) lowerBound = -lowerBound - 1;
            // get greedy now and check numbers before (since we are not guaranteed we got the first X from the array):
            while (lowerBound > i && nums[i] + nums[lowerBound - 1] >= lower)
                lowerBound--;
            if (lowerBound <= i) lowerBound = i + 1; // i < j must hold
            int upperBound = Arrays.binarySearch(nums, upper - nums[i]);
            if (upperBound < 0) upperBound = -upperBound - 2;
            // get greedy again:
            while (upperBound < nums.length - 1 && nums[i] + nums[upperBound + 1] <= upper)
                upperBound++;
            if (upperBound >= lowerBound) result += upperBound - lowerBound + 1;
            System.out.printf("For index %d and value %d we get lower bound %d and upper bound %d, total result now %d%n", i, nums[i], lowerBound, upperBound, result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] t = {0,1,7,4,4,5};
        System.out.println(countFairPairsSorted(t, 3, 6));
    }
}
