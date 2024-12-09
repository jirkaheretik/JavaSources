package cz.braza.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
2461. Maximum Sum of Distinct Subarrays With Length K
Medium
Topics
Companies
Hint
You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
Example 2:

Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.


Constraints:

1 <= k <= nums.length <= 105
1 <= nums[i] <= 105
 */
public class L2461MaximumSumOfDistinctSubarraysLengthK {
    /*
    Key observation/hint: in the sliding window, keep also track of frequencies(!) of all values
    So:
    create a map of value-frequency
    compute first k-sum

    runs 51ms, beats 41.6% (wide range from 30ms-70ms)
     */
    public static long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        Map<Integer, Integer> frequencies = new HashMap<>();
        long kSum = 0;
        int kLength = 0;
        int numDuplicates = 0;
        for (int index = 0; index < nums.length; index++) {
            // add a new element:
            int num = nums[index];
            kSum += num;
            kLength++;
            int former = frequencies.getOrDefault(num, 0);
            if (former == 1)
                numDuplicates++; // new duplicate number in our current window
            frequencies.put(num, former + 1);
            // remove element, if needed:
            if (kLength > k) {
                // remove element at `index-k`
                num = nums[index - k];
                kSum -= num;
                kLength--;
                former = frequencies.getOrDefault(num, 0);
                if (former == 2)
                    numDuplicates--; // we are removing one element, thus frequency is now 1 and no duplicate
                frequencies.put(num, former - 1);
            }
            // check our sum and conditions:
            if (kSum > maxSum && numDuplicates == 0 && kLength == k)
                maxSum = kSum;
        }
        return maxSum;
    }

    /*
    Try making the same algorithm with frequencies as an array instead of map
    WOW! Runs 8ms, beats 97.3%
     */
    public static long maximumSubarraySumArr(int[] nums, int k) {
        long maxSum = 0;
        //Map<Integer, Integer> frequencies = new HashMap<>();
        int[] frequencies = new int[100001];
        long kSum = 0;
        int kLength = 0;
        int numDuplicates = 0;
        for (int index = 0; index < nums.length; index++) {
            // add a new element:
            int num = nums[index];
            kSum += num;
            kLength++;
            int former = frequencies[num];
            if (former == 1)
                numDuplicates++; // new duplicate number in our current window
            frequencies[num] = former + 1;
            // remove element, if needed:
            if (kLength > k) {
                // remove element at `index-k`
                num = nums[index - k];
                kSum -= num;
                kLength--;
                former = frequencies[num];
                if (former == 2)
                    numDuplicates--; // we are removing one element, thus frequency is now 1 and no duplicate
                frequencies[num] = former - 1;
            }
            // check our sum and conditions:
            if (kSum > maxSum && numDuplicates == 0 && kLength == k)
                maxSum = kSum;
        }
        return maxSum;
    }

}
