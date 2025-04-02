package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 26.3.2025 - https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/

2033. Minimum Operations to Make a Uni-Value Grid
Medium
Topics
Companies
Hint
You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.



Example 1:


Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following:
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
Example 2:


Input: grid = [[1,5],[2,3]], x = 1
Output: 5
Explanation: We can make every element equal to 3.
Example 3:


Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= x, grid[i][j] <= 10^4
 */
public class L2033MakeUniValueGrid {
    /*
    Lets assume median is the value towards which we aim. Try in your head values like [1, 1, 1, 9], [1, 1, 1, 9, 9] and [1, 1, 1, 9, 9, 9] (in the last case we can actually aim for any number between 1-9.
    In order to get it, we want 1D array, and during filling it up, we also check if all values have the same remainder when dividing by x, if not, return -1
    (as there is no way we can make those values equal by adding or subtracting x).
    Then traverse the array, and for each value compute its diff to median, divide by x, add to result.
    Runs 26ms, beats 98%, 62 testcases
     */
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int modulo = grid[0][0] % x;
        int[] arr = new int[m * n];
        int idx = 0;
        for (int[] row: grid)
            for (int num: row)
                if (num % x != modulo) return -1;
                else arr[idx++] = num;
        Arrays.sort(arr);
        int median = arr[arr.length / 2];
        int operations = 0;
        for (int num: arr)
            operations += num < median ? median - num % x : num - median % x;
        return operations;
    }
}

/*
Linked - 462 - https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int operations = 0;
        for (int num: nums)
            operations += num < median ? median - num : num - median;
         /* ---- not needed in the end ---
        if (nums.length % 2 == 0) {
            median = nums[(nums.length / 2) - 1];
            int operations2 = 0;
            for (int num: nums)
                operations2 += num < median ? median - num : num - median;
            if (operations2 < operations) {
                System.out.println("Sudo a prvni je lepsi");
                return operations2;
            }
        }
        *   /
        return operations;

    }


*/
