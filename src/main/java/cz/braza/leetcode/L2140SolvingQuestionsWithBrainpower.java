package cz.braza.leetcode;

/*
Daily 1.4.2025 - https://leetcode.com/problems/solving-questions-with-brainpower/
2140. Solving Questions With Brainpower
Solved
Medium
Topics
Companies
Hint
You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.

For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
Return the maximum points you can earn for the exam.



Example 1:

Input: questions = [[3,2],[4,3],[4,4],[2,5]]
Output: 5
Explanation: The maximum points can be earned by solving questions 0 and 3.
- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
- Unable to solve questions 1 and 2
- Solve question 3: Earn 2 points
Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
Example 2:

Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
Output: 7
Explanation: The maximum points can be earned by solving questions 1 and 4.
- Skip question 0
- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
- Unable to solve questions 2 and 3
- Solve question 4: Earn 5 points
Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.


Constraints:

1 <= questions.length <= 10^5
questions[i].length == 2
1 <= pointsi, brainpoweri <= 10^5
 */
public class L2140SolvingQuestionsWithBrainpower {
    /*
    One of the easiest DP problems, 1D array is enough, where dp[x] is the
    best we can do if we start at this pos, so returning getDp(0, ...) will
    eventually get us the answer.
    Runs 10ms, beats 42.9%, 54 testcases, majority runs 5ms
     */
    public static long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length];
        //long result = getDp(0, dp, questions);
        //System.out.println(Arrays.toString(dp));
        //return result;
        return getDp(0, dp, questions);
    }

    public static long getDp(int pos, long[] dp, int[][] q) {
        if (pos >= dp.length) return 0;
        if (dp[pos] > 0) return dp[pos];
        long solved = q[pos][0] + getDp(pos + q[pos][1] + 1, dp, q);
        long unsolved = getDp(pos + 1, dp, q);
        //dp[pos] = solved > unsolved ? solved : unsolved;
        dp[pos] = unsolved;
        if (solved > unsolved) {
            dp[pos] = solved;
            System.out.printf("Solving question %d for %d points, skipping %d, max from here is %d%n", pos, q[pos][0], q[pos][1], dp[pos]);
        }
        return dp[pos];
    }

    public static void main(String[] args) {
        int[][] questions = {{21, 5}, {92, 3}, {74, 2}, {39, 4}, {58, 2}, {5, 5}, {49, 4}, {65, 3}};
        System.out.println(mostPoints(questions));
    }
}
