package cz.braza.leetcode;

import java.util.Arrays;

/*
44. Wildcard Matching
Hard
Topics
Companies
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 */
// See L10 for an "inspiration"/rework
public class L44WildcardMatching {
    // solves 1752/1811 test cases, then TLE
    public static boolean isMatchIncomplete(String s, String p) {
        if (p.contains("**"))
            return isMatch(s, p.replace("**", "*"));
        String longest = findLongestStaticString(p);
        int longestPos = s.indexOf(longest);
        if (longestPos < 0) return false;
        if (longest.length() > 3) {
            int pPos = p.indexOf(longest);
            String pFront = p.substring(0, pPos);
            String pBack = p.substring(pPos + longest.length());
            do {
                boolean result = isMatch(s.substring(0, longestPos), pFront)
                        &&
                        isMatch(s.substring(longestPos + longest.length()), pBack);
                if (result) return true;
                System.out.println("Failed for pos " + longestPos);
                longestPos = s.indexOf(longest, longestPos + 1);
            } while (longestPos > 0);
            return false;
        }
        int currentPos = 0;
        for (int pPos = 0; pPos < p.length(); pPos++) {
            char toMatch = p.charAt(pPos);
            if (toMatch == '*')
                    return (currentPos < s.length() && isMatch(s.substring(currentPos + 1), p.substring(pPos)))
                            || (pPos < p.length() && isMatch(s.substring(currentPos), p.substring(pPos + 1)));
            else
            if (currentPos >= s.length() || (toMatch != '?' && s.charAt(currentPos) != toMatch))
                return false;
            else
                currentPos++;
        }
        return currentPos >= s.length();
    }

    public static String findLongestStaticString(String s) {
        String longest = "";
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray())
            if (c != '*' && c != '?')
                sb.append(c);
            else {
                if (sb.length() > longest.length())
                    longest = sb.toString();
                sb.setLength(0);
            }
        if (sb.length() > longest.length())
            longest = sb.toString();
        return longest;
    }


    /*
    Solution from the web
    Basically, we create an m x n array to store intermediate results and a couple of recursive functions

    First run 14ms
    Removed ** before anything else, now it runs 13ms, beating 87.1%
    String to char[]: 11ms, beats 88.8%
    Removed filling the array with -1s - now 12ms, beats 88.1%
     */
    public static boolean isMatch(String s, String p) {
        if (p.contains("**"))
            return isMatch(s, p.replace("**", "*"));
        return wildcardMatching(s.toCharArray(), p.toCharArray()) == 1;
    }

    private static int wildcardMatchingUtil(char[] s, char[] p, int sLimit, int pLimit, int[][] dp) {
        // first get rid of "zero" cases (we cant store them anyway):
        if (sLimit < 0 && pLimit < 0)
            return 1;
        if (pLimit < 0)
            return 0;
        if (sLimit < 0)
            // if a star (there cannot be more), then ok, otherwise fail
            return pLimit == 0 && p[0] == '*' ? 1 : -1;
        // do we know this result already?
        if (dp[sLimit][pLimit] != 0) return dp[sLimit][pLimit];

        if (s[sLimit] == p[pLimit] || p[pLimit] == '?')
            // if match, continue with next char
            return dp[sLimit][pLimit] = wildcardMatchingUtil(s, p, sLimit - 1, pLimit - 1, dp);
        else
            if (p[pLimit] == '*')
                // star either matches (remove char from source) or not (remove star from pattern)
                return dp[sLimit][pLimit] = (wildcardMatchingUtil(s, p, sLimit - 1, pLimit, dp) == 1 || wildcardMatchingUtil(s, p, sLimit, pLimit - 1, dp) == 1) ? 1 : -1;
            else
                return dp[sLimit][pLimit] = -1;
    }

    private static int wildcardMatching(char[] s, char[] p) {
        // intermediate results for anything we find out during recursion, 1 ok, -1 mismatch, 0 prefilled
        int[][] dp = new int[s.length][p.length];
        return wildcardMatchingUtil(s, p, s.length - 1, p.length - 1, dp);
    }

    public static void main(String[] args) {
        /*
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aaa", "ab*a*c*a"));
        System.out.println(isMatch("a", "ab*a"));
        System.out.println(isMatch("bbbba", ".*a*a"));

         */
        System.out.println(isMatch("abbbaaababbaaabaaabbbabbbbaaabbaaababaabbbbbbaababbabababbababaaabbbbbabababaababaaaaaaabbbaabaabbbaabbabaababbabaababbbabbaaabbbaaaababbaaabbaabaabbbbbaaababaabaabaaabbabaabbbabbbaabbababaabbbbbbbbaaa", "*ba***bba*b**abbaa***a*****b*a*bb*b***a*bbb***a***bba*****a****a*a*b**aaaba*aab*a*aa***a*a*b**b**a*b*"));
        //System.out.println(isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*b*aba*babaa*bbaba*a*aaba*b*aa*a*b*ba*a*a*"));
        //System.out.println(findLongestStaticString("*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
        //System.out.println(findLongestStaticString("**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
        System.out.println(isMatch("abcdefghijk", "abc*defghijk"));
        System.out.println(isMatch("baaaa", "*aaa"));
        System.out.println(isMatch("aaabbbbaaaabbabbbbaabbabaaababaababaaaaaaabaaababbaababbaababbbaaaaabaabbabbaabaababbaabababbbbbbbbabbaabbaaabaababaabaababababababbbbbbabbabbaabbaabaaaaaababaabbbaaabaaabbbbbbbbbaabaabaaabaaabbabbabb", "****a*b*b**b*bbb*b**bba***b**b*b*b**ba***b*b*a*b*b*****a**aaa*baaa*ba*****a****ba*a****a**b*******a*a"));
    }

}
