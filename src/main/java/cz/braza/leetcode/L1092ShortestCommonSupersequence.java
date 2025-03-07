package cz.braza.leetcode;

/*
Daily 28.2.2025

1092. Shortest Common Supersequence
Hard
Topics
Companies
Hint
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
 */
public class L1092ShortestCommonSupersequence {
    /*
    Mostly from editorial: first we populate 2D dp table which stores
    only LENGTH of shortest common supersequence, then we reconstruct
    the result from the table (if chars at given position are the same, take
    the letter and advanced diagonally (decrease both pointers), otherwise pick the
    one that leads to shorter sequence. In the end we need to check if there are
    any surplus characters in either string (not both), and reverse the sequence built,
    since we constructed it backwards.
    Runs 22ms, beats 64.5%, 49 testcases
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int str1Length = str1.length();
        int str2Length = str2.length();

        int[][] dp = new int[str1Length + 1][str2Length + 1];

        // Initialize the base cases
        // When str2 is empty, the supersequence is str1 itself (length = row index)
        for (int row = 0; row <= str1Length; row++) dp[row][0] = row;
        // When str1 is empty, the supersequence is str2 itself (length = col index)
        for (int col = 0; col <= str2Length; col++) dp[0][col] = col;

        // Fill the DP table
        for (int row = 1; row <= str1Length; row++)
            for (int col = 1; col <= str2Length; col++)
                if (str1.charAt(row - 1) == str2.charAt(col - 1))
                    // If characters match, inherit the length from the diagonal +1
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                else
                    // If characters do not match, take the minimum length option +1
                    dp[row][col] = dp[row - 1][col] < dp[row][col - 1] ? dp[row - 1][col] + 1 : dp[row][col - 1] + 1;

        StringBuilder supersequence = new StringBuilder();
        int row = str1Length, col = str2Length;

        while (row > 0 && col > 0)
            if (str1.charAt(row - 1) == str2.charAt(col - 1)) {
                // If characters match, take it from diagonal
                supersequence.append(str1.charAt(--row));
                col--;
            } else if (dp[row - 1][col] < dp[row][col - 1])
                // If str1’s character is part of the supersequence, move up
                supersequence.append(str1.charAt(--row));
            else
                // If str2’s character is part of the supersequence, move left
                supersequence.append(str2.charAt(--col));

        // Append any remaining characters
        // If there are leftover characters in str1
        while (row > 0) supersequence.append(str1.charAt(--row));
        // If there are leftover characters in str2
        while (col > 0) supersequence.append(str2.charAt(--col));

        // Reverse the built sequence to get the correct order
        return supersequence.reverse().toString();
    }
}
