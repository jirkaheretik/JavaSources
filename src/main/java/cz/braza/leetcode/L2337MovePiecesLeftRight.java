package cz.braza.leetcode;

/*
2337. Move Pieces to Obtain a String
Medium
Topics
Companies
Hint
You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:

The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.



Example 1:

Input: start = "_L__R__R_", target = "L______RR"
Output: true
Explanation: We can obtain the string target from start by doing the following moves:
- Move the first piece one step to the left, start becomes equal to "L___R__R_".
- Move the last piece one step to the right, start becomes equal to "L___R___R".
- Move the second piece three steps to the right, start becomes equal to "L______RR".
Since it is possible to get the string target from start, we return true.
Example 2:

Input: start = "R_L_", target = "__LR"
Output: false
Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
Example 3:

Input: start = "_R", target = "R_"
Output: false
Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.


Constraints:

n == start.length == target.length
1 <= n <= 105
start and target consist of the characters 'L', 'R', and '_'.
 */
public class L2337MovePiecesLeftRight {
    public boolean canChange(String start, String target) {
        int l1count = 0, l2count = 0, r1count = 0, r2count = 0;
        for (int index = 0; index < start.length(); index++) {
            char l = start.charAt(index);
            char r = target.charAt(index);
            if (l == 'L')
                l1count++;
            else if (l == 'R')
                r1count++;
            if (r == 'L')
                l2count++;
            else if (r == 'R')
                r2count++;
            if (l1count > l2count || r2count > r1count) return false;
        }
        /*
        This concept fails for cases like "R_L_" vs "__LR" (corrent position conditions, but pieces cannot jump)
         */
        return true;
    }

    /*
    Look for a piece, compare if it is the same, and THEN position conditions.
    Runs 15ms, beats 66.5%
     */
    public static boolean canChange2ndAttempt(String start, String target) {
        char startC = 'X';
        int startPos = 0;
        char targetC = 'X';
        int targetPos = 0;
        while (startPos < start.length() || targetPos < target.length()) {
            // find next piece in start
            while (startPos < start.length() && start.charAt(startPos) == '_')
                startPos++;
            startC = startPos < start.length() ? start.charAt(startPos) : 'O';
            // find next piece in target
            while (targetPos < target.length() && target.charAt(targetPos) == '_')
                targetPos++;
            targetC = targetPos < target.length() ? target.charAt(targetPos) : 'O';
            if (startC != targetC) return false;
            if (startC == 'L' && startPos < targetPos) return false;
            if (startC == 'R' && targetPos < startPos) return false;
            startPos++;
            targetPos++;
        }
        return true;
    }

    /*
    Optimizations:
    1) save s.length() to a variable and use that - 13ms instead of 15ms
    2) save s.charAt() to a char to NOT call s.charAt() twice for every index:
    Runs 11ms, beats 100%
     */
    public static boolean canChange2ndAttemptOptimized(String start, String target) {
        char startC = 'X';
        int startPos = 0;
        char targetC = 'X';
        int targetPos = 0;
        int sLength = start.length();
        int tLength = target.length();
        while (startPos < sLength || targetPos < target.length()) {
            char c = '_';
            // find next piece in start
            while (startPos < sLength &&  (c = start.charAt(startPos)) == '_')
                startPos++;
            startC = startPos < sLength ? c : 'O';
            // find next piece in target
            while (targetPos < tLength && (c = target.charAt(targetPos)) == '_')
                targetPos++;
            targetC = targetPos < tLength ? c : 'O';
            if (startC != targetC) return false;
            if (startC == 'L' && startPos < targetPos) return false;
            if (startC == 'R' && targetPos < startPos) return false;
            startPos++;
            targetPos++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canChange2ndAttempt("__L", "_LL"));
    }
}
