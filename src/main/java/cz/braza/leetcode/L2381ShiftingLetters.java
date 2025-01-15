package cz.braza.leetcode;

import java.util.Arrays;

/*
2381. Shifting Letters II
Medium
Topics
Companies
Hint
You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.

Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').

Return the final string after all such shifts to s are applied.



Example 1:

Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
Example 2:

Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
Output: "catz"
Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".


Constraints:

1 <= s.length, shifts.length <= 5 * 10^4
shifts[i].length == 3
0 <= starti <= endi < s.length
0 <= directioni <= 1
s consists of lowercase English letters.
 */
public class L2381ShiftingLetters {
    /*
    We create an array to hold shift count at different positions.
    For each shift, we mark +1 or -1 at the start position, and its counterpart
    at position end+1 (if that is not outside the string).
    Then we go through the array and update chars from the string,
    return the string when done.
    Runs 7ms, beats 74.5%, 39 testcases
     */
    public static String shiftingLetters(String s, int[][] shifts) {
        int[] shiftBy = new int[s.length()];
        // pre-process shifts:
        for (int[] shift: shifts) {
            int by = shift[2] == 1 ? 1 : -1;
            shiftBy[shift[0]] += by;
            // move back at position shift[1]+1, if it exists (otherwise ignore)
            if (shift[1] + 1 < shiftBy.length)
                shiftBy[shift[1] + 1] -= by;
        }
        System.out.println(Arrays.toString(shiftBy));
        char[] letters = s.toCharArray();
        int shiftCount = 0;
        for (int idx = 0; idx < shiftBy.length; idx++) {
            shiftCount += shiftBy[idx];
            shiftCount %= 26;
            if (letters[idx] + shiftCount < 'a') shiftCount += 26;
            if (letters[idx] + shiftCount > 'z') shiftCount -= 26;
            letters[idx] += shiftCount;
        }
        return new String(letters);
    }

    public static void main(String[] args) {
        System.out.println(shiftingLetters("abc", new int[][]{{0, 1, 0}, {1, 2, 1}, {0, 2, 1}}));
    }
}
