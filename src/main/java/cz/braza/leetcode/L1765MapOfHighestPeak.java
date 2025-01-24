package cz.braza.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Daily 22.1.2025

1765. Map of Highest Peak
Medium
Topics
Companies
Hint
You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

If isWater[i][j] == 0, cell (i, j) is a land cell.
If isWater[i][j] == 1, cell (i, j) is a water cell.
You must assign each cell a height in a way that follows these rules:

The height of each cell must be non-negative.
If the cell is a water cell, its height must be 0.
Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
Find an assignment of heights such that the maximum height in the matrix is maximized.

Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.



Example 1:



Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.
Example 2:



Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.


Constraints:

m == isWater.length
n == isWater[i].length
1 <= m, n <= 1000
isWater[i][j] is 0 or 1.
There is at least one water cell.
 */
public class L1765MapOfHighestPeak {
    public static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /*
    Implementation using Queue, starting with all the water cells, then going "up"
    boolean 2D array visited to mark already visited cells.

    Runs 70ms, beats 12.2%, 59 testcases (majority uses 45-55ms)
     */
    public int[][] highestPeak(int[][] isWater) {
        int R = isWater.length;
        int C = isWater[0].length;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        // find water cells and add them to the queue:
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                if (isWater[r][c] == 1)
                    queue.add(new int[]{r, c, 0});
        // process queue:
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int height = cell[2];
            if (visited[row][col]) continue;
            visited[row][col] = true;
            isWater[row][col] = height;
            for (int[] dir: DIR) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];
                if (nRow >= 0 && nCol >= 0 && nRow < R && nCol < C && !visited[nRow][nCol])
                    queue.add(new int[]{nRow, nCol, height + 1});
            }
        }
        return isWater;
    }

    /*
    Just tried to use two-pass queue processing (that is note how many items for a level we have
    and process them in for cycle, thus storing only [r,c] coordinates and not height.

    Same or similar time, though: 72ms, beats 11.4%
     */
    public int[][] highestPeakTwoPass(int[][] isWater) {
        int R = isWater.length;
        int C = isWater[0].length;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        // find water cells and add them to the queue:
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                if (isWater[r][c] == 1)
                    queue.add(new int[]{r, c});
        // process queue:
        int height = 0;
        while (!queue.isEmpty()) {
            int layerCount = queue.size();
            for (int i = 0; i < layerCount; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                if (visited[row][col]) continue;
                visited[row][col] = true;
                isWater[row][col] = height;
                for (int[] dir : DIR) {
                    int nRow = row + dir[0];
                    int nCol = col + dir[1];
                    if (nRow >= 0 && nCol >= 0 && nRow < R && nCol < C && !visited[nRow][nCol])
                        queue.add(new int[]{nRow, nCol});
                }
            }
            height++; // increase height for next round
        }
        return isWater;
    }
}
