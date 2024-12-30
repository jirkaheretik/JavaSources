package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
2940. Find Building Where Alice and Bob Can Meet
Hard
Topics
Companies
Hint
You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.

If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].

You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.

Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.



Example 1:

Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
Output: [2,5,-1,5,2]
Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
In the fifth query, Alice and Bob are already in the same building.
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
Example 2:

Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
Output: [7,6,-1,4,6]
Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.



Constraints:

1 <= heights.length <= 5 * 10^4
1 <= heights[i] <= 10^9
1 <= queries.length <= 5 * 10^4
queries[i] = [ai, bi]
0 <= ai, bi <= heights.length - 1
 */
public class L2940BuildingForAliceAndBob {
    /*
    Linear approach, expecting TLE
    Got TLE on testcase 946/952 or so :-P
     */
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] answers = new int[queries.length];
        Arrays.fill(answers, -1);
        for (int idx = 0; idx < queries.length; idx++) {
            int idx1 = queries[idx][0];
            int idx2 = queries[idx][1];
            if (idx1 == idx2) { // already in the same building
                answers[idx] = idx1;
                continue;
            }
            if (idx2 < idx1) {
                idx1 = queries[idx][1];
                idx2 = queries[idx][0];
            }
            int alice = heights[idx1];
            int bob = heights[idx2];
            if (alice < bob) { // one can move to the other
                answers[idx] = idx2;
                continue;
            }
            for (int i = idx2; i < heights.length; i++)
                if (heights[i] > bob) {
                    answers[idx] = i;
                    break;
                }
        }
        return answers;
    }

    /*
    Updated method from editorial, using int[] instead of Pair<Integer, Integer>
    Runs 41ms, beats 76.9%
     */
    public static int[] leftmostBuildingQueriesEditorial(int[] heights, int[][] queries) {
        List<int[]> monoStack = new ArrayList<>();
        int[] result = new int[queries.length];
        Arrays.fill(result, -1);
        List<List<int[]>> newQueries = new ArrayList<>(heights.length);

        for (int i = 0; i < heights.length; i++) {
            newQueries.add(new ArrayList<>());
        }

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (heights[b] > heights[a] || a == b)
                result[i] = b;
            else
                newQueries.get(b).add(new int[]{heights[a], i});
        }

        for (int i = heights.length - 1; i >= 0; i--) {
            int monoStackSize = monoStack.size();
            for (int[] pair : newQueries.get(i)) {
                int position = search(pair[0], monoStack);
                if (position < monoStackSize && position >= 0)
                    result[pair[1]] = monoStack.get(position)[1];
            }

            while (!monoStack.isEmpty() && monoStack.get(monoStack.size() - 1)[0] <= heights[i])
                monoStack.remove(monoStack.size() - 1);

            monoStack.add(new int[]{heights[i], i});
        }
        return result;
    }

    private static int search(int height, List<int[]> monoStack) {
        int left = 0;
        int right = monoStack.size() - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (monoStack.get(mid)[0] > height) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
