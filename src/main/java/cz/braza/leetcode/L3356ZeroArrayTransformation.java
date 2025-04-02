package cz.braza.leetcode;

/*
Daily 13.3.2025 - https://leetcode.com/problems/zero-array-transformation-ii/
SHOW: WOW, Line Sweep, Insight

3356. Zero Array Transformation II
Medium
Topics
Companies
Hint
You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].

Each queries[i] represents the following action on nums:

Decrement the value at each index in the range [li, ri] in nums by at most vali.
The amount by which each value is decremented can be chosen independently for each index.
A Zero Array is an array with all its elements equal to 0.

Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.



Example 1:

Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]

Output: 2

Explanation:

For i = 0 (l = 0, r = 2, val = 1):
Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
The array will become [1, 0, 1].
For i = 1 (l = 0, r = 2, val = 1):
Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.
Example 2:

Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]

Output: -1

Explanation:

For i = 0 (l = 1, r = 3, val = 2):
Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
The array will become [4, 1, 0, 0].
For i = 1 (l = 0, r = 2, val = 1):
Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
The array will become [3, 0, 0, 0], which is not a Zero Array.


Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 5 * 10^5
1 <= queries.length <= 10^5
queries[i].length == 3
0 <= li <= ri < nums.length
1 <= vali <= 5
 */
public class L3356ZeroArrayTransformation {
    /*
    Line sweep: we create a helper array updates of length nums.length, into which we store query updates:
    at updates[left] we ADD value, while at updates[right + 1] we SUBTRACT value.
    Thus we only need to remember current sum of updates (of all the indexes up to current) to resolve how
    much we can subtract at given index.
    Traverse nums one by one, and IF we have enough to subtract to zero, we are ok and can move to nest index.
    If not, we need to process more queries, updating updates array and sum if needed.
    If we run out of queries, we return -1, if we process all nums, we return current k

    Update: lowered time from 4ms to 3ms by updating conditions inside while-if...

    Runs 3ms, beats 100%, 627 testcases
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int k = 0;
        int sum = 0;
        int[] updates = new int[nums.length];
        for (int idx = 0; idx < nums.length; idx++) {
            sum += updates[idx];
            while (sum < nums[idx]) {
                // need to process more queries:
                // do we have them?
                if (k >= queries.length) return -1;
                if (queries[k][1] >= idx) {
                    // update high limit if available (not outside the array, then ignore it)
                    if (queries[k][1] + 1 < updates.length) updates[queries[k][1] + 1] -= queries[k][2];
                    // if current query affects current number at index idx, update sum as well
                    if (queries[k][0] <= idx) sum += queries[k][2];
                    else updates[queries[k][0]] += queries[k][2];
                } // no else, that is we ignore queries for lower indices, already catered for.
                k++;
            }
        }
        return k;
    }
}
