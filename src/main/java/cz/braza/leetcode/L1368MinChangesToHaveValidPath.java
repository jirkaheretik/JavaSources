package cz.braza.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Daily 18.1.2025

1368. Minimum Cost to Make at Least One Valid Path in a Grid
Hard
Topics
Companies
Hint
Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.



Example 1:


Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.
Example 2:


Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).
Example 3:


Input: grid = [[1,2],[4,3]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
1 <= grid[i][j] <= 4
 */
public class L1368MinChangesToHaveValidPath {
    public static final int[][] DIR = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // Runs 13ms, beats 81.8%, 69 testcases
    public int minCost(int[][] grid) {
        /*
        Make it a graph, use BFS:
        Specifically, where the arrow points, it has cost of 0, while all other
        directions have cost 1. Then, BFS to find [n - 1][m - 1] node and the cost
        is the number of changes needed.

        Directions [i][j]:
        1 - right, that is j+1
        2 - left, that is j-1
        3 - down, that is i+1
        4 - up, that is i-1
         */
        int height = grid.length;
        int width = grid[0].length;
        if (height < 2 && width < 2) return 0;
        boolean[][] visited = new boolean[height][width];
        visited[0][0] = true; // starting point
        Deque<int[]> deque = new ArrayDeque<>();
        if (grid[0][0] == 1) {
            if (width > 1) deque.addFirst(new int[]{0, 1, 0});
            if (height > 1) deque.addLast(new int[]{1, 0, 1});
        } else if (grid[0][0] == 3) {
            if (height > 1) deque.addFirst(new int[]{1, 0, 0});
            if (width > 1) deque.addLast(new int[]{0, 1, 1});
        } else {
            if (height > 1) deque.addLast(new int[]{1, 0, 1});
            if (width > 1) deque.addLast(new int[]{0, 1, 1});
        }
        while (!deque.isEmpty()) {
            int[] cell = deque.poll();
            int x = cell[1];
            int y = cell[0];
            int cost = cell[2];
            if (x == width - 1 && y == height - 1) return cost;
            if (visited[y][x]) continue; // been there already
            visited[y][x] = true;
            // add all directions:
            for (int idx = 1; idx < DIR.length; idx++) {
                int newX = x + DIR[idx][1];
                int newY = y + DIR[idx][0];
                if (newX < 0 || newY < 0 || newX >= width || newY >= height || visited[newY][newX]) continue;
                if (idx == grid[y][x]) // if the arrow is pointing this direction, cost is +0, otherwise +1
                    deque.addFirst(new int[]{newY, newX, cost});
                else
                    deque.addLast(new int[]{newY, newX, cost + 1});
            }
        }
        return -1;
    }
}
