package cz.braza.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
2290. Minimum Obstacle Removal to Reach Corner
Hard
Topics
Companies
Hint
You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.

Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).



Example 1:


Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
Output: 2
Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
It can be shown that we need to remove at least 2 obstacles, so we return 2.
Note that there may be other ways to remove 2 obstacles to create a path.
Example 2:


Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
Output: 0
Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
2 <= m * n <= 105
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class L2290MinimumObstacleRemoval {
    private final int[][] directions = {{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },};

    /*
    From Editorial:
    Dequeue, MxN array for min number of obstacles, prioritize empty cells
    (put them in front), while appending obstacle cells to the back of the queue.
    In the end, when we explore everything, we have correct minObstacles in the
    bottom right corner (target cell).
    Q: How we do not visit cells again and again?
    A: We only visit a cell if it has not been visited before (minObstacles == Integer.MAX_VALUE)
    Q: How do we know the solution is minimal?
    A: No obstacles=put in front of the queue, obstacle=put it back. This way we first visit
    all the reachable and unblocked cells, in queue there are cells with just one obstacle. Later on,
    we are processing all the "after 1 obstacle" cells, in queue we are adding "2 obstacle cells"
    and so on. Once we hit target cell, it is guaranteed to be (one of) shortest paths in term of obstacles.

    Runs: 59ms, beats 81.3%
    Minor update (once we find/poll target cell, we return the number of obstacles
    as we do not need to explore all the other cells):
    Runs 53ms, beats 90%
    Update: inlined isValid() method:
    Runs 48ms, beats 95.6%
     */
    public int minimumObstacles(int[][] grid) {
        int h = grid.length, w = grid[0].length;
        // Distance matrix to store the minimum obstacles removed to reach each cell
        int[][] minObstacles = new int[h][w];
        // Initialize all cells with a large value, representing unvisited cells
        Arrays.stream(minObstacles).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));

        minObstacles[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] { 0, 0, 0 }); // {obstacles, row, col}

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int obstacles = current[0], row = current[1], col = current[2];

            // Explore all four possible directions from the current cell
            for (int[] dir : directions) {
                int r = row + dir[0], c = col + dir[1];
                if (r >= 0 && c >= 0 && r < h && c < w &&
                    minObstacles[r][c] == Integer.MAX_VALUE) {
                    if (r == h - 1 && c == w - 1) return obstacles; // there must not be an obstacle in the target cell
                    if (grid[r][c] == 1) {
                        // If it's an obstacle, add 1 to obstacles and push to the back
                        minObstacles[r][c] = obstacles + 1;
                        deque.addLast(
                                new int[] { obstacles + 1, r, c }
                        );
                    } else {
                        // If it's an empty cell, keep the obstacle count and push to the front
                        minObstacles[r][c] = obstacles;
                        deque.addFirst(new int[] { obstacles, r, c });
                    }
                }
            }
        }

        return minObstacles[h - 1][w - 1];
    }
}
