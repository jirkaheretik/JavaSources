package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.util.HashSet;
import java.util.Set;

public class Day10TopographicMap {
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_10.txt";
        // 310 basecamps, 245 peaks
        int[][] map = AOCHelper.readFile2DigitArray(fileName);
        int baseCount = 0;
        int peakCount = 0;
        long totalScore = 0;
        long totalScoreP2 = 0;
        // just as a start:
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == 0) baseCount++;
                else if (map[r][c] == 9) peakCount++;
        System.out.printf("This map has %d basecamps and %d peaks.%n", baseCount, peakCount);
        // now our business:
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == 0) {
                    // found a trailhead, go search for peaks:
                    Set<Integer> step = new HashSet<>();
                    step.add(100 * r + c);
                    for (int i = 0; i < 9; i++)
                        step = doStep(step, map);
                    totalScore += step.size();
                    int tmp = dfs(map, r, c, 0);
                    totalScoreP2 += tmp;
                    System.out.printf("Adding trailhead at [%d,%d] with a score of %d and rating %d.%n", r, c, step.size(), tmp);
                }
        System.out.println("Part 1 sum of scores: " + totalScore);
        System.out.println("Part 2 sum of ratings: " + totalScoreP2);
    }

    public static Set<Integer> doStep(Set<Integer> input, int[][] map) {
        Set<Integer> result = new HashSet<>();
        for (int cell: input) {
            int r = cell / 100;
            int c = cell % 100;
            if (r > 0 && map[r][c] + 1 == map[r - 1][c])
                result.add(100 * (r - 1) + c);
            if (c > 0 && map[r][c] + 1 == map[r][c - 1])
                result.add(100 * r + c - 1);
            if (r < map.length - 1 && map[r][c] + 1 == map[r + 1][c])
                result.add(100 * (r + 1) + c);
            if (c < map[r].length - 1 && map[r][c] + 1 == map[r][c + 1])
                result.add(100 * r + c + 1);
        }
        return result;
    }

    public static int dfs(int[][] map, int row, int col, int expected) {
        if (row < 0 || col < 0 || row >= map.length || col >= map[row].length) return 0;
        int current = map[row][col];
        if (current != expected) return 0;
        if (current == 9) return 1;
        return dfs(map, row - 1, col, current + 1)
             + dfs(map, row + 1, col, current + 1)
             + dfs(map, row, col - 1, current + 1)
             + dfs(map, row, col + 1, current + 1);
    }
}
