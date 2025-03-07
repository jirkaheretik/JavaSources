package cz.braza.leetcode;

/*
1769. Minimum Number of Operations to Move All Balls to Each Box
Medium
Topics
Companies
Hint
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.



Example 1:

Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
Example 2:

Input: boxes = "001011"
Output: [11,8,5,4,3,4]


Constraints:

n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
 */
public class L1769MoveBallsToEachBox {
    /*
    Go from left to right, updating answers with "how many moves to move balls before to this box",
    then go from right to left and do the same, return answers.
    Runs 4ms, beats 76.6%, 95 testcases
     */
    public int[] minOperations(String boxes) {
        int[] answer = new int[boxes.length()];
        int balls = 0;
        int moves = 0;
        // go from left:
        for (int box = 0; box < boxes.length(); box++) {
            moves += balls;
            answer[box] += moves;
            if (boxes.charAt(box) == '1') balls++;
        }
        // now from right:
        moves = 0;
        balls = 0;
        for (int box = boxes.length() - 1; box >= 0; box--) {
            moves += balls;
            answer[box] += moves;
            if (boxes.charAt(box) == '1') balls++;
        }
        return answer;
    }

    /*
    Create array of boolean (is there a ball or not) in order NOT to traverse again the string char by char
    Runs 3ms, beats 94.8%
     */
    public int[] minOperationsBool(String boxes) {
        int[] answer = new int[boxes.length()];
        boolean[] ballPos = new boolean[boxes.length()];
        int balls = 0;
        int moves = 0;
        // go from left:
        for (int box = 0; box < boxes.length(); box++) {
            moves += balls;
            answer[box] += moves;
            if (boxes.charAt(box) == '1') {
                balls++;
                ballPos[box] = true;
            }
        }
        // now from right:
        moves = 0;
        balls = 0;
        for (int box = boxes.length() - 1; box >= 0; box--) {
            moves += balls;
            answer[box] += moves;
            if (ballPos[box]) balls++;
        }
        return answer;
    }
}
