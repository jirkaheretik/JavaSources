package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Hard Daily 31.1.2025
SHOW: Yes, progress, hard difficulty, thought process, dynamic structures


827. Making A Large Island
Hard
Topics
Companies
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
 */
public class L827MakingALargeIsland {
    public static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    /*
    Quite straighforward solution, with couple of dynamic structures though:
    - color each region/island with its own id (starting at two not to collide with 1/0 there are)
    - note size of each island (by id), and note max island size in the process
    - then go again for zeroes (water), and count together all the differend land sizes
    - in case there is no water, return maxIslandSize, otherwise max size found

    Runs 63ms, beats 95.7%, 75 testcases
     */
    public int largestIsland(int[][] grid) {
        // first we color each region/island with its own id and note its size
        // also count max island size in the process:
        int maxIslandSize = 0;
        List<Integer> idSizes = new ArrayList<>();
        idSizes.add(0); // we won't be using id 0
        idSizes.add(0); // we won't be using id 0
        int newId = 2;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 1) {
                    // we got a new unprocessed region
                    int size = colorAndCount(row, col, newId++, grid);
                    if (size > maxIslandSize) maxIslandSize = size;
                    // note size for this id:
                    idSizes.add(size);
                }
        /*
        Now go again, this time looking for zeros that can connect different islands
         */
        int maxResult = 0;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 0) {
                    int count = 1; // my cell turned into a land:
                    Set<Integer> neighbors = new HashSet<>();
                    for (int[] dir: DIR) {
                        int nr = row + dir[0];
                        int nc = col + dir[1];
                        if (nc >= 0 && nr >= 0 && nr < grid.length && nc < grid[nr].length && grid[nr][nc] > 1)
                            // found a land:
                            if (!neighbors.contains(grid[nr][nc])) {
                                neighbors.add(grid[nr][nc]);
                                count += idSizes.get(grid[nr][nc]);
                            }
                    }
                    if (count > maxResult) maxResult = count;
                }
        // now return result - maxResult, but lets check for maxIslandSize as well, in case we do not have a single zero cell
        return maxIslandSize > maxResult ? maxIslandSize : maxResult;
    }

    /*
    "colors" whole island to a given color/id, returns count of tiles
     */
    public static int colorAndCount(int row, int col, int colorId, int[][] grid) {
        grid[row][col] = colorId;
        int count = 1;
        for (int[] dir: DIR) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            if (nc >= 0 && nr >= 0 && nr < grid.length && nc < grid[nr].length && grid[nr][nc] == 1)
                count += colorAndCount(nr, nc, colorId, grid);
        }
        return count;
    }

    /*
    From Editorial: we can skip zero check (second pass) if:
    a) no island at all - return 0
    b) just one island - if size NxN, return it, otherwise return size+1 (as we can turn one 0 at shoreline to land)

    Runs 53ms, beats 98.3%
     */
    public int largestIslandSmallSpeedup(int[][] grid) {
        // first we color each region/island with its own id and note its size
        // also count max island size in the process:
        int maxIslandSize = 0;
        List<Integer> idSizes = new ArrayList<>();
        idSizes.add(0); // we won't be using id 0
        idSizes.add(0); // we won't be using id 0
        int newId = 2;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 1) {
                    // we got a new unprocessed region
                    int size = colorAndCount(row, col, newId++, grid);
                    if (size > maxIslandSize) maxIslandSize = size;
                    // note size for this id:
                    idSizes.add(size);
                }
        /*
        Check for edgecases now:
         */
        if (newId == 2) return 1; // no land at all, turn any cell into lone island
        if (newId == 3)
            if (idSizes.get(2) == grid.length * grid[0].length) return grid.length * grid[0].length;
            else return idSizes.get(2) + 1;
        /*
        Now go again, this time looking for zeros that can connect different islands
         */
        int maxResult = 0;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == 0) {
                    int count = 1; // my cell turned into a land:
                    Set<Integer> neighbors = new HashSet<>();
                    for (int[] dir: DIR) {
                        int nr = row + dir[0];
                        int nc = col + dir[1];
                        if (nc >= 0 && nr >= 0 && nr < grid.length && nc < grid[nr].length && grid[nr][nc] > 1)
                            // found a land:
                            if (!neighbors.contains(grid[nr][nc])) {
                                neighbors.add(grid[nr][nc]);
                                count += idSizes.get(grid[nr][nc]);
                            }
                    }
                    if (count > maxResult) maxResult = count;
                }
        // now return result - maxResult, but lets check for maxIslandSize as well, in case we do not have a single zero cell
        return maxIslandSize > maxResult ? maxIslandSize : maxResult;
    }
}
