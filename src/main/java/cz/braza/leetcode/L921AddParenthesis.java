package cz.braza.leetcode;

/**
 * # Minimum Add to Make Parenthesis Valid
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 * Example 2:
 *
 * Input: s = "((("
 * Output: 3
 */
public class L921AddParenthesis {
    public static int minAddToMakeValid(String s) {
        int leftCount = 0;
        int rightCount = 0;
        int insertCount = 0;
        int stringLength = s.length();
        for (int idx = 0; idx < stringLength; idx++) {
            if (s.charAt(idx) == '(')
                leftCount++;
            else
                rightCount++;
            if (rightCount > leftCount) {
                leftCount++;
                insertCount++;
            }
        }
        insertCount+= leftCount - rightCount;
        return insertCount;
    }

    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("((("));
        System.out.println(minAddToMakeValid("())"));
    }
}
