package cz.braza.leetcode;

import java.util.HashMap;

/*
Daily 16.4.2025 - https://leetcode.com/problems/count-the-number-of-good-subarrays/
2537. Count the Number of Good Subarrays
Solved
Medium
Topics
Companies
Hint
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i], k <= 10^9
 */
public class L2537CountGoodSubarrays {
    /*
    Two pointer and hashmap to store frequencies
    Runs 37ms, beats 90.7%, 41 testcases
     */
    public long countGood(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        long pairs = 0;
        long total = 0;
        int n = nums.length;
        int left = 0;
        int right = 0;
        while (right < n) {
            // move right (enlarge to get bigger array)
            // process nums[right]:
            int times = freq.getOrDefault(nums[right], 0);
            freq.put(nums[right++], times + 1);
            if (times > 0) pairs += times;
            // do we have enough?
            if (pairs >= k) {
                // all the subarrays up to the end are also valid:
                total += n - right + 1;
                // now remove from the left until we have less pairs than k:
                while (pairs >= k) {
                    int leftTimes = freq.get(nums[left]);
                    if (leftTimes == 1) freq.remove(nums[left]);
                    else {
                        leftTimes--;
                        pairs -= leftTimes;
                        freq.put(nums[left], leftTimes);
                    }
                    left++;
                    // if we still have enough pairs, count good subarrays starting from left:
                    if (pairs >= k) total += n - right + 1;
                }
            }
        }
        return total;
    }

    /*
    Simplify the cycle (do it for every left, enlarge right if needed), it is slower though:
    Runs 47ms, beats 76%
     */
    public long countGoodRearranged(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        long pairs = 0;
        long total = 0;
        int n = nums.length;
        int right = 0;
        for (int left = 0; left < n; left++) {
            // enlarge right if needed:
            while (right < n && pairs < k) {
                // process nums[right]:
                int times = freq.getOrDefault(nums[right], 0);
                freq.put(nums[right++], times + 1);
                if (times > 0) pairs += times;
            }
            if (pairs >= k)
                // all the subarrays up to the end are also valid:
                total += n - right + 1;
            int leftTimes = freq.get(nums[left]) - 1;
            pairs -= leftTimes;
            freq.put(nums[left], leftTimes);
        }
        return total;
    }
}
