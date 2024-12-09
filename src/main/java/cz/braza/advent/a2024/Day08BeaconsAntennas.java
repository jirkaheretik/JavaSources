package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.util.HashSet;
import java.util.Set;

public class Day08BeaconsAntennas {
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_08.txt";
        char[][] map = AOCHelper.readFile2CharArray(fileName);
        Set<Integer> positions = new HashSet<>();
        Set<Integer> positionsP2 = new HashSet<>();
        int maxRow = map.length;
        int maxCol = map[0].length;
        for (int r = 0; r < maxRow; r++)
            for (int c = 0; c < maxCol; c++)
                if (map[r][c] != '.') {
                    char beacon = map[r][c];
                    for (int dr = r; dr < maxRow; dr++)
                        for (int dc = 0; dc < maxCol; dc++)
                            if ((dr > r || dc > c) && map[dr][dc] == beacon) {
                                int dy = dr - r;
                                int dx = dc - c;
                                // go one way, including beacon positions (for i == 0)
                                for (int i = 0; i < 50; i++) {
                                    int y = r - i * dy;
                                    int x = c - i * dx;
                                    if (x < 0 || y < 0 || x >= maxCol || y >= maxCol)
                                        break;
                                    if (i == 1) positions.add(100 * y + x); // just this one for P1
                                    positionsP2.add(100 * y + x); // everything for P2, break once we run out of map
                                }
                                // go the other way, including beacon positions (for i == 0)
                                for (int i = 0; i < 50; i++) {
                                    int y = dr + i * dy;
                                    int x = dc + i * dx;
                                    if (x < 0 || y < 0 || x >= maxCol || y >= maxCol)
                                        break;
                                    if (i == 1) positions.add(100 * y + x); // just this one for P1
                                    positionsP2.add(100 * y + x); // everything for P2, break once we run out of map
                                }
                                /* // HISTORY, part 1 only, now replaced with cycle above
                                // project to both ends, check constraints, add to set
                                if (r - dy >= 0 && r - dy < maxRow && c - dx >= 0 && c - dx < maxCol) {
                                    positions.add((r - dy) * 100 + c - dx);
                                    System.out.printf("Adding %c at [%d,%d] for beacons [%d,%d,%c] and [%d,%d,%c]%n", beacon, r - dy, c - dx, r, c, map[r][c], dr, dc, map[dr][dc]);
                                }
                                if (dr + dy >= 0 && dr + dy < maxRow && dc + dx >= 0 && dc + dx < maxCol) {
                                    positions.add((dr + dy) * 100 + dc + dx);
                                    System.out.printf("Adding %c at [%d,%d] for beacons [%d,%d,%c] and [%d,%d,%c]%n", beacon, dr + dy, dc + dx, r, c, map[r][c], dr, dc, map[dr][dc]);
                                }
                                */
                            }
                }
        System.out.println("Part 1: antinode position count: " + positions.size());
        System.out.println("Part 2: all those resonant antinode position count: " + positionsP2.size());
        // P1: 454 is too high :-(
        // P1: 413 correct, it did not correctly recognize duplicates
        // P2: 1417
    }
}
