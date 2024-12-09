package cz.braza.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
2577. Minimum Time to Visit a Cell In a Grid
Hard
Topics
Companies
Hint
You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.



Example 1:



Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.
Example 2:



Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
Output: -1
Explanation: There is no path from the top left to the bottom-right cell.


Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
0 <= grid[i][j] <= 105
grid[0][0] == 0 */
public class L2577MinimumTimeToVisitACell {
    private final int[][] directions = {{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },};

    /*
    Using process from L2290, this time we cannot use Dequeue, but PriorityQueue
    + if the difference between our time and cell minimal time is EVEN, we need to add +1
    + we can mark cells as visited by setting grid value to -1, we do not need to revisit the cell again
    Runs 243ms, beats 11%
     */

    public int minimumTime(int[][] grid) {
        int h = grid.length, w = grid[0].length;

        // failfast:
        if (grid[1][0] > 1 && grid[0][1] > 1) return -1; // cannot go anywhere

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] { 0, 0, 0 }); // {time, row, col}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0], row = current[1], col = current[2];
            //System.out.printf("Picked [%d][%d] at time %d%n", row, col, time);
            if (grid[row][col] < 0) continue; // don't do there again
            if (row == h - 1 && col == w - 1) return time; // we get there, this is the result

            // Explore all four possible directions from the current cell
            for (int[] dir : directions) {
                int r = row + dir[0], c = col + dir[1];
                if (r >= 0 && c >= 0 && r < h && c < w && grid[r][c] >= 0) {
                    int gridVal = grid[r][c];
                    int newTime = time + 1 >= gridVal ? time + 1 : gridVal + (((gridVal - time) % 2 == 0) ? 1 : 0);
                    pq.offer(new int[] { newTime, r, c});
                }
            }
            grid[row][col] = -1; // mark as visited
        }

        return -7; // should not get here at all
    }
}
