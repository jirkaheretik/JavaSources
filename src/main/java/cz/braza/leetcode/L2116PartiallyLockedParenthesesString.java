package cz.braza.leetcode;

/*
2116. Check if a Parentheses String Can Be Valid
Medium
Topics
Companies
Hint
A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,

If locked[i] is '1', you cannot change s[i].
But if locked[i] is '0', you can change s[i] to either '(' or ')'.
Return true if you can make s a valid parentheses string. Otherwise, return false.



Example 1:


Input: s = "))()))", locked = "010100"
Output: true
Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
Example 2:

Input: s = "()()", locked = "0000"
Output: true
Explanation: We do not need to make any changes because s is already valid.
Example 3:

Input: s = ")", locked = "0"
Output: false
Explanation: locked permits us to change s[0].
Changing s[0] to either '(' or ')' will not make s valid.


Constraints:

n == s.length == locked.length
1 <= n <= 10^5
s[i] is either '(' or ')'.
locked[i] is either '0' or '1'.
 */
public class L2116PartiallyLockedParenthesesString {
// 240/258 TC
    public static boolean canBeValid(String s, String locked) {
        if (s.length() % 2 == 1) return false;
        int freeCount = 0;
        int unsatisfiedLeft = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            if (locked.charAt(idx) == '1')
                if (s.charAt(idx) == '(')
                    unsatisfiedLeft++;
                else // closing bracket
                    if (unsatisfiedLeft > 0)
                        unsatisfiedLeft--;
                    else if (freeCount > 0)
                        freeCount--;
                    else {
                        System.out.println("Closing bracket found but no free pos before at index " + idx);
                        return false;
                    }
            else // free position, do not care about the bracket
                if (unsatisfiedLeft > 0)
                    unsatisfiedLeft--;
                else freeCount++;
            System.out.printf("Pos %d, free: %d, unsatisfied: %d, current lock: %c, current char: %c%n", idx, freeCount, unsatisfiedLeft, locked.charAt(idx), s.charAt(idx));
        }
        System.out.println("Got to the end with " + unsatisfiedLeft + " brackets.");
        return unsatisfiedLeft == 0;
    }

    public static void main(String[] args) {
        // expected true
        System.out.println(canBeValid3("((()(()()))()((()()))))()((()(()", "10111100100101001110100010001001"));
        // expected false
        System.out.println(canBeValid3("())(()(()(())()())(())((())(()())((())))))(((((((())(()))))(", "100011110110011011010111100111011101111110000101001101001111"));
    }

    // failing at 207/258
    public static boolean canBeValid2(String s, String locked) {
        if (s.length() % 2 == 1) return false;
        int freeCount = 0;
        int unsatisfiedLeft = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            if (locked.charAt(idx) == '1')
                if (s.charAt(idx) == '(')
                    unsatisfiedLeft++;
                else // closing bracket
                    if (unsatisfiedLeft > 0)
                        unsatisfiedLeft--;
                    else if (freeCount > 0)
                        freeCount--;
                    else {
                        System.out.println("Closing bracket found but no free pos before at index " + idx);
                        return false;
                    }
            else // free position, do not care about the bracket
                freeCount++;
            System.out.printf("Pos %d, free: %d, unsatisfied: %d, current lock: %c, current char: %c%n", idx, freeCount, unsatisfiedLeft, locked.charAt(idx), s.charAt(idx));
        }
        System.out.println("Got to the end with " + unsatisfiedLeft + " brackets.");
        return unsatisfiedLeft <= freeCount;
    }

    /*
    Check parentheses both ways, in order to cope with edge cases.
    Still runs quite ok:
    Runs 15ms, beats 83.4%, 258 testcases
     */
    public static boolean canBeValid3(String s, String locked) {
        if (s.length() % 2 == 1) return false;
        int freeCount = 0;
        int unsatisfiedLeft = 0;
        for (int idx = 0; idx < s.length(); idx++)
            if (locked.charAt(idx) == '1')
                if (s.charAt(idx) == '(') unsatisfiedLeft++;
                else // closing bracket
                    if (unsatisfiedLeft > 0) unsatisfiedLeft--;
                    else if (freeCount > 0) freeCount--;
                    else return false;
            // free position, do not care about the bracket
            else freeCount++;
        // now go back:
        freeCount = 0;
        unsatisfiedLeft = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--)
            if (locked.charAt(idx) == '1')
                // changed open/close direction
                if (s.charAt(idx) == ')') unsatisfiedLeft++;
                else // closing bracket
                    if (unsatisfiedLeft > 0) unsatisfiedLeft--;
                    else if (freeCount > 0) freeCount--;
                    else return false;
            // free position, do not care about the bracket
            else freeCount++;
        return unsatisfiedLeft <= freeCount;
    }

    /*
    Since we are checking the unsatisfied opening brackets (from either end) separately,
    we do not need to bother with them at all, and greatly simplify the code.
    Still runs 15ms, beating 83.4% (no gain at all)
     */
    public boolean canBeValid4(String s, String locked) {
        if (s.length() % 2 == 1) return false;
        int freeCount = 0;
        for (int idx = 0; idx < s.length(); idx++)
            if (locked.charAt(idx) == '1' && s.charAt(idx) == ')')
                if (freeCount > 0) freeCount--;
                else return false;
                // free position, do not care about the bracket
            else freeCount++;
        // now go back:
        freeCount = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--)
            if (locked.charAt(idx) == '1' && s.charAt(idx) == '(')
                if (freeCount > 0) freeCount--;
                else return false;
                // free position, do not care about the bracket
            else freeCount++;
        return true;
    }

}
