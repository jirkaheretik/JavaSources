package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Daily 27.2.2025

873. Length of Longest Fibonacci Subsequence
Medium
Topics
Companies
A sequence x1, x2, ..., xn is Fibonacci-like if:

n >= 3
xi + xi+1 == xi+2 for all i + 2 <= n
Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest Fibonacci-like subsequence of arr. If one does not exist, return 0.

A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr, without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].



Example 1:

Input: arr = [1,2,3,4,5,6,7,8]
Output: 5
Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: arr = [1,3,7,11,12,14,18]
Output: 3
Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].


Constraints:

3 <= arr.length <= 1000
1 <= arr[i] < arr[i + 1] <= 10^9
 */
public class L873LongestFiboSubsequence {
    /*
    Bruteforce, iterate over all possible pairs, check if fibonacci-like, keep track of max length.
    Binary search for next step.
    Runs 213ms, beats 25.2%, 57 testcases
     */
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int MAX = arr[n - 1];
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = arr[i], b = arr[j];
                int count = 2;
                while (b <= MAX && a + b <= MAX) {
                    // if a+b is in arr, increment count and update a, b
                    if (Arrays.binarySearch(arr, a + b) >= 0) {
                        int temp = b;
                        b = a + b;
                        a = temp;
                        count++;
                    } else  break;
                }
                if (count > 2 && count > maxLength) maxLength = count;
            }
        }
        return maxLength;
    }

    /*
    Hashset, same as bruteforce but with hashset for faster lookup.
    Runs 140ms, beats 71%, 57 testcases
     */
    public int lenLongestFibSubseqHashset(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for (int num: arr) set.add(num);
        int MAX = arr[n - 1];
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = arr[i], b = arr[j];
                int count = 2;
                while (b <= MAX && a + b <= MAX) {
                    // if a+b is in arr, increment count and update a, b
                    if (set.contains(a + b)) {
                        int temp = b;
                        b = a + b;
                        a = temp;
                        count++;
                    } else  break;
                }
                if (count > 2 && count > maxLength) maxLength = count;
            }
        }
        return maxLength;
    }
}
