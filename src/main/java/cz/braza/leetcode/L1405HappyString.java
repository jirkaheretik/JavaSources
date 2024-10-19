package cz.braza.leetcode;

/*
1405. Longest Happy String
Medium
Topics
Companies
Hint
A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class L1405HappyString {
    public String longestDiverseStringSolution(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int endA = 0, endB = 0, endC = 0;
        int maxLength = a + b + c;
        int stringLength = 0;
        while (stringLength < maxLength) {
            if (endA < 2 && a >= b && a >= c || a > 0 && (endB == 2 || endC == 2)) {
                sb.append('a');
                endA++;
                endB = 0;
                endC = 0;
                a--;
            } else if (endB < 2 && b >= a && b >= c || b > 0 && (endA == 2 || endC == 2)) {
                sb.append('b');
                endB++;
                endA = 0;
                endC = 0;
                b--;
            } else if (endC < 2 && c >= a && c >= b || c > 0 && (endA == 2 || endB == 2)) {
                sb.append('c');
                endC++;
                endA = 0;
                endB = 0;
                c--;
            }
            stringLength++;
        }
        return sb.toString();
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        String result = createLongestString(sb, a, b, c);
        return result; //TODO!
    }

    public static String createLongestString(StringBuilder sb, int a, int b, int c) {

        if (a >= b && a >= c && a > 0 && addLetter(sb, 'a'))
            return createLongestString(sb, a - 1, b, c);
        else if (b >= a && b >= c && b > 0 && addLetter(sb, 'b'))
            return createLongestString(sb, a, b - 1, c);
        else if (c >= b && c >= a && c > 0 && addLetter(sb, 'c'))
            return createLongestString(sb, a, b, c - 1);
        return sb.toString();
    }

    public static boolean addLetter(StringBuilder sb, char letter) {
        String watchFor = "" + letter + letter + letter;
        for (int pos = 0; pos < sb.length(); pos++) {
            sb.insert(pos, letter);
            if (sb.indexOf(watchFor) < 0)
                return true;
            sb.deleteCharAt(pos);
        }
        return false;
    }
}
