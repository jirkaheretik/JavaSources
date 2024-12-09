package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/*
773. Sliding Puzzle
Hard
Topics
Companies
Hint
On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.



Example 1:


Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Example 2:


Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Example 3:


Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]


Constraints:

board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
 */
public class L773SlidingPuzzle {
    public static final int MAX = 6;
    public static final int MAXR = 1;
    public static final int MAXC = 2;
    public static final int[][] SOLVED = {{1, 2, 3}, {4, 5, 0}};
    public static final int SOLVEDI = 11190;

    public static final int[][] MOVES = {
            {11190 },
            {10545, 11165, },
            {8385, 11045, 8585, },
            {1905, 11825, 14835, 3305, 2105, },
            {32865, 14850, 12900, 16265, 33065, 14715, },
            {21425, 19505, 14420, 33125, 33015, 12890, 6975, },
            {12770, 21395, 20580, 33030, 21450, 31835, 9020, 31725, 33150, 39375, },
            {20560, 35075, 20805, 5030, 44535, 32600, 32505, 5915, 2540, 5805, 33885, 41535, },
            {31520, 31425, 16690, 20530, 34530, 42180, 16485, 36150, 24470, 44550, 44505, 33500, },
            {5600, 5505, 3525, 22090, 29870, 42160, 36145, 3730, 37875, 33590, 22935, 44120, 5050, 34525, 35390, 29630, },
            {33600, 29600, 11265, 37890, 22950, 34375, 35240, 35400, 29640, 31945, 24490, 44300, 32300, 11470, 42130, 32275, 36085, 37815, 39800, 20985, 22905, 30300, 39580, },
            {7425, 29890, 37700, 42820, 5125, 6855, 30280, 6025, 7180, 32525, 21135, 6355, 28565, 43670, 11415, 3415, 44390, 3430, 11560, 7400, 37675, 6380, 35185, 25780, 4280, },
            {8835, 16390, 14920, 25800, 21835, 39825, 8980, 31445, 23830, 39255, 30750, 21150, 43680, 15140, 11430, 15975, 23720, 30250, 44400, 38130, 24565, 44725, 24245, 16375, 43640, 25720, 11580, 39230, },
            {14980, 2500, 4805, 25990, 20935, 3015, 27145, 26825, 10505, 10570, 4940, 29965, 40335, 17680, 24725, 5525, 30745, 44185, 30170, 41115, 17055, 19615, 24800, 13155, 21925, 4390, 34025, 22825, 41835, 14380, 38125, 19630, 6830, 44145, 44785, 2355, 7030, 40310, 15230, },
            {12545, 34050, 39430, 15240, 28745, 39625, 20490, 8780, 27150, 25230, 33935, 27025, 8530, 38035, 40980, 15000, 8345, 8410, 23645, 30685, 41055, 26850, 30050, 12130, 30180, 17700, 40740, 16615, 24295, 13800, 41130, 21930, 26795, 22830, 22705, 41715, 19830, 44790, 39865, 17530, 41850, 31675, 24380, 16765, },
            {13505, 2050, 28805, 20485, 25670, 3655, 28615, 1865, 40970, 1930, 42445, 40270, 40720, 14355, 22165, 26075, 28700, 3805, 19805, 13790, 2975, 7075, 41635, 7465, 22185, 33835, 13100, 4205, 42865, 25205, 41590, 4855, 20855, 3575, 12280, 7225, 11770, 5755, 2300, 12605, },
            {39680, 13640, 43080, 29130, 12300, 25680, 16785, 40850, 42450, 39190, 12630, 16535, 13530, 28830, 8735, 19685, 13415, 23915, 22380, 20335, 28975, 25520, 42355, 39475, 40630, 8630, 8955, 25085, 15935, },
            {17825, 14115, 29125, 2150, 6790, 42920, 5675, 2475, 5900, 2255, 7280, 3825, 43345, 43060, 24820, 17015, 22360, 18075, 4475, 11870, 13055, },
            {38240, 34785, 17955, 15910, 17735, 14055, 18090, 31595, 35435, 31820, 14130, 43350, 43000, 43225, 17850, 23740, 23965, 22270, },
            {26320, 35060, 2950, 35465, 6315, 33755, 4300, 4525, 26125, 38270, 16990, 34815, },
            {35490, 26340, 38280, 26170, 32235, 34830, },
            {37635, }
    };

    /*
    Uses "prepared" 2D array with combinations in array where its index is the number of moves needed (-1 if nothing is found)
    Runs 0ms, beats 100%
     */
    public int slidingPuzzlePrepared(int[][] board) {
        int comb = combination(board);
        for (int moves = 0; moves < MOVES.length; moves++)
            for (int move: MOVES[moves])
                if (move == comb) return moves;
        return -1;
    }

    /*
    Copy the process from printAll and adapt for the solution
    BFS for possible combinations, return number of steps if found.
    Runs 10ms, beats 53.4%
     */
    public int slidingPuzzle(int[][] board) {
        int target = combination(board);
        if (target == SOLVEDI) return 0;
        HashSet<Integer> done = new HashSet<>();
        HashSet<Integer> queue = new HashSet<>();
        queue.add(SOLVEDI);
        int step = 0;
        while (queue.size() > 0) {
            HashSet<Integer> nextStep = new HashSet<>();
            step++;
            for (int element: queue) {
                // add it to the done-set
                done.add(element);
                // get possible moves
                int[] moves = moves(element);
                // traverse the moves and all that arent in done or queue add to queue
                for (int move : moves)
                    if (move == target) return step;
                    else if (!done.contains(move) && !queue.contains(move)) nextStep.add(move);
            }
            queue = nextStep;
        }
        return -1;
    }

    private static int combination(int[][] board) {
        final int MAX = 6;
        int result = 0;
        for (int[] row: board)
            for (int cell: row)
                result = MAX * result + cell;
        return result;
    }

    private static int[][] board(int combination) {
        int[][] result = new int[MAXR + 1][MAXC + 1];
        int row = MAXR;
        int col = MAXC;
        while (combination > 0) {
            result[row][col] = combination % MAX;
            combination /= MAX;
            col--;
            if (col < 0) {
                col = MAXC;
                row--;
            }
        }
        return result;
    }

    /*
    From a given combination (int) returns a list of possible moves
    Find out 0 tile position, then create those 2 (edges) or 3 moves
     */
    public static int[] moves(int combination) {
        int[][] board = board(combination);
        int[] result;
        int current = 0;
        int zeroRow = 0;
        int zeroCol = 0;
        for (int r = 0; r <= MAXR; r++)
            for (int c = 0; c <= MAXC; c++)
                if (board[r][c] == 0) {
                    zeroRow = r;
                    zeroCol = c;
                }
        if (zeroCol == 1)
            result = new int[3];
        else
            result = new int[2];
        // switch rows (that I can do always):
        int[][] b0 = cloneBoard(board);
        b0[zeroRow][zeroCol] = board[MAXR - zeroRow][zeroCol];
        b0[MAXR - zeroRow][zeroCol] = 0;
        result[current++] = combination(b0);
        // can i go left?
        if (zeroCol > 0) {
            b0 = cloneBoard(board);
            b0[zeroRow][zeroCol - 1] = 0;
            b0[zeroRow][zeroCol] = board[zeroRow][zeroCol - 1];
            result[current++] = combination(b0);
        }
        // can i go right?
        if (zeroCol < MAXC) {
            b0 = cloneBoard(board);
            b0[zeroRow][zeroCol + 1] = 0;
            b0[zeroRow][zeroCol] = board[zeroRow][zeroCol + 1];
            result[current++] = combination(b0);
        }
        return result;
    }

    private static int[][] cloneBoard(int[][] source) {
        int[][] result = new int[source.length][];
        for (int row = 0; row < source.length; row++)
            result[row] = source[row].clone();
        return result;
    }

    /*
    Find all the combinations and number of steps needed to reach them:
     */
    public static void printAll() {
        HashSet<Integer> done = new HashSet<>();
        HashSet<Integer> queue = new HashSet<>();
        queue.add(SOLVEDI);
        int step = 0;
        while (queue.size() > 0) {
            HashSet<Integer> nextStep = new HashSet<>();
            System.out.println(step++);
            System.out.print("{");
            for (int element: queue) {
                // pick/remove element
                // step++;
                //int element = queue.iterator().next();
                //queue.remove(element);
                System.out.print(element + ", ");
                //printCombination(element);
                // add it to the done-set
                done.add(element);
                // get possible moves
                int[] moves = moves(element);
                // traverse the moves and all that arent in done or queue add to queue
                for (int move : moves)
                    if (!done.contains(move) && !queue.contains(move)) nextStep.add(move);
            }
            System.out.println("}");
            queue = nextStep;
        }
        System.out.println("Total: " + step);
    }

    public static void printCombination(int combination) {
        int[][] board = board(combination);
        String result = "" + combination + ": ";
        for (int[] row: board)
            result += Arrays.toString(row) + " ";
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[][] T1 = {{1, 2, 3}, {4, 0, 5}};
        int solved = combination(SOLVED);
        System.out.println(solved);
        System.out.println(moves(SOLVEDI).length);
        System.out.println(moves(combination(T1)).length);
        int[] moves = moves(combination(T1));
        for (int move: moves)
            printCombination(move);
        System.out.println();
        printAll();
    }
}
