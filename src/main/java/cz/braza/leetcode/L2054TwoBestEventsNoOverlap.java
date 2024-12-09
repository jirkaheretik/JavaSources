package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
2054. Two Best Non-Overlapping Events
Medium
Topics
Companies
Hint
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.



Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.


Constraints:

2 <= events.length <= 10^5
events[i].length == 3
1 <= startTimei <= endTimei <= 10^9
1 <= valuei <= 10^6
 */

record Pair<TFirst, TSecond>(TFirst first, TSecond second) {}

public class L2054TwoBestEventsNoOverlap {
    /*
    Editorial implementation using pq
    Runs 55ms, beats 51.3%
     */
    public int maxTwoEvents(int[][] events) {
        // Create a PriorityQueue to store the pair ending time and value.
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Pair::first)
        );

        //Sort the array in ascending order (start time)
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        int maxVal = 0, maxSum = 0;

        for (int[] event : events) {
            // Poll all valid events from queue and take their maximum.
            while (!pq.isEmpty() && pq.peek().first() < event[0]) {
                maxVal = Math.max(maxVal, pq.peek().second());
                pq.poll();
            }

            maxSum = Math.max(maxSum, maxVal + event[2]);
            pq.add(new Pair<>(event[1], event[2]));
        }

        return maxSum;
    }

}
