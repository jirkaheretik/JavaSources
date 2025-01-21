package cz.braza.leetcode;

/*
Daily 20.1.2025

2017. Grid Game
Solved
Medium
Topics
Companies
Hint
You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c) on the matrix. Two robots are playing a game on this matrix.

Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).

At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path. For all cells (r, c) traversed on the path, grid[r][c] is set to 0. Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may intersect with one another.

The first robot wants to minimize the number of points collected by the second robot. In contrast, the second robot wants to maximize the number of points it collects. If both robots play optimally, return the number of points collected by the second robot.



Example 1:


Input: grid = [[2,5,4],[1,5,1]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 0 + 4 + 0 = 4 points.
Example 2:


Input: grid = [[3,3,1],[8,5,2]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 3 + 1 + 0 = 4 points.
Example 3:


Input: grid = [[1,3,1,15],[1,3,3,1]]
Output: 7
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.


Constraints:

grid.length == 2
n == grid[r].length
1 <= n <= 5 * 10^4
1 <= grid[r][c] <= 10^5
 */
public class L2017GridGame {
    /*
    Grid of size 2xN, robots going from [0,0] to [1, n - 1], they can only move right or down (exactly once).
    This means the first robot can only choose one column `i` where it changes rows, taking points [0,0]-[0,i] and [1,i]-[1,n-1].
    The other robot can either stay in its lane up to the end, taking [0,i+1]-[0,n-1] or switch right away, taking [1,0]-[1,i-1].
    We can count sums in both rows, then processing the grid again, looking for a minimum of the above.

    Runs 3ms, beats 100%, 109 testcases
    */
    public long gridGame(int[][] grid) {
        // init, count both sums
        long sum0 = 0, sum1 = 0;
        for (int idx = 0; idx < grid[0].length; idx++) {
            sum0 += grid[0][idx];
            sum1 += grid[1][idx];
        }
        // System.out.printf("Grid value up: %d, down: %d%n", sum0, sum1);
        // process:
        // switch right away:
        long upSum = sum0 - grid[0][0];
        long downSum = 0;
        long max = upSum;
        for (int idx = 1; idx < grid[0].length; idx++) {
            upSum -= grid[0][idx];
            downSum += grid[1][idx - 1];
            if (downSum >= upSum) {
                // System.out.printf("Switching at index %d or before, upSum: %d, downSum: %d, max: %d%n", idx, upSum, downSum, max);
                return max > downSum ? downSum : max;
            }
            max = upSum;
        }
        return max;
    }
}
