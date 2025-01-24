package cz.braza.leetcode;

/*
36. Valid Sudoku
Medium
Topics
Companies
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
public class L36ValidSudoku {
    /* make a function to check validity of nine char tuple, then call it for rows, columns and 9 blocks

    Runs 1ms, beats 100%, 507 testcases
     */
    public boolean isValidSudoku(char[][] board) {
        // check rows:
        for (int row = 0; row < 9; row++)
            if (isInvalidBlock(board[row][0], board[row][1], board[row][2], board[row][3], board[row][4], board[row][5], board[row][6], board[row][7], board[row][8])) return false;
        // check columns:
        for (int col = 0; col < 9; col++)
            if (isInvalidBlock(board[0][col], board[1][col], board[2][col], board[3][col], board[4][col], board[5][col], board[6][col], board[7][col], board[8][col])) return false;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (isInvalidBlock(board[3 * row][3 * col], board[3 * row + 1][3 * col], board[3 * row + 2][3 * col], board[3 * row][3 * col + 1], board[3 * row + 1][3 * col + 1], board[3 * row + 2][3 * col + 1], board[3 * row][3 * col + 2], board[3 * row + 1][3 * col + 2], board[3 * row + 2][3 * col + 2])) return false;
        return true;
    }

    public static boolean isInvalidBlock(char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        boolean[] tmp = new boolean[9];
        if (c1 != '.')
            if (tmp[c1 - '1']) return true;
            else tmp[c1 - '1'] = true;
        if (c2 != '.')
            if (tmp[c2 - '1']) return true;
            else tmp[c2 - '1'] = true;
        if (c3 != '.')
            if (tmp[c3 - '1']) return true;
            else tmp[c3 - '1'] = true;
        if (c4 != '.')
            if (tmp[c4 - '1']) return true;
            else tmp[c4 - '1'] = true;
        if (c5 != '.')
            if (tmp[c5 - '1']) return true;
            else tmp[c5 - '1'] = true;
        if (c6 != '.')
            if (tmp[c6 - '1']) return true;
            else tmp[c6 - '1'] = true;
        if (c7 != '.')
            if (tmp[c7 - '1']) return true;
            else tmp[c7 - '1'] = true;
        if (c8 != '.')
            if (tmp[c8 - '1']) return true;
            else tmp[c8 - '1'] = true;
        if (c9 != '.')
            if (tmp[c9 - '1']) return true;
            else tmp[c9 - '1'] = true;
        return false;
    }
}
