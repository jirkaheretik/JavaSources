package cz.braza.leetcode;

/*
10. Regular Expression Matching
Hard
Topics
Companies
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

 */
public class L10RegularExpressionMatching {
    /*
    First try, works in most normal case, but fails at guessing game, that is
    if it is not clear whether or not to match another char.
    Currently at 292/354 testcases.
     */
    public static boolean isMatchNotEnough(String s, String p) {
        int currentPos = 0;
        for (int pPos = 0; pPos < p.length(); pPos++) {
            char toMatch = p.charAt(pPos);
            boolean hasStar = pPos < p.length() - 1 && p.charAt(pPos + 1) == '*';
            // case .*
            if (toMatch == '.' && hasStar) {
                String rest = p.substring(pPos + 2);
                System.out.println(rest);
                if (rest.isEmpty()) return true;
                else return s.lastIndexOf(rest) + rest.length() >= s.length();
                // return true; // can I do that or will patterns like '.*a' appear? Turns out we cannot :-(
            }
            // case .
            if (toMatch == '.') currentPos++;
            else {
                // toMatch is a char
                // deal with * first:
                if (hasStar) {
                    int lastPos = currentPos;
                    while (currentPos < s.length() && toMatch == s.charAt(currentPos))
                        currentPos++;
                    pPos++; // skip the *
                    // maybe we were too greedy?
                    if (currentPos >= s.length() && pPos < p.length()) {
                        String rest = p.substring(pPos + 1);
                        System.out.println(rest);
                        if (rest.isEmpty()) return true;
                        else if (lastPos >= s.length()) // we already were at the end of a string, now something in the pattern
                            return false;
                        else
                            return isMatch(s.substring(s.length() - 1), rest);
                                //s.lastIndexOf(rest) + rest.length() >= s.length();
                    }
                } else
                    if (currentPos >= s.length() || toMatch != s.charAt(currentPos))
                            // bail out
                            return false;
                    else
                        // found a match
                        currentPos++;
            }
        }
        return currentPos >= s.length();
    }

    /*
    Finally succeeded with this recursive approach
    Runs 1031ms, beats 5.3 % :-(
     */
    public static boolean isMatch(String s, String p) {
        int currentPos = 0;
        for (int pPos = 0; pPos < p.length(); pPos++) {
            char toMatch = p.charAt(pPos);
            boolean hasStar = pPos < p.length() - 1 && p.charAt(pPos + 1) == '*';
            if (hasStar)
                if (toMatch == '.' || (currentPos < s.length() && s.charAt(currentPos) == toMatch)) {
                    /* // debugging (do we have a match or not?):
                    String current = s.substring(currentPos);
                    String skipped = currentPos >= s.length() ? "" : s.substring(currentPos + 1);
                    String pCurrent = p.substring(pPos);
                    String pSkipped = pPos + 1 >= p.length() ? "" : p.substring(pPos + 2);
                    boolean skipLet = currentPos < s.length() && isMatchRec(skipped, pCurrent);
                    boolean letSkip = pPos + 1 < p.length() && isMatchRec(current, pSkipped);
                    System.out.println("DBG:\nCurrent: " + current + " next pattern: " + pSkipped + " has result " + letSkip + "\nand skiped: " + skipped + " and current pattern " + pCurrent + " has result " + skipLet);
                    */
                    // char matches:
                    return (currentPos < s.length() && isMatch(s.substring(currentPos + 1), p.substring(pPos)))
                            || (pPos + 1 < p.length() && isMatch(s.substring(currentPos), p.substring(pPos + 2)));
                } else
                    // drop the char+star, go for next
                    pPos++;
            else
                if (currentPos >= s.length() || (toMatch != '.' && s.charAt(currentPos) != toMatch))
                    return false;
                else
                    currentPos++;
        }
        return currentPos >= s.length();
    }

    public static void main(String[] args) {
        /*
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aaa", "ab*a*c*a"));
        System.out.println(isMatch("a", "ab*a"));
        System.out.println(isMatch("bbbba", ".*a*a"));

         */
        System.out.println(isMatch("mississippi", "mis*is*ip*."));
    }
}
