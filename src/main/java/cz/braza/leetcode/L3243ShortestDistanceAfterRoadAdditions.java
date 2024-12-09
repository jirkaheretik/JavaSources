package cz.braza.leetcode;

import java.util.*;

/*
3243. Shortest Distance After Road Addition Queries I
Medium
Topics
Companies
Hint
You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.



Example 1:

Input: n = 5, queries = [[2,4],[0,2],[0,4]]

Output: [3,2,1]

Explanation:



After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.



After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.



After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.

Example 2:

Input: n = 4, queries = [[0,3],[0,2]]

Output: [1,1]

Explanation:



After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.



After the addition of the road from 0 to 2, the length of the shortest path remains 1.



Constraints:

3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n  IMPORTANT!!!  We are never going back
1 < queries[i][1] - queries[i][0]
There are no repeated roads among the queries.
 */
public class L3243ShortestDistanceAfterRoadAdditions {
    /*
    From solution, BFS.
    Runs 188ms, beats 39.1%
     */
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            adj.get(i).add(i + 1);
        }

        int[] result = new int[queries.length];
        for (int qIdx = 0; qIdx < queries.length; qIdx++) {
            adj.get(queries[qIdx][0]).add(queries[qIdx][1]);
            result[qIdx] = shortestPath(adj, n);
        }
        return result;
    }

    public static int shortestPath(List<List<Integer>> adj, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // node, length
        Set<Integer> visit = new HashSet<>();
        visit.add(0);

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cur = curr[0];
            int length = curr[1];

            if (cur == n - 1) return length;

            for (int nei : adj.get(cur)) {
                if (!visit.contains(nei)) {
                    q.offer(new int[]{nei, length + 1});
                    visit.add(nei);
                }
            }
        }
        return -1; // never happens, but must be here for the compiler
    }
}
