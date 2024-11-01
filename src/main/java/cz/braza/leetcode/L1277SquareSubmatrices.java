package cz.braza.leetcode;

import java.util.Arrays;

/*
1277. Count Square Submatrices with All Ones
Medium
Topics
Companies
Hint
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
 */
public class L1277SquareSubmatrices {
    /*
    First try.
    Main idea (for larger squares): in matrix[i][j] there is already largest square found,
    so 0 or 1 at start, 2 if 2x2 is found and so on. So we pick this number and
    check sides only, updating current matrix[i][j] and result in the process.

    Runs 6ms, beats 70.7%
     */
    public static int countSquares(int[][] matrix) {
        int result = 0;
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[row].length; col++)
                if (matrix[row][col] > 0) {
                    result++; // 1x1 square
                    int howFar = row > 0 && col > 0 ? matrix[row - 1][col - 1] : 0;
                    for (int j = 1; j <= howFar; j++) {
                        // if we have positives on both sides, we can add one
                        // to the result and to our square as well
                        if (matrix[row - j][col] > 0 && matrix[row][col - j] > 0) {
                            result++;
                            matrix[row][col]++;
                        }
                        // if not, do not look further!
                        else break;
                    }
                }
        return result;
    }

    public static void main(String[] args) {
        int[][] test = {{1,1,0,0,1}, {1,0,1,1,1},{1,1,1,1,1},{1,0,1,0,1},{0,0,1,0,1}};
        System.out.println(countSquares(test));
        for (int[] a: test)
            System.out.println(Arrays.toString(a));
    }
}
