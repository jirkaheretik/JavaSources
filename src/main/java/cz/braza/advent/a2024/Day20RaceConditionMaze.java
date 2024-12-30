package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.util.PriorityQueue;

/*
How to:
Part 1 - first we BFS (PriorityQueue) to find out the best route from S to E
Then we try to remove each and every obstacle (excluding borders) and check the path again.
If it is at least 100 steps shorter, we count it.
This is a bit time consuming, runs 5seconds.
And useless for part 2.
Therefore an update: first fill up FROMSTART 2D array with lengths counted from the start tile.
We need to run the full algorithm and not end at E.
Then we run the same algorithm again and fill TOEND array, as we are now
starting from the E tile and visit every possible cell.
Now both part 1 and 2 can be solved with the same function: for every cell that is on a path (not a #)
we go up to LIMIT cells in any directory (2 for part 1, 20 for part 2), and
count FROMSTART + dx + dy + TOEND and check if it is at least 100 better than former path.
Runs in about 100ms, finds 1 million shortcuts in a 20k map :-D
 */
public class Day20RaceConditionMaze {
    // specific order of directions, turn right => +1 index, turn left => -1 index
    public static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static final int SIZE = 150;
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_20up.txt";
        char[][] map = AOCHelper.readFile2CharArray(fileName);
        int[][] FROMSTART = new int[map.length][map[0].length];
        int[][] TOEND = new int[map.length][map[0].length];
        int start = findPos(map, 'S');
        long startTime = System.currentTimeMillis();
        int LENGTH = findPathLength(map, FROMSTART, start);
        findPathLength(map, TOEND, findPos(map, 'E'));
        int[][] tst = {{1, 1}, {2, 2}, {7, 7}, {23, 11}};
        for (int[] coord: tst)
            System.out.printf("DBG For pos [%d,%d] '%c' length from start is %d and %d to the end.%n", coord[0], coord[1], map[coord[0]][coord[1]], FROMSTART[coord[0]][coord[1]], TOEND[coord[0]][coord[1]]);
        int LIMIT = 30;
        System.out.println("Classic path is " + LENGTH + " steps (picoseconds) long.");
        long endTime = System.currentTimeMillis();
        /*
        int count = part1slow(map, LIMIT, LENGTH);
        System.out.printf("This part run %d ms.%n", endTime - startTime);
        System.out.printf("P1: There are %d cheats out of %d runs that save at least %d picoseconds.%n", count, runCount, LIMIT);

         */
        endTime = System.currentTimeMillis();
        System.out.printf("Program run %d ms.%n", endTime - startTime);
        long shortcuts = part2length(map, FROMSTART, TOEND, LIMIT, LENGTH, 2);
        endTime = System.currentTimeMillis();
        System.out.printf("Part 1 run %d ms and found %d shortcuts.%n", endTime - startTime, shortcuts);
        // P2:
        shortcuts = part2length(map, FROMSTART, TOEND, LIMIT, LENGTH, 20);
        endTime = System.currentTimeMillis();
        System.out.printf("Part 2 run %d ms and found %d shortcuts.%n", endTime - startTime, shortcuts);
    }

    public static int part1better(char[][] map, int[][] FROMSTART, int[][] TOEND, int LIMIT, int LENGTH) {
        int shortcuts = 0;
        for (int r = 1; r < map.length - 1; r++)
            for (int c = 1; c < map.length - 1; c++)
                if (map[r][c] != '#')
                    for (int[] D: DIRECTIONS)
                        if (r + 2 * D[0] > 0 && r + 2 * D[0] < map.length - 1 && c + 2 * D[1] > 0 && c + 2 * D[1] < map[0].length - 1 && map[r + 2 * D[0]][c + 2 * D[1]] != '#' && FROMSTART[r][c] + 2 + TOEND[r + 2 * D[0]][c + 2 * D[1]] + LIMIT <= LENGTH)
                            shortcuts++;
        return shortcuts;
    }

    public static int part2length(char[][] map, int[][] FROMSTART, int[][] TOEND, int LIMIT, int LENGTH, int SHORTCUTLENGTH) {
        int shortcuts = 0;
        for (int r = 1; r < map.length - 1; r++)
            for (int c = 1; c < map.length - 1; c++)
                if (map[r][c] != '#')
                    for (int dx = -SHORTCUTLENGTH; dx <= SHORTCUTLENGTH; dx++)
                        for (int dy = -SHORTCUTLENGTH + Math.abs(dx); dy <= SHORTCUTLENGTH - Math.abs(dx); dy++)
                            if ((dx != 0 || dy != 0) && r + dy > 0 && r + dy < map.length - 1 && c + dx > 0 && c + dx < map[0].length - 1 && map[r + dy][c + dx] != '#' && FROMSTART[r][c] + Math.abs(dx) + Math.abs(dy) + TOEND[r + dy][c + dx] + LIMIT <= LENGTH)
                                shortcuts++;
        return shortcuts;
    }

    public static int part1slow(char[][] map, int LIMIT, int LENGTH) {
        int count = 0;
        for (int r = 1; r < map.length - 1; r++)
            for (int c = 1; c < map[r].length - 1; c++)
                if (map[r][c] == '#') {
                    map[r][c] = '.';
                    int length = findPathLength(map);
                    //if (length < LENGTH)
                    //    System.out.printf("Find a shortcut through [%d,%d] with length %d, thus saving %d picoseconds.%n", r, c, length, LENGTH - length);
                    if (length + LIMIT <= LENGTH)
                        count++;
                    map[r][c] = '#';
                }
        return count;
    }

    public static int findPathLength(char[][] map) {
        return findPathLength(map, null, findPos(map, 'S'));
    }

    /*
    Returns length of path from a starting point (given as parameter) to the END point.
    If STEPS array is given, it does not stop when encountering the end tile, and processes
    whole maze, saving to the array number of steps from the start.
    NOTE: We are running this also with start in the E tile, to fill in number of steps
    to the end, basically.
    Code copy/pasted&updated from the Day16
     */
    public static int findPathLength(char[][] map, int[][] STEPS, int start) {
        boolean[][] memory = new boolean[map.length][map[0].length];
        boolean countSteps = STEPS != null;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, start / SIZE, start % SIZE});
        int totalScoreP1 = 0;
        // GO!
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int score = curr[0];
            int row = curr[1];
            int col = curr[2];
            // mark being there:
            if (memory[row][col]) continue;
            memory[row][col] = true;
            if (countSteps && (STEPS[row][col] == 0 || STEPS[row][col] > score))
                STEPS[row][col] = score;
            if (map[row][col] == 'E') {
                totalScoreP1 = score;
                if (!countSteps) break;
            }
            for (int[] dDir: DIRECTIONS)
                if (map[row + dDir[1]][col + dDir[0]] != '#' && !memory[row + dDir[1]][col + dDir[0]])
                    pq.offer(new int[]{score + 1, row + dDir[1], col + dDir[0]});
        }
        return totalScoreP1;
    }

    public static int findPos(char[][] map, char target) {
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == target)
                    return SIZE * r + c;
        return -1;
    }
}
