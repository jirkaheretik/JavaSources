package cz.braza.leetcode;

/*
877. Stone Game
Medium
Topics
Companies
Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.



Example 1:

Input: piles = [5,3,4,5]
Output: true
Explanation:
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
Example 2:

Input: piles = [3,7,2,3]
Output: true

 */
public class L877StoneGame {
    /*
    Intuition:
    create NxN array of ints, N is piles.size
    For any i <= j, a[i][j] is how many can the player get from interval <i; j>
    For i <> j, a[j][i] is then 2nd prize
    Thus:
    a[i][j]+a[j][i] is sum piles[i..j]
    a[i][i] == piles[i]
    if a[0][size]>a[size][0] return true
    For any m-n, one can take piles[m] plus 2nd prize of <m + 1, n>, or piles[n] and 2nd prize of <m, n - 1>
    Dynamic programming will solve the rest.
     */
    public boolean stoneGame(int[] piles) {
        int[][] matrix = new int[piles.length][piles.length];
        int maxFirstCanGet = miniMax(piles, matrix, 0, piles.length - 1);
        // previous line should populate whole DP array `matrix`
        return maxFirstCanGet > matrix[piles.length - 1][0];
    }

    public static int miniMax(int[] piles, int[][] dp, int minIndex, int maxIndex) {
        if (minIndex >= maxIndex) return piles[minIndex];
        int returnVal = Math.max(piles[minIndex] + dp[minIndex][maxIndex], piles[maxIndex]);
        return 0;
    }
}
