package cz.braza.advent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AoC202310Pipes {
    public static final char EX = 'X';
    public static final char FILL = '█';
    public static final char SPACE = ' ';
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_10.txt";
        //Scanner vstup = new Scanner(new File(fileName));
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        // convert to an array:
        char[][] field = new char[lines.size()][140];
        int lineNum = 0;
        for (String line: lines)
            field[lineNum++] = line.toCharArray();
        // now the whole process: 17/83
        // Starting character S is at pos [17][83] and should be "J", that is we can go W or N from there
        // System.out.println(field[17][83]);
        // lets go north first:
        int steps = 1; // we went north already
        int x = 83;
        int y = 16;
        char direction = 'N';
        while (field[y][x] != 'S') {
            char tile = field[y][x];
            direction = sanityCheck(tile, direction);
            steps++;
            field[y][x] = FILL;
            switch (direction) {
                case 'N' -> y--;
                case 'S' -> y++;
                case 'W' -> x--;
                case 'E' -> x++;
                case EX -> System.out.println("Error on the board or path, position [y=" + y + ", x = " + x + "]");
            }
        }
        for (int row = 0; row < field.length; row++)
            System.out.println(new String(field[row]));
        System.out.println("Part 1 steps needed: " + (steps / 2));
        /* ******* FILL WITH BLANKS ************
        // 1st line is "outside":
        Arrays.fill(field[0], SPACE);
        for (int row = 1; row < field.length; row++)
            for (int col = 0; col < 70; col++) {
                if (col == 0) {
                    field[row][0] = SPACE;
                    field[row][field[row].length - 1] = SPACE;
                } else {
                    if ((field[row - 1][col] == SPACE || field[row - 1][col - 1] == SPACE) && field[row][col] != FILL)
                        field[row][col] = SPACE;
                    if ((field[row - 1][field[row].length - 1 - col] == SPACE || field[row - 1][field[row].length - col] == SPACE) && field[row][field[row].length - 1 - col] != FILL)
                        field[row][field[row].length - 1 - col] = SPACE;
                }
            }
        System.out.println("What happens? " + field.length);
        for (int row = 0; row < field.length; row++)
            System.out.println(new String(field[row]));
        System.out.println("After the print.");

         */
        fileName = "/home/jirka/src/java0/aoc23_10out.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int countX = 0;
        int countIn = 0;
        System.out.println(0 + FILL);
        while (vstup.hasNextLine()) {
            for (char c: vstup.nextLine().toCharArray())
                if (c > SPACE && c < FILL) {
                    if (c == EX) countX++;
                    else countIn++;
                }
        }
        System.out.println("Part 2: count: " + (countIn + countX) + ", of those " + countX + " are arguable");

        // Part 2: count: 858, of those 8 are arguable
        // 858 - too high
        // 850 - too high
        // 835 - too high
        // 820 - no
        // 801 - no
    }

    /**
     * First this makes sure the steps are ok ("pipe has to be connected"), then it returns the new direction
     * @param tile
     * @param direction
     * @return
     */
    public static char sanityCheck(char tile, char direction) {
        char newDir = EX;
        if (direction == 'N')
            switch (tile) {
                case '7' -> newDir = 'W';
                case 'F' -> newDir = 'E';
                case '|' -> newDir = 'N';
            }
        else if (direction == 'S')
            switch (tile) {
                case 'J' -> newDir = 'W';
                case 'L' -> newDir = 'E';
                case '|' -> newDir = 'S';
            }
        else if (direction == 'W')
            switch (tile) {
                case 'F' -> newDir = 'S';
                case 'L' -> newDir = 'N';
                case '-' -> newDir = 'W';
            }
        else if (direction == 'E')
            switch (tile) {
                case 'J' -> newDir = 'N';
                case '7' -> newDir = 'S';
                case '-' -> newDir = 'E';
            }
        if (newDir == EX)
            System.out.println("DBG ERR Position not found, tile '" + tile + "' and direction '" + direction + "'");
        return newDir;
    }

    public static void spreadTheSpace(char[][] arr, int x, int y) {
        if (arr[y][x] == SPACE) {
            for (int r = y + 1; r < arr.length; r++)
                if (arr[r][x] == EX)
                    break;
                else
                    arr[r][x] = SPACE;
            for (int c = x + 1; c < arr[y].length; c++)
                if (arr[y][c] == EX)
                    break;
                else
                    arr[y][c] = SPACE;
            if (x + 1 < arr[y].length)
                spreadTheSpace(arr, x + 1, y);
            if (y + 1 < arr.length)
                spreadTheSpace(arr, x, y + 1);
        }
    }
}
