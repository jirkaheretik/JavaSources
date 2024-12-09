package cz.braza.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
130. Surrounded Regions
Medium
Topics
Companies
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]



Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */
public class L130SurroundedRegions {
    /*
    Use approach from L200 for flooding - that is, for BORDER regions only,
    filling it with some intermediate value P. Then "normalize" the string
    by replacing all Os with Xs and all Ps back with Os.

    Runs 4ms, beats 22.1% (50% submissions run 2ms)
     */
    public static final char ISLAND = 'O';
    public static final char WATER = 'X';
    public static final char PENINSULA = 'P';
    public void solve(char[][] board) {
        Queue<Integer> toFlood = new LinkedList<>();
        int lastCol = board[0].length - 1;
        // flood peninsulas:
        // from the sides:
        for (int r = 0; r < board.length; r++) {
            if (board[r][0] == ISLAND)
                toFlood.add(1000 * r);
            if (board[r][lastCol] == ISLAND)
                toFlood.add(1000 * r + lastCol);
        }
        // from top and bottom:
        for (int c = 0; c <= lastCol; c++) {
            if (board[0][c] == ISLAND)
                toFlood.add(c);
            if (board[board.length - 1][c] == ISLAND)
                toFlood.add(1000 * (board.length - 1) + c);
        }
        flood(toFlood, board);
        // now normalize: replace Ps with Os and former Os with Xs
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c <= lastCol; c++)
                if (board[r][c] == ISLAND)
                    board[r][c] = WATER;
                else if (board[r][c] == PENINSULA)
                    board[r][c] = ISLAND;
    }

    /*
    Taken from L200, updated to fill with P char
     */
    public static final void flood(Queue<Integer> list, final char[][] grid) {
        while (!list.isEmpty()) {
            int val = list.poll();
            int r = val / 1000;
            int c = val % 1000;
            System.out.println("DBG picking value " + val + " row " + r + " and col " + c);
            if (r >= grid.length || c >= grid[r].length || grid[r][c] == WATER || grid[r][c] == PENINSULA)
                continue;
            System.out.println("and processing it!");
            grid[r][c] = PENINSULA;
            if (r > 0) list.add(1000 * r + c - 1000);
            if (c > 0) list.add(1000 * r + c - 1);
            list.add(1000 * r + c + 1);
            list.add(1000 * r + c + 1000);
        }
    }
}
