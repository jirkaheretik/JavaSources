package cz.braza.leetcode;

import java.util.stream.IntStream;

/*
+ Daily 14.3.2025
2226. Maximum Candies Allocated to K Children https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
SHOW: binary search, progress, inlining a function
Medium
Topics
Companies
Hint
You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.

You are also given an integer k. You should allocate piles of candies to k children such that each child gets the same number of candies. Each child can take at most one pile of candies and some piles of candies may go unused.

Return the maximum number of candies each child can get.



Example 1:

Input: candies = [5,8,6], k = 3
Output: 5
Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.
Example 2:

Input: candies = [2,5], k = 11
Output: 0
Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.


Constraints:

1 <= candies.length <= 10^5
1 <= candies[i] <= 10^7
1 <= k <= 10^12
 */
public class L2226MaxNumberOfCandiesForChildren {
    // runs 39ms, beats 18.4%
    public int maximumCandies(int[] candies, long k) {
        // 1. get total number of candies
        long candiesCount = IntStream.of(candies).asLongStream().sum();
        if (candiesCount < k) return 0; // not enough candies for all the children
        long upperBound = candiesCount / k;
        long lowerBound = 1;
        while (lowerBound < upperBound) {
            long mid = lowerBound + (upperBound - lowerBound) / 2;
            if (mid == lowerBound) mid++;
            System.out.printf("Trying %d of interval %d, %d%n", mid, lowerBound, upperBound);
            if (getCSizedPiles(candies, mid) >= k)
                lowerBound = mid;
            else
                upperBound = upperBound == mid ? mid - 1 : mid;
        }
        return (int) lowerBound;
    }

    /*
    How many piles of at least `c` can we get?
     */
    public static long getCSizedPiles(int[] candies, long c) {
        long count = 0;
        for (int pile: candies)
            count += pile / c;
        return count;
    }

    /*
    Inlined for better performance, tweaked the binary search logic a small bit
    Runs 19ms, beats 94.4%, 100 testcases

    Then converted to int for the bounds, and check for size of maxPile, as this is a good upper bound too.
    Runs 10ms, beats 100%
     */
    public int maximumCandiesInlined(int[] candies, long k) {
        // 1. get total number of candies
        int maxPile = 0;
        long candiesCount = 0;
        for (int pile: candies) {
            if (pile > maxPile) maxPile = pile;
            candiesCount += pile;
        }
        if (candiesCount < k) return 0; // not enough candies for all the children
        int upperBound = (int) (candiesCount / k);
        if (maxPile < upperBound) upperBound = maxPile;
        int lowerBound = 1;
        while (lowerBound < upperBound) {
            int mid = (upperBound + lowerBound + 1) / 2;
            long currCount = 0;
            for (int pile: candies) currCount += pile / mid;
            if (currCount >= k) lowerBound = mid;
            else upperBound = mid - 1;
        }
        return lowerBound;
    }

}
