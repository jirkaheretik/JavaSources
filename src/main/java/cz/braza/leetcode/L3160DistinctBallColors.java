package cz.braza.leetcode;

import java.util.HashMap;

/*
Daily 7.2.2025

3160. Find the Number of Distinct Colors Among the Balls
Medium
Topics
Companies
Hint
You are given an integer limit and a 2D array queries of size n x 2.

There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored. For every query in queries that is of the form [x, y], you mark ball x with the color y. After each query, you need to find the number of distinct colors among the balls.

Return an array result of length n, where result[i] denotes the number of distinct colors after ith query.

Note that when answering a query, lack of a color will not be considered as a color.



Example 1:

Input: limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]

Output: [1,2,2,3]

Explanation:



After query 0, ball 1 has color 4.
After query 1, ball 1 has color 4, and ball 2 has color 5.
After query 2, ball 1 has color 3, and ball 2 has color 5.
After query 3, ball 1 has color 3, ball 2 has color 5, and ball 3 has color 4.
Example 2:

Input: limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]

Output: [1,2,2,3,4]

Explanation:



After query 0, ball 0 has color 1.
After query 1, ball 0 has color 1, and ball 1 has color 2.
After query 2, ball 0 has color 1, and balls 1 and 2 have color 2.
After query 3, ball 0 has color 1, balls 1 and 2 have color 2, and ball 3 has color 4.
After query 4, ball 0 has color 1, balls 1 and 2 have color 2, ball 3 has color 4, and ball 4 has color 5.


Constraints:

1 <= limit <= 10^9
1 <= n == queries.length <= 10^5
queries[i].length == 2
0 <= queries[i][0] <= limit
1 <= queries[i][1] <= 10^9
 */
public class L3160DistinctBallColors {
    /*
    Trying to avoid hashmaps, but apparently 1G array is too much anyway, but the logic is ok
     */
    public int[] queryResultsStatic(int limit, int[][] queries) {
        // we need to know color of every ball (initially 0):
        int[] ballColors = new int[limit + 1];
        // we need to know frequency of every color (up to 10^9 colors) - can be a hashmap
        int[] colorFreqs = new int[1000000001];
        // and current color count (for queries, initially 0):
        int colorCount = 0;
        // initialize result array:
        int[] result = new int[queries.length];
        // process queries, update ball color, color count, set result item
        for (int idx = 0; idx < queries.length; idx++) {
            int[] q = queries[idx];
            // q[0] is ball id, q[1] is ball color
            if (ballColors[q[0]] > 0) {
                colorFreqs[ballColors[q[0]]]--;
                if (colorFreqs[ballColors[q[0]]] == 0) colorCount--;
            }
            ballColors[q[0]] = q[1];
            colorFreqs[q[1]]++;
            if (colorFreqs[q[1]] == 1) colorCount++;
            result[idx] = colorCount;
        }
        return result;
    }

    /*
    Same as above, except hashmaps for both "large arrays".
    Runs 46ms, beats 31%, 551 testcases
     */
    public int[] queryResultsHashmaps(int limit, int[][] queries) {
        // we need to know color of every ball (initially 0):
        HashMap<Integer, Integer> ballColors = new HashMap<>();
        // we need to know frequency of every color (up to 10^9 colors) - can be a hashmap
        HashMap<Integer, Integer> colorFreqs = new HashMap<>();
        // and current color count (for queries, initially 0):
        int colorCount = 0;
        // initialize result array:
        int[] result = new int[queries.length];
        // process queries, update ball color, color count, set result item
        for (int idx = 0; idx < queries.length; idx++) {
            int[] q = queries[idx];
            // q[0] is ball id, q[1] is ball color
            int ballColor = ballColors.getOrDefault(q[0], 0);
            if (ballColor > 0) {
                colorFreqs.put(ballColor, colorFreqs.get(ballColor) - 1);
                if (colorFreqs.get(ballColor) == 0) colorCount--;
            }
            ballColors.put(q[0], q[1]);
            colorFreqs.put(q[1], colorFreqs.getOrDefault(q[1], 0) + 1);
            if (colorFreqs.get(q[1]) == 1) colorCount++;
            result[idx] = colorCount;
        }
        return result;
    }
}
