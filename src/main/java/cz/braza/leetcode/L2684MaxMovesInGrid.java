package cz.braza.leetcode;

import java.util.Arrays;

/*
2684. Maximum Number of Moves in a Grid
Medium
Topics
Companies
Hint
You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.
 */
public class L2684MaxMovesInGrid {
    /*
    First attempt, dp with same size MxN array of max possible moves
    Runs 6ms, beats 80.8 % (47k)
     */
    public int maxMoves(int[][] grid) {
        // create dp and fill it with -1s
        int[][] dp = new int[grid.length][grid[0].length];
        for (int[] row: dp)
            Arrays.fill(row, -1);
        int result = 0;
        // we can start from any cell in the first column, iterate over
        // possible rows, let the array populate in the meantime,
        // and pick highest value
        for (int r = 0; r < grid.length; r++) {
            int val = dpMoves(grid, dp, r, 0);
            if (val > result)
                result = val;
        }
        return result;
    }

    public static int dpMoves(int[][] grid, int[][] dp, int row, int col) {
        // return an answer if we already have it
        if (dp[row][col] != -1) return dp[row][col];
        int result = 0;
        // do we have another col to go to:
        if (col < grid[row].length - 1)
            // try all the possible new cells
            for (int newRow = Math.max(0, row - 1); newRow <= Math.min(row + 1, grid.length - 1); newRow++)
                // and if we can go there (higher value)
                if (grid[newRow][col + 1] > grid[row][col])
                    // update result with their value
                    result = Math.max(result, dpMoves(grid, dp, newRow, col + 1) + 1);
        // store the computed value
        dp[row][col] = result;
        // and return it
        return result;
    }
}
