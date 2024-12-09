package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day04FindXmasCount {
    public static final char[] XMAS = {'X', 'M', 'A', 'S'};
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_04.txt";
        char[][] chars = AOCHelper.readFile2CharArray(fileName);
        int count = 0;
        for (int row = 0; row < chars.length; row++)
            for (int col = 0; col < chars[row].length; col++)
                for (int dc = -1; dc <= 1; dc++)
                    for (int dr = -1; dr <= 1; dr++)
                        if ((dc != 0 || dr != 0) && isXmas(chars, row, col, dr, dc))
                            count++;
        System.out.println("Part 1 sum of XMAS: " + count);
        int p2Sum = 0;
        for (int row = 0; row < chars.length; row++)
            for (int col = 0; col < chars[row].length; col++)
                if (isXmAs(chars, row, col)) p2Sum++;
        System.out.println("Part 2 sum of X-MAS: " + p2Sum);
    }

    /*
    Returns true if XMAS starts from a given cell [row][col] in direction specified by [dr,dc]
     */
    private static boolean isXmas(char[][] arr, int row, int col, int dr, int dc) {
        try {
            for (int i = 0; i < XMAS.length; i++)
                if (arr[row + i * dr][col + i * dc] != XMAS[i]) return false;
            return true;
        } catch (ArrayIndexOutOfBoundsException aie) {
            return false;
        }
    }

    /*
    In order to be X-MAS:
    - current char must be 'A'
    - both diagonals are M&S
    Got it wrong at first, those MAS has to be in X shape, not any of four directions.
     */
    private static boolean isXmAs(char[][] arr, int row, int col) {
        if (arr[row][col] != 'A') return false;
        try {
            return ((arr[row + 1][col + 1] == 'M' && arr[row - 1][col - 1] == 'S')
                    || (arr[row + 1][col + 1] == 'S' && arr[row - 1][col - 1] == 'M'))
            &&
            ((arr[row + 1][col - 1] == 'M' && arr[row - 1][col + 1] == 'S')
                    || (arr[row + 1][col - 1] == 'S' && arr[row - 1][col + 1] == 'M'));
        } catch (ArrayIndexOutOfBoundsException aie) {
            return false;
        }
    }
}
