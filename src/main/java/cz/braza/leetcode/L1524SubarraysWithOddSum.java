package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 25.2.2025
SHOW: O(n), thought process, easy to program, hard to get right

1524. Number of Sub-arrays With Odd Sum
Medium
Topics
Companies
Hint
Given an array of integers arr, return the number of subarrays with an odd sum.

Since the answer can be very large, return it modulo 109 + 7.



Example 1:

Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
Example 2:

Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.
Example 3:

Input: arr = [1,2,3,4,5,6,7]
Output: 16


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 100
 */
public class L1524SubarraysWithOddSum {
    // this works quite well, but count all subsequences (even non contigous):
    public static int numOfSubsequences(int[] arr) {
        long oddCount = 0;
        long evenCount = 0;
        int MOD = 1000000007;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenCount += evenCount + 1; // double even count and add one new
                oddCount *= 2; // double odd count since we can add even number to any odd sum group
                // sum += oddCount;
            } else {
                long newEvenCount = evenCount + oddCount; // adding odd to any odd sum group makes a new even sum group
                //evenCount += oddCount;
                oddCount += 1 + evenCount; // number of odd sum groups increases by 1 (single odd number) and all the even number sums, that get odd if we add this odd number
                evenCount = newEvenCount;
            }

        }
        return (int)(oddCount % MOD);
    }

    /*
    Updated the process from function above, just get right how many current (open)
    odd and even sequences we have, and with odd number, we can use all the even sequences, and
    vice versa.
    Runs 5ms, beats 97.8%, 151 testcases
     */
    public static int numOfSubarrays(int[] arr) {
        long totalSumCount = 0;
        long currOdd = 0;
        long currEven = 0;
        int MOD = 1000000007;
        for (int num : arr) {
            if (num % 2 == 0) {
                currEven++;
                totalSumCount += currOdd;
                //currOdd *= 2;
            } else { // last number is odd
                long newEven = currOdd;
                currOdd = currEven + 1; // odd sequences are all even-sum up to now together with this odd number
                currEven = newEven;
                totalSumCount += currOdd;
            }
            //System.out.printf("After %d we have odd %d and even %d and total %d%n", num, currOdd, currEven, totalSumCount);
        }
        return (int)(totalSumCount % MOD);
    }

    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{1, 3, 5})); // 4
        System.out.println(numOfSubarrays(new int[]{1, 3})); // 0
        int[] tst = {1,2,3,4,5,6,7};
        System.out.println(numOfSubarrays(tst)); // 16
        for (int i = 1; i < tst.length; i++) {
            System.out.println(numOfSubarrays(Arrays.copyOfRange(tst, 0, i)  ));
        }
    }
}
