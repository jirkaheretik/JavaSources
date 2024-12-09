package cz.braza.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
200. Number of Islands
Medium
Topics
Companies
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */
public class L200NumberOfIslands {
    /*
    Easy attempt:
    traverse the matrix, and whenever we find a land, we count +1 and fill
    it with zeroes - queueing all the adjacent cells to be filled
    Runs 8ms, beats 15.7% (36% solutions runs 3ms)
     */

    public static final char LAND = '1';
    public static final char WATER = '0';

    public static int numIslands(char[][] grid) {
        int islandCount = 0;
        Queue<Integer> toFlood = new LinkedList<>();
        for (char[] r: grid)
            System.out.println(Arrays.toString(r));
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == LAND) {
                    islandCount++;
                    grid[row][col] = WATER;
                    toFlood.add(1000 * row + col + 1);
                    toFlood.add(1000 * row + col + 1000);
                    flood(toFlood, grid);
                    System.out.println("After " + islandCount + ". island:");
                    for (char[] r: grid)
                        System.out.println(Arrays.toString(r));
                }
            }
        return islandCount;
    }

    public static final void flood(Queue<Integer> list, final char[][] grid) {
        while (!list.isEmpty()) {
            int val = list.poll();
            int r = val / 1000;
            int c = val % 1000;
            System.out.println("DBG picking value " + val + " row " + r + " and col " + c);
            if (r >= grid.length || c >= grid[r].length || grid[r][c] == WATER)
                continue;
            System.out.println("and processing it!");
            grid[r][c] = WATER;
            if (r > 0) list.add(1000 * r + c - 1000);
            if (c > 0) list.add(1000 * r + c - 1);
            list.add(1000 * r + c + 1);
            list.add(1000 * r + c + 1000);
        }
    }

    public static void main(String[] args) {
        char[][] g = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(g));
    }
}
