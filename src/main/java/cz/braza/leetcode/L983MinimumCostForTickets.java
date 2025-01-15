package cz.braza.leetcode;

import java.util.HashSet;

/*
983. Minimum Cost For Tickets
Medium
Topics
Companies
You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.



Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.


Constraints:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
 */
public class L983MinimumCostForTickets {
    /*
    Naive solution without any further memory or anything, always try
    three possibilities at a current day - buy 1-day ticket, buy 7-day pass or 30-day pass and return min value
    Works ok, leads to TLE even on those small data
    Tried to add memo and failed (wrong results)
     */
    public int mincostTickets(int[] days, int[] costs) {
        int[] memo = new int[days.length]; // memoization array, zero values ok if all costs are > 0
        return findMin(days, costs, memo, 0, 0, 0);
    }

    public static int findMin(final int[] days, final int[] costs, int[] memo, int currentCost, int daysCovered, int minDayIndex) {
        // skip days already paid for:
        while (minDayIndex < days.length && days[minDayIndex] < daysCovered) minDayIndex++;
        if (minDayIndex >= days.length) return currentCost;
        if (memo[minDayIndex] > 0) {
            System.out.printf("Using memoized value %d for day %d at index %d.%n", memo[minDayIndex], days[minDayIndex], minDayIndex);
            return memo[minDayIndex];
        }
        /*
        int val = Math.min(findMin(days, costs, memo, currentCost + costs[2], days[minDayIndex] + 30, minDayIndex + 1),
                Math.min(findMin(days, costs, memo, currentCost + costs[1], days[minDayIndex] + 7, minDayIndex + 1),
                        findMin(days, costs, memo, currentCost + costs[0], days[minDayIndex], minDayIndex + 1)));

         */
        int val = Math.min(findMin(days, costs, memo, currentCost + costs[0], days[minDayIndex], minDayIndex + 1),
                Math.min(findMin(days, costs, memo, currentCost + costs[1], days[minDayIndex] + 7, minDayIndex + 1),
                        findMin(days, costs, memo, currentCost + costs[2], days[minDayIndex] + 30, minDayIndex + 1)));
        memo[minDayIndex] = val;
        System.out.printf("Setting best value %d for day %d at index %d.%n", val, days[minDayIndex], minDayIndex);
        return val;
    }

    /*
    DP with memoization
    Runs 2ms, beats 45.4%
     */
    public int mincostTicketsDp(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] memo = new int[lastDay + 1]; // memoization array, zero values ok if all costs are > 0
        HashSet<Integer> isTravelNeeded = new HashSet<>();
        // Mark the days on which we need to travel.
        for (int day : days) isTravelNeeded.add(day);
        return solve(memo, costs, isTravelNeeded, lastDay, 1);
    }

    public static int solve(final int[] memo, final int[] costs, HashSet<Integer> isTravelNeeded, int maxDay, int currentDay) {
        if (currentDay > maxDay) return 0;
        if (!isTravelNeeded.contains(currentDay)) return solve(memo, costs, isTravelNeeded, maxDay, currentDay + 1);
        if (memo[currentDay] > 0) return memo[currentDay]; // already calculated
        int result = costs[0] + solve(memo, costs, isTravelNeeded, maxDay, currentDay + 1);
        int tmp = costs[1] + solve(memo, costs, isTravelNeeded, maxDay, currentDay + 7);
        if (tmp < result) result = tmp;
        tmp = costs[2] + solve(memo, costs, isTravelNeeded, maxDay, currentDay + 30);
        if (tmp < result) result = tmp;
        memo[currentDay] = result;
        return result;
    }
}
