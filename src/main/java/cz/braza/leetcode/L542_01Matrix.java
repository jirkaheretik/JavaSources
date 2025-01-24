package cz.braza.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
542. 01 Matrix (Quite SAME as L1765)
Medium
Topics
Companies
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.

 */
public class L542_01Matrix {
    public static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /*
    Used a solution from L1765 with minor updates/refactoring.

    Runs 21ms, beats 11%, 50 testcases (majority uses 13-15ms)
     */
    public int[][] updateMatrix(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        // find water cells and add them to the queue:
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                if (mat[r][c] == 0)
                    queue.add(new int[]{r, c, 0});
        // process queue:
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int height = cell[2];
            if (visited[row][col]) continue;
            visited[row][col] = true;
            mat[row][col] = height;
            for (int[] dir: DIR) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];
                if (nRow >= 0 && nCol >= 0 && nRow < R && nCol < C && !visited[nRow][nCol])
                    queue.add(new int[]{nRow, nCol, height + 1});
            }
        }
        return mat;
    }
}
