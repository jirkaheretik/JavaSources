package cz.braza.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Daily 28.3.2025 - https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/

2503. Maximum Number of Points From Grid Queries
Hard
Topics
Companies
Hint
You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.



Example 1:


Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.
Example 2:


Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.


Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
k == queries.length
1 <= k <= 10^4
1 <= grid[i][j], queries[i] <= 10^6
 */
public class L2503MaxPointsFromGridQueries {
    /*
    Basically for any query value we need to find how many tiles we can visit if we start
    at the top left and cannot visit any cell with higher value than the query.
    In order not to do repetitions too much, we instead sort queries (together with its index to rebuild result array)
    and process them in increasing order - doing BFS in the grid, while values are lower than current query value.

    Runs 99ms, beats 55%, 21 testcases
     */
    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    public int[] maxPoints(int[][] grid, int[] queries) {
        int rowCount = grid.length, colCount = grid[0].length;
        int[][] qInc = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) qInc[i] = new int[]{queries[i], i};
        Arrays.sort(qInc, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[rowCount][colCount];
        // Keeps track of the number of cells processed
        int totalPoints = 0;
        // Start from the top-left cell
        minHeap.add(new int[] { grid[0][0], 0, 0 });
        visited[0][0] = true;

        // Process queries in sorted order
        for (int[] query : qInc) {
            int queryValue = query[0], queryIndex = query[1];
            // Expand the cells that are smaller than the current query value
            while (!minHeap.isEmpty() && minHeap.peek()[0] < queryValue) {
                int[] top = minHeap.poll();
                int currentRow = top[1], currentCol = top[2];
                // Increment count of valid cells
                totalPoints++;

                // Explore all four possible directions
                for (int[] dir : DIRECTIONS) {
                    int newRow = currentRow + dir[0], newCol = currentCol + dir[1];

                    // Check if the new cell is within bounds and not visited
                    if (newRow >= 0 && newCol >= 0 && newRow < rowCount && newCol < colCount && !visited[newRow][newCol]) {
                        minHeap.add(new int[] { grid[newRow][newCol], newRow, newCol });
                        // Mark as visited
                        visited[newRow][newCol] = true;
                    }
                }
            }
            // Store the result for this query
            queries[queryIndex] = totalPoints;
        }

        return queries;
    }
}
