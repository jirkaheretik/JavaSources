package cz.braza.leetcode;

/*
Daily 20.1.2025

2661. First Completely Painted Row or Column
Medium
Topics
Companies
Hint
You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].

Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].

Return the smallest index i at which either a row or a column will be completely painted in mat.



Example 1:

image explanation for example 1
Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
Output: 2
Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
Example 2:

image explanation for example 2
Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
Output: 3
Explanation: The second column becomes fully painted at arr[3].


Constraints:

m == mat.length
n = mat[i].length
arr.length == m * n
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= arr[i], mat[r][c] <= m * n
All the integers of arr are unique.
All the integers of mat are unique.
 */
public class L2661FirstCompleteRowOrColumn {
    /*
    We create several processing arrays - one to store number of painted cells in each row, one in each column, and one to store values with their row/col indices.
    Then we just process arr, find its position, and update row/col count. If enough, return result.

    Runs 15ms, beats 85.6%, 1058 testcases
    */
    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        // init
        int H = mat.length;
        int W = mat[0].length;
        int N = arr.length;
        int[][] positions = new int[N][2];
        int[] rows = new int[H];
        int[] cols = new int[W];
        // where to find values:
        for (int r = 0; r < H; r++)
            for (int c = 0; c < W; c++)
                positions[mat[r][c] - 1] = new int[]{r, c};
        // now process arr:
        for (int idx = 0; idx < N; idx++) {
            int val = arr[idx];
            int[] pos = positions[val - 1];
            rows[pos[0]]++;
            if (rows[pos[0]] == H) return idx;
            cols[pos[1]]++;
            if (cols[pos[1]] == W) return idx;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstCompleteIndex(new int[]{1,4,5,2,6,3}, new int[][]{{4, 3, 5}, {1, 2, 6}}));
    }
}
