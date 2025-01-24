package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Daily 23.1.2025

1267. Count Servers that Communicate
Medium
Topics
Companies
Hint
You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.



Example 1:



Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.
Example 2:



Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.
Example 3:



Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.


Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1
 */
public class L1267CountServersThatCommunicate {
    /*
    First go through the MxN array counting counts in rows and cols.
    Then we need to find "double agents", that is computers that are on
    communicating rows and cols and are counted twice.

    Runs 3ms, beats 47.4%, 55 testcases (over 50% submissions run in 2ms)
     */
    public int countServers(int[][] grid) {
        int serverCount = 0;
        int doubleCount = 0;
        int[] rows = new int[grid.length];
        int[] cols = new int[grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 1) {
                    rows[row]++;
                    cols[col]++;
                }
            if (rows[row] > 1) serverCount += rows[row];
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col] > 1) {
                serverCount += cols[col];
                for (int row = 0; row < rows.length; row++)
                    if (rows[row] > 1 && grid[row][col] == 1) doubleCount++;
            }
        }
        return serverCount - doubleCount;
    }

    /*
    In order not to traverse again all the rows (for each communicating column),
    we can use a Set to note ONLY communicating rows. Speeding search at a cost
    of using a dynamic structure.

    While this does less comparations and leads to cleaner code, it is slower:
    Runs 6ms, beats 20.5%
     */
    public int countServersDynamic(int[][] grid) {
        int serverCount = 0;
        int doubleCount = 0;
        List<Integer> rows = new ArrayList<>();
        int[] cols = new int[grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            int rowCount = 0;
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 1) {
                    rowCount++;
                    cols[col]++;
                }
            if (rowCount > 1) {
                serverCount += rowCount;
                rows.add(row); // this row communicates (contains multiple servers)
            }
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col] > 1) {
                serverCount += cols[col];
                for (int row: rows)
                    if (grid[row][col] == 1) doubleCount++;
            }
        }
        return serverCount - doubleCount;
    }
}
