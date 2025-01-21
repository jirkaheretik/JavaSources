package cz.braza.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Daily 19.1.2025

407. Trapping Rain Water II
Hard
Topics
Companies
Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.



Example 1:


Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
Example 2:


Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10


Constraints:

m == heightMap.length
n == heightMap[i].length
1 <= m, n <= 200
0 <= heightMap[i][j] <= 2 * 10^4
 */
public class L408TrappingRainWater {
    /*
    Updated Editorial Solution, removed helper function, Math.max() and static Cell class, using int[] only.
    Basically we start with outer edge (both rows and columns), find smallest value, then "fill in the inside"
    up to the given value and continue inwards.

    Runs 21ms, beats 38.2%, 42 testcases
     */
    public int trapRainWater(int[][] heightMap) {
        // Direction arrays
        int[] dRow = { 0, 0, -1, 1 };
        int[] dCol = { -1, 1, 0, 0 };

        int numOfRows = heightMap.length;
        int numOfCols = heightMap[0].length;

        boolean[][] visited = new boolean[numOfRows][numOfCols];

        // Priority queue (min-heap) to process boundary cells in increasing height order
        PriorityQueue<int[]> boundary = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Add the first and last column cells to the boundary and mark them as visited
        for (int i = 0; i < numOfRows; i++) {
            boundary.offer(new int[]{heightMap[i][0], i, 0});
            boundary.offer(new int[]{heightMap[i][numOfCols - 1], i, numOfCols - 1});
            // Mark left and right boundary cells as visited
            visited[i][0] = visited[i][numOfCols - 1] = true;
        }

        // Add the first and last row cells to the boundary and mark them as visited
        for (int i = 0; i < numOfCols; i++) {
            boundary.offer(new int[]{heightMap[0][i], 0, i});
            boundary.offer(new int[]{heightMap[numOfRows - 1][i], numOfRows - 1, i});
            // Mark top and bottom boundary cells as visited
            visited[0][i] = visited[numOfRows - 1][i] = true;
        }

        // Initialize the total water volume to 0
        int totalWaterVolume = 0;

        // Process cells in the boundary (min-heap will always pop the smallest height)
        while (!boundary.isEmpty()) {
            // Pop the cell with the smallest height from the boundary
            int[] currentCell = boundary.poll();

            int currentRow = currentCell[1];
            int currentCol = currentCell[2];
            int minBoundaryHeight = currentCell[0];

            // Explore all 4 neighboring cells
            for (int direction = 0; direction < 4; direction++) {
                // Calculate the row and column of the neighbor
                int neighborRow = currentRow + dRow[direction];
                int neighborCol = currentCol + dCol[direction];

                // Check if the neighbor is within the grid bounds and not yet visited
                if (neighborRow >= 0 && neighborCol >= 0 && neighborCol < numOfCols && neighborRow < numOfRows && !visited[neighborRow][neighborCol]) {
                    // Get the height of the neighbor cell
                    int neighborHeight = heightMap[neighborRow][neighborCol];

                    // If the neighbor's height is less than the current boundary height, water can be trapped
                    if (neighborHeight < minBoundaryHeight)
                        // Add the trapped water volume
                        totalWaterVolume += minBoundaryHeight - neighborHeight;

                    // Push the neighbor into the boundary with updated height (to prevent water leakage)
                    boundary.offer(new int[]{neighborHeight > minBoundaryHeight ? neighborHeight : minBoundaryHeight, neighborRow, neighborCol});
                    visited[neighborRow][neighborCol] = true;
                }
            }
        }

        // Return the total amount of trapped water
        return totalWaterVolume;
    }
}
