package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day06GuardGallivant {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_06.txt";
        char[][] board = AOCHelper.readFile2CharArray(fileName);
        int[] startExample = new int[]{6, 4};
        int[] startFull = new int[]{61, 78};
        int[] lukasRaska = new int[]{80, 79};
        int[] myStart = startFull;
        int row = myStart[0];
        int col = myStart[1];
        if (board[row][col] != '^') System.out.println("Bad start!");
        int stepCount = 1; // starting position counts

        // Brute force :-(
        Set<Integer> obstacles2 = new HashSet<>();
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++) {
                char former = board[r][c];
                if (former == '.') {
                    board[r][c] = '#';
                    // look for a cycle
                    if (isCycle(board, myStart[0], myStart[1], 0, -1))
                        obstacles2.add(1000 * r + c);
                    // get the char back:
                    board[r][c] = '.';
                }
            }
        System.out.println("Now part 2 obstacles count: " + obstacles2.size());


        Set<Integer> obstacles = new HashSet<>();
        int dx = 0, dy = -1;
        try {
            while (true) {
                // can I make next step?
                if (board[row + dy][col + dx] != '#') {
                    row += dy;
                    col += dx;
                    if (board[row][col] == '.') {
                        stepCount++;
                    }
                    // lets try an obstacle here:
                    char oldChar = board[row + dy][col + dx];
                    if (oldChar != '.') continue; // been there, do NOT put an obstacle
                    board[row + dy][col + dx] = '#';
                    // look for a cycle
                    if (isCycle(board, row, col, dx, dy))
                        obstacles.add(1000 * (row + dy) + col + dx);
                    // get the char back:
                    board[row + dy][col + dx] = oldChar;
                    board[row][col] = newChar(dx, dy);
                } else {
                    // turn right:
                    if (dx == 0) {
                        dx = -dy;
                        dy = 0;
                    } else {
                        dy = dx;
                        dx = 0;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException aie) {

        }
        System.out.println("Part 1: " + stepCount);
        System.out.println("Part 2: " + obstacles.size());
        if (obstacles.contains(1000 * myStart[0] + myStart[1]))
            System.out.println("Obstacles DO contain starting position.");

        // update board:
        for (int o: obstacles)
            board[o / 1000][o % 1000] = 'O';
        board[myStart[0]][myStart[1]] = 'S';
        AOCHelper.printField(board, "Výsledek");

        for (int i: obstacles2)
            if (!obstacles.contains(i))
                System.out.printf("Previous method did not detect obstacle at [%d,%d]%n", i / 1000, i % 1000);
        /*
        System.out.println("New obstacles:");
        for (int o: obstacles)
            System.out.printf("[%d, %d]%n", o / 1000, o % 1000);

         */
    }
    /*
    Failed answers:
    320 too low, as expected, as I cannot get even the examples right
    1836 too high, 1835 too high
    1767 not correct
     */

    public static char newChar(int dx, int dy) {
        // for P1:
        //return ',';

        // for P2: (obsoleted)

        if (dx == 0)
            return dy == -1 ? '^' : 'v';
        else
            return dx == -1 ? '<' : '>';


    }

    public static boolean isCycle(char[][] board, int row, int col, int dx, int dy) {
        // will we cycle if just ahead of us is a new block?
        Set<Integer> bumps = new HashSet<>();
        int sameBumps = 0;
        try {
            while (true) {
                // can I make next step?
                if (board[row + dy][col + dx] != '#') {
                    row += dy;
                    col += dx;
                } else {
                    // compute new bump:
                    int val = 1000 * row + col + 1_000_000 * (10 * (dy + 2) + dx + 2);
                    if (bumps.contains(val)) sameBumps++; // found a cycle!
                    else if (sameBumps > 0)
                        System.out.println("Unknown NEW bump after " + sameBumps + " bumps.");
                    bumps.add(val);
                    if (sameBumps > 2) return true; // now that really needs to be correct!
                    // turn right:
                    if (dx == 0) {
                        dx = -dy;
                        dy = 0;
                    } else {
                        dy = dx;
                        dx = 0;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException aie) {
            return false;
        }
    }
}