package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.io.File;
import java.util.*;

public class Day18FallingBytes {
    public static final int LIMIT = 71;
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc24_18.txt";
        Scanner vstup = new Scanner(new File(fileName));
        char[][] map = new char[LIMIT][LIMIT];
        for (char[] line: map)
            Arrays.fill(line, '.');
        int counter = 0;
        boolean first = true;
        boolean last = true;
        while (vstup.hasNextLine()) {
            String[] line = vstup.nextLine().split(",");
            map[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = '#';
            counter++;
            String suffix = "";
            int shortest = doPart1(map);
            if (first && shortest > 140) {
                first = false;
                suffix = ", first hurdle";
            }
            if (counter == 1024) {
                //AOCHelper.printField(map, "After 1024 steps");
                //System.out.println("Part 1 length: " + doPart1(map));
                suffix = ", Part 1 answer";
            }
            if (last && shortest < 0) {
                last = false;
                suffix = ", Part 2 answer '" + line[0] + "," + line[1] + "'";
            }
            System.out.printf("%d,%d%s%n", counter, shortest, suffix);
        }
        //System.out.println("In the end: " + doPart1(map));
        //AOCHelper.printField(map, "In the end");
    }

    public static int doPart1(char[][] map) {
        boolean[][] history = new boolean[LIMIT][LIMIT];
        int length = 0;
        int target = 7070;
        // do step:
        Set<Integer> work = new HashSet<>();
        work.add(0); // starting point
        while (!work.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            for (int pos: work) {
                if (pos == target) {
                    return length;
                }
                int x = pos / 100;
                int y = pos % 100;
                history[x][y] = true;
                // map 4 new positions, if not wall and not visited:
                if (x > 0 && !history[x - 1][y] && map[x - 1][y] == '.')
                    next.add(100 * (x - 1) + y);
                if (y > 0 && !history[x][y - 1] && map[x][y - 1] == '.')
                    next.add(100 * x + y - 1);
                if (x < LIMIT - 1 && !history[x + 1][y] && map[x + 1][y] == '.')
                    next.add(100 * (x + 1) + y);
                if (y < LIMIT - 1 && !history[x][y + 1] && map[x][y + 1] == '.')
                    next.add(100 * x + y + 1);
            }
            length++;
            work = next;
        }
        return -1;
    }
}
