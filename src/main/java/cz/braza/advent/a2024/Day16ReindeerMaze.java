package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Day16ReindeerMaze {
    // specific order of directions, turn right => +1 index, turn left => -1 index
    public static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static final int SIZE = 150;
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_16.txt"; // ex7036, ex11048
        char[][] map = AOCHelper.readFile2CharArray(fileName);
        int[][] memory = new int[map.length][map[0].length];
        int start = findPos(map, 'S');
        int end = findPos(map, 'E');
        AOCHelper.printField(map, "Start " + start + ", end " + end);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, start / SIZE, start % SIZE});
        int totalScoreP1 = 0;
        // GO!
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int score = curr[0];
            int dir = curr[1];
            int row = curr[2];
            int col = curr[3];
            // if I was at the same position already (and same direction!), do not go any further
            int bit = 1 << dir;
            if ((memory[row][col] & bit) > 0) {
                //System.out.printf("been there already [%d,%d] facing %c.%n", row, col, DIRS.charAt(dir));
                continue;
            }
            // mark being there:
            memory[row][col] |= bit;
            //System.out.printf("Got to pos [%d,%d] facing %c with a score %d.%n", row, col, DIRS.charAt(dir), score);
            if (map[row + DIRECTIONS[dir][1]][col + DIRECTIONS[dir][0]] == 'E') {
                totalScoreP1 = score + 1;
                break;
            }
            if (map[row + DIRECTIONS[dir][1]][col + DIRECTIONS[dir][0]] == '.')
                pq.offer(new int[]{score + 1, dir, row + DIRECTIONS[dir][1], col + DIRECTIONS[dir][0]});
            pq.offer(new int[]{score + 1000, (dir + 1) % DIRECTIONS.length, row, col});
            pq.offer(new int[]{score + 1000, dir > 0 ? dir - 1 : DIRECTIONS.length - 1, row, col});
        }
        System.out.println("Part 1: " + totalScoreP1);
        // P2:
        memory = new int[map.length][map[0].length]; // restart
        List<Integer> history = new ArrayList<>();
        findBest(map, history, memory, start / SIZE, start % SIZE, 0, 0, totalScoreP1);
        int p2count = 0;
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == 'O') p2count++;
        AOCHelper.printField(map,"Part 2 found best cells: " + (p2count + 1) + " including end tile that is unmarked.");
        System.out.println("Part 2 found best cells: " + (p2count + 1) + " including end tile that is unmarked.");
    }

    public static int findPos(char[][] map, char target) {
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == target)
                    return SIZE * r + c;
        return -1;
    }

    public static void findBest(char[][] map, List<Integer> history, int[][] memory, int row, int col, int dir, int price, int limit) {
        if (price > limit || map[row][col] == '#') return;
        if (map[row][col] == 'E') {
            // color the path
            int colored = 0;
            for (int pos: history) {
                pos %= SIZE * SIZE; // we don't care about direction
                if (map[pos / SIZE][pos % SIZE] != 'O') {
                    map[pos / SIZE][pos % SIZE] = 'O';
                    colored++;
                }
            }
            System.out.println("Got a path with price " + price + " to the end tile, found " + colored + " new cells.");
            return;
        }
        if (memory[row][col] > 0 && memory[row][col] + 1000 < price) {
            // System.out.printf("Bailed out from cell [%d,%d] count %d%n", row, col, memory[row][col]);
            return;
        }
        if (memory[row][col] == 0 || price < memory[row][col]) memory[row][col] = price;
        history.add(dir * SIZE * SIZE + row * SIZE + col);
        // generate the three possible ways out, check if they are not already in history, go there, remove itself from history
        int forward = dir * SIZE * SIZE + (row + DIRECTIONS[dir][1]) * SIZE + col + DIRECTIONS[dir][0];
        if (!history.contains(forward)) findBest(map, history, memory, row + DIRECTIONS[dir][1], col + DIRECTIONS[dir][0], dir, price + 1, limit);
        int right = ((dir + 1) % DIRECTIONS.length) * SIZE * SIZE + row * SIZE + col;
        if (!history.contains(right)) findBest(map, history, memory, row, col, (dir + 1) % DIRECTIONS.length, price + 1000, limit);
        int leftDir = dir > 0 ? dir - 1 : DIRECTIONS.length - 1;
        int left = leftDir * SIZE * SIZE + row * SIZE + col;
        if (!history.contains(left)) findBest(map, history, memory, row, col, leftDir, price + 1000, limit);
        history.remove((Integer) (dir * SIZE * SIZE + row * SIZE + col));
    }
}
