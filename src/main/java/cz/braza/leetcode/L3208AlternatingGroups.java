package cz.braza.leetcode;

/*
Daily 9.3.2025 - https://leetcode.com/problems/alternating-groups-ii/

3208. Alternating Groups II
Medium
Topics
Companies
Hint
There is a circle of red and blue tiles. You are given an array of integers colors and an integer k. The color of tile i is represented by colors[i]:

colors[i] == 0 means that tile i is red.
colors[i] == 1 means that tile i is blue.
An alternating group is every k contiguous tiles in the circle with alternating colors (each tile in the group except the first and last one has a different color from its left and right tiles).

Return the number of alternating groups.

Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.



Example 1:

Input: colors = [0,1,0,1,0], k = 3

Output: 3

Explanation:



Alternating groups:



Example 2:

Input: colors = [0,1,0,0,1,0,1], k = 6

Output: 2

Explanation:



Alternating groups:



Example 3:

Input: colors = [1,1,0,1], k = 4

Output: 0

Explanation:





Constraints:

3 <= colors.length <= 10^5
0 <= colors[i] <= 1
3 <= k <= colors.length
 */
public class L3208AlternatingGroups {
    /*
    Process the array twice, but up to k positions in second run in order to cater with the circular nature
    If current length of alternating colors is at least k, a new group can end here, increase result.
    If two balls of same color are found, reset length to 1.

    Runs 3ms, beats 86.9%, 625 testcases
     */
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int result = 0;
        int pos = 1;
        int length = 1;
        boolean rolledOver = false;
        while (!rolledOver || pos < k - 1) {
            if ((pos == 0 && colors[0] != colors[colors.length - 1]) || (pos > 0 && colors[pos] != colors[pos - 1])) length++;
            else length = 1;
            if (length >= k) result++;
            pos++;
            if (pos == colors.length) {
                if (rolledOver) break;
                rolledOver = true;
                pos = 0;
            }
        }
        return result;
    }


    /*
    Simplify the main condition and manage the edge case separately (and once)

    Runs 2ms, beats 98%, 625 testcases
     */
    public int numberOfAlternatingGroupsUpdate(int[] colors, int k) {
        int result = 0;
        int pos = 1;
        int length = 1;
        boolean rolledOver = false;
        while (!rolledOver || pos < k - 1) {
            if (pos > 0 && colors[pos] != colors[pos - 1]) length++;
            else length = 1;
            if (length >= k) result++;
            pos++;
            if (pos == colors.length) {
                rolledOver = true;
                // handle the break separately here in order to simplify the condition for each step
                if (colors[0] != colors[colors.length - 1]) length++;
                else break; // no need to do the rest, we already processed that since the chain starts and ends with the same color
                if (length >= k) result++;
                pos = 1;
            }
        }
        return result;
    }
}
