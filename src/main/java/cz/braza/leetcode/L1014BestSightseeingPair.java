package cz.braza.leetcode;

/*
1014. Best Sightseeing Pair
Medium
Topics
Companies
Hint
You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.



Example 1:

Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
Example 2:

Input: values = [1,2]
Output: 2


Constraints:

2 <= values.length <= 5 * 10^4
1 <= values[i] <= 1000
 */
public class L1014BestSightseeingPair {
    /*
    Key insight is that a good sightseeing spot can be either
    left (and counts as value+index) or right (and counts as value-index)
    Therefore we can compute it in just one left to right pass with no extra data (O(1)).
    We remember best left spot up to date, and try current value as right
    spot, remembering best result in the process, and update left max if needed.
    Runs 2ms, beats 100%
     */
    public int maxScoreSightseeingPair(int[] values) {
        int maxLeft = values[0];
        int maxScore = 0;
        for (int index = 1; index < values.length; index++) {
            int val = values[index];
            // have better max score?
            if (maxLeft + val - index > maxScore) maxScore = maxLeft + val - index;
            // update max left
            if (val + index > maxLeft) maxLeft = val + index;
        }
        return maxScore;
    }
}
