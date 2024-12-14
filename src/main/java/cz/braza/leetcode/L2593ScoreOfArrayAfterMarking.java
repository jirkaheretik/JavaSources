package cz.braza.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
2593. Find Score of an Array After Marking All Elements
Medium
Topics
Companies
Hint
You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.



Example 1:

Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.
Example 2:

Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
 */
public class L2593ScoreOfArrayAfterMarking {
    /*
    Easy approach now - create and fill priority queue with numbers and its indices,
    then polling, and marking neighbouring elements
    Runs 212ms, beats 27%
     */
    public long findScore(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Set<Integer> marked = new HashSet<>();
        for (int idx = 0; idx < nums.length; idx++)
            pq.offer(new int[]{nums[idx], idx});
        long sum = 0;
        while (!pq.isEmpty()) {
            int[] rec = pq.poll();
            System.out.printf("Picked {%d, %d}, marked: %b%n", rec[0], rec[1], marked.contains(rec[1]));
            if (marked.contains(rec[1])) continue; // skip this element
            sum += rec[0];
            marked.add(rec[1] + 1);
            marked.add(rec[1] - 1);
        }
        return sum;
    }

    /*
    Same as before, just do boolean[] marked instead of Set<Integer> marked
    Runs 178ms, beats 50.5%
     */
    public long findScoreArr(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        boolean[] marked = new boolean[nums.length]; // all are false, thus unmarked
        for (int idx = 0; idx < nums.length; idx++)
            pq.offer(new int[]{nums[idx], idx});
        long sum = 0;
        while (!pq.isEmpty()) {
            int[] rec = pq.poll();
            if (marked[rec[1]]) continue; // skip this element
            sum += rec[0];
            if (rec[1] + 1 < marked.length) marked[rec[1] + 1] = true;
            if (rec[1] > 0) marked[rec[1] - 1] = true;
        }
        return sum;
    }

}
