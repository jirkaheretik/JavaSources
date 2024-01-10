package cz.braza.advent;

import java.util.ArrayList;

public class AoC202321GardenersPedometer {
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc23_21.txt";
        char[][] garden = AOCHelper.readFile2CharArray(fileName);
        AOCHelper.printFrequencies(garden);
        int ysize = garden.length;
        int xsize = garden[0].length;
        System.out.println("Garden " + xsize + "x" + ysize);
        System.out.println(garden[xsize / 2][ysize / 2]);
        int step = 0;
        int plotCount = 1; // starting point
        garden[ysize / 2][xsize / 2] = 'O';
        // int[] nextFields = {(ysize / 2) * 1000 + (xsize / 2)};
        ArrayList<Integer> nextFields = new ArrayList<>();
        nextFields.add((ysize / 2) * 1000 + (xsize / 2));
        while (step < 64) {
            ArrayList<Integer> another = new ArrayList<>();
            for (int coord : nextFields) {
                int x = coord % 1000;
                int y = coord / 1000;
                if (processField(garden, x - 1, y, step)) {
                    another.add(1000 * y + x - 1);
                    if (step % 2 == 1) plotCount++;
                }
                if (processField(garden, x + 1, y, step)) {
                    another.add(1000 * y + x + 1);
                    if (step % 2 == 1) plotCount++;
                }
                if (processField(garden, x, y - 1, step)) {
                    another.add(1000 * (y - 1) + x);
                    if (step % 2 == 1) plotCount++;
                }
                if (processField(garden, x, y + 1, step)) {
                    another.add(1000 * (y + 1) + x);
                    if (step % 2 == 1) plotCount++;
                }
            }
            nextFields = another;
            step++;
        }
        AOCHelper.printField(garden, "Garden");
        System.out.println("Part 1 count: " + plotCount);
        AOCHelper.printFrequencies(garden);
    }

    public static boolean processField(char[][] g, int y, int x, int step) {
        if (g[y][x] == '.') {
            g[y][x] = (step % 2 == 0 ? 'E' : 'O');
            return true;
        }
        return false;
    }
}

