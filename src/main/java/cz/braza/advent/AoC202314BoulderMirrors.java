package cz.braza.advent;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class AoC202314BoulderMirrors {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_14.txt";
        final int BOXSIZE = 100;
        char[][] field = AOCHelper.readFile2CharArray(fileName);
        // just for debugging:
        // DBG: printFrequencies(field);
        // now the whole process:
        // Part 1 move all the round boulders upwards:
        moveNorth(field);
        //System.out.println("----");
        printField(field, "North");
        // DBG: printFrequencies(field);

        // part 1:
        long sumOfBoulders = 0;
        for (int col = 0; col < BOXSIZE; col++) {
            for (int row = 0; row < BOXSIZE; row++) {
                if (field[row][col] == 'O')
                    sumOfBoulders += field.length - row;
            }
        }
        System.out.println("Part 1: total load on north support beams: " + sumOfBoulders);
        moveWest(field);
        AOCHelper.printField(field, "West");
        moveSouth(field);
        AOCHelper.printField(field, "South");
        moveEast(field);
        AOCHelper.printField(field, "East");

        // runs 3/4h for 10M, but we need 100 times more :-(
        for (int count = 1; count < 10000000; count++) {
            moveNorth(field);
            moveWest(field);
            moveSouth(field);
            moveEast(field);
        }
        AOCHelper.printField(field, "Million");
    }

    public static void moveNorth(char[][] field) {
        for (int col = 0; col < field[0].length; col++) {
            int freeRow = -1;
            for (int row = 0; row < field.length; row++) {
                char c = field[row][col];
                if (c == '.') {
                    if (freeRow == -1) freeRow = row;
                } else if (c == '#') {
                    freeRow = -1;
                } else if (c == 'O') {
                    if (freeRow > -1) {
                        field[freeRow][col] = 'O';
                        field[row][col] = '.';
                        freeRow++;
                    }
                }
            }
        }
    }
    public static void moveSouth(char[][] field) {
        for (int col = 0; col < field[0].length; col++) {
            int freeRow = -1;
            for (int row = field.length - 1; row >= 0; row--) {
                char c = field[row][col];
                if (c == '.') {
                    if (freeRow == -1) freeRow = row;
                } else if (c == '#') {
                    freeRow = -1;
                } else if (c == 'O') {
                    if (freeRow > -1) {
                        field[freeRow][col] = 'O';
                        field[row][col] = '.';
                        freeRow--;
                    }
                }
            }
        }
    }
    public static void moveEast(char[][] field) {
        for (int row = 0; row < field.length; row++) {
            int freeCol = -1;
            for (int col = field[0].length - 1; col >= 0; col--) {
                char c = field[row][col];
                if (c == '.') {
                    if (freeCol == -1) freeCol = col;
                } else if (c == '#') {
                    freeCol = -1;
                } else if (c == 'O') {
                    if (freeCol > -1) {
                        field[row][freeCol] = 'O';
                        field[row][col] = '.';
                        freeCol--;
                    }
                }
            }
        }
    }
    public static void moveWest(char[][] field) {
        for (int row = 0; row < field.length; row++) {
            int freeCol = -1;
            for (int col = 0; col < field[0].length; col++) {
                char c = field[row][col];
                if (c == '.') {
                    if (freeCol == -1) freeCol = col;
                } else if (c == '#') {
                    freeCol = -1;
                } else if (c == 'O') {
                    if (freeCol > -1) {
                        field[row][freeCol] = 'O';
                        field[row][col] = '.';
                        freeCol++;
                    }
                }
            }
        }
    }
    public static void printField(char[][] field, String label) {
        System.out.println(label + ":\n======\n");
        for (int row = 0; row < field.length; row++) {
            System.out.println(new String(field[row]));
        }
    }
}