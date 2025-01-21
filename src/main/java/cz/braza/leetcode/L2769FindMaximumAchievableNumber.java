package cz.braza.leetcode;

/*
2769. Find the Maximum Achievable Number
Solved
Easy
Topics
Companies
Hint
Given two integers, num and t. A number x is achievable if it can become equal to num after applying the following operation at most t times:

Increase or decrease x by 1, and simultaneously increase or decrease num by 1.
Return the maximum possible value of x.



Example 1:

Input: num = 4, t = 1

Output: 6

Explanation:

Apply the following operation once to make the maximum achievable number equal to num:

Decrease the maximum achievable number by 1, and increase num by 1.
Example 2:

Input: num = 3, t = 2

Output: 7

Explanation:

Apply the following operation twice to make the maximum achievable number equal to num:

Decrease the maximum achievable number by 1, and increase num by 1.


Constraints:

1 <= num, t <= 50
*/
class L2769FindMaximumAchievableNumber {
    /*
    Nobrainer - in each steps (there are `t` of them) we can decrease our number and increase `num`, which means we can achieve up to `num + 2 * t`, thus return this.

    Runs 1ms, beats 100%, 2502 testcases
    */
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }
}
