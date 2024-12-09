package cz.braza.leetcode;

/*
3184. Count Pairs That Form a Complete Day I
Easy
Topics
Companies
Hint
Given an integer array hours representing times in hours, return an integer denoting the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.

A complete day is defined as a time duration that is an exact multiple of 24 hours.

For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.



Example 1:

Input: hours = [12,12,30,24,24]

Output: 2

Explanation:

The pairs of indices that form a complete day are (0, 1) and (3, 4).

Example 2:

Input: hours = [72,48,24,3]

Output: 3

Explanation:

The pairs of indices that form a complete day are (0, 1), (0, 2), and (1, 2).



Constraints:

1 <= hours.length <= 100
1 <= hours[i] <= 109
 */
public class L3184PairsForCompleteDay {
    /*
    Find how many are of each "trailing hour" count, then count combinations from that.
    Since i < j must hold for all indices, we need to halve the number
    (we do that for hours 24 and 12, and we do that for all others by
     only considering trailing hours 1-11)
     Runs 1ms, beats 98.8%
     */
    public int countCompleteDayPairs(int[] hours) {
        int[] trailingHours = new int[24];
        for (int num : hours)
            trailingHours[num % 24]++;
        int count = 0;
        if (trailingHours[0] > 0)
            count += trailingHours[0] * (trailingHours[0] - 1) / 2;
        if (trailingHours[12] > 0)
            count += trailingHours[12] * (trailingHours[12] - 1) / 2;
        for (int h = 1; h < 12; h++)
            count += trailingHours[h] * trailingHours[24 - h];
        return count;
    }

}
