package cz.braza.leetcode;

/*
1975. Maximum Matrix Sum
Medium
Topics
Companies
Hint
You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.



Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.


Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105
 */
public class L1975MaxMatrixSum {
    /*
    Let's assume we can flip to positive any two values (even through many steps), which
    means we can add all abs values except ONE smallest (smallest absolute, that is closest
    to zero) IFF there is odd number of negatives.
    Go through the matrix, remembering number of negatives and the one closest to zero,
    then subtract it twice from the result, if odd.
    Updates (through failing test cases):
    - if zero is there, we can "flip" it, thus get rid of the negative
    - actually, we are not looking for a highest negative (smallest in abs value),
    but number closest to zero, that is the one we can left out.

    Runs 3ms, beats 100%
     */
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int negativeCount = 0;
        int smallestAbs = Integer.MAX_VALUE;
        for (int[] row: matrix)
            for (int num: row) {
                if (num < 0) {
                    num *= -1;
                    negativeCount++;
                }
                if (num < smallestAbs)
                    smallestAbs = num;
                sum += num;
            }
        if (negativeCount % 2 == 1)
            sum -= 2 * smallestAbs;
        return sum;
    }
}
