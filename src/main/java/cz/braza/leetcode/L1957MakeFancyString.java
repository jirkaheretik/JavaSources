package cz.braza.leetcode;

/*
1957. Delete Characters to Make Fancy String
Easy
Topics
Companies
Hint
A fancy string is a string where no three consecutive characters are equal.

Given a string s, delete the minimum possible number of characters from s to make it fancy.

Return the final string after the deletion. It can be shown that the answer will always be unique.



Example 1:

Input: s = "leeetcode"
Output: "leetcode"
Explanation:
Remove an 'e' from the first group of 'e's to create "leetcode".
No three consecutive characters are equal, so return "leetcode".
Example 2:

Input: s = "aaabaaaa"
Output: "aabaa"
Explanation:
Remove an 'a' from the first group of 'a's to create "aabaaaa".
Remove two 'a's from the second group of 'a's to create "aabaa".
No three consecutive characters are equal, so return "aabaa".
Example 3:

Input: s = "aab"
Output: "aab"
Explanation: No three consecutive characters are equal, so return "aab".


Constraints:

1 <= s.length <= 105
s consists only of lowercase English letters.
 */
public class L1957MakeFancyString {
    /*
    First try, straightforward, work with array of chars and stringbuilder to
    add to the result.
    Runs 24ms, beats 100% (85k submissions)
     */
    public static String makeFancyString(String s) {
        StringBuilder res = new StringBuilder();
        char lastChar = '|'; // lowercase letters only, so whatever works
        int lastCharCount = 0;
        for (char c: s.toCharArray()) {
            if (c != lastChar) {
                lastChar = c;
                lastCharCount = 0;
            }
            lastCharCount++;
            if (lastCharCount < 3)
                res.append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(makeFancyString("leeetcoooode"));
    }
}
