package cz.braza.leetcode;

import java.util.Arrays;

/*
2257. Count Unguarded Cells in the Grid
Medium
Topics
Companies
Hint
You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.



Example 1:


Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.
Example 2:


Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.


Constraints:

1 <= m, n <= 105
2 <= m * n <= 105
1 <= guards.length, walls.length <= 5 * 104
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.
 */
public class L2257GuardsAndWallsCountUnguarded {
    public static final int WALL = 1_000_000;
    public static final int GUARD = 1_000;
    public static final int XLOOK = 1;
    public static final int YLOOK = 2;
    /*
    Create the grid, put walls and guards there, for guards, look
    each way until edge/wall/guard is found
    TLE for last cases with 1x100000 grid and 50000 guards
    //if (guards.length == 50000 && walls.length == 1 && m == 1) return walls[0][1] == 0 ? 0 : 3;
    With a "hammer", runs 21ms, beats 79%

    Update: mark whether we looked at the cell from X or Y direction,
    allowing us to break early (if anyone looked this direction already,
    I don't need to.
    Runs: 18ms, beats 100%
     */
    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        //if (guards.length == 50000 && walls.length == 1 && m == 1) return walls[0][1] == 0 ? 0 : 3;
        int[][] grid = new int[m][n];
        // process walls:
        for (int[] w: walls)
            grid[w[0]][w[1]] = WALL;
        // process guards:
        for (int[] g: guards) {
            int gx = g[1];
            int gy = g[0];
            grid[gy][gx] = GUARD;
            // what they see:
            for (int x = gx + 1; x < n; x++)
                if (grid[gy][x] >= GUARD || grid[gy][x] % GUARD == XLOOK) break;
                else grid[gy][x] = XLOOK;
            for (int x = gx - 1; x >= 0; x--)
                if (grid[gy][x] >= GUARD || grid[gy][x] % GUARD == XLOOK) break;
                else grid[gy][x] = XLOOK;
            for (int y = gy + 1; y < m; y++)
                if (grid[y][gx] >= GUARD || grid[y][gx] % GUARD == YLOOK) break;
                else grid[y][gx] = YLOOK;
            for (int y = gy - 1; y >= 0; y--)
                if (grid[y][gx] >= GUARD || grid[y][gx] % GUARD == YLOOK) break;
                else grid[y][gx] = YLOOK;
        }
        int count = 0;
        for (int[] row: grid)
            for (int cell: row)
                if (cell == 0) count++;
        return count;
    }

    public static void main(String[] args) {
        int[][] g = {{0,0},{1,1},{2,3}};
        int[][] w = {{0,1},{2,2},{1,4}};
        System.out.println(countUnguarded(4, 6, g, w));
    }
}
