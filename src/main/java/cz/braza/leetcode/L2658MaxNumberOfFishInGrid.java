package cz.braza.leetcode;

/*
Daily 28.1.2025
SHOW: Yes, progress, mid difficulty, thought process, cost of recursion

2658. Maximum Number of Fish in a Grid
Medium
Topics
Companies
Hint
You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.



Example 1:


Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.
Example 2:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10
 */
public class L2658MaxNumberOfFishInGrid {
    public static final int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int catchFish(int r, int c, int maxHeight, int maxWidth, int[][] grid) {
        if (r < 0 || c < 0 || r >= maxHeight || c >= maxWidth || grid[r][c] == 0) return 0;
        int fishCount = grid[r][c];
        grid[r][c] = 0;
        for (int[] dir: DIR)
            fishCount += catchFish(r + dir[0], c + dir[1], maxHeight, maxWidth, grid);
        return fishCount;
    }

    /*
    Easy DFS - just once we find a non zero cell (fish), we try to count (add up) all the adjacent cells with fish in it
    And look for a max value through whole grid.

    Runs 4ms, beats 77%, 3842 testcases
     */
    public int findMaxFish(int[][] grid) {
        int result = 0;
        int H = grid.length;
        int W = grid[0].length;
        for (int r = 0; r < H; r++)
            for (int c = 0; c < W; c++)
                if (grid[r][c] > 0) {
                    int fishCount = catchFish(r, c, H, W, grid);
                    if (fishCount > result) result = fishCount;
                }
        return result;
    }

    /*
    Another version of the recursive function with all the checks right away, in order NOT to call the function when not needed

    Runs 3ms, beats 100%, 3842 testcases
     */
    public static int catchFishLBYL(int r, int c, int maxHeight, int maxWidth, int[][] grid) {
        int fishCount = grid[r][c];
        grid[r][c] = 0;
        for (int[] dir: DIR) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < maxHeight && nc < maxWidth && grid[nr][nc] > 0)
                fishCount += catchFish(nr, nc, maxHeight, maxWidth, grid);
        }
        return fishCount;
    }
}
