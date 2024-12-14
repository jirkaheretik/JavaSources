package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

public class Day12Gardening {
    public static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_12.txt";
        char[][] map = AOCHelper.readFile2CharArrayWithBorder(fileName, '.');
        AOCHelper.printField(map, "Board:");
        // for every region:
        // count of tiles (greedy)
        // perimeter, which is (3-neighbours) for every cell, +1 for first
        int sumScore = 0;
        for (int row = 1; row < map.length - 1; row++)
            for (int col = 1; col < map[row].length; col++)
                if (map[row][col] >= 'A' && map[row][col] <= 'Z') {
                    int[] values = new int[2]; // count, borders
                    char cell = map[row][col];
                    greedyCell(map, row, col, values, cell);
                    //values[1]++;
                    sumScore += values[0] * values[1];
                    System.out.printf("Processed %c with a size %d and border count %d%n", cell, values[0], values[1]);
                }
        System.out.println("Part 1 sum: " + sumScore);
        AOCHelper.printField(map, "Result:");
    }

    public static void greedyCell(final char[][] map, final int r, final int c, final int[] result, final char expected) {
        if (map[r][c] != expected) return;
        //System.out.printf("Got into a cell [%d,%d] witch char %c and current values for count %d and fences %d%n", r, c, expected, result[0], result[1]);
        map[r][c] = (char) (expected + 32); // mark as visited
        result[0] += 1;
        int fences = 4;
        for (int[] dir: directions)
            if (map[r + dir[0]][c + dir[1]] == expected) {
                greedyCell(map, r + dir[0], c + dir[1], result, expected);
                fences--;
            } else if (map[r + dir[0]][c + dir[1]] - 32 == expected)
                fences--;
        int old = result[1];
        result[1] += fences;
        //System.out.printf("Finished cell [%d,%d] setting fences from %d to %d%n", r, c, old, result[1]);
    }
}
