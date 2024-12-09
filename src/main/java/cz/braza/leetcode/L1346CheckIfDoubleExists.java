package cz.braza.leetcode;

import java.util.Arrays;

/*
1346. Check If N and Its Double Exist
Easy
Topics
Companies
Hint
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]


Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.


Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103
 */
public class L1346CheckIfDoubleExists {
    /*
    Sort the array, the loop through values and binary search for the doubles.
    Special case for zero, as we need two different indices for value and double
    Runs 3ms, beats 33.1% (majority runs in 2ms)
     */
    public boolean checkIfExist(int[] arr) {
        Arrays.sort(arr);
        int max = arr[arr.length - 1];
        for (int val: arr) {
            if (2 * val > max) return false; // no more doubles
            int idx = Arrays.binarySearch(arr, 2 * val);
            if (idx >= 0 && val != 0) return true;
            if (idx >= 0 && val == 0) {
                // zero value, zero double, true only if there are at least two zeroes:
                if (idx > 0 && arr[idx - 1] == 0 || idx < arr.length - 1 && arr[idx + 1] == 0)
                    return true;
            }
        }
        return false;
    }

}
