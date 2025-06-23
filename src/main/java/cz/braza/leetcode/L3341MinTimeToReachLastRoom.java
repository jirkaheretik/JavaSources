package cz.braza.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/*
Daily 7.5.2025 - https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/
3341. Find Minimum Time to Reach Last Room I
Solved
Medium
Topics
Companies
Hint
There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.



Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.
Example 2:

Input: moveTime = [[0,0,0],[0,0,0]]

Output: 3

Explanation:

The minimum time required is 3 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 3



Constraints:

2 <= n == moveTime.length <= 50
2 <= m == moveTime[i].length <= 50
0 <= moveTime[i][j] <= 10^9
 */

public class L3341MinTimeToReachLastRoom {
    /*
    Priority queue for passing through the "graph".
    Runs 6ms, beats 98.8%
     */
    public int minTimeToReach(int[][] moveTime) {
        int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int R = moveTime.length;
        int C = moveTime[0].length;
        boolean[][] visited = new boolean[R][C];
        int t = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        queue.add(new int[]{0, 0, 0}); // moveTime[0, 0] is to be ignored!!
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            t = curr[0];
            int r = curr[1];
            int c = curr[2];
            for (int[] dir : DIR) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int time = moveTime[nr][nc] + 1;
                    if (t + 1 > time) time = t + 1;
                    if (nr == R - 1 && nc == C - 1) return time;
                    queue.add(new int[]{time, nr, nc});
                }
            }
        }
        return -1;
    }
}
