package cz.braza.leetcode;

import java.util.*;

/*
PROGRESS:
First I thought I need to find dominant element, so count frequencies everywhere, then
find the element itself in the map (both value and count).
Then traverse the array, counting occurences of the dominant element, and deciding if there is a valid split.
If there is no valid split, return -1.

Later I realized what takes time is traversing the map looking for the dominant element,
since TreeMap could not be used (logically, it is ordered by keys, not values, as ordering by values CHANGES in time.
But I can remember the maximum (dominant count and value) during filling up the frequency map.
Thats speeds up things.

Third way does rely on the insight that a valid split ALWAYS occurs when moving one dominant value from the right to the left
(or adding it to the left array) - if not, there would already be a valid split before anyway.
This leads to a conclusion we do not need to KNOW the actual dominant element or its frequency. Just stuff everything to the frequency map first,
and then move one element at a time to the left group, counting frequency there, and if frequency of the new element in dominant in left and the remainder as well, we have a winner.

Fourth - Boyer-Moore voting algorithm, from editorial
Does not need any extra space, any collections. We take first element, whenever we find another same value, increase count, otherwise DECREASE count.
If count is 0, there are more of other element, take the one we have.
Then we know the dominant element, but not its frequency, which we need to decide about valid split. Traverse nums again just to count dominant frequency.
Now the last step is the same again, traverse nums, noting the count, and deciding if it is a valid split or not.
Boyer-Moore main insight: after every pair of DIFFERENT elements is eliminated (disregarding order we take it), only dominant elements can remain.
 */

/*
Daily 27.3.2025 - https://leetcode.com/problems/minimum-index-of-a-valid-split/

2780. Minimum Index of a Valid Split
Medium
Topics
Companies
Hint
An element x of an integer array arr of length m is dominant if more than half the elements of arr have a value of x.

You are given a 0-indexed integer array nums of length n with one dominant element.

You can split nums at an index i into two arrays nums[0, ..., i] and nums[i + 1, ..., n - 1], but the split is only valid if:

0 <= i < n - 1
nums[0, ..., i], and nums[i + 1, ..., n - 1] have the same dominant element.
Here, nums[i, ..., j] denotes the subarray of nums starting at index i and ending at index j, both ends being inclusive. Particularly, if j < i then nums[i, ..., j] denotes an empty subarray.

Return the minimum index of a valid split. If no valid split exists, return -1.



Example 1:

Input: nums = [1,2,2,2]
Output: 2
Explanation: We can split the array at index 2 to obtain arrays [1,2,2] and [2].
In array [1,2,2], element 2 is dominant since it occurs twice in the array and 2 * 2 > 3.
In array [2], element 2 is dominant since it occurs once in the array and 1 * 2 > 1.
Both [1,2,2] and [2] have the same dominant element as nums, so this is a valid split.
It can be shown that index 2 is the minimum index of a valid split.
Example 2:

Input: nums = [2,1,3,1,1,1,7,1,2,1]
Output: 4
Explanation: We can split the array at index 4 to obtain arrays [2,1,3,1,1] and [1,7,1,2,1].
In array [2,1,3,1,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
In array [1,7,1,2,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
Both [2,1,3,1,1] and [1,7,1,2,1] have the same dominant element as nums, so this is a valid split.
It can be shown that index 4 is the minimum index of a valid split.
Example 3:

Input: nums = [3,3,3,3,7,2,2]
Output: -1
Explanation: It can be shown that there is no valid split.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
nums has exactly one dominant element.
 */
public class L2780MinIndexOfValidSplit {
    /*
    First we need to find the dominant element, so make a frequency scan of the array/list to find it.
    Then we process the list one more time from the start, and whenever we find a valid first part, count the rest as well, if that is a valid split, we have a winner.
    NOTE: While MINIMAL valid split will ALWAYS be after odd number of elements (even index), it may make the rest of the array invalid, so we need to consider even splits too.
    Runs 31ms, beats 57.1%, 917 testcases
     */
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num: nums) frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        int dominant = Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        int dominantCount = frequencyMap.get(dominant);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums.get(i) == dominant) count++;
            // did we find a valid split (both parts?)
            if (count * 2 > i + 1 && (dominantCount - count) * 2 > n - i + 1) return i;
        }
        return -1;
    }

    /*
    Small update - just note max during filling up frequencies, no need to scan hashmap for dominant element.
    Runs 27ms, beats 63.5%
     */
    public int minimumIndexNoCollectionsMax(List<Integer> nums) {
        int n = nums.size();
        int dominantCount = 0;
        int dominant = -1;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num: nums) {
            int val = frequencyMap.getOrDefault(num, 0) + 1;
            if (val > dominantCount) {
                dominantCount = val;
                dominant = num;
            }
            frequencyMap.put(num, val);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums.get(i) == dominant) count++;
            // did we find a valid split (both parts?)
            if (count * 2 > i + 1 && (dominantCount - count) * 2 > n - i + 1) return i;
        }
        return -1;
    }

    /*
    Traverse three times (1. to find dominant element, 2. to find count of dominant element, 3. to find a valid split)
    but needs NO EXTRA space (esp. no maps or other collections).

    Runs 11ms, beats 95.5%
     */
    public int minimumIndexBoyerMoore(List<Integer> nums) {
        // Find the majority element
        int x = nums.get(0), count = 0, xCount = 0, n = nums.size();

        for (int num : nums) {
            if (num == x) count++;
            else count--;
            if (count == 0) {
                x = num;
                count = 1;
            }
        }

        // Count frequency of majority element
        for (int num : nums) if (num == x) xCount++;

        // Check if valid split is possible
        count = 0;
        for (int index = 0; index < n; index++) {
            if (nums.get(index) == x) count++;
            // Check if both left and right partitions satisfy the condition
            if (count * 2 > index + 1 && (xCount - count) * 2 > n - index - 1) return index;
        }

        return -1;
    }
}
