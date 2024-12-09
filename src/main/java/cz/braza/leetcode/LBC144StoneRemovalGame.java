package cz.braza.leetcode;

/*
100467. Stone Removal Game
User Accepted:13142
User Tried:14571
Total Accepted:14111
Total Submissions:37611
Difficulty:Easy
Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.

Alice starts by removing exactly 10 stones on her first turn.
For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
The player who cannot make a move loses the game.

Given a positive integer n, return true if Alice wins the game and false otherwise.



Example 1:

Input: n = 12

Output: true

Explanation:

Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
Bob cannot remove 9 stones, so Alice wins.
Example 2:

Input: n = 1

Output: false

Explanation:

Alice cannot remove 10 stones, so Alice loses.


Constraints:

1 <= n <= 50
 */
public class LBC144StoneRemovalGame {
    public static final boolean[] RES = {false, false, false, false, false, false, false, false, false, false,
            true, true, true, true, true, true, true, true, true,
            false, false, false, false, false, false, false, false,
            true, true, true, true, true, true, true,
            false, false, false, false, false, false,
            true, true, true, true, true,
            false, false, false, false,
            true, true, true, false, false, true
    };
    /*
    Fist and brute force/hard coded solution, see array above.
     */
    public static boolean canAliceWinHardCoded(int n) {
        return RES[n];
    }

    /*
    Starting with false (Alice cannot win if she cannot make a move) and 10 stones,
    we are removing stones and flipping result value.
    When number of stones drops below zero, we return the result (player cannot make a move)
     */
    public static boolean canAliceWin(int n) {
        boolean result = false;
        int num = 10;
        while (true) {
            n -= num--;
            if (n < 0) return result;
            result = !result;
        }
    }

    /*
    Same as above, though we removed the endless cycle. In each cycle we decrease number of
    stones and flip value, thus starting with true (gets flipped during first cycle),
    returning the value once we get below zero again.
     */
    public static boolean canAliceWinNoWhileTrue(int n) {
        boolean result = true;
        int num = 10;
        while (n >= 0) {
            n -= num--;
            result = !result;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < RES.length; i++) {
            if (canAliceWinHardCoded(i) != canAliceWinNoWhileTrue(i))
                System.out.println("Error for i = " + i);
        }
        System.out.println("Done.");
    }
}
