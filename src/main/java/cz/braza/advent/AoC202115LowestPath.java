package cz.braza.advent;

import java.io.File;
import java.util.Scanner;

public class AoC202115LowestPath {
    public static final int SIZE = 100;
    public static final int MAXIDX = SIZE - 1;
    public static final int MULTIPLY = 5;
    public static final int MAXIDX2 = MULTIPLY * SIZE - 1;
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc21_15.txt";
        Scanner vstup = new Scanner(new File(fileName));

        int[][] matrix = new int[SIZE][SIZE];
        int[][] path = new int[SIZE][SIZE];
        int row = 0;
        while (vstup.hasNextLine()) {
            String[] data = vstup.nextLine().split("");
            int col = 0;
            for (String value : data) {
                matrix[row][col++] = Integer.parseInt(value);
            }
            row++;
        }
        // find answer for PART 1
        for (int r = MAXIDX; r >= 0; r--)
            for (int c = MAXIDX; c >= 0; c--) {
                path[r][c] = matrix[r][c] +
                        (r == MAXIDX ?
                                (c == MAXIDX ? 0 : path[r][c + 1]) :
                                (c == MAXIDX ? path[r + 1][c] : Math.min(path[r + 1][c], path[r][c + 1])));
                // System.out.println("P[" + r + "][" + c + "] == " + path[r][c]);
            }
        System.out.println("Total path (part 1): " + (path[0][0] - matrix[0][0]));
        int[][] bigMatrix = new int[MULTIPLY * SIZE][MULTIPLY * SIZE];
        path = new int[MULTIPLY * SIZE][MULTIPLY * SIZE];
        // fill in bigMatrix first:
        for (int r = 0; r < MULTIPLY * SIZE; r++)
            for (int c = 0; c < MULTIPLY * SIZE; c++) {
                int r0 = r % SIZE;
                int c0 = c % SIZE;
                int r1 = r / SIZE;
                int c1 = c / SIZE;
                int val = matrix[r0][c0] + r1 + c1;
                if (val > 9) val -= 9;
                bigMatrix[r][c] = val;
            }
        // sanity check:
        System.out.println("P[99][99] = " + bigMatrix[99][99]);
        System.out.println("P[199][199] = " + bigMatrix[199][199]);
        System.out.println("P[499][499] = " + bigMatrix[499][499]);
        // find answer for PART 1
        for (int r = MAXIDX2; r >= 0; r--)
            for (int c = MAXIDX2; c >= 0; c--) {
                path[r][c] = bigMatrix[r][c] +
                        (r == MAXIDX2 ?
                                (c == MAXIDX2 ? 0 : path[r][c + 1]) :
                                (c == MAXIDX2 ? path[r + 1][c] : Math.min(path[r + 1][c], path[r][c + 1])));
                // System.out.println("P[" + r + "][" + c + "] == " + path[r][c]);
            }
        System.out.println("Total path (part 2): " + (path[0][0] - bigMatrix[0][0]));
    }
}
