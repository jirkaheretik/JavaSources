package cz.braza.leetcode;

import java.util.Collections;
import java.util.HashMap;

/*
1072. Flip Columns For Maximum Number of Equal Rows
Medium
Topics
Companies
Hint
You are given an m x n binary matrix matrix.

You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).

Return the maximum number of rows that have all values equal after some number of flips.



Example 1:

Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
 */
public class L1072FlipColumnsForMaxRows {
    /*
    Count "patterns" for each row, that is numbers of same values, like -1-3-1
    (doesn't matter if it is 01110 or 10001, as you can "normalize" that with
    the same flips.
    Use HashMap to store frequencies, return max of values after going through the matrix.
    Runs 44ms, beats 48.3%
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        HashMap<String, Integer> mapa = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int[] row: matrix) {
            sb.setLength(0);
            int current = row[0];
            int count = 1;
            for (int index = 1; index < row.length; index++)
                if (row[index] == current)
                    count++;
                else {
                    sb.append("-" + count);
                    current = row[index];
                    count = 1;
                }
            sb.append("-" + count);
            String key = sb.toString();
            mapa.put(key, mapa.getOrDefault(key, 0) + 1);
        }
        return Collections.max(mapa.values());
    }

}
