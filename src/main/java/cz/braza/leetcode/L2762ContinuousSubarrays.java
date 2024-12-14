package cz.braza.leetcode;

import java.util.PriorityQueue;

/*
2762. Continuous Subarrays
Medium
Topics
Companies
Hint
You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:

Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
Return the total number of continuous subarrays.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [5,4,2,4]
Output: 8
Explanation:
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.


Example 2:

Input: nums = [1,2,3]
Output: 6
Explanation:
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */
public class L2762ContinuousSubarrays {
    /*
    From editorial, its AoC time currently :-/
    Runs 60ms, beats 26.3%, many solutions at rougly 20-25ms
     */
    public long continuousSubarrays(int[] nums) {
        int left = 0, right = 0;
        long count = 0; // Total count of valid subarrays

        // Min and max heaps storing indices, sorted by nums[index] values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);

        while (right < nums.length) {
            // Add current index to both heaps
            minHeap.add(right);
            maxHeap.add(right);

            // While window violates |nums[i] - nums[j]| ≤ 2 condition
            // Shrink window from left and remove outdated indices
            while (left < right && nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
                left++;

                // Remove indices that are now outside window
                while (!maxHeap.isEmpty() && maxHeap.peek() < left)
                    maxHeap.poll();
                while (!minHeap.isEmpty() && minHeap.peek() < left)
                    minHeap.poll();
            }

            // Add count of all valid subarrays ending at right
            count += right - left + 1;
            right++;
        }
        return count;
    }
}
