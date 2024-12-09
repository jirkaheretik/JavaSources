package cz.braza.leetcode;

/*
1574. Shortest Subarray to be Removed to Make Array Sorted
Medium
Topics
Companies
Hint
Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

Return the length of the shortest subarray to remove.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
Example 2:

Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
Example 3:

Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any elements.


Constraints:

1 <= arr.length <= 105
0 <= arr[i] <= 109
 */
public class L1574ShortestSubarrayRemovalForSortedArray {
    /*
    Easier part, expecting TLE. Find left (where sorted part ends) and right (the same from the end)
    Then remove up to the point arr[newLeft] <= arr[newRight]
    Runs 1ms, beats 100%
    We *could* use binary search when looking how far we can get left/right in the last
    part, but that would complicate things and since this is more than ok, no worries.
     */
    public static int findLengthOfShortestSubarray(int[] arr) {
        // find where the left sorted part ends:
        int leftIndex = -1;
        for (int index = 1; index < arr.length; index++)
            if (arr[index] < arr[index - 1]) {
                leftIndex = index - 1;
                break;
            }
        if (leftIndex == -1) // whole array sorted
            return 0;
        // find where the right sorted part begins:
        int rightIndex = arr.length - 1;
        for (int index = arr.length - 2; index > 0; index--)
            if (arr[index] > arr[index + 1]) {
                rightIndex = index + 1;
                break;
            }
        // now we need to find out the minimum:
        // we can always start with `arr.length - 1`, but we can go better,
        // starting with min of `rightIndex` and `arr.length - leftIndex`
        // essentially discarding left or right part of the array
        int minLengthOut = Math.min(rightIndex, arr.length - 1 - leftIndex);
        System.out.printf("DBG left: %d, right: %d, start out: %d%n", leftIndex, rightIndex, minLengthOut);
        // linear approach first:
        for (int j = rightIndex; j < arr.length; j++)
            for (int i = leftIndex; i >= 0; i--) {
                System.out.printf("DBG i: %d, left: %d, j: %d, right: %d, limit: %d%n", i, leftIndex, j, rightIndex, minLengthOut);
                // fast bail out:
                if (j - i - 1 >= minLengthOut) break;
                if (arr[i] <= arr[j]) {
                    System.out.printf("Found here for %d - %d%n", i, j);
                    if (j - i - 1 < minLengthOut)
                        minLengthOut = j - i - 1;
                    break;
                }
            }
        // special case here:
        if (arr[leftIndex] <= arr[rightIndex] && minLengthOut > rightIndex - leftIndex - 1)
            minLengthOut = rightIndex - leftIndex - 1;
        return minLengthOut;
    }

    public static void main(String[] args) {
        int[] t = {11,26,3,14,24,6,10,16,32,9,36,24,27,17,31,32,35,36,11,22,30};
        System.out.println(findLengthOfShortestSubarray(t));
    }
}
