package cz.braza.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
1962. Remove Stones to Minimize the Total
Medium
Topics
Companies
Hint
You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:

Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
Notice that you can apply the operation on the same pile more than once.

Return the minimum possible total number of stones remaining after applying the k operations.

floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).



Example 1:

Input: piles = [5,4,9], k = 2
Output: 12
Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [5,4,5].
- Apply the operation on pile 0. The resulting piles are [3,4,5].
The total number of stones in [3,4,5] is 12.
Example 2:

Input: piles = [4,3,6,7], k = 3
Output: 12
Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [4,3,3,7].
- Apply the operation on pile 3. The resulting piles are [4,3,3,4].
- Apply the operation on pile 0. The resulting piles are [2,3,3,4].
The total number of stones in [2,3,3,4] is 12.


Constraints:

1 <= piles.length <= 10^5
1 <= piles[i] <= 10^4
1 <= k <= 10^5
 */
public class L1962MinTotalStonesAfterRemovals {
    /*
    Slightly updated process from L2208 - we work with ints all the way:
    - first create maxheap (priorityqueue) and fill it with pile numbers, count the sum in the process
    - apply k operations where we pick an item, halve it, update sum and get it back
    - return rest of the sum afterwards

    Runs 419ms, beats 74.5%, 60 testcases
     */
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;
        for (int pile: piles) {
            sum += pile;
            pq.offer(pile);
        }
        int steps = 0;
        while (steps < k) {
            steps++;
            int pile = pq.poll();
            sum -= pile / 2;
            pq.offer(pile - pile / 2);
        }
        return sum;
    }
}
