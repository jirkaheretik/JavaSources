package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class AoC202305GarnerersSeeds {
    public static final int STEPS = 8;
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_05.txt";
        Scanner vstup = new Scanner(new File(fileName));
        String[] seeds = vstup.nextLine().split(" ");
        vstup.nextLine(); // ignore empty line
        vstup.nextLine(); // read "seed-to-soil" description
        // prepare the seeds:
        long[][] map = new long[seeds.length - 1][STEPS];
        long[][] mapP2 = new long[seeds.length / 2 + 1][STEPS];
        long[] ranges = new long[seeds.length / 2 + 1];
        System.out.println("DBG: mapP2 size: " + mapP2.length);

        long sumOfSeeds = 0;
        for (int i = 1; i < seeds.length; i++) {
            Arrays.fill(map[i - 1], -1); // fill with negative values
            map[i - 1][0] = Long.parseLong(seeds[i].trim());
            if (i % 2 == 0) {
                sumOfSeeds += map[i - 1][0];
                ranges[i / 2] = map[i - 1][0];
            } else {
                mapP2[i / 2][0] = map[i - 1][0];
            }
        }
        System.out.println("DBG: For part 2, there are " + sumOfSeeds + " seeds. Good luck.");
        int step = 1;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.isBlank()) continue;
            if (line.contains("-to-")) {
                // switch to the next cycle:
                // first resolve those that werent mapped:
                for (int row = 0; row < map.length; row++) {
                    if (map[row][step] == -1)
                        map[row][step] = map[row][step - 1];
                }
                // increase step:
                step++;
            } else {
                // should be standard "dest source range" line
                String[] values = line.split(" ");
                long dest = Long.parseLong(values[0].trim());
                long source = Long.parseLong(values[1].trim());
                long range = Long.parseLong(values[2].trim());
                // process all the seeds, and resolve if they fit within <source; source+range)
                for (int row = 0; row < map.length; row++) {
                    long val = map[row][step - 1];
                    if (val >= source && val < source + range) {
                        long diff = val - source;
                        if (map[row][step] != -1)
                            System.out.println("Field remapped, row: " + row + ", step: " + step + ", old value: " + map[row][step] + ", new value: " + (dest + diff));
                        map[row][step] = dest + diff;
                    }
                }
            }

        }
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++)
                System.out.print("-" + map[row][col]);
            System.out.println();
        }
        // now we are supposed to find the minimum, yet we need to remap those -1 first:
        long lowest = Long.MAX_VALUE;
        for (int row = 0; row < map.length; row++) {
            if (map[row][step] == -1)
                map[row][step] = map[row][step - 1];
            if (map[row][step] < lowest)
                lowest = map[row][step];
        }


        System.out.println("Part 1: lowest location corresponding to seed is " + lowest);
        //System.out.println("Part 2: sum of calibration values is " + calibrationPart2);
    }
}
