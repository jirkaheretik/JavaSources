package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AoC202309Oasis {
    public static final boolean DBG = false;
    public static final boolean PART1 = true;
    public static void main(String[] args) throws Exception {

        String fileName = "/home/jirka/src/java0/aoc23_09.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long extrapolatedSum = 0;
        while (vstup.hasNextLine()) {
            long val = getNextValue(processLine(vstup.nextLine()));
            if (DBG) System.out.println(val);
            extrapolatedSum += val;
        }
        System.out.println("Part " + (PART1 ? "1" : "2") + ": Sum of extrapolated values: " + extrapolatedSum);

        // TESTING:
        // System.out.println(getNextValue(processLine("10 13 16 21 30 45"), false));
        // System.out.println(getNextValue(processLine("24 38 52 66 80 94 108 122 136 150 164 178 192 206 220 234 248 262 276 290 304"), false));
        // OK: System.out.println(getNextValue(processLine("10 9 16 44 108 222 396 633 926 1255 1584 1858 2000 1908 1452 471 -1230 -3883 -7760 -13176 -20492")));
    }

    public static int getNextValue(int[] values) {
        /*
        Create 2 dimensional array, start with the same length, but in each new row, the actual (used) length is one less
         */
        int[][] field = new int[values.length][values.length];
        field[0] = values;
        printArr(values, values.length);
        boolean done = false;
        int line = 0;
        while (!done && line < values.length) {
            done = true; // just maybe?
            int lastValue = -1; // whatever here
            for (int i = 1; i < values.length - line; i++) {
                field[line + 1][i - 1] = field[line][i] - field[line][i - 1];
                if (i == 1)
                    lastValue = field[line + 1][0];
                else
                    if (field[line + 1][i - 1] != lastValue)
                        done = false;
            }
            // DBG:
            if (done)
                printArr(field[line + 1], values.length - line - 1);
            line++;
        }
        while (line > 0) {
            field[line][values.length - line] = field[line][values.length - line - 1] + field[line + 1][values.length - line - 1];
            printArr(field[line], values.length - line + 1);
            line--;
        }
        return (field[0][values.length - 1] + field[1][values.length - 1]);
    }

    public static int[] processLine(String line) {
        String[] vals = line.split(" ");
        if (!PART1)
            Collections.reverse(Arrays.asList(vals));
        int[] result = new int[vals.length];
        for (int i = 0; i < vals.length; i++) {
            result[i] = Integer.parseInt(vals[i]);
        }
        return result;
    }

    public static void printArr(int[] arr, int size) {
        if (DBG) {
            for (int i = 0; i < size; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
        }
    }
}
