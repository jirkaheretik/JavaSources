package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 9.2.2025

2364. Count Number of Bad Pairs
Medium
Topics
Companies
Hint
You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.



Example 1:

Input: nums = [4,1,3,3]
Output: 5
Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
There are a total of 5 bad pairs, so we return 5.
Example 2:

Input: nums = [1,2,3,4,5]
Output: 0
Explanation: There are no bad pairs.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */
public class L2364CountBadPairsInArray {
    public long countBadPairsNaive(int[] nums) {
        long count = 0;
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] - nums[i] != j - i) count++;
        return count;
    }

    /*
    instead of hash map or whatever (as suggested in hints) we create an array of value-index values,
    sort it, find how many there are same items, then count pairs count from that,
    and subtract from total number of pairs in the array ((N - 1) * N / 2)

    Runs 20ms, beats 100%, 65 testcases
     */
    public long countBadPairsArr(int[] nums) {
        long count = 0;
        long N = nums.length;
        int[] upNums = new int[(int)N];
        for (int i = 0; i < N; i++)
            upNums[i] = nums[i] - i;
        Arrays.sort(upNums);
        int idx = 1;
        int lastVal = upNums[0];
        long valCount = 1;
        while (idx < N) {
            if (upNums[idx] == lastVal) valCount++;
            else {
                if (valCount > 1) count += (valCount - 1) * valCount / 2;
                lastVal = upNums[idx];
                valCount = 1;
            }
            idx++;
        }
        if (valCount > 1) count += (valCount - 1) * valCount / 2;

        return N * (N - 1) / 2 - count;
    }

    /*
    Instead of waiting for value change and count, count through, save
    one condition in the end.
    Works well,
    Runs 19ms, beats 100%
     */
    public long countBadPairsThrough(int[] nums) {
        long count = 0;
        long N = nums.length;
        int[] upNums = new int[(int)N];
        for (int i = 0; i < N; i++)
            upNums[i] = nums[i] - i;
        Arrays.sort(upNums);
        int idx = 1;
        int lastVal = upNums[0];
        long valCount = 1;
        while (idx < N) {
            if (upNums[idx] == lastVal) {
                count += valCount;
                valCount++;
            } else {
                lastVal = upNums[idx];
                valCount = 1;
            }
            idx++;
        }
        return N * (N - 1) / 2 - count;
    }
}
