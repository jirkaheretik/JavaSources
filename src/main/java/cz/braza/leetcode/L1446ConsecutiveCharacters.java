package cz.braza.leetcode;

/*
1446. Consecutive Characters
Easy
Topics
Companies
Hint
The power of the string is the maximum length of a non-empty substring that contains only one unique character.

Given a string s, return the power of s.



Example 1:

Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.
Example 2:

Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.


Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters.
 */
public class L1446ConsecutiveCharacters {
    public int maxPower(String s) {
        int maxPower = 0;
        int currentPower = 0;
        char lastChar = 'ň';
        for (char c: s.toCharArray())
            if (c == lastChar) {
                currentPower++;
                if (currentPower > maxPower)
                    maxPower = currentPower;
            } else {
                lastChar = c;
                currentPower = 1;
            }
        return maxPower > 0 ? maxPower : currentPower;
    }
}
