package cz.braza.leetcode;

/*
2109. Adding Spaces to a String
Medium
Topics
Companies
Hint
You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the original string where spaces will be added. Each space should be inserted before the character at the given index.

For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C', which are at indices 5 and 9 respectively. Thus, we obtain "Enjoy Your Coffee".
Return the modified string after the spaces have been added.



Example 1:

Input: s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
Output: "Leetcode Helps Me Learn"
Explanation:
The indices 8, 13, and 15 correspond to the underlined characters in "LeetcodeHelpsMeLearn".
We then place spaces before those characters.
Example 2:

Input: s = "icodeinpython", spaces = [1,5,7,9]
Output: "i code in py thon"
Explanation:
The indices 1, 5, 7, and 9 correspond to the underlined characters in "icodeinpython".
We then place spaces before those characters.
Example 3:

Input: s = "spacing", spaces = [0,1,2,3,4,5,6]
Output: " s p a c i n g"
Explanation:
We are also able to place spaces before the first character of the string.


Constraints:

1 <= s.length <= 3 * 105
s consists only of lowercase and uppercase English letters.
1 <= spaces.length <= 3 * 105
0 <= spaces[i] <= s.length - 1
All the values of spaces are strictly increasing.
 */
public class L2109AddingSpacesToString {
    /*
    Very easy solution with a stringbuffer, processing spaces from the end (to not disturb index positions)
    and returning toString() in the end. Got TLE for last test cases with 300k length of
    string and 300k length of spaces array (thus need a space before every char),
    did a hammer for that using `s.replaceAll()`.
    Runs 983ms, beats 17.1%
     */
    public String addSpaces(String s, int[] spaces) {
        if (s.length() == spaces.length) {
            //return " " + String.join(" ", s.toCharArray());
            return s.replaceAll(".", " $0");
        }
        StringBuilder sb = new StringBuilder(s);
        for (int space = spaces.length - 1; space >= 0; space--)
            sb.insert(spaces[space], ' ');
        return sb.toString();
    }

    /*
    Due to TLE, do it from scratch instead. Adding characters one-by-one, occassionally
    with spaces (two pointer).
    Runs 23ms, beats 73.4%
    new StringBuilder(capacity) instead of new StringBuilder(); sb.ensureCapacity(capacity):
    Runs 22ms, beats 85.6%
     */
    public String addSpacesFromStart(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder(s.length() + spaces.length);
        int spIndex = 0;
        for (int index = 0; index < s.length(); index++) {
            if (spIndex < spaces.length && index == spaces[spIndex]) {
                sb.append(' ');
                spIndex++;
            }
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    /*
    What if StringBuilder.insert() is only slow if I have a String inside, so
    it needs to split it (create two new strings)? Let's try doing first approach,
    this time initializing sb not with s, but with s.toCharArray().
    Result: still painfully slow, 963ms instead of 983ms before.
     */
    public String addSpacesByChars(String s, int[] spaces) {
        if (s.length() == spaces.length) {
            //return " " + String.join(" ", s.toCharArray());
            return s.replaceAll(".", " $0");
        }
        StringBuilder sb = new StringBuilder(s.length() + spaces.length);
        sb.append(s.toCharArray());
        for (int space = spaces.length - 1; space >= 0; space--)
            sb.insert(spaces[space], ' ');
        return sb.toString();
    }
}
