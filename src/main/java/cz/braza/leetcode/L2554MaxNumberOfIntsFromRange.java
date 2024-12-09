package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
2554. Maximum Number of Integers to Choose From a Range I
Solved
Medium
Topics
Companies
Hint
You are given an integer array banned and two integers n and maxSum. You are choosing some number of integers following the below rules:

The chosen integers have to be in the range [1, n].
Each integer can be chosen at most once.
The chosen integers should not be in the array banned.
The sum of the chosen integers should not exceed maxSum.
Return the maximum number of integers you can choose following the mentioned rules.



Example 1:

Input: banned = [1,6,5], n = 5, maxSum = 6
Output: 2
Explanation: You can choose the integers 2 and 4.
2 and 4 are from the range [1, 5], both did not appear in banned, and their sum is 6, which did not exceed maxSum.
Example 2:

Input: banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
Output: 0
Explanation: You cannot choose any integer while following the mentioned conditions.
Example 3:

Input: banned = [11], n = 7, maxSum = 50
Output: 7
Explanation: You can choose the integers 1, 2, 3, 4, 5, 6, and 7.
They are from the range [1, 7], all did not appear in banned, and their sum is 28, which did not exceed maxSum.


Constraints:

1 <= banned.length <= 10^4
1 <= banned[i], n <= 10^4
1 <= maxSum <= 10^9
 */
public class L2554MaxNumberOfIntsFromRange {
    /*
    Sort banned, so we can quickly (logn) find out if a value is there
    Then go through numbers starting from 1, and if not banned, add them to sum
    While we are lower that maxSum, keep doing and count number of additions,
    return when we run out of n or pass maxSum.
    Runs 57ms, beats 14.7%
     */
    public int maxCount(int[] banned, int n, int maxSum) {
        int sum = 0;
        Arrays.sort(banned);
        int count = 0;
        for (int i = 1; i <= n; i++)
            if (Arrays.binarySearch(banned, i) < 0) {
                sum += i;
                if (sum > maxSum) break;
                count++;
            }
        return count;
    }

    /*
    Optimization possibilities:
    1) we can remove anything higher than n from the banned array
    2) we can remove duplicates.
    => ArrayList, but got several TLEs instead, NO GOOD
     */
    public int maxCountOpti(int[] banned, int n, int maxSum) {
        int sum = 0;
        ArrayList<Integer> bann = new ArrayList<>();
        for (int b: banned)
            if (b <= n && !bann.contains(b))
                bann.add(b);
        //Arrays.sort(banned);
        Collections.sort(bann);
        int count = 0;
        for (int i = 1; i <= n; i++)
//            if (Arrays.binarySearch(banned, i) < 0) {
            if (!bann.contains(i)) {
                sum += i;
                if (sum > maxSum) break;
                count++;
            }
        return count;
    }

    /*
    Now, he have sorted i values, we have sorted banned, no need for
    binarySearch. Instead, increase pointer and look
    Runs 48ms beats 45.7%, 1/3 can do it in 46 or 47ms
     */
    public int maxCountSweep(int[] banned, int n, int maxSum) {
        int sum = 0;
        Arrays.sort(banned);
        int count = 0;
        int banIdx = 0;
        for (int i = 1; i <= n; i++) {
            while (banIdx < banned.length - 1 && banned[banIdx] < i) banIdx++;
            if (banned[banIdx] != i) {
                sum += i;
                if (sum > maxSum) break;
                count++;
            }
        }
        return count;
    }
}
