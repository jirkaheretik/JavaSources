package cz.braza.leetcode;

/*
1106. Parsing A Boolean Expression
Hard
Topics
Companies
Hint
A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:

't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, return the evaluation of that expression.

It is guaranteed that the given expression is valid and follows the given rules.



Example 1:

Input: expression = "&(|(f))"
Output: false
Explanation:
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.
Example 2:

Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.
Example 3:

Input: expression = "!(&(f,t))"
Output: true
Explanation:
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.


Constraints:

1 <= expression.length <= 2 * 104
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
 */
public class L1106ParsingBooleanExpression {
    // 1. verze pracuje se Stringem + charAt(), 3ms, beats 70%, beats 92 % in memory
    // 2. verze pracuje s toCharArray(), tj. všude pak char[], 2ms, beats 81.6 %, but 60 % in memory
    public static boolean parseBoolExpr(String expression) {
        return parse(expression.toCharArray(), 0, expression.length() - 1);
    }

    public static boolean parse(char[] s, int start, int end) {
        switch (s[start]) {
            case 't': return true;
            case 'f': return false;
            case '&':
                return parse_and(s, start + 2, end - 1);
            case '|':
                return parse_or(s, start + 2, end - 1);
            case '!':
                return parse_not(s, start + 2, end - 1);
            default:
                System.out.println("ERR unknown character " + s[start] + " at pos " + start);
                return false;
        }
    }

    public static boolean parse_not(char[] s, int start, int end) {
        return !parse(s, start, end);
    }

    public static boolean parse_or(char[] s, int start, int end) {
        boolean result = false;
        int lastPos = start;
        do {
            int commaPos = findCommaAtThisLevel(s, lastPos, end);
            result |= parse(s, lastPos, commaPos - 1);
            lastPos = commaPos + 1;
        } while (lastPos <= end);
        return result;
    }

    public static boolean parse_and(char[] s, int start, int end) {
        boolean result = true;
        int lastPos = start;
        do {
            int commaPos = findCommaAtThisLevel(s, lastPos, end);
            result &= parse(s, lastPos, commaPos - 1);
            lastPos = commaPos + 1;
        } while (lastPos <= end);
        return result;
    }

    public static int findCommaAtThisLevel(char[] s, int start, int end) {
        int braceCount = 0;
        for (int pos = start; pos < end; pos++) {
            char c = s[pos];
            if (c == '(') braceCount++;
            else if (c == ')') braceCount--;
            else if (c == ',' && braceCount == 0) return pos;
        }
        return end + 1;
    }

    public static void main(String[] args) {
        System.out.println(parseBoolExpr("!(&(!(&(f)),&(t),|(f,f,t)))"));
    }
}
