package cz.braza.leetcode;

import java.util.Arrays;

/*
1861. Rotating the Box
Medium
Topics
Companies
Hint
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.



Example 1:



Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:



Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:



Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]


Constraints:

m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.
 */
public class L1861RotatingTheBox {
    public static final char STONE = '#';
    public static final char WALL = '*';
    public static final char EMPTY = '.';

    /*
    Used the base of AoC 2023/14 for moving stones
    Runs 10ms, beats 48%
    */
    public static char[][] rotateTheBox(char[][] box) {
        // create resulting array
        int w = box.length;
        char[][] result = new char[box[0].length][w];
        // DBG:
        for (char[] row: box)
            System.out.println(Arrays.toString(row));
        // copy the content:
        for (int r = 0; r < result.length; r++)
            for (int c = 0; c < result[0].length; c++)
                result[r][c] = box[w - 1 - c][r];
        // DBG:
        for (char[] row: result)
            System.out.println(Arrays.toString(row));

        // rotate:
        moveSouth(result);
        System.out.println();
        System.out.println();
        // DBG:
        for (char[] row: result)
            System.out.println(Arrays.toString(row));

        return result;
    }

    public static void moveSouth(char[][] field) {
        for (int col = 0; col < field[0].length; col++) {
            int freeRow = -1;
            for (int row = field.length - 1; row >= 0; row--) {
                char c = field[row][col];
                if (c == EMPTY) {
                    if (freeRow == -1) freeRow = row;
                } else if (c == WALL) {
                    freeRow = -1;
                } else if (c == STONE) {
                    if (freeRow > -1) {
                        field[freeRow][col] = STONE;
                        field[row][col] = EMPTY;
                        freeRow--;
                    }
                }
            }
        }
    }

    public static void moveNorth(char[][] field) {
        for (int col = 0; col < field[0].length; col++) {
            int freeRow = -1;
            for (int row = 0; row < field.length; row++) {
                char c = field[row][col];
                if (c == EMPTY) {
                    if (freeRow == -1) freeRow = row;
                } else if (c == WALL) {
                    freeRow = -1;
                } else if (c == STONE) {
                    if (freeRow > -1) {
                        field[freeRow][col] = STONE;
                        field[row][col] = EMPTY;
                        freeRow++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] box = {{'#','.','#'}};
        //char[][] box = {{'#','.','*','.'}, {'#','#','*','.'} };
        rotateTheBox(box);
    }
}
