package cz.braza.advent.a2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day02SafeReportsAndLevels {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_02.txt";
        System.out.println("Processing " + fileName);
        Scanner vstup = new Scanner(new File(fileName));
        int safeCount = 0;
        int safeCountP2 = 0;
        int linesCount = 0;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            linesCount++;
            String[] levels = line.split(" ");
            boolean p1safe = isP1Safe(levels);
            if (isP1Safe(levels)) safeCount++;
            else {
                for (int i = 0; i < levels.length; i++) {
                    if (isP1Safe(removeElement(levels, i))) {
                        System.out.println(line);
                        safeCountP2++;
                        break;
                    }
                    //if (i == levels.length - 1) System.out.println(line);
                }
            }
        }
        System.out.println("Read total " + linesCount + " lines.");
        System.out.println("Part 1: Safe report count: " + safeCount);
        System.out.println("Part 2: Safe report count: " + (safeCount + safeCountP2));
/*
Read total 1000 lines.
Part 1: Safe report count: 486
Part 2: Safe report count: 540
 */
    }

    private static boolean isP1Safe(String[] levels) {
        int first, last;
        try {
            first = Integer.parseInt(levels[0]);
            last = Integer.parseInt(levels[1]);
            boolean increasing = last > first;
            last = first;
            boolean safe = true;
            for (int i = 1; i < levels.length; i++) {
                int val = Integer.parseInt(levels[i]);
                if (val == last || Math.abs(val - last) > 3 || val > last != increasing) {
                    safe = false;
                    break;
                }
                last = val;
            }
            return safe;
        } catch (NumberFormatException nfe) {
            System.out.println("Problem with " + Arrays.toString(levels));
            return false;
        }
    }

    private static String[] removeElement(String[] arr, int index) {
        String[] result = new String[arr.length - 1];
        for (int idx = 0; idx < index; idx++)
            result[idx] = arr[idx];
        for (int idx = index + 1; idx < arr.length; idx++)
            result[idx - 1] = arr[idx];
        return result;
    }
}

/*
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            linesCount++;
            String[] levels = line.split(" ");
            int first = Integer.parseInt(levels[0]);
            int last = Integer.parseInt(levels[1]);
            boolean increasing = last > first;
            last = first;
            boolean safe = true;
            boolean safeP2 = true;
            boolean usedDampener = false;
            for (int i = 1; i < levels.length; i++) {
                int val = Integer.parseInt(levels[i]);
                if (val == last || Math.abs(val - last) > 3 || val > last != increasing) {
                    safe = false;
                    if (usedDampener) {
                        safeP2 = false;
                        break; // cannot be safe anymore even in P2
                    }
                    usedDampener = true;
                    continue; // do not val=last!
                }
                last = val;
            }
            if (safe) {
                safeCount++;
                //System.out.println("Safe report: " + line);
            }
            if (safeP2) {
                safeCountP2++;
                //if (!safe)
                //    System.out.println("Saved with dampener: " + line);
            }
            if (!safeP2) {
                if (isP1Safe(removeElement(levels, 0))) {
                    System.out.println("*** Safe after removing first element: " + line);
                    safeCountP2++;
                    continue;
                }
                if (isP1Safe(removeElement(levels, 1))) {
                    System.out.println("*** Safe after removing second element: " + line);
                    safeCountP2++;
                    continue;
                }
                System.out.println("Still unsafe: " + line);
            }
        }

 */