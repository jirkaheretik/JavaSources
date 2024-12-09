package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day06GuardGallivant {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_06ex.txt";
        char[][] board = AOCHelper.readFile2CharArray(fileName);
        int[] startExample = new int[]{6, 4};
        int[] startFull = new int[]{61, 78};
        int row = 6;//61; // 6
        int col = 4;//78; // 4
        int stepCount = 1; // starting position counts
        Set<int[]> obstacles = new HashSet<>();
        board[row][col] = '^';
        int dx = 0, dy = -1;
        try {
            while (true) {
                // can I make next step?
                if (board[row + dy][col + dx] != '#') {
                    row += dy;
                    col += dx;
                    if (board[row][col] == '.') {
                        stepCount++;
                    } else {
                        // just for P2 (I was on this cell already)
                        // if I am crossing it from the right direction, I may
                        // be able to place an obstacle to cycle.
                        if (board[row][col] == '^' && dx == -1 ||
                                board[row][col] == 'v' && dx == 1 ||
                                board[row][col] == '<' && dy == 1 ||
                                board[row][col] == '>' && dy == -1
                        ) {
                            // we may place new obstacle
                            // peek ahead?
                            if (board[row + dy][col + dx] == '#')
                                System.out.println("Crap, obstacle already there.");
                            else
                                obstacles.add(new int[]{row + dy, col + dx});
                        }
                    }
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
        AOCHelper.printField(board, "Výsledek:");
        System.out.println("New obstacles:");
        for (int[] o: obstacles)
            System.out.printf("[%d, %d]%n", o[0], o[1]);
    }
    /*
    Failed answers:
    320 too low, as expected, as I cannot get even the examples right
     */

    public static char newChar(int dx, int dy) {
        // for P1:
        // return ',';

        // for P2:
        if (dx == 0)
            return dy == -1 ? '^' : 'v';
        else
            return dx == -1 ? '<' : '>';
    }
}