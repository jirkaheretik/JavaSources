package cz.braza.advent.a2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
https://adventofcode.com/2024/day/1

 */
public class Day01ComparePairs {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_01.txt";
        final int LIMIT = 1000;
        Scanner vstup = new Scanner(new File(fileName));
        int[] left = new int[LIMIT];
        int[] right = new int[LIMIT];
        int index = 0;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.length() > 10) {
                left[index] = Integer.parseInt(line.substring(0, 6).trim());
                right[index] = Integer.parseInt(line.substring(7).trim());
            }
            index++;
        }
        long sum = 0;
        long similarity = 0;
        Arrays.sort(left);
        Arrays.sort(right);
        for (index = 0; index < LIMIT; index++) {
            sum += Math.abs(left[index] - right[index]);
            int rightIdx = Arrays.binarySearch(right, left[index]);
            if (rightIdx >= 0) {
                int back = 0;
                while (rightIdx - back > 0 && right[rightIdx - back - 1] == left[index]) back++;
                int forward = 0;
                while (rightIdx + forward < LIMIT - 1 && right[rightIdx + forward + 1] == left[index]) forward++;
                if (back > 0)
                    System.out.println("Did not get first value for index " + rightIdx + " and value " + left[index]);
                if (forward > 0)
                    System.out.println("Did not get last value for index " + rightIdx + " and value " + left[index]);
                similarity += left[index] * (back + forward + 1);
            }
        }
        System.out.println("Part 1 sum: " + sum);
        System.out.println("Part 2 similarity: " + similarity);
    }
}
