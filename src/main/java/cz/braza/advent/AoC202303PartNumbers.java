package cz.braza.advent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AoC202303PartNumbers {
    public static final String NONSYMBOLS = "0123456789.";
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_03.txt";
        //Scanner vstup = new Scanner(new File(fileName));
        int sumOfParts = 0;
        int nonPartsCount = 0;
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        // convert to an array:
        char[][] field = new char[lines.size()][140];
        int lineNum = 0;
        for (String line: lines)
            field[lineNum++] = line.toCharArray();
        // now the whole process:
        // flags:
        boolean inNumber = false;
        String nextNumber = "";
        int indexStart = -1;
        int indexEnd = -1;
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                boolean cislice = Character.isDigit(field[row][col]);
                if (cislice) {
                    if (inNumber)
                        nextNumber += field[row][col];
                    else {
                        inNumber = true;
                        indexStart = col;
                        nextNumber = "" + field[row][col];
                    }
                } else {
                    if (inNumber) {
                        inNumber = false;
                        indexEnd = col - 1;
                        if (isPartNumber(field, row, indexStart, indexEnd))
                            sumOfParts += Integer.parseInt(nextNumber);
                        else {
                            System.out.println("DBG: NON PART (" + nextNumber + ") found at line " + row + ", positions " + indexStart + "-" + indexEnd);
                            nonPartsCount++;
                        }
                        nextNumber = "";
                    }
                    // else (not in number and was not in number) not needed
                }
            }
            if (inNumber) {
                // line ended with a number, process it correctly:
                inNumber = false;
                if (isPartNumber(field, row, indexStart, field[row].length - 1))
                    sumOfParts += Integer.parseInt(nextNumber);
                else {
                    System.out.println("DBG: NON PART (" + nextNumber + ") found at line " + row + ", positions " + indexStart + "-" + indexEnd);
                    nonPartsCount++;
                }
                nextNumber = "";
            }
        }
        System.out.println("God Bless, sum of part numbers might be " + sumOfParts);
        System.out.println("While there were " + nonPartsCount + " non parts.");
        // And now for something completely different (aka Part 2)
        long sumOfGearRatios = 0;
        int gearCount = 0;
        int starCount = 0;
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == '*') {
                    starCount++;
                    // found a gear
                    int parts = 0;
                    if (col > 0 && Character.isDigit(field[row][col - 1]))
                        parts++;
                    if (col < field[row].length - 1 && Character.isDigit(field[row][col + 1]))
                        parts++;
                    if (row > 0) {
                        int tmpParts = 0;
                        for (int i = Math.max(0, col - 1); i < Math.min(field[row - 1].length, col + 2); i++)
                            if (Character.isDigit(field[row - 1][i]))
                                tmpParts++;
                        parts += (tmpParts > 1) ? (Character.isDigit(field[row - 1][col]) ? 1 : 2) : tmpParts;
                    }
                    if (row < field.length - 1) {
                        int tmpParts = 0;
                        for (int i = Math.max(0, col - 1); i < Math.min(field[row + 1].length, col + 2); i++)
                            if (Character.isDigit(field[row + 1][i]))
                                tmpParts++;
                        parts += (tmpParts > 1) ? (Character.isDigit(field[row + 1][col]) ? 1 : 2) : tmpParts;
                    }
                    if (parts == 2) {
                        gearCount++;
                        // count its gear ratio:
                        int gearRatio = 1; // we will be multiplying
                        // no star is at the border, so we can safely go to every direction:
                        // if digit directly above, looking for ONE number, possibly both ways
                        if (Character.isDigit(field[row - 1][col]))
                            gearRatio *= findNum(field, row - 1, col, true, true);
                        // otherwise looking for left and right number
                        else
                            gearRatio *= findNum(field, row - 1, col - 1, true, false) * findNum(field, row - 1, col + 1, false, true);
                        // are there numbers in the same row?
                        gearRatio *= findNum(field, row, col - 1, true, false) * findNum(field, row, col + 1, false, true);
                        // same as line above, process line below:
                        if (Character.isDigit(field[row + 1][col]))
                            gearRatio *= findNum(field, row + 1, col, true, true);
                        else
                            gearRatio *= findNum(field, row + 1, col - 1, true, false) * findNum(field, row + 1, col + 1, false, true);
                        sumOfGearRatios += gearRatio;
                        System.out.println("Found a gear at [" + row + "," + col + "] with ratio " + gearRatio);
                    } else {
                        System.out.println("Found a star at [" + row + "," + col + "] with " + parts + " parts, so it is not considered gear.");
                    }
                }
            }
        }
        System.out.println("Found " + starCount + " stars of which " + gearCount + " are gears.");
        System.out.println("Part 2, sum of gear ratios: " + sumOfGearRatios);
    }

    public static boolean isPartNumber(char[][] pole, int lineNum, int idxStart, int idxEnd) {
        // check previous line
        if (lineNum > 0) {
            for (int i = Math.max(0, idxStart - 1); i < Math.min(pole[lineNum - 1].length, idxEnd + 2); i++)
                if (!NONSYMBOLS.contains("" + pole[lineNum - 1][i]))
                    return true;
        }
        // check previous and next char:
        if (idxStart > 0 && !NONSYMBOLS.contains("" + pole[lineNum][idxStart - 1]))
            return true;
        if (idxEnd + 1 < pole[lineNum].length && !NONSYMBOLS.contains("" + pole[lineNum][idxEnd + 1]))
            return true;
        // check next line:
        if (lineNum + 1 < pole.length)
            for (int i = Math.max(0, idxStart - 1); i < Math.min(pole[lineNum + 1].length, idxEnd + 2); i++)
                if (!NONSYMBOLS.contains("" + pole[lineNum + 1][i]))
                    return true;
        return false;
    }

    private static int findNum(char[][] pole, int r, int c, boolean goBack, boolean goForth) {
        if (!Character.isDigit(pole[r][c]))
            return 1; // we will be multiplying
        String number = "" + pole[r][c];
        if (goBack) {
            int col = c - 1;
            while (col >= 0 && Character.isDigit(pole[r][col])) {
                number = pole[r][col] + number;
                col--;
            }
        }
        if (goForth) {
            int col = c + 1;
            while (col < pole[r].length && Character.isDigit(pole[r][col])) {
                number += pole[r][col];
                col++;
            }
        }
        return Integer.parseInt(number);
    }
}
