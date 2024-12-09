package cz.braza.advent.a2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day07ElephantsWithOperators {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_07.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long mulMax = 0; // just for debugging, which value is largest (234 bil)
        int maxCount = 0; // just for debugging, how many items I have in a row max (12)
        int count = 0; // just for debugging, number of input lines
        long sumOfCorrect = 0;
        long sumOfCorrectP2 = 0;
        while (vstup.hasNextLine()) {
            // read line and split:
            String[] line = vstup.nextLine().split(": ");
            long left = Long.parseLong(line[0]);
            // debugging:
            if (left > mulMax) mulMax = left;
            String[] vals = line[1].split(" ");
            if (vals.length > maxCount) maxCount = vals.length;
            count++;
            // processing of a line:
            int[] items = new int[vals.length];
            for (int i = 0; i < vals.length; i++)
                items[i] = Integer.parseInt(vals[i]);
            if (canFit(left, items[0] + items[1], items, 2) || canFit(left, items[0] * items[1], items, 2)) {
                sumOfCorrect += left;
                System.out.println("DBG Correct! " + left + ": " + Arrays.toString(items));
            } else if (canFit2(left, items[0] + items[1], items, 2) || canFit2(left, items[0] * items[1], items, 2) || canFit2(left, concat(items[0], items[1]), items, 2)) {
                sumOfCorrectP2 += left;
                System.out.println("P2_ Correct! " + left + ": " + Arrays.toString(items));
            }
        }
        System.out.printf("DBG. %d lines. Max left value %d (%f digits), max count: %d%n", count, mulMax, Math.log10(mulMax), maxCount);
        System.out.println("Part 1 sum: " + sumOfCorrect);
        System.out.println("Part 2 only: " + sumOfCorrectP2);
        System.out.println("Part 2 total: " + (sumOfCorrect + sumOfCorrectP2));
    }

    public static boolean canFit(long target, long middle, int[] arr, int index) {
        if (index >= arr.length) return middle == target;
        if (middle > target) return false;
        return canFit(target, middle + arr[index], arr, index + 1) ||
               canFit(target, middle * arr[index], arr, index + 1);
    }

    public static boolean canFit2(long target, long middle, int[] arr, int index) {
        if (index >= arr.length) return middle == target;
        if (middle > target) return false;
        return canFit2(target, middle + arr[index], arr, index + 1) ||
                canFit2(target, middle * arr[index], arr, index + 1) ||
                canFit2(target, concat(middle, arr[index]), arr, index + 1);
    }

    public static long concat(long left, long right) {
        return Long.parseLong("" + left + right);
    }
}
