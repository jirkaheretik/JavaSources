package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 20.2.2025
SHOW: progress, thinking, random?, specific conditions

1980. Find Unique Binary String
Medium
Topics
Companies
Hint
Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.



Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.


Constraints:

n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
 */
public class L1980FindUniqueBinaryString {
    /*
     * The idea here is to create a boolean array where each index represents a binary string of length n.
     *
     * Runs 6ms, beats 21.3%, 184 testcases
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        boolean[] found = new boolean[1 << n];
        for (String num : nums)
            found[Integer.parseInt(num, 2)] = true;

        for (int idx = found.length - 1; idx >= 0; idx--)
            if (!found[idx]) {
                String result = Integer.toBinaryString(idx);
                if (result.length() == n) return result;
                else return String.format("%" + n + "s", result).replace(' ', '0');
            }
        return "000"; // never gonna happen
    }

    /*
    Unfinished. First I'd try to generate random numbers in the upper half (since they have 1 at the start),
    but for smaller n (1, 2) that space can be full and we need to return smaller number
     */
    public String findDifferentBinaryStringMonteCarlo(String[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0].equals("0") ? "1" : "0";
        if (n == 2) {

        }
        Arrays.sort(nums);


        // return String.format("%" + n + "s", result).replace(' ', '0');
        return "000"; // never gonna happen
    }

    /*
    Very simple idea - we have n strings of length n, therefore generate a string that in i-th pos differs from ith string.
    This has to be a valid answer.
    Runs 0ms, beats 100%
     */
    public String findDifferentBinaryStringEachPos(String[] nums) {
        StringBuilder sb = new StringBuilder(nums[0].length());
        for (int i = 0; i < nums.length; i++) sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        return sb.toString();
    }

    /*
    Same as above but using char array instead of StringBuilder
    Runs 0ms, beats 100%
     */
    public String findDifferentBinaryStringEachPosArr(String[] nums) {
        char[] arr = new char[nums.length];
        for (int i = 0; i < nums.length; i++) arr[i] = nums[i].charAt(i) == '0' ? '1' : '0';
        return new String(arr);
    }
}