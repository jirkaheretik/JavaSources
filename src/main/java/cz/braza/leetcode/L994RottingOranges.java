package cz.braza.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
994. Rotting Oranges
Medium
Topics
Companies
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
public class L994RottingOranges {
    /*
    First traverse whole grid and note:
    - all the rotten oranges, store them in a set/queue
    - all the fresh oranges, we need to know their COUNT, and for each
      of them we also may check all four directions. If these are all
      empty (of those that exist), we can return -1 (this one never rots)
    It does not help us though to properly decide on a group of (fresh)
    oranges.
    Now, we go through the set/queue of rotten oranges, find their adjacent
    fresh oranges and:
    - add them to the next step queue
    - mark them rotten
    - decrease the count of fresh oranges
    If the count is zero, return number of steps. If the queue is empty, return -1.
    Otherwise, do another step with the new queue.

    Runs 2ms, beats 72.25%
     */
    public int orangesRotting(int[][] grid) {
        int freshCount = 0;
        int steps = 0;
        int h = grid.length;
        int w = grid[0].length;
        Queue<Integer> rottens = new LinkedList<>();
        for (int row = 0; row < h; row++)
            for (int col = 0; col < w; col++) {
                if (grid[row][col] == 1) freshCount++;
                else if (grid[row][col] == 2)
                    rottens.add(row * 100 + col);
            }
        while (!rottens.isEmpty()) {
            if (freshCount == 0) break;
            Queue<Integer> nextStep = new LinkedList<>();
            steps++;
            for (int orange: rottens) {
                int co = orange % 100;
                int ro = orange / 100;
                if (rot(ro, co - 1, w, h, grid, nextStep)) freshCount--;
                if (rot(ro, co + 1, w, h, grid, nextStep)) freshCount--;
                if (rot(ro - 1, co, w, h, grid, nextStep)) freshCount--;
                if (rot(ro + 1, co, w, h, grid, nextStep)) freshCount--;
            }
            rottens = nextStep;
        }
        return freshCount > 0 ? -1 : steps;
    }

    public boolean rot(int r, int c, int w, int h, int[][] grid, Queue<Integer> list) {
        if (r < 0 || r >= h || c < 0 || c >= w) return false;
        if (grid[r][c] == 1) {
            grid[r][c] = 2;
            list.add(100 * r + c);
            return true;
        }
        return false;
    }

}
